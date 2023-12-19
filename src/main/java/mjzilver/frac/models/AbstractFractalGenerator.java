package mjzilver.frac.models;

public abstract class AbstractFractalGenerator implements FractalGenerator {
    protected int[][] fractal;
    protected int max;
    protected int width;
    protected int height;
    protected String fractalType;

    public AbstractFractalGenerator(int width, int height, int max) {
        this.width = width;
        this.height = height;
        this.max = max;
        fractal = new int[width][height];
    }

    @Override
    public String getFractalType() {
        return fractalType;
    }

    @Override
    public int[][]  getFractal() {
        return fractal;
    }
}
