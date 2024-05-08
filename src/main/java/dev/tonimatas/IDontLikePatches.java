package dev.tonimatas;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class IDontLikePatches {
    public static Map<String, String> rawPatches = new HashMap<>();
    
    public static void main(String[] args) {
        List<File> patches = getAllPatchesFromFolder("patches");

        System.out.println("Founded " + patches.size() + " patches.");
        classification(patches);
    }
    
    public static void classification(List<File> patches) {
        int count = 0;
        
        for (File file : patches) {
            try {
                Scanner scanner = new Scanner(file);
                StringBuilder rawPatch = new StringBuilder();

                while (scanner.hasNext()) {
                    rawPatch.append(scanner.nextLine()).append("\n");
                }

                String rawPatchString = rawPatch.toString();
                rawPatches.put(file.getPath(), rawPatchString);

                if (rawPatchString.contains("craftbukkit") ||
                        rawPatchString.contains("CraftBukkit") ||
                        rawPatchString.contains("Bukkit") ||
                        rawPatchString.contains("bukkit") ||
                        rawPatchString.contains("Spigot") ||
                        rawPatchString.contains("spigot") ||
                        rawPatchString.contains("Paper") ||
                        rawPatchString.contains("paper") ||
                        rawPatchString.contains("Ketting") ||
                        rawPatchString.contains("ketting")) {
                    count++;
                    System.out.println(count + " " + file.getName());
                } else {
                    file.deleteOnExit();
                }

                scanner.close();
            } catch (FileNotFoundException ignored) {
            }
        }
    }
    
    public static List<File> getAllPatchesFromFolder(String folder) {
        File[] files = new File(folder).listFiles();
        List<File> result = new ArrayList<>();
        
        if (files == null) return result;
        
        for (File file : files) {
            if (file.isDirectory()) {
                result.addAll(getAllPatchesFromFolder(file.getPath()));
            } else {
                result.add(file);
            }
        }

        return result;
    }
}
