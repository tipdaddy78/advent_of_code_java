package com.tipdaddy.adventofcode.util;

public class DayGetter {

    public Day getDay(String year, String day) {

        return switch (year) {
            case "2015": {
                yield switch (day) {
                    case "1" -> new com.tipdaddy.adventofcode.year_2015.Day1();
                    case "2" -> new com.tipdaddy.adventofcode.year_2015.Day2();
                    case "3" -> new com.tipdaddy.adventofcode.year_2015.Day3();
                    case "4" -> new com.tipdaddy.adventofcode.year_2015.Day4();
                    case "5" -> new com.tipdaddy.adventofcode.year_2015.Day5();
                    case "6" -> new com.tipdaddy.adventofcode.year_2015.Day6();
                    case "7" -> new com.tipdaddy.adventofcode.year_2015.Day7();
                    default -> null;
                };
            }
            case "2022": {
                yield switch (day) {
                    case "1" -> new com.tipdaddy.adventofcode.year_2022.Day1();
                    case "2" -> new com.tipdaddy.adventofcode.year_2022.Day2();
                    default -> null;
                };
            }
            default: throw new IllegalArgumentException();
        };
    }
}
