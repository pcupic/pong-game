package bots;

import gameobjects.Ball;
import gameobjects.Paddle;

public class EasyBot extends BotDifficulty {
    public EasyBot() {
        cooldown = 15;
    }

    public void update(Paddle secondPlayer, Ball ball) {
        super.update(secondPlayer, ball);
    }
}