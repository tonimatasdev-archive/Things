package dev.tonimatas;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MemoryConsumer {
    public static void main(String[] args) {
        try {
            List<byte[]> allocations = new ArrayList<>();

            while (allocations.size() <= 76) {
                TimeUnit.MILLISECONDS.sleep(10);
                allocations.add(new byte[100 * 1024 * 1024]);
                System.out.println("Allocated 100MB, total allocated: " + allocations.size() * 100 + "MB");
            }
        } catch (OutOfMemoryError | InterruptedException e) {
            System.err.println("Out of memory!");
        }
    }
}
