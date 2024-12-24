package pong;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.Timer;

import bots.BotManager;
import gameobjects.Ball;
import gamestates.DifficultySelection;
import gamestates.GameOver;
import gamestates.GamePlay;

public class PongGame implements ActionListener {
    private static PongGame pong;
    private int width = 800, height = 700;
    private int scoreLimit = 1;
    private int winner;

    private Renderer renderer;
    private GameStateManager manager;
    private InputHandler inputHandler;
    private PlayerManager playerManager;
    private Ball ball;
    private BotManager botManager;
    private Timer timer;

    private PongGame() {
        botManager = new BotManager();
        playerManager = new PlayerManager(this);
        ball = new Ball(this);
        manager = new GameStateManager(this);
        inputHandler = new InputHandler();
        timer = new Timer(20, this);

        JFrame frame = new JFrame("Pong Game");
        frame.setSize(width + 15, height + 35);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        renderer = new Renderer(this);
        frame.add(renderer);
        frame.addKeyListener(inputHandler);
        timer.start();
    }
    
    public Renderer getRenderer() {
    	return this.renderer;
    }
    
    public PlayerManager getPlayerManager() {
    	return this.playerManager;
    }
    
    public BotManager getBotManager() {
    	return this.botManager;
    }
    
    public static PongGame GetInstance() {
    	if(pong == null) 
    		pong = new PongGame();
    	return pong;
    }
    
    public Ball getBall() {
    	return this.ball;
    }
    
    public GameStateManager getGameStateManager() {
    	return this.manager;
    }

    public Timer getTimer() {
        return timer;
    }

    public void startGame() {
        manager.setState(new GamePlay(this));
    }
    
    public int getScoreLimit() {
        return scoreLimit;
    }

    public void setScoreLimit(int scoreLimit) {
        this.scoreLimit = scoreLimit;
    }

    public void increaseScoreLimit() {
        scoreLimit++;
    }

    public void decreaseScoreLimit() {
        if (scoreLimit > 1) {
            scoreLimit--;
        }
    }
    
    public int getWidth() {
    	return this.width;
    }
    
    public int getHeight() {
    	return this.height;
    }
    
    public void setWinner(int winner) {
    	this.winner = winner;
    }
    
    public int getWinner() {
    	return this.winner;
    }

    public void render(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        manager.render(g, this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (manager.getCurrentState() instanceof GamePlay) {
            updateGame();
        }        
        renderer.repaint();
    }

    private void updateGame() {
        if (playerManager.getFirstPlayer().score >= getScoreLimit()) {
            manager.setState(new GameOver(this));
            winner = 1;
        }
        if (playerManager.getSecondPlayer().score >= getScoreLimit()) {
            if (getBotManager().isBotEnabled()) {
                manager.setState(new GameOver(this));
                winner = 3;
            } else {
                manager.setState(new GameOver(this));
                playerManager.getSecondPlayer().increaseScore();
                winner = 2;
            }
        }
        if (inputHandler.isWPressed()) playerManager.getFirstPlayer().move(true);
        if (inputHandler.isSPressed()) playerManager.getFirstPlayer().move(false);
        if (!botManager.isBotEnabled()) {
            if (inputHandler.isUpPressed()) playerManager.getSecondPlayer().move(true);
            if (inputHandler.isDownPressed()) playerManager.getSecondPlayer().move(false);
        } else {
            botManager.update(playerManager.getSecondPlayer(), ball);
        }

        ball.update(playerManager.getFirstPlayer(), playerManager.getSecondPlayer());
    }

    public static void main(String[] args) {
        pong = new PongGame();
    }

    public void activateBot() {
        botManager.activateBot();
        manager.setState(new DifficultySelection());
    }

    public void resetGameState() {
        playerManager.resetScores();
        ball.resetPosition();
        botManager.disableBot();
    }
}