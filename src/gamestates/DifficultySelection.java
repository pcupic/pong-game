package gamestates;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import bots.BotDifficulty;
import bots.EasyBot;
import bots.HardBot;
import bots.InsaneBot;
import bots.MediumBot;
import pong.GameState;
import pong.PongGame;
import ui.Button;

public class DifficultySelection implements GameState, MouseListener {
    private Button easyButton;
    private Button mediumButton;
    private Button hardButton;
    private Button insaneButton;
    private Button backButton;

    public DifficultySelection() {
        easyButton = new Button(150, 250, 200, 60, "Easy", () -> selectDifficulty(new EasyBot()));
        mediumButton = new Button(150, 320, 200, 60, "Medium", () -> selectDifficulty(new MediumBot()));
        hardButton = new Button(150, 390, 200, 60, "Hard", () -> selectDifficulty(new HardBot()));
        insaneButton = new Button(150, 460, 200, 60, "Insane", () -> selectDifficulty(new InsaneBot()));
        backButton = new Button(150, 530, 200, 60, "Back to Menu", this::goBackToMenu);
    }

    @Override
    public void render(Graphics2D g, PongGame game) {
        // Background Gradient
        g.setPaint(new java.awt.GradientPaint(0, 0, new Color(60, 60, 60), 0, game.getHeight(), new Color(20, 20, 20)));
        g.fillRect(0, 0, game.getWidth(), game.getHeight());

        // Title Background
        g.setColor(new Color(0, 100, 200)); // Light Blue
        g.fillRoundRect(50, 120, 700, 70, 30, 30); // Rounded rectangle for title background

        // Title Text
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 50));
        g.drawString("Select Bot Difficulty", 60, 170);

        // Instructions
        g.setColor(Color.LIGHT_GRAY);
        g.setFont(new Font("Arial", Font.PLAIN, 24));
        g.drawString("Choose the difficulty level of the bot:", 150, 230);

        // Render Buttons
        easyButton.render(g);
        mediumButton.render(g);
        hardButton.render(g);
        insaneButton.render(g);
        backButton.render(g);
    }

    private void selectDifficulty(BotDifficulty difficulty) {
        PongGame.GetInstance().getBotManager().setDifficulty(difficulty);
        PongGame.GetInstance().startGame();
    }

    private void goBackToMenu() {
        PongGame.GetInstance().getGameStateManager().setState(new MainMenu(PongGame.GetInstance()));
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        easyButton.mouseClicked(e);
        mediumButton.mouseClicked(e);
        hardButton.mouseClicked(e);
        insaneButton.mouseClicked(e);
        backButton.mouseClicked(e);
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