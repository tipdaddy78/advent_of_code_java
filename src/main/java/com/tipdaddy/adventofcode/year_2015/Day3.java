package com.tipdaddy.adventofcode.year_2015;

import com.tipdaddy.adventofcode.util.FileReader;
import com.tipdaddy.adventofcode.util.Point;
import java.util.HashSet;
import java.util.Set;

public class Day3 {

    public static void main(String[] args) {

        final String directions = FileReader.readSingleLineFile("2015", "3");
        int x = 0;
        int y = 0;
        int x2 = 0;
        int y2 = 0;
        int roboX = 0;
        int roboY = 0;
        boolean roboSanta = false;

        Set<Point> houses1 = new HashSet<>();
        Set<Point> houses2 = new HashSet<>();
        for (char c : directions.toCharArray()) {
            // Add location to set.
            houses1.add(new Point(x, y));
            houses2.add(new Point(x2, y2));
            houses2.add(new Point(roboX, roboY));

            int xChange = 0;
            int yChange = 0;
            switch(c) {
                case '>' -> xChange = 1;
                case '<' -> xChange = -1;
                case '^' -> yChange = 1;
                case 'v' -> yChange  = -1;
            }

            x += xChange;
            y += yChange;
            if (roboSanta) {
                roboX += xChange;
                roboY += yChange;
            } else {
                x2 += xChange;
                y2 += yChange;
            }
            roboSanta = !roboSanta;
        }

        System.out.println(houses1.size());
        System.out.println(houses2.size());
    }
}
