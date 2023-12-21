package mjzilver.frac.services;

import org.springframework.stereotype.Service;

import mjzilver.frac.models.conway.ConwayGame;

@Service
public class ConwayService {
    private ConwayGame game = new ConwayGame();

    public boolean[][] click(int x, int y) {
        game.click(x, y);
        return game.getBoard();
    }

    public boolean[][] renew(int newRandomness) {
        game.initializeBoard(newRandomness);
        return game.getBoard();
    }

    public boolean[][] getNextGeneration() {
        return game.getNextGeneration(); 
    }

    public boolean[][] textToBoard(String text) {
        return game.textToBoard(text);
    }

    public boolean[][] resize(int rows, int cols) {
        return game.resize(rows, cols);
    }

	public boolean[][] setWrapped(boolean wrapped) {
        game.setWrapped(wrapped);
        return game.getBoard();
	}
}
