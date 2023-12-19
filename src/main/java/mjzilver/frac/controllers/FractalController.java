package mjzilver.frac.controllers;

import mjzilver.frac.services.FractalService;
import mjzilver.frac.websocket.payloads.FractalTypePayload;
import mjzilver.frac.websocket.payloads.ZoomPayload;

import java.util.List;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FractalController {

    @Autowired
    private FractalService fractalService;

    @GetMapping("/fractal")
    @SendTo("/fractals/fractal")
    public String showFractal(Model model) {
        int[][] fractal = fractalService.generateFractal();
        List<String> fractalOptions = Arrays.asList("mandelbrot", "julia", "burningship");

        model.addAttribute("fractal", fractal);
        model.addAttribute("fractalType", fractalService.getFractalType());
        model.addAttribute("fractalOptions", fractalOptions);

        return "fractal";
    }

    @MessageMapping("/changeFractal")
    @SendTo("/fractals/fractal")
    public int[][] changeFractal(FractalTypePayload fractalTypePayload) {
        return fractalService.changeFractal(fractalTypePayload.getFractalType());
    }

    @MessageMapping("/zoom")
    @SendTo("/fractals/fractal")
    public int[][] zoomIn(ZoomPayload payload) {
        return fractalService.zoomIn(payload.getX(), payload.getY(), payload.getZoom());
    }
}

