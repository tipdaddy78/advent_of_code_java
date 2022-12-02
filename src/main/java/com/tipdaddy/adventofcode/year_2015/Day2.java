package com.tipdaddy.adventofcode.year_2015;

import com.tipdaddy.adventofcode.util.Day;
import com.tipdaddy.adventofcode.util.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day2 implements Day {

    private int calculateSurfaceArea(final int length, final int width, final int height) {

        final List<Integer> areas = new ArrayList<>();
        areas.add(length * width);
        areas.add(length * height);
        areas.add(width * height);

        final int min = Collections.min(areas);

        return (2 * length * width) + (2 * width * height) + (2 * height * length) + min;
    }

    private int calculateRibbonLength(final int length, final int width, final int height) {

        final List<Integer> dimensions = new ArrayList<>();
        dimensions.add(length);
        dimensions.add(width);
        dimensions.add(height);

        Collections.sort(dimensions);
        return (2 * dimensions.get(0)) + (2 * dimensions.get(1)) + (length * width * height);
    }

    @Override
    public void run() {

        final List<String> lines = FileReader.readMultiLineFile("2015", "2");
        int sum = 0;
        int ribbon = 0;
        for (String line : lines) {
            String[] parts = line.split("x");
            sum += calculateSurfaceArea(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
            ribbon += calculateRibbonLength(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
        }
        System.out.printf("Part 1: %d\n", sum);
        System.out.printf("Part 2: %d\n", ribbon);
    }
}
