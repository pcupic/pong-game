package bots;

import gameobjects.Ball;
import gameobjects.Paddle;

public class HardBot extends BotDifficulty {
    public HardBot() {
        cooldown = 5;
    }

    @Override
    public void update(Paddle secondPlayer, Ball ball) {
        super.update(secondPlayer, ball);
    }
}