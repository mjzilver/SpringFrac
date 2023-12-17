package mjzilver.frac.controllers;

import mjzilver.frac.services.ConwayService;
import mjzilver.frac.websocket.payloads.BoardClickPayload;
import mjzilver.frac.websocket.payloads.BoardResizePayload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@CrossOrigin
@Controller
public class ConwayController {

    @Autowired
    private ConwayService conwayService;

    @GetMapping("/conway")
    public String showConway(Model model) {
        boolean[][] conway = conwayService.getNextGeneration();

        model.addAttribute("conway", conway);
        return "conway";
    }

    @MessageMapping("/update")
    @SendTo("/conway/board")
    public boolean[][] updateConway() {
        return conwayService.getNextGeneration();
    }

    @MessageMapping("/renew")
    @SendTo("/conway/board")
    public boolean[][] renewConway() {
        return conwayService.renew();
    }

    @MessageMapping("/click")
    @SendTo("/conway/board")
    public boolean[][] clickConway(BoardClickPayload payload) {
        conwayService.click(payload.getRowIndex(), payload.getColIndex());
        return conwayService.getBoard();
    }

    @MessageMapping("/resizeBoard")
    @SendTo("/conway/board")
    public boolean[][] resizeBoard(BoardResizePayload payload) {
        System.out.println("resizeBoard" + payload.getRows() + " " + payload.getCols());
        if(payload.getRows() < 10 || payload.getCols() < 10) {
            return conwayService.getBoard();
        }

        conwayService.resize(payload.getRows(), payload.getCols());
        return conwayService.getBoard();
    }
}

