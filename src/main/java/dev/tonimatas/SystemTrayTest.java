package dev.tonimatas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SystemTrayTest {
    public static void main(String[] args) {
        if (!SystemTray.isSupported()) {
            System.out.println("System tray is not supported.");
            return;
        }

        PopupMenu popup = new PopupMenu();

        MenuItem exitItem = new MenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));
        popup.add(exitItem);

        TrayIcon trayIcon = new TrayIcon(createImage("icon.png", "tray icon"));
        trayIcon.setImageAutoSize(true);
        trayIcon.setPopupMenu(popup);

        trayIcon.addActionListener(e -> JOptionPane.showMessageDialog(null, "This is the system tray icon."));

        trayIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    JOptionPane.showMessageDialog(null, "You clicked the tray icon.");
                }
            }
        });

        SystemTray tray = SystemTray.getSystemTray();

        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            System.out.println("TrayIcon could not be added.");
            return;
        }

        trayIcon.displayMessage("Hello, World", "This is a notification message", TrayIcon.MessageType.INFO);
    }

    protected static Image createImage(String path, String description) {
        return (new ImageIcon(path, description)).getImage();
    }
}
