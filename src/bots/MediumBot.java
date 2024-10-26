package bots;

import gameobjects.Ball;
import gameobjects.Paddle;

public class MediumBot extends BotDifficulty {
    public MediumBot() {
        cooldown = 10;
    }

    @Override
    public void update(Paddle secondPlayer, Ball ball) {
        super.update(secondPlayer, ball);
    }
}