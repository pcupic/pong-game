package gamestates;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import pong.GameState;
import pong.PongGame;
import ui.Button;

public class GameOver implements GameState, MouseListener {
    private Button playAgainButton;
    private Button exitToMenuButton;
    private PongGame game;

    public GameOver(PongGame game) {
        this.game = game;

        playAgainButton = new Button(250, 400, 200, 50, "Play Again", this::playAgain);
        exitToMenuButton = new Button(250, 460, 200, 50, "Exit to Menu", this::exitToMenu);
    }

    @Override
    public void render(Graphics2D g, PongGame game) {
        // Background Gradient
        g.setPaint(new java.awt.GradientPaint(0, 0, new Color(50, 50, 50), 0, game.getHeight(), new Color(20, 20, 20)));
        g.fillRect(0, 0, game.getWidth(), game.getHeight());

        // Title Background
        g.setColor(new Color(200, 0, 0)); // Dark Red
        g.fillRoundRect(150, 150, 500, 100, 30, 30); // Rounded rectangle for title background

        // Title Text
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 50));
        g.drawString("Game Over", 220, 210);

        // Winner Message
        g.setFont(new Font("Arial", Font.BOLD, 40));
        g.setColor(Color.WHITE);
        String winnerMessage;
        if (game.getWinner() == 1) {
            winnerMessage = "Player 1 Wins!";
        } else if (game.getWinner() == 2) {
            winnerMessage = "Player 2 Wins!";
        } else {
            winnerMessage = "Bot Wins!";
        }
        g.drawString(winnerMessage, 250, 300);

        // Render Buttons
        playAgainButton.render(g);
        exitToMenuButton.render(g);
    }

    private void playAgain() {
        game.getPlayerManager().resetScores();
        game.getBall().resetPosition();

        game.getGameStateManager().setState(new GamePlay(game));
        game.startGame();
    }

    private void exitToMenu() {
        game.getPlayerManager().resetScores();
        game.getBall().resetPosition();
        game.getBotManager().disableBot();
        game.getGameStateManager().setState(new MainMenu(game));
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        playAgainButton.mouseClicked(e);
        exitToMenuButton.mouseClicked(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
}