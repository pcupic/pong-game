package bots;

import gameobjects.Ball;
import gameobjects.Paddle;

public abstract class BotDifficulty {
    protected int cooldown;
    protected int cooldownCounter = 0;
    protected int botMoves = 0;
    protected int maxMoves = 10; 

    public int getCooldown() {
        return cooldown;
    }

    public void update(Paddle secondPlayer, Ball ball) {
        if (cooldownCounter > 0) {
            cooldownCounter--;
            return;
        }

        if (botMoves < maxMoves) {
            if (secondPlayer.y + secondPlayer.height / 2 < ball.getY()) {
                secondPlayer.move(false);
                botMoves++;
            } else if (secondPlayer.y + secondPlayer.height / 2 > ball.getY()) {
                secondPlayer.move(true);
                botMoves++;
            }
        }

        if (botMoves >= maxMoves) {
            cooldownCounter = cooldown;
            botMoves = 0;
        }
    }
}