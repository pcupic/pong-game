package pong;

import gameobjects.Paddle;

public class PlayerManager {
    private Paddle firstPlayer;
    private Paddle secondPlayer;

    public PlayerManager(PongGame game) {
        firstPlayer = new Paddle(game, 1);
        secondPlayer = new Paddle(game, 2);
    }

    public Paddle getFirstPlayer() {
        return firstPlayer;
    }

    public Paddle getSecondPlayer() {
        return secondPlayer;
    }

    public void resetScores() {
        firstPlayer.resetScore();
        secondPlayer.resetScore();
    }
}