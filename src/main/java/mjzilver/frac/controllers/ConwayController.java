package mjzilver.frac.controllers;

import mjzilver.frac.services.ConwayService;

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
}

