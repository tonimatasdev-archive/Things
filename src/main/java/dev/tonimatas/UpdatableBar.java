package dev.tonimatas;

public class UpdatableBar {
    private static final String filledChar = "#";
    private static final String voidChar = "-";
    private static final int numberChars = 10;
    private static final long everyMillis = 1000;



    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();
        int max = (int) (runtime.totalMemory() / (1024 * 1024));

        int part = max / numberChars;

        while (true) {
            int now = (int) ((runtime.totalMemory() - runtime.freeMemory()) / (1024 * 1024));

            int filledParts = now / part;

            String result = filledChar.repeat(filledParts) + voidChar.repeat(numberChars - filledParts);

            printReplace(result, now + "/" + max + "MB");
        }
    }

    public static void printReplace(String newMessage, String otherMessage) {
        try {
            Thread.sleep(everyMillis);
            System.out.print("\r" + newMessage + " " + otherMessage);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
