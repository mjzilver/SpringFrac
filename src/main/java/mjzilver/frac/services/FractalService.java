package mjzilver.frac.services;

import org.springframework.stereotype.Service;

@Service
public class FractalService {

    public static final int WIDTH = 1080;
    public static final int HEIGHT = 1920;
    public static final int MAX_ITER = 1000;
    
    public int[][] generateMandelbrot() {
        int[][] fractal = new int[WIDTH][HEIGHT];

        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                double zx = x * 3.5 / WIDTH - 2.5;
                double zy = y * 2.0 / HEIGHT - 1.0;

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
