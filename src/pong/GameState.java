package pong;

import java.awt.Graphics2D;

public interface GameState {
    void render(Graphics2D g, PongGame game);
}