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
    public boolean[][] renewConway(int randomness) {
        return conwayService.renew(randomness);
    }

    @MessageMapping("/click")
    @SendTo("/conway/board")
    public boolean[][] clickConway(BoardClickPayload payload) {
        return conwayService.click(payload.getRowIndex(), payload.getColIndex());
    }

    @MessageMapping("/resizeBoard")
    @SendTo("/conway/board")
    public boolean[][] resizeBoard(BoardResizePayload payload) {
        return conwayService.resize(payload.getRows(), payload.getCols());
    }

    @MessageMapping("/text")
    @SendTo("/conway/board")
    public boolean[][] textConway(String text) {
        return conwayService.textToBoard(text);
    }

    @MessageMapping("/setWrap")
    @SendTo("/conway/board")
    public boolean[][] setWrap(boolean wrapped) {
       return conwayService.setWrapped(wrapped);
    }
}

