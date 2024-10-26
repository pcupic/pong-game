package bots;

import gameobjects.Ball;
import gameobjects.Paddle;

public class InsaneBot extends BotDifficulty {
    public InsaneBot() {
        cooldown = 1;
    }

    public void update(Paddle secondPlayer, Ball ball) {
        super.update(secondPlayer, ball);
    }
}