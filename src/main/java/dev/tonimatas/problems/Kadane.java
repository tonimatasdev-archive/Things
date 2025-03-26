package dev.tonimatas.problems;

import java.util.Arrays;

public class Kadane {
    public static void main(String[] args) {
        int[] array = {-1, 3, 2, 1, -3, 9, -5};
        
        int actualStartPos = 0;
        int startPos = 0;
        int endPos = 0;
        int sequenceSum = array[0];
        int biggestSum = array[0];
        
        for (int i = 0; i < array.length; i++) {
            sequenceSum += array[i];

            if (sequenceSum > biggestSum) {
                biggestSum = sequenceSum;
                endPos = i;
                startPos = actualStartPos;
            } else if (sequenceSum < 0) {
                sequenceSum = 0;
                actualStartPos = i + 1;
            }
        }
        
        int numberCount = endPos - startPos + 1;
        int[] resultSequence = new int[numberCount];

        System.arraycopy(array, startPos, resultSequence, 0, numberCount);

        System.out.println(Arrays.toString(resultSequence));
        System.out.println(biggestSum);
    }
}
