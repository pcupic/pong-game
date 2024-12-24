package pong;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

public class Renderer extends JPanel implements MouseListener {

    private static final long serialVersionUID = 1L;
    private PongGame game;

    public Renderer(PongGame game) {
        this.game = game;
        addMouseListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (game != null) {
            game.render((Graphics2D) g);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (game.getGameStateManager().getCurrentState() instanceof MouseListener) {
            ((MouseListener) game.getGameStateManager().getCurrentState()).mouseClicked(e);
        }
    }

    // Unused MouseListener methods
    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
}