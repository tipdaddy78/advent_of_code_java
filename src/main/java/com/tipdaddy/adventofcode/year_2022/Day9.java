package com.tipdaddy.adventofcode.year_2022;

import com.tipdaddy.adventofcode.util.Day;
import com.tipdaddy.adventofcode.util.FileReader;
import com.tipdaddy.adventofcode.util.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

public class Day9 implements Day {

    @Override
    public void run() {

        final List<String> directions = FileReader.readMultiLineFile("2022", "9");
        //  Part 1 variables.
        final Point head = new Point(0, 0);
        final Point tail = new Point(0, 0);

        // Part 2 variables
        final List<Point> part2PointList = new ArrayList<>(10);
        IntStream.range(0, 10).forEach(i -> {
          part2PointList.add(new Point(0, 0));
        });

        // To keep track of where tail has been.
        final Set<Point> tailVisited = new HashSet<>();
        final Set<Point> tailVisited2 = new HashSet<>();
        tailVisited.add(new Point(tail.getX(), tail.getY()));
        tailVisited2.add(new Point(tail.getX(), tail.getY()));

        for (String direction : directions) {
            final String[] parts = direction.split(" ");
            final int steps = Integer.parseInt(parts[1]);
            IntStream.range(0, steps).forEach(i -> {
               switch (parts[0]) {
                   case "L" -> {
                       head.setX(head.getX() - 1);
                       part2PointList.get(0).setX(part2PointList.get(0).getX() - 1);
                   }
                   case "R" -> {
                       head.setX(head.getX() + 1);
                       part2PointList.get(0).setX(part2PointList.get(0).getX() + 1);
                   }
                   case "D" -> {
                       head.setY(head.getY() - 1);
                       part2PointList.get(0).setY(part2PointList.get(0).getY() - 1);
                   }
                   case "U" -> {
                       head.setY(head.getY() + 1);
                       part2PointList.get(0).setY(part2PointList.get(0).getY() + 1);
                   }
                   default -> throw new IllegalArgumentException();
               }
               moveTail(head, tail);
               tailVisited.add(new Point(tail.getX(), tail.getY()));
               for (int n = 1; n < part2PointList.size(); n++) {
                   final Point simulatedTail = part2PointList.get(n);
                   final Point simulatedHead = part2PointList.get(n - 1);
                   moveTail(simulatedHead, simulatedTail);
               }
               final Point simulatedTail = part2PointList.get(part2PointList.size() - 1);
               tailVisited2.add(new Point(simulatedTail.getX(), simulatedTail.getY()));
            });
        }
        System.out.printf("Part 1 is %d\n", tailVisited.size());
        System.out.printf("Part 2 is %d\n", tailVisited2.size());
    }

    /**
     * This method moves the tail closer to the head according to rules.
     * It needs to always be within 1 coordinate away. That can be diagonal.
     * If necessary, it will change the x and/or y value of the tail to move it closer.
     * @param head a Point object representing the head
     * @param tail a Point object representing the tail.
     */
    private void moveTail(final Point head, final Point tail) {

        if (head.getY() == tail.getY()) {
            final int diff = Math.abs(head.getX() - tail.getX());
            if (diff > 1) {
                if (head.getX() > tail.getX()) {
                    tail.setX(tail.getX() + 1);
                } else {
                    tail.setX(tail.getX() - 1);
                }
            }
        } else if (head.getX() == tail.getX()) {
            final int diff = Math.abs(head.getY() - tail.getY());
            if (diff > 1) {
                if (head.getY() > tail.getY()) {
                    tail.setY(tail.getY() + 1);
                } else {
                    tail.setY(tail.getY() - 1);
                }
            }
        } else { // Diagonal case
            final int xDiff = Math.abs(head.getX() - tail.getX());
            final int yDiff = Math.abs(head.getY() - tail.getY());

            if (xDiff != 1 || yDiff != 1) {
                if (head.getY() > tail.getY()) {
                    tail.setY(tail.getY() + 1);
                } else {
                    tail.setY(tail.getY() - 1);
                }

                if (head.getX() > tail.getX()) {
                    tail.setX(tail.getX() + 1);
                } else {
                    tail.setX(tail.getX() - 1);
                }
            }
        }
    }
}
