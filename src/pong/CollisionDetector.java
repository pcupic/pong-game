package pong;

import gameobjects.Ball;
import gameobjects.Paddle;

public class CollisionDetector {

    // Main method to check the collision
    public int checkCollision(Ball ball, Paddle paddle) {
        if (isBallCollidingWithPaddle(ball, paddle)) {
            return 1; // Ball collided with paddle
        } else if (isBallOutOfBounds(ball, paddle)) {
            return 2; // Ball went past the paddle (missed)
        }
        return 0; // No collision
    }

    // Check if the ball is colliding with the paddle
    private boolean isBallCollidingWithPaddle(Ball ball, Paddle paddle) {
        return ball.getX() < paddle.x + paddle.width && 
               ball.getX() + ball.getWidth() > paddle.x && 
               ball.getY() < paddle.y + paddle.height && 
               ball.getY() + ball.getHeight() > paddle.y;
    }

    // Check if the ball has missed the paddle (out of bounds for a specific player)
    private boolean isBallOutOfBounds(Ball ball, Paddle paddle) {
        return (paddle.number == 1 && paddle.x > ball.getX()) || 
               (paddle.number == 2 && paddle.x < ball.getX() - ball.getWidth());
    }
}