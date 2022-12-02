package com.tipdaddy.adventofcode.year_2015;

import com.tipdaddy.adventofcode.util.FileReader;

public class Day1 {

    public static void main(String[] args) {

        String line = FileReader.readSingleLineFile("2015", "1");

        int floor = 0;
        int part2 = 0;
        boolean setPart2 = false;
        for (int i = 0; i < line.length(); i++) {
            final char c = line.charAt(i);
            if (c == '(') {
                floor++;
            } else if (c == ')') {
                floor--;
            }

            if (floor == -1 && !setPart2) {
                setPart2 = true;
                part2 = i + 1;
            }
        }

        System.out.println(floor);
        System.out.println(part2);
    }
}