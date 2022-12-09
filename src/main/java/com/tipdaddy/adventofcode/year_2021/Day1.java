package com.tipdaddy.adventofcode.year_2021;

import com.tipdaddy.adventofcode.util.Day;
import com.tipdaddy.adventofcode.util.FileReader;
import java.util.List;

public class Day1 implements Day {

    @Override
    public void run() {

        List<String> lines = FileReader.readMultiLineFile("2021", "1");
        int prev = Integer.parseInt(lines.get(0));
        int decreaseCount = 0;
        for (String line : lines) {
            int cur = Integer.parseInt(line);
            if (cur > prev) {
                decreaseCount++;
            }
            prev = cur;
        }
        System.out.printf("Part 1 is %d\n", decreaseCount);

        int prevSum = lines.subList(0, 3)
                .stream()
                .map(Integer::parseInt)
                .mapToInt(Integer::intValue)
                .sum();
        int sumDecreaseCount = 0;
        for (int i = 0; i < lines.size() - 2; i++) {
            Integer sum = lines.subList(i, i+3)
                    .stream()
                    .map(Integer::parseInt)
                    .mapToInt(Integer::intValue)
                    .sum();

            if (sum > prevSum) {
                sumDecreaseCount++;
            }
            prevSum = sum;
        }
        System.out.printf("Part 2 is %d\n", sumDecreaseCount);

    }
}
