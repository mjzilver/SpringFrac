package mjzilver.frac.services;

import org.springframework.stereotype.Service;

@Service
public class FractalService {

    public final int WIDTH = 800;
    public final int HEIGHT = 800;
    public final int MAX_ITER = 3000;

    public int[][] generateMandelbrot() {
        return generateFractal(-2.5, 1, -1.2, 1.2);
    }

    public int[][] generateFractal(double minX, double maxX, double minY, double maxY) {
        int[][] fractal = new int[WIDTH][HEIGHT];
    
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                double zx = x * (maxX - minX) / WIDTH + minX;
                double zy = y * (maxY - minY) / HEIGHT + minY;
    
                double cx = zx;
                double cy = zy;
    
                int iter = 0;
                while (iter < MAX_ITER) {
                    double temp = zx * zx - zy * zy + cx;
                    zy = 2.0 * zx * zy + cy;
                    zx = temp;
    
                    if (zx * zx + zy * zy > 4) {
                        break;
                    }
    
                    iter++;
                }
    
                fractal[x][y] = iter;
            }
        }
    
        return fractal;
    }
    
}
