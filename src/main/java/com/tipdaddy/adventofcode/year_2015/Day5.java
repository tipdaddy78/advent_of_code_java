package com.tipdaddy.adventofcode.year_2015;

import com.tipdaddy.adventofcode.util.FileReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Day5 {

    public static final Set<String> DISALLOWED = Set.of("ab", "cd", "pq", "xy");
    public static final Set<Character> VOWELS = Set.of('a', 'e', 'i', 'o', 'u');

    public static boolean hasThreeVowels(final String word) {

        return word
                .chars()
                .filter(c -> VOWELS.contains((char) c))
                .count() >= 3;
    }

    public static boolean hasDisallowed(final String word) {

        return DISALLOWED.stream()
                .anyMatch(word::contains);
    }

    public static boolean hasDouble(final String word) {

        for (int i = 0; i < word.length(); i++) {
            // No next character to check
            if (i == word.length() - 1) {
                return false;
            }
            if (word.charAt(i) == word.charAt(i + 1)) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasAtLeastTwoPairsNotOverlapping(final String word) {

        for (int i = 0; i < word.length(); i++) {
            // No next characters to check
            if (i == word.length() - 2) {
                return false;
            }
            final String pair = word.substring(i, i+2);
            if (word.substring(i+2).contains(pair)) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasSandwich(final String word) {

        for (int i = 0; i < word.length(); i++) {
            // No next characters to check
            if (i == word.length() - 2) {
                return false;
            }
            if (word.charAt(i) == word.charAt(i + 2)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {

        final List<String> lines = FileReader.readMultiLineFile("2015", "5");

        int niceCount1 = (int) lines.stream().filter(word -> hasThreeVowels(word) && !hasDisallowed(word) && hasDouble(word)).count();
        System.out.println(niceCount1);

        int niceCount2 = (int) lines.stream().filter(word -> hasAtLeastTwoPairsNotOverlapping(word) && hasSandwich(word)).count();
        System.out.println(niceCount2);
    }
}
