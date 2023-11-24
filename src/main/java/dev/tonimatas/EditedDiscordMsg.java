package dev.tonimatas;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.Scanner;

public class EditedDiscordMsg {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.print("Write you message (:stop for close): ");
            String result = scanner.nextLine();

            if (result.equalsIgnoreCase(":stop")) {
                break;
            }

            result = result + " \u202B";
            System.out.println(result);

            result = result.replaceAll(":edited", "  \u202B ");

            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(new StringSelection(result), null);
            System.out.println("Edit the message and replace the text with ctrl+v");
        }

        System.out.println("Program stopped.");
        scanner.close();
    }
}