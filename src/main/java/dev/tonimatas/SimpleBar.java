package dev.tonimatas;

public class SimpleBar {
    private static final String filledChar = "▰";
    private static final String voidChar = "▱";
    private static final int numberChars = 10;
    private static final long everyMillis = 1000;

    public static void main(String[] args) {
        int max = 1000;
        int now = 100;

        int part = max / numberChars;
        int filledParts = now / part;

        String result = filledChar.repeat(filledParts) + voidChar.repeat(numberChars - filledParts);

        System.out.println(result);
    }
}
