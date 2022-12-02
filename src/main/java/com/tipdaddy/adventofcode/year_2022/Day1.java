package com.tipdaddy.adventofcode.year_2022;

import com.tipdaddy.adventofcode.util.Day;
import com.tipdaddy.adventofcode.util.FileReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day1 implements Day {

    @Override
    public void run() {

        List<String> lines = FileReader.readMultiLineFile("2022", "1");
        Map<Integer, Integer> elfToCalories = new HashMap<>();
        int curElf = 0;
        int curCalories = 0;
        for (String line : lines) {
            if (line.isEmpty()) {
                elfToCalories.put(curElf, curCalories);
                curElf++;
                curCalories = 0;
            } else {
                curCalories += Double.parseDouble(line);
            }
        }

        List<Integer> calories = elfToCalories.values().stream().sorted(Comparator.reverseOrder()).toList().subList(0, 3);
        System.out.printf("Part 1 answer is: %d\n", calories.get(0));
        System.out.printf("Part 2 answer is: %d\n", calories.stream().mapToInt(Integer::intValue).sum());
    }
}
