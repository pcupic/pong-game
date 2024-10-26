package pong;

import bots.BotManager;
import gamestates.DifficultySelection;
import gamestates.GameOver;
import gamestates.GamePlay;
import gamestates.MainMenu;
import gamestates.PausedGame;

public class GameController {
    private GameStateManager stateManager;
    private BotManager botManager;
    private PlayerManager playerManager;
    private PongGame game;
    
    public GameController(PongGame game) {
    	this.stateManager = game.getGameStateManager(); 
        this.botManager = game.getBotManager();
        this.playerManager = game.getPlayerManager();
    }

    public void toggleBotActivation() {
        if (stateManager.getCurrentState() instanceof MainMenu) {
            game.activateBot();
        }
    }

    public void increaseDifficultyOrScoreLimit() {
        if (stateManager.getCurrentState() instanceof DifficultySelection) {
            botManager.increaseDifficulty();
        } else if (stateManager.getCurrentState() instanceof MainMenu) {
            game.increaseScoreLimit();
        }
    }

    public void decreaseDifficultyOrScoreLimit() {
        if (stateManager.getCurrentState() instanceof DifficultySelection) {
            botManager.decreaseDifficulty();
        } else if (stateManager.getCurrentState() instanceof MainMenu && game.getScoreLimit() > 1) {
            game.decreaseScoreLimit();
        }
    }

    public void startOrPauseGame() {
        if (stateManager.getCurrentState() instanceof MainMenu) {
            startGameFromMenu();
        } else if (stateManager.getCurrentState() instanceof GameOver) {
            restartGameAfterGameOver();
        } else if (stateManager.getCurrentState() instanceof DifficultySelection) {
            startGameFromDifficultySelection();
        } else if (stateManager.getCurrentState() instanceof PausedGame) {
            resumeGame();
        } else if (stateManager.getCurrentState() instanceof GamePlay) {
            pauseGame();
        }
    }

    private void startGameFromMenu() {
        if (!botManager.isBotEnabled()) {
            stateManager.setState(new GamePlay(game));
            game.startGame();
        } else {
            botManager.disableBot();
            stateManager.setState(new GamePlay(game));
            game.startGame();
        }
    }

    public void restartGameAfterGameOver() {
        playerManager.resetScores();
        game.getBall().resetPosition();
        
        if (botManager.isBotEnabled()) {
            stateManager.setState(new GamePlay(game));  // Start a new game with the bot
            game.startGame();  // You can use startGame() here to keep logic consistent
        } else {
            stateManager.setState(new GamePlay(game));  // Start a new game without bot
            game.startGame();
        }
    }

    private void startGameFromDifficultySelection() {
        stateManager.setState(new GamePlay(game));
        game.startGame();
    }

    private void resumeGame() {
        stateManager.setState(new GamePlay(game));
    }

    private void pauseGame() {
        stateManager.setState(new PausedGame(game));
    }

    public void resetGameAndReturnToMenu() {
        if (stateManager.getCurrentState() instanceof GamePlay || 
            stateManager.getCurrentState() instanceof GameOver || 
            stateManager.getCurrentState() instanceof DifficultySelection) {
            game.resetGameState();
            stateManager.setState(new MainMenu(game));
        }
    }
}