package mjzilver.frac.models.fractal;

public class JuliaGenerator extends AbstractFractalGenerator {

    private double juliaConstantX = -0.8;
    private double juliaConstantY = 0.156;

    public JuliaGenerator(int width, int height, int max) {
        super(width, height, max);
        fractalType = "julia";
    }

    @Override
    public int[][] generateFractal() {
        double minX = -2.5;
        double maxX = 1;
        double minY = -1.2;
        double maxY = 1.2;

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                double zx = x * (maxX - minX) / width + minX;
                double zy = y * (maxY - minY) / height + minY;

                double cx = zx;
                double cy = zy;

                fractal[x][y] = generateStep(zx, zy, cx, cy);
            }
        }
        return fractal;
    }

    @Override
    public int generateStep(double zx, double zy, double cx, double cy) {
        int iter = 0;
        while (iter < max) {
            double temp = zx * zx - zy * zy + juliaConstantX;
            zy = 2.0 * zx * zy + juliaConstantY;
            zx = temp;

            if (zx * zx + zy * zy > 4) {
                break;
            }

            iter++;
        }

        return iter;
    }

    @Override
    public int[][] zoomIn(int x, int y, double zoomFactor) {
        double originalMinX = -2.5;
        double originalMaxX = 1;
        double originalMinY = -1.2;
        double originalMaxY = 1.2;

        double zoomX = x * (originalMaxX - originalMinX) / width + originalMinX;
        double zoomY = y * (originalMaxY - originalMinY) / height + originalMinY;

        double zoomedMinX = zoomX - 1 / zoomFactor;
        double zoomedMaxX = zoomX + 1 / zoomFactor;
        double zoomedMinY = zoomY - 1 / zoomFactor;
        double zoomedMaxY = zoomY + 1 / zoomFactor;

        double zoomedWidth = zoomedMaxX - zoomedMinX;
        double zoomedHeight = zoomedMaxY - zoomedMinY;

        int[][] zoomedFractal = new int[width][height];

        for (int x2 = 0; x2 < width; x2++) {
            for (int y2 = 0; y2 < height; y2++) {
                double zx = x2 * zoomedWidth / width + zoomedMinX;
                double zy = y2 * zoomedHeight / height + zoomedMinY;

                double cx = zx;
                double cy = zy;

                zoomedFractal[x2][y2] = generateStep(zx, zy, cx, cy);
            }
        }

        fractal = zoomedFractal;
        return fractal;
    }
}
