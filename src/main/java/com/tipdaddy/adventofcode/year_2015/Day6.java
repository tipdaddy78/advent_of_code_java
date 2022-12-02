package com.tipdaddy.adventofcode.year_2015;

import com.tipdaddy.adventofcode.util.Day;
import com.tipdaddy.adventofcode.util.FileReader;
import java.util.List;

public class Day6 implements Day {

    @Override
    public void run() {

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

        System.out.printf("Part 1: %d\n", countOnLights(lights));
        System.out.printf("Part 2: %d\n", countBrightness(brightness));
    }

    public void turnOnLights(final boolean[][] lights, final int startX, final int startY, final int endX, final int endY) {

        for (int x = startX; x <= endX; x++) {
            for (int y = startY; y <= endY; y++) {
                lights[x][y] = true;
            }
        }
    }

    public void upBrightness(final int[][] lights, final int startX, final int startY, final int endX, final int endY) {

        for (int x = startX; x <= endX; x++) {
            for (int y = startY; y <= endY; y++) {
                lights[x][y] += 1;
            }
        }
    }

    public void turnOffLights(final boolean[][] lights, final int startX, final int startY, final int endX, final int endY) {

        for (int x = startX; x <= endX; x++) {
            for (int y = startY; y <= endY; y++) {
                lights[x][y] = false;
            }
        }
    }

    public void downBrightness(final int[][] lights, final int startX, final int startY, final int endX, final int endY) {

        for (int x = startX; x <= endX; x++) {
            for (int y = startY; y <= endY; y++) {
                lights[x][y] = Math.max(lights[x][y] - 1, 0);
            }
        }
    }

    public void toggleLights(final boolean[][] lights, final int startX, final int startY, final int endX, final int endY) {

        for (int x = startX; x <= endX; x++) {
            for (int y = startY; y <= endY; y++) {
                lights[x][y] = !lights[x][y];
            }
        }
    }

    public void toggleBrightness(final int[][] lights, final int startX, final int startY, final int endX, final int endY) {

        for (int x = startX; x <= endX; x++) {
            for (int y = startY; y <= endY; y++) {
                lights[x][y] += 2;
            }
        }
    }

    public int countOnLights(final boolean[][] lights) {
        int count = 0;
        for (boolean[] x : lights) {
            for (boolean y : x) {
                count += y ? 1 : 0;
            }
        }
        return count;
    }

    public int countBrightness(final int[][] lights) {
        int count = 0;
        for (int[] x : lights) {
            for (int y : x) {
                count += y;
            }
        }
        return count;
    }
}
