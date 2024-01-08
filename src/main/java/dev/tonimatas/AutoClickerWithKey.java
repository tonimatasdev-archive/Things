package dev.tonimatas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AutoClickerWithKey extends JFrame {
    private boolean autoClickerActive = false;
    private Robot robot;
    private long delayBetweenClicks = 1;

    public AutoClickerWithKey() {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_X) {
                    autoClickerActive = !autoClickerActive;
                    System.out.println("AutoClicker " + (autoClickerActive ? "Activado" : "Desactivado"));
                    if (autoClickerActive) {
                        startAutoClicker();
                    }
                }
            }
        });

        setFocusable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(200, 200);
        setVisible(true);
    }

    private void startAutoClicker() {
        new Thread(() -> {
            while (autoClickerActive) {
                robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                try {
                    Thread.sleep(delayBetweenClicks);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void main(String[] args) {
        new AutoClickerWithKey();
    }
}