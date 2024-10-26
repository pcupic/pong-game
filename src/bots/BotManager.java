package bots;

import java.util.ArrayList;
import java.util.List;

import gameobjects.Ball;
import gameobjects.Paddle;

public class BotManager {
    private boolean isBotEnabled;
    private List<BotDifficulty> difficulties;
    private BotDifficulty currentDifficulty;
    private int difficultyIndex;

    public BotManager() {
        this.isBotEnabled = false;
        this.difficulties = new ArrayList<>();
        this.difficulties.add(new EasyBot());
        this.difficulties.add(new MediumBot());
        this.difficulties.add(new HardBot());
        this.currentDifficulty = difficulties.get(0);
        this.difficultyIndex = 0;
    }

    public void activateBot() {
        isBotEnabled = true;
    }

    public void disableBot() {
        isBotEnabled = false;
    }

    public boolean isBotEnabled() {
        return isBotEnabled;
    }

    public void increaseDifficulty() {
        difficultyIndex = (difficultyIndex + 1) % difficulties.size();
        currentDifficulty = difficulties.get(difficultyIndex);
    }

    public void decreaseDifficulty() {
        difficultyIndex = (difficultyIndex - 1 + difficulties.size()) % difficulties.size();
        currentDifficulty = difficulties.get(difficultyIndex);
    }

    public void update(Paddle secondPlayer, Ball ball) {
        if (!isBotEnabled) return;
        currentDifficulty.update(secondPlayer, ball);
    }

    public BotDifficulty getCurrentDifficulty() {
        return currentDifficulty;
    }

	public void setDifficulty(BotDifficulty difficulty) {
		this.currentDifficulty = difficulty;
	}
}