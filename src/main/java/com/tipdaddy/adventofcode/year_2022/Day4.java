package com.tipdaddy.adventofcode.year_2022;

import com.tipdaddy.adventofcode.util.Day;
import com.tipdaddy.adventofcode.util.FileReader;
import java.util.List;

public class Day4 implements Day {

    @Override
    public void run() {
        final List<String> directions = FileReader.readMultiLineFile("2022", "4");

        final long part1 = directions.stream().filter(this::pairFullyContainsOther).count();
        System.out.printf("Part 1 is %d\n", part1);
        final long part2 = directions.stream().filter(this::pairOverlapsAtAll).count();
        System.out.printf("Part 2 is %d\n", part2);

    }

    private boolean pairFullyContainsOther(final String directions) {

        final String[] dSplit = directions.split(",");
        final String direction1 = dSplit[0];
        final String direction2 = dSplit[1];
        final String[] d1Parts = direction1.split("-");
        final String[] d2Parts = direction2.split("-");
        final int low1 = Integer.parseInt(d1Parts[0]);
        final int high1 = Integer.parseInt(d1Parts[1]);
        final int low2 = Integer.parseInt(d2Parts[0]);
        final int high2 = Integer.parseInt(d2Parts[1]);

        return (low1 >= low2 && high1 <= high2) || (low2 >= low1 && high2 <= high1);
    }

    private boolean pairOverlapsAtAll(final String directions) {

        final String[] dSplit = directions.split(",");
        final String direction1 = dSplit[0];
        final String direction2 = dSplit[1];
        final String[] d1Parts = direction1.split("-");
        final String[] d2Parts = direction2.split("-");
        final int low1 = Integer.parseInt(d1Parts[0]);
        final int high1 = Integer.parseInt(d1Parts[1]);
        final int low2 = Integer.parseInt(d2Parts[0]);
        final int high2 = Integer.parseInt(d2Parts[1]);

        return (low1 <= low2 && high1 >= low2) || (low2 <= low1 && high2 >= low1);
    }
}
