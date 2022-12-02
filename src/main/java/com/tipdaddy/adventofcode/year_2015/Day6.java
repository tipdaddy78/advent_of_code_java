package com.tipdaddy.adventofcode.year_2015;

import com.tipdaddy.adventofcode.util.FileReader;
import com.tipdaddy.adventofcode.util.Point;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day6 {

    public static void turnOnLights(final boolean[][] lights, final int startX, final int startY, final int endX, final int endY) {

        for (int x = startX; x <= endX; x++) {
            for (int y = startY; y <= endY; y++) {
                lights[x][y] = true;
            }
        }
    }

    public static void upBrightness(final int[][] lights, final int startX, final int startY, final int endX, final int endY) {

        for (int x = startX; x <= endX; x++) {
            for (int y = startY; y <= endY; y++) {
                lights[x][y] += 1;
            }
        }
    }

    public static void turnOffLights(final boolean[][] lights, final int startX, final int startY, final int endX, final int endY) {

        for (int x = startX; x <= endX; x++) {
            for (int y = startY; y <= endY; y++) {
                lights[x][y] = false;
            }
        }
    }

    public static void downBrightness(final int[][] lights, final int startX, final int startY, final int endX, final int endY) {

        for (int x = startX; x <= endX; x++) {
            for (int y = startY; y <= endY; y++) {
                lights[x][y] = Math.max(lights[x][y] - 1, 0);
            }
        }
    }

    public static void toggleLights(final boolean[][] lights, final int startX, final int startY, final int endX, final int endY) {

        for (int x = startX; x <= endX; x++) {
            for (int y = startY; y <= endY; y++) {
                lights[x][y] = !lights[x][y];
            }
        }
    }

    public static void toggleBrightness(final int[][] lights, final int startX, final int startY, final int endX, final int endY) {

        for (int x = startX; x <= endX; x++) {
            for (int y = startY; y <= endY; y++) {
                lights[x][y] += 2;
            }
        }
    }

    public static int countOnLights(final boolean[][] lights) {
        int count = 0;
        for (int x = 0; x < lights.length; x++) {
            for (int y = 0; y < lights[x].length; y++) {
                count += lights[x][y] ? 1 : 0;
            }
        }
        return count;
    }

    public static int countBrightness(final int[][] lights) {
        int count = 0;
        for (int x = 0; x < lights.length; x++) {
            for (int y = 0; y < lights[x].length; y++) {
                count += lights[x][y];
            }
        }
        return count;
    }

    public static void main(String[] args) {

        boolean[][] lights = new boolean[1000][1000];
        int[][] brightness = new int[1000][1000];
        final List<String> directions = FileReader.readMultiLineFile("2015", "6");
        directions.forEach(d -> {
            String[] parts = d.split(" ");
            if (d.startsWith("turn on")) {
                int startX = Integer.parseInt(parts[2].split(",")[0]);
                int startY = Integer.parseInt(parts[2].split(",")[1]);
                int endX = Integer.parseInt(parts[4].split(",")[0]);
                int endY = Integer.parseInt(parts[4].split(",")[1]);
                turnOnLights(lights, startX, startY, endX, endY);
                upBrightness(brightness, startX, startY, endX, endY);
            } else if (d.startsWith("turn off")) {
                int startX = Integer.parseInt(parts[2].split(",")[0]);
                int startY = Integer.parseInt(parts[2].split(",")[1]);
                int endX = Integer.parseInt(parts[4].split(",")[0]);
                int endY = Integer.parseInt(parts[4].split(",")[1]);
                turnOffLights(lights, startX, startY, endX, endY);
                downBrightness(brightness, startX, startY, endX, endY);
            } else if (d.startsWith("toggle")) {
                int startX = Integer.parseInt(parts[1].split(",")[0]);
                int startY = Integer.parseInt(parts[1].split(",")[1]);
                int endX = Integer.parseInt(parts[3].split(",")[0]);
                int endY = Integer.parseInt(parts[3].split(",")[1]);
                toggleLights(lights, startX, startY, endX, endY);
                toggleBrightness(brightness, startX, startY, endX, endY);
            }
        });

        System.out.println(countOnLights(lights));
        System.out.println(countBrightness(brightness));
    }
}
