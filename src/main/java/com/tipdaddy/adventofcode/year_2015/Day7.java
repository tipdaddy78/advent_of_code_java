package com.tipdaddy.adventofcode.year_2015;

import com.tipdaddy.adventofcode.util.Day;
import com.tipdaddy.adventofcode.util.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.function.BiFunction;

public class Day7 implements Day {

    @Override
    public void run() {

        List<String> directions = FileReader.readMultiLineFile("2015", "7");
        Map<String, List<String>> directionMap = new HashMap<>();
        Map<String, Integer> wires = new HashMap<>();
        Queue<String> priorityDirections = new PriorityQueue<>();
        Queue<String> dQueue = new PriorityQueue<>();
        directions.forEach(d -> {
            dQueue.add(d);
            String[] dParts = d.split(" ");
            if (d.contains("SHIFT")) {
                directionMap.put(dParts[0], directionMap.compute(dParts[0], updateDirectionMap));
            } else if (d.contains("AND") || d.contains("OR")) {
                try {
                    Integer num = Integer.parseInt(dParts[0]);
                } catch (NumberFormatException e) {
                    directionMap.put(dParts[0], directionMap.compute(dParts[0], updateDirectionMap));
                } finally {
                    directionMap.put(dParts[2], directionMap.compute(dParts[2], updateDirectionMap));
                }
            } else if (d.contains("NOT")) {
                directionMap.put(dParts[1], directionMap.compute(dParts[1], updateDirectionMap));
            } else {
                // THERE'S A VALUE
                dQueue.remove(d);
                priorityDirections.add(d);
            }
        });

        while(!dQueue.isEmpty()) {
            String direction = dQueue.remove();
            if (!priorityDirections.isEmpty()) {

            }

            String[] dParts = direction.split(" ");
            // LSHIFT and RSHIFT
            if (direction.contains("SHIFT")) {
                // Check if computation can be done yet.
                if (!wires.containsKey(dParts[0])) {
                    dQueue.add(direction);
                    continue;
                }
                if (direction.contains("RSHIFT")) {
                    rightShift(dParts[0], Integer.parseInt(dParts[2]), dParts[4], wires);
                } else {
                    leftShift(dParts[0], Integer.parseInt(dParts[2]), dParts[4], wires);
                }
            } else if (direction.contains("AND") || direction.contains("OR")) {
                try {
                    int num = Integer.parseInt(dParts[0]);
                    if (!wires.containsKey(dParts[2])) {
                        dQueue.add(direction);
                        continue;
                    }
                    andWires(num, dParts[2], dParts[4], wires);

                } catch (NumberFormatException e) {
                    if (!wires.containsKey(dParts[0]) && !wires.containsKey(dParts[2])) {
                        dQueue.add(direction);
                        continue;
                    }
                }
                if (direction.contains("AND")) {
                    andWires(dParts[0], dParts[2], dParts[4], wires);
                } else {
                    orWires(dParts[0], dParts[2], dParts[4], wires);
                }
            } else if (direction.contains("NOT")) {
                if (!wires.containsKey(dParts[1])) {
                    dQueue.add(direction);
                    continue;
                }
                complement(dParts[1], dParts[3], wires);
            } else {
                // THERE'S A VALUE
                wires.put(dParts[2], Integer.parseInt(dParts[0]));
            }
        }
        System.out.println(wires.get("a"));
    }

    private void andWires(String wireA, String wireB, String dest, Map<String, Integer> wires) {
        wires.put(dest, wires.get(wireA) & wires.get(wireB));
    }

    private void andWires(int valueA, String wireB, String dest, Map<String, Integer> wires) {
        wires.put(dest, valueA & wires.get(wireB));
    }

    private void orWires(String wireA, String wireB, String dest, Map<String, Integer> wires) {
        wires.put(dest, wires.get(wireA) | wires.get(wireB));
    }

    private void leftShift(String wire, int dist, String dest, Map<String, Integer> wires) {
        wires.put(dest, wires.get(wire) << dist);
    }

    private void rightShift(String wire, int dist, String dest, Map<String, Integer> wires) {
        wires.put(dest, wires.get(wire) >> dist);
    }

    private void complement(String wire, String dest, Map<String, Integer> wires) {
        wires.put(dest, ~wires.get(wire));
    }

    private final BiFunction<String, List<String>, List<String>> updateDirectionMap = (k, v) -> {
        if (v == null) {
            v = new ArrayList<>();
        }
        v.add(k);
        return v;
    };


}
