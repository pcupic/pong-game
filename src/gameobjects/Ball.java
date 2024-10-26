package gameobjects;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import pong.CollisionDetector;
import pong.PlayerManager;
import pong.PongGame;

public class Ball {
    private int x, y, width = 25, height = 25;
    private int motionX, motionY;
    public Random random;
    public int numberOfHits;
    private PongGame game;
    private CollisionDetector collisionDetector;
    private PlayerManager playerManager;

    public Ball(PongGame game) {
        this.game = game;
        playerManager = game.getPlayerManager();
        random = new Random();
        collisionDetector = new CollisionDetector();
        resetPosition();
    }

    public void update(Paddle firstPaddle, Paddle secondPaddle) {
        updatePosition();
        handleBoundaryCollisions();
        handlePaddleCollisions(firstPaddle, secondPaddle);
    }

    private void updatePosition() {
        int speed = 5;
        this.x += motionX * speed;
        this.y += motionY * speed;
    }
    
    public int getX() {
    	return this.x;
    }
    
    public int getY() {
    	return this.y;
    }
    
    public int getWidth() {
    	return this.width;
    }
    
    public int getHeight() {
    	return this.height;
    }

    private void handleBoundaryCollisions() {
        if (this.y + height > game.getHeight()) {
            this.y = game.getHeight() - height;
            this.motionY = -this.motionY;
        }
        if (this.y < 0) {
            this.y = 0;
            this.motionY = -this.motionY;
        }
    }

    private void handlePaddleCollisions(Paddle firstPaddle, Paddle secondPaddle) {
        int collisionResultFirstPaddle = collisionDetector.checkCollision(this, firstPaddle);
        int collisionResultSecondPaddle = collisionDetector.checkCollision(this, secondPaddle);

        if (collisionResultFirstPaddle == 1) {
            adjustMotionAfterCollision(true);
            numberOfHits++;
        } else if (collisionResultSecondPaddle == 1) {
            adjustMotionAfterCollision(false);
            numberOfHits++;
        } else if (collisionResultFirstPaddle == 2) {
            playerManager.getSecondPlayer().increaseScore();
            resetPosition();
        } else if (collisionResultSecondPaddle == 2) {
            playerManager.getFirstPlayer().increaseScore();
            resetPosition();
        }
    }

    private void adjustMotionAfterCollision(boolean isLeftPaddle) {
        if (isLeftPaddle) {
            motionX = 1 + (numberOfHits / 5);
        } else {
            motionX = -1 - (numberOfHits / 5);
        }
        motionY = -2 + random.nextInt(4);
        if (motionY == 0) {
            motionY = 1;
        }
    }

    public void resetPosition() {
        this.numberOfHits = 0;
        this.x = game.getWidth() / 2 - this.width / 2;
        this.y = game.getHeight() / 2 - this.height / 2;
        this.motionY = -2 + random.nextInt(4);
        if (motionY == 0) {
            motionY = 1;
        }

        if (random.nextBoolean()) {
            motionX = 1;
        } else {
            motionX = -1;
        }
    }

    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(x, y, width, height);
    }
}