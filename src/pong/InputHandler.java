package pong;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

public class InputHandler implements KeyListener {
    private boolean isWPressed, isSPressed, isUpPressed, isDownPressed;

    private Map<Integer, Runnable> movementKeys;
    private Map<Integer, Runnable> keyReleaseKeys;

    public InputHandler() {
    	initMovementKeys();
        initKeyReleaseKeys();
    }

    private void initMovementKeys() {
        movementKeys = new HashMap<>();
        movementKeys.put(KeyEvent.VK_W, () -> isWPressed = true);
        movementKeys.put(KeyEvent.VK_S, () -> isSPressed = true);
        movementKeys.put(KeyEvent.VK_UP, () -> isUpPressed = true);
        movementKeys.put(KeyEvent.VK_DOWN, () -> isDownPressed = true);
    }

    private void initKeyReleaseKeys() {
        keyReleaseKeys = new HashMap<>();
        keyReleaseKeys.put(KeyEvent.VK_W, () -> isWPressed = false);
        keyReleaseKeys.put(KeyEvent.VK_S, () -> isSPressed = false);
        keyReleaseKeys.put(KeyEvent.VK_UP, () -> isUpPressed = false);
        keyReleaseKeys.put(KeyEvent.VK_DOWN, () -> isDownPressed = false);
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int id = e.getKeyCode();
        if (movementKeys.containsKey(id)) {
            movementKeys.get(id).run();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int id = e.getKeyCode();
        if (keyReleaseKeys.containsKey(id)) {
            keyReleaseKeys.get(id).run();
        }
    }

    public boolean isWPressed() {
        return isWPressed;
    }

    public boolean isSPressed() {
        return isSPressed;
    }

    public boolean isUpPressed() {
        return isUpPressed;
    }

    public boolean isDownPressed() {
        return isDownPressed;
    }
}