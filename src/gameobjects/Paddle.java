package gameobjects;

import java.awt.Color;
import java.awt.Graphics;

import pong.PongGame;

public class Paddle {
	public int number;
	public int x, y, width = 50, height = 200;
	public int score;
	private PongGame game;
	
	public Paddle(PongGame game, int number) {
		this.game = game;
		this.number = number;
		if(number == 1) 
			this.x = 0;
		if(number == 2) 
			this.x = game.getWidth() - width;
		this.y = game.getHeight() / 2 - this.height / 2;
	}
	
	public void resetScore() {
		score = 0;
	}
	
	public void increaseScore() {
		score++;
	}
	
	public int getScore() {
		return this.score;
	}
	
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x, y, width, height);
	}

	public void move(boolean up) {
		int speed = 8;
		int panelHeight = game.getRenderer().getHeight();

		if (up) {
		    if (y - speed > 0) y -= speed;
		    else y = 0;
		} else {
		    if (y + height + speed < panelHeight) y += speed;
		    else y = panelHeight - height;
		}
	}
	
}