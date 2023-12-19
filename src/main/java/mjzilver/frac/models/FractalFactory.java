package mjzilver.frac.models;

public class FractalFactory {

    public FractalGenerator getFractalGenerator(String fractalType) {
        if (fractalType == null) {
            return null;
        }
        if (fractalType.equalsIgnoreCase("mandelbrot")) {
            return new MandelbrotGenerator(800, 800, 1000);
        } else if (fractalType.equalsIgnoreCase("julia")) {
            return new JuliaGenerator(800, 800, 1000);
        }
        return null;
    }
    
}
