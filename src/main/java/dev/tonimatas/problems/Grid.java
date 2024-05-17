package dev.tonimatas.problems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Grid {
    public static Map<Integer, List<int[]>> pathsMap = new HashMap<>();
    
    public static void main(String[] args) {
        long time = System.currentTimeMillis();
        //String string = "[0,6,0]\n[5,8,7]\n[0,9,0]";
        //String string = "[1,0,7]\n[2,0,6]\n[3,4,5]\n[0,3,0]\n[9,0,20]";
        //String string = "[1,0,7,0,0,0]\n[2,0,6,0,1,0]\n[3,5,6,7,4,2]\n[4,3,1,0,2,0]\n[3,0,5,0,20,0]";
        String string = "[0,0,0,0,0,0,0,41,0,0,0,0,0,0,0]\n[0,0,0,0,22,0,0,0,0,0,7,0,0,0,0]\n[0,0,0,95,0,0,0,0,0,0,0,0,0,0,0]\n[0,0,0,0,0,0,66,0,0,0,0,0,0,0,0]\n[0,59,0,0,0,7,0,0,0,0,0,0,0,0,0]\n[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]\n[0,0,0,90,0,0,0,0,0,0,0,16,0,0,0]\n[0,0,0,0,83,0,0,0,0,0,0,0,0,0,0]\n[0,0,0,0,0,0,0,0,0,0,0,23,0,0,0]\n[0,0,0,0,0,0,0,0,0,0,0,0,0,0,6]\n[0,0,0,0,56,38,0,27,0,0,0,0,0,0,0]\n[0,0,0,0,0,0,0,29,0,0,0,0,0,0,0]\n[0,0,0,0,75,0,0,0,0,0,0,0,0,0,10]\n[53,0,0,0,0,29,0,0,0,0,0,0,0,0,0]\n[0,0,86,11,0,0,0,0,0,31,0,0,71,0,0]";
        
        System.out.println(string);
        
        String[] string2 = string.split("\n");
        
        int[][] grid = new int[string2.length][string2[0].split(",").length];
        
        
        for (int i = 0; i < grid.length; i++) {
            String[] string3 = string2[i].replaceAll("\\[", "").replaceAll("]", "").split(",");
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = Integer.parseInt(string3[j]);
            }
        }
        

        Map<Integer, List<Path>> map = new HashMap<>();
        
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 0) continue;
                
                map.putAll(getPaths(grid, i, j, 0, new ArrayList<>()));
            }
        }
        
        int k = 0;
        for (int key : map.keySet()) {
            if (key < k) continue;
            k = key;
        }

        System.out.println("Output: " + k + "(in " + (System.currentTimeMillis() - time) + "ms)");
    }
    
    public static Map<Integer, List<Path>> getPaths(int[][] grid, int x, int y, int gold, List<Path> paths) {
        boolean oneMore = true;
        Map<Integer, List<Path>> map = new HashMap<>();
        paths.add(new Path(x, y));
        gold = gold + grid[x][y];
        map.put(gold, paths);
        
        
        while (oneMore) {
            boolean more = false;
            if (x - 1 >= 0 && x - 1 <= grid.length && !paths.contains(new Path(x - 1, y)) && grid[x - 1][y] != 0) {
                map.putAll(getPaths(grid, x - 1, y, gold, paths));
                more = true;
            }

            if (x + 1 >= 0 && x + 1 < grid.length && !paths.contains(new Path(x + 1, y)) && grid[x + 1][y] != 0) {
                map.putAll(getPaths(grid, x + 1, y, gold, paths));
                more = true;
            }

            if (y - 1 >= 0 && y - 1 <= grid[x].length && !paths.contains(new Path(x, y - 1)) && grid[x][y - 1] != 0) {
                map.putAll(getPaths(grid, x, y - 1, gold, paths));
                more = true;
            }

            if (y + 1 >= 0 && y + 1 < grid[x].length && !paths.contains(new Path(x, y + 1)) && grid[x][y + 1] != 0) {
                map.putAll(getPaths(grid, x, y + 1, gold, paths));
                more = true;
            }

            oneMore = more;
        }
        
        return map;
    }
    
    public record Path(int x, int y) {}
}
