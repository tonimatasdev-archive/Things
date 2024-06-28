package dev.tonimatas;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;

public class MemoryCleaner {
    public static void main(String[] args) {
        String[] array = new String[100000];
        for (int i = 0; i < array.length; i++) {
            array[i] = "String " + i;
        }

        MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
        MemoryUsage heapUsage = memoryBean.getHeapMemoryUsage();
        long usedMemory = heapUsage.getUsed();
        long maxMemory = heapUsage.getMax();

        System.out.println("Memory used: " + usedMemory / (1024 * 1024) + " MB");
        System.out.println("Max memory: " + maxMemory / (1024 * 1024) + " MB");

        System.gc();

        heapUsage = memoryBean.getHeapMemoryUsage();
        usedMemory = heapUsage.getUsed();

        System.out.println("Memory used after gc" + usedMemory / (1024 * 1024) + " MB");
    }
}
