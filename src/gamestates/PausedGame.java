package gamestates;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import pong.GameState;
import pong.PongGame;
import ui.Button;

public class PausedGame implements GameState, MouseListener {
    private Button resumeButton;
    private Button backToMenuButton;
    private PongGame game;

    public PausedGame(PongGame game) {
        this.game = game;
        resumeButton = new Button(game.getWidth() / 2 - 100, game.getHeight() / 2, 200, 50, "Resume", this::resumeGame);
        backToMenuButton = new Button(game.getWidth() / 2 - 100, game.getHeight() / 2 + 60, 200, 50, "Back to Menu", this::backToMenu);
    }

    @Override
    public void render(Graphics2D g, PongGame game) {
        int width = game.getWidth();
        int height = game.getHeight();
        RadialGradientPaint gradient = new RadialGradientPaint(
                width / 2, height / 2, Math.min(width, height) / 2,
                new float[]{0.0f, 1.0f},
                new Color[]{new Color(50, 50, 50), new Color(0, 0, 0)}
        );
        g.setPaint(gradient);
        g.fillRect(0, 0, width, height);

        // Title Text
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 50));
        g.drawString("PAUSED", width / 2 - 103, height / 2 - 100);

        // Render the buttons
        resumeButton.render(g);
        backToMenuButton.render(g);
    }

    private void resumeGame() {
        // Switch back to the game state and resume gameplay
        game.getGameStateManager().setState(new GamePlay(game));
    }

    private void backToMenu() {
        // Go back to the main menu
        game.getGameStateManager().setState(new MainMenu(game));
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        resumeButton.mouseClicked(e);
        backToMenuButton.mouseClicked(e);
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