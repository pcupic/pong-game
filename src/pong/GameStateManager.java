package pong;

import java.awt.Graphics2D;

import gamestates.MainMenu;

public class GameStateManager {
    private GameState currentState;
    
    public GameState getCurrentState() {
    	return this.currentState;
    }

    public GameStateManager(PongGame game) {
        setState(new MainMenu(game));
    }

    public void setState(GameState state) {
        this.currentState = state;
    }

    public void render(Graphics2D g, PongGame game) {
        if (currentState != null) {
            currentState.render(g, game);
        }
    }
}