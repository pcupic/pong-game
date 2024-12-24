package gamestates;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import pong.GameState;
import pong.PongGame;
import ui.Button;

public class GamePlay implements GameState, MouseListener {
    private Button pauseButton;
    private PongGame game;

    public GamePlay(PongGame game) {
        this.game = game;
        pauseButton = new Button(game.getWidth() - 120, 10, 100, 40, "Pause", this::pauseGame);
    }

    @Override
    public void render(Graphics2D g, PongGame game) {
        // Gradient Background
        int width = game.getWidth();
        int height = game.getHeight();
        RadialGradientPaint gradient = new RadialGradientPaint(width / 2, height / 2, Math.min(width, height) / 2,
                new float[]{0.0f, 1.0f}, new Color[]{new Color(0, 0, 0), new Color(20, 20, 20)});
        g.setPaint(gradient);
        g.fillRect(0, 0, width, height);

        g.setColor(Color.WHITE);
        g.setStroke(new BasicStroke(5f));
        g.drawLine(width / 2, 0, width / 2, height);
        g.setStroke(new BasicStroke(2f));
        g.drawOval(width / 2 - 150, height / 2 - 150, 300, 300);

        // Score Display
        g.setFont(new Font("Arial", Font.BOLD, 60));
        g.setColor(Color.WHITE);
        g.drawString(String.valueOf(game.getPlayerManager().getFirstPlayer().getScore()), width / 2 - 100, 50);
        g.drawString(String.valueOf(game.getPlayerManager().getSecondPlayer().getScore()), width / 2 + 60, 50);

        // Render Players and Ball
        game.getPlayerManager().getFirstPlayer().render(g);
        game.getPlayerManager().getSecondPlayer().render(g);
        game.getBall().render(g);

        // Render Pause Button with improved styling
        pauseButton.render(g);
    }

    private void pauseGame() {
        game.getGameStateManager().setState(new PausedGame(game));
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        pauseButton.mouseClicked(e);
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