package com.tipdaddy.adventofcode.year_2022;

import com.tipdaddy.adventofcode.util.FileReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day1 {

    public static void main(String[] args) {

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
        System.out.println(calories.get(0));
        System.out.println(calories.stream().mapToInt(Integer::intValue).sum());
    }
}
