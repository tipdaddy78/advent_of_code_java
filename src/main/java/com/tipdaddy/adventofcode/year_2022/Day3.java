package com.tipdaddy.adventofcode.year_2022;

import com.tipdaddy.adventofcode.util.Day;
import com.tipdaddy.adventofcode.util.FileReader;
import java.util.List;
import java.util.function.Function;

public class Day3 implements Day {

    public static int UPPER_CASE_OFFSET = 38;
    public static int LOWER_CASE_OFFSET = 96;

    @Override
    public void run() {

        System.out.println((int)'L');
        List<String> rucksacks = FileReader.readMultiLineFile("2022", "3");
        final int part1 = rucksacks.stream().mapToInt(this::getRucksackPriority).sum();
        System.out.printf("Part 1 is %d\n", part1);

        int part2 = 0;
        for (int i = 0; i < rucksacks.size(); i += 3) {
            final List<String> group = rucksacks.subList(i, i+3);
            part2 += getRucksackGroupPriority(group);
        }
        System.out.printf("Part 2 is %d\n", part2);
    }

    public int getRucksackPriority(String rucksack) {

        final int length = rucksack.length();
        final String container1 = rucksack.substring(0, length/2);
        final String container2 = rucksack.substring(length/2);

        int priority = container1
                .chars()
                .filter(cInt -> container2.indexOf(cInt) != -1)
                .findFirst()
                .orElseThrow();

        // lower case
        if (97 <= priority && priority <= 122) {
            return priority - LOWER_CASE_OFFSET;
        } else {
            return priority - UPPER_CASE_OFFSET;
        }
    }

    public int getRucksackGroupPriority(List<String> rucksacks) {

        final String rucksack1 = rucksacks.get(0);
        final List<String> otherRucksacks = rucksacks.subList(1, rucksacks.size());

        int priority = rucksack1
                .chars()
                .filter(cInt -> otherRucksacks.stream().allMatch(r -> r.indexOf(cInt) != -1))
                .findFirst()
                .orElseThrow();

        // lower case
        if (97 <= priority && priority <= 122) {
            return priority - LOWER_CASE_OFFSET;
        } else {
            return priority - UPPER_CASE_OFFSET;
        }
    }
}
