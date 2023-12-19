package mjzilver.frac.models;

public interface FractalGenerator {
    public int[][] generateFractal();
    public int generateStep(double zx, double zy, double cx, double cy);
    public int[][] getFractal();
    public int[][] zoomIn(int x, int y, double zoomFactor);
    public String getFractalType();
}