package dev.tonimatas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Mines extends JPanel implements Runnable {
    Thread gameThread;
    int FPS = 60;
    KeyHandler keyHandler = new KeyHandler();
    int block = 16;
    int blockRow = 25;
    int blockColumn = 25;
    int mines = 100;
    int blockCount = blockColumn * blockRow;
    int width = block * blockRow;
    int height = block * blockColumn;
    int[] blocks = new int[blockCount];

    public static void main(String[] args) {
        JFrame windows = new JFrame();
        windows.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        windows.setResizable(false);
        windows.setTitle("Mines");

        Mines gamePanel = new Mines();
        windows.add(gamePanel);

        windows.pack();

        windows.setLocationRelativeTo(null);
        windows.setVisible(true);

        gamePanel.startGameThread();
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public Mines() {
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    @Override
    public void run() {
        double drawInterval = 1000000000d / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {

            update();

            repaint();

            try {
                double reamingTime = nextDrawTime - System.nanoTime();
                reamingTime = reamingTime / 1000000;

                if (reamingTime < 0) {
                    reamingTime = 0;
                }

                Thread.sleep((long) reamingTime);

                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void update() {
        // TODO: Make all the logic
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        Graphics2D graphics2D = (Graphics2D) graphics;

        graphics2D.setColor(Color.WHITE);
        graphics2D.fillRect(100, 100, 16, 16);

        graphics2D.setColor(Color.GREEN);
        graphics2D.fillRect(100, 100, 16, 16);
        graphics2D.dispose();
    }

    public class KeyHandler implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }
    }
}
