package mjzilver.frac.controllers;

import mjzilver.frac.services.FractalService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FractalController {

    @Autowired
    private FractalService fractalService;

    @GetMapping("/fractal")
    public String showFractal(Model model) {
        int[][] fractal = fractalService.generateMandelbrot();

        model.addAttribute("fractal", fractal);
        return "fractal";
    }
}

