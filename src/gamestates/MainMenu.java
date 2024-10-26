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

public class MainMenu implements GameState, MouseListener {
    private Button startButton;
    private Button botButton;
    private Button increaseScoreLimitButton;
    private Button decreaseScoreLimitButton;
    private Button scoreLimitButton;
    private Button exitButton;  // New button
    private PongGame game;

    public MainMenu(PongGame game) {
        this.game = game;

        startButton = new Button(300, 200, 200, 50, "Start Game", this::startGame);
        botButton = new Button(300, 270, 200, 50, "Play with Bot", this::toggleBot);
        increaseScoreLimitButton = new Button(500, 340, 50, 50, "+", this::increaseScoreLimit);
        decreaseScoreLimitButton = new Button(250, 340, 50, 50, "-", this::decreaseScoreLimit);
        scoreLimitButton = new Button(300, 340, 200, 50, "Score Limit: " + game.getScoreLimit(), null);
        exitButton = new Button(300, 410, 200, 50, "Exit Game", this::exitGame);  // Initialize new button

        // Initialize button updates
        updateButtons();
    }

    @Override
    public void render(Graphics2D g, PongGame game) {
        // Gradient Background
        int width = game.getWidth();
        int height = game.getHeight();
        RadialGradientPaint gradient = new RadialGradientPaint(width / 2, height / 2, width / 2,
                new float[]{0.0f, 1.0f}, new Color[]{new Color(0, 0, 0, 255), new Color(0, 0, 50, 255)});
        g.setPaint(gradient);
        g.fillRect(0, 0, width, height);

        // Title Styling
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 80));
        g.drawString("PONG", width / 2 - 100, 100);

        // Render Buttons
        startButton.render(g);
        botButton.render(g);
        increaseScoreLimitButton.render(g);
        decreaseScoreLimitButton.render(g);
        scoreLimitButton.render(g);
        exitButton.render(g);  // Render new button
    }

    private void startGame() {
        game.startGame();
    }

    private void toggleBot() {
        game.activateBot();
        updateButtons();
    }

    private void increaseScoreLimit() {
        game.increaseScoreLimit();
        updateButtons();
    }

    private void decreaseScoreLimit() {
        game.decreaseScoreLimit();
        updateButtons();
    }

    private void updateButtons() {
        scoreLimitButton = new Button(300, 340, 200, 50, "Score Limit: " + game.getScoreLimit(), null);
    }

    private void exitGame() {
        System.exit(0);  // Exit the game
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        startButton.mouseClicked(e);
        botButton.mouseClicked(e);
        increaseScoreLimitButton.mouseClicked(e);
        decreaseScoreLimitButton.mouseClicked(e);
        exitButton.mouseClicked(e);  // Handle new button clicks
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