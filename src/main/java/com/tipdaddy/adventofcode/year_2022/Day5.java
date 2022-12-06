package com.tipdaddy.adventofcode.year_2022;

import com.tipdaddy.adventofcode.util.Day;
import com.tipdaddy.adventofcode.util.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.stream.IntStream;

public class Day5 implements Day {

    /*
        Based on input file, this program assumes the input file will have 9 columns of crates.
        Example of lines denoting crates where | and _ characters wrap the input:
        _____________________________________
        |                    [L]     [H] [W]|
        |                [J] [Z] [J] [Q] [Q]|
        |[S]             [M] [C] [T] [F] [B]|
        |[P]     [H]     [B] [D] [G] [B] [P]|
        |[W]     [L] [D] [D] [J] [W] [T] [C]|
        |[N] [T] [R] [T] [T] [T] [M] [M] [G]|
        |[J] [S] [Q] [S] [Z] [W] [P] [G] [D]|
        |[Z] [G] [V] [V] [Q] [M] [L] [N] [R]|
        | 1   2   3   4   5   6   7   8   9 |
        _____________________________________
     */

    private static final int CRATE_1_INDEX = 1;
    private static final int CRATE_2_INDEX = 5;
    private static final int CRATE_3_INDEX = 9;
    private static final int CRATE_4_INDEX = 13;
    private static final int CRATE_5_INDEX = 17;
    private static final int CRATE_6_INDEX = 21;
    private static final int CRATE_7_INDEX = 25;
    private static final int CRATE_8_INDEX = 29;
    private static final int CRATE_9_INDEX = 33;

    private final Map<Integer, Stack<String>> crateMap = new HashMap<>();
    private final Map<Integer, Stack<String>> crateMap2 = new HashMap<>();

    @Override
    public void run() {

        final List<String> lines = FileReader.readMultiLineFile("2022", "5");
        final List<String> crateDirections = new ArrayList<>();
        boolean placingCrates = true;

        // Since lines are read from top to bottom, need to create an initial map that will then be flipped
        final Map<Integer, Stack<String>> initialCrateMap = new HashMap<>();
        for (String line : lines) {

            // Either the line of " 1   2 ..." or the empty between initial setup/directions.
            if (line.startsWith(" 1") || line.isEmpty()) {
                placingCrates = false;
                continue;
            }

            // First put crates in their right column.
            if (placingCrates) {
                parseCrateLine(line, initialCrateMap);
            } else {
                crateDirections.add(line);
            }
        }

        // All lines read, time to flip "initialCrateMap" into "crateMap"
        initialCrateMap.forEach((k, v) -> {

           while (!v.isEmpty()) {
               String crate = v.pop();
               crateMap.compute(k, (k2, v2) -> {
                   if (v2 == null) {
                       v2 = new Stack<>();
                   }
                   v2.push(crate);
                   return v2;
               });

               crateMap2.compute(k, (k2, v2) -> {
                   if (v2 == null) {
                       v2 = new Stack<>();
                   }
                   v2.push(crate);
                   return v2;
               });
           }
        });

        crateDirections.forEach(direction -> {
            final String[] dParts = direction.split(" ");
            moveCrates_part1(Integer.parseInt(dParts[1]), Integer.parseInt(dParts[3]), Integer.parseInt(dParts[5]));
            moveCrates_part2(Integer.parseInt(dParts[1]), Integer.parseInt(dParts[3]), Integer.parseInt(dParts[5]));
        });

        final String part1TopCrates = getTopCrates(true);
        System.out.printf("Part 1 is: %s\n", part1TopCrates);
        final String part2TopCrates = getTopCrates(false);
        System.out.printf("Part 2 is: %s\n", part2TopCrates);
    }

    public void moveCrates_part1(final int times, final int start, final int end) {

        IntStream.rangeClosed(1, times)
                .forEach(i -> {
                    String crate = crateMap.get(start).pop();
                    crateMap.get(end).push(crate);
                });
    }

    public void moveCrates_part2(final int times, final int start, final int end) {

        Stack<String> tempStack = new Stack<>();
        IntStream.rangeClosed(1, times)
                .forEach(i -> {
                    String crate = crateMap2.get(start).pop();
                    tempStack.push(crate);
                });

        while (!tempStack.isEmpty()) {
            crateMap2.get(end).push(tempStack.pop());
        }
    }

    public String getTopCrates(final boolean part1) {

        final StringBuilder stringBuilder = new StringBuilder();
        if (part1) {
            crateMap.values().forEach(stack -> stringBuilder.append(stack.peek()));
        } else {
            crateMap2.values().forEach(stack -> stringBuilder.append(stack.peek()));
        }
        return stringBuilder.toString();
    }

    public void parseCrateLine(final String line, final Map<Integer, Stack<String>> initialCrateMap) {

        if (line.charAt(CRATE_1_INDEX) != ' ') {
            initialCrateMap.compute(1, (k, v) -> {
                if (v == null) {
                    v = new Stack<>();
                }
                v.push(String.valueOf(line.charAt(CRATE_1_INDEX)));
                return v;
            });
        }
        if (line.charAt(CRATE_2_INDEX) != ' ') {
            initialCrateMap.compute(2, (k, v) -> {
                if (v == null) {
                    v = new Stack<>();
                }
                v.push(String.valueOf(line.charAt(CRATE_2_INDEX)));
                return v;
            });
        }
        if (line.charAt(CRATE_3_INDEX) != ' ') {
            initialCrateMap.compute(3, (k, v) -> {
                if (v == null) {
                    v = new Stack<>();
                }
                v.push(String.valueOf(line.charAt(CRATE_3_INDEX)));
                return v;
            });
        }
        if (line.charAt(CRATE_4_INDEX) != ' ') {
            initialCrateMap.compute(4, (k, v) -> {
                if (v == null) {
                    v = new Stack<>();
                }
                v.push(String.valueOf(line.charAt(CRATE_4_INDEX)));
                return v;
            });
        }
        if (line.charAt(CRATE_5_INDEX) != ' ') {
            initialCrateMap.compute(5, (k, v) -> {
                if (v == null) {
                    v = new Stack<>();
                }
                v.push(String.valueOf(line.charAt(CRATE_5_INDEX)));
                return v;
            });
        }
        if (line.charAt(CRATE_6_INDEX) != ' ') {
            initialCrateMap.compute(6, (k, v) -> {
                if (v == null) {
                    v = new Stack<>();
                }
                v.push(String.valueOf(line.charAt(CRATE_6_INDEX)));
                return v;
            });
        }
        if (line.charAt(CRATE_7_INDEX) != ' ') {
            initialCrateMap.compute(7, (k, v) -> {
                if (v == null) {
                    v = new Stack<>();
                }
                v.push(String.valueOf(line.charAt(CRATE_7_INDEX)));
                return v;
            });
        }
        if (line.charAt(CRATE_8_INDEX) != ' ') {
            initialCrateMap.compute(8, (k, v) -> {
                if (v == null) {
                    v = new Stack<>();
                }
                v.push(String.valueOf(line.charAt(CRATE_8_INDEX)));
                return v;
            });
        }
        if (line.charAt(CRATE_9_INDEX) != ' ') {
            initialCrateMap.compute(9, (k, v) -> {
                if (v == null) {
                    v = new Stack<>();
                }
                v.push(String.valueOf(line.charAt(CRATE_9_INDEX)));
                return v;
            });
        }
    }
}
