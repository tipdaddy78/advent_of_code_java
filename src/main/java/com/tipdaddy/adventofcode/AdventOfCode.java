package com.tipdaddy.adventofcode;

import com.tipdaddy.adventofcode.util.Day;
import java.lang.reflect.Constructor;
import java.util.Scanner;
import java.util.Set;

public class AdventOfCode {

    private static final Set<String> VALID_YEARS = Set.of("2015", "2022");

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        System.out.println("Welcome to tipdaddy's Advent of Code implementation");
        System.out.println("To run a file, start by entering a year to run from.\n");

        final Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Valid years include: " + VALID_YEARS);
            System.out.println("To quit the program, simply enter q as the year.");
            System.out.print("Year to run from? ");
            String year = scanner.nextLine();
            if (year.equalsIgnoreCase("q")) {
                System.out.println("Quit command detected, now exiting...");
                break;
            } else if (!VALID_YEARS.contains(year)) {
                System.out.println("Not a valid year");
            } else {
                while (true) {
                    System.out.printf("Selected year is %s. To select a new year, type 'new'\n", year);
                    System.out.print("Day to run? ");
                    String day = scanner.nextLine();
                    if (day.equalsIgnoreCase("new")) {
                        System.out.println("Returning to year selection.");
                        break;
                    } else {
                        // Get the right file and run it.
                        try {
                            Class<Day> dayClass = (Class<Day>) Class.forName(String.format("com.tipdaddy.adventofcode.year_%s.Day%s", year, day));
                            Constructor<Day> constructor = dayClass.getConstructor();
                            Day dayToRun = constructor.newInstance();
                            dayToRun.run();
                        } catch (Exception e) {
                            System.out.println("Not a valid day to run.");
                        }
                    }
                }
            }
        }
    }
}
