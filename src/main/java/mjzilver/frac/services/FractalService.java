package mjzilver.frac.services;

import org.springframework.stereotype.Service;

import mjzilver.frac.models.fractal.FractalFactory;
import mjzilver.frac.models.fractal.FractalGenerator;

@Service
public class FractalService {

    private FractalFactory fractalFactory = new FractalFactory();
    private FractalGenerator generator = null;

    public int[][] generateFractal() {
        generator = fractalFactory.getFractalGenerator("mandelbrot");
        return generator.generateFractal();
    }

    public String getFractalType() {
        return generator.getFractalType();
    }

    public int[][] changeFractal(String fractalType) {
        generator = fractalFactory.getFractalGenerator(fractalType);
        if(generator == null) {
            return null;
        }
        return generator.generateFractal();
    }
    
    public int[][] zoomIn(int x, int y, double zoomFactor) {
        return generator.zoomIn(x, y, zoomFactor);
    }
}
