package com.tipdaddy.adventofcode.year_2022;

import com.tipdaddy.adventofcode.util.Day;
import com.tipdaddy.adventofcode.util.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Day8 implements Day {

    int numVisibleTrees = 0;

    @Override
    public void run() {

        final List<String> treeLines = FileReader.readMultiLineFile("2022", "8");
        final int yMax = treeLines.size();
        final int xMax = treeLines.get(0).length();
        final List<List<Integer>> visibleTrees = new ArrayList<>(yMax);
        IntStream.range(0, yMax).forEach(y -> {
            Integer[] treeRow = new Integer[xMax];
            Arrays.fill(treeRow, null);
            visibleTrees.add(Arrays.asList(treeRow));
        });
        for (int x = 0; x < xMax; x++) {
            checkFromTop(x, 0, treeLines, visibleTrees, false);
            checkFromBottom(x, yMax - 1, treeLines, visibleTrees, false);
        }

        for (int y = 0; y < yMax; y++) {
            checkFromLeft(0, y, treeLines, visibleTrees, false);
            checkFromRight(xMax - 1, y, treeLines, visibleTrees, false);
        }

        System.out.printf("Part 1 is %d\n", numVisibleTrees);

        // Now check every spot for optimal treehouse placement
        int maxScore = 0;
        for (int y = 0; y < yMax; y++) {
            for (int x = 0; x < xMax; x++) {
                final int leftVisibilty = checkFromLeft(x, y, treeLines, visibleTrees, true);
                final int rightVisibility = checkFromRight(x, y, treeLines, visibleTrees, true);
                final int topVisiblity = checkFromTop(x, y, treeLines, visibleTrees, true);
                final int bottomVisibility = checkFromBottom(x, y, treeLines, visibleTrees, true);
                final int curScore = leftVisibilty * rightVisibility * topVisiblity * bottomVisibility;
                maxScore = Math.max(curScore, maxScore);
            }
        }

        System.out.printf("Part 2 is %d\n", maxScore);
    }

    private int checkFromLeft(final int startX, final int y, final List<String> treeLines, final List<List<Integer>> visibleTrees, final boolean part2) {

        final String treeLine = treeLines.get(y);
        int curMax = -1;
        int visible = 0;
        int startTree = Integer.parseInt(((Character)treeLine.charAt(startX)).toString());
        for (int x = startX + 1; x < treeLine.length(); x++) {
            if (curMax == 9 || (part2 && startTree <= curMax)) { // No trees will be visible past this.
                break;
            }
            int cur = Integer.parseInt(((Character)treeLine.charAt(x)).toString());
            boolean alreadyVisible = visibleTrees.get(y).get(x) != null;
            if ((!alreadyVisible && cur > curMax) || part2) { // Tree is visible.
                visibleTrees.get(y).set(x, cur);
                visible++;
                numVisibleTrees++;
            }
            curMax = Math.max(curMax, cur);
        }
        return visible;
    }

    private int checkFromRight(final int startX, final int y, final List<String> treeLines, final List<List<Integer>> visibleTrees, final boolean part2) {

        final String treeLine = treeLines.get(y);
        int curMax = -1;
        int visible = 0;
        int startTree = Integer.parseInt(((Character)treeLine.charAt(startX)).toString());
        for (int x = startX - 1; x >= 0; x--) {
            if (curMax == 9 || (part2 && startTree <= curMax)) { // No trees will be visible past this.
                break;
            }
            int cur = Integer.parseInt(((Character)treeLine.charAt(x)).toString());
            boolean alreadyVisible = visibleTrees.get(y).get(x) != null;
            if ((!alreadyVisible && cur > curMax) || part2) { // Tree is visible.
                visibleTrees.get(y).set(x, cur);
                visible++;
                numVisibleTrees++;
            }
            curMax = Math.max(curMax, cur);
        }
        return visible;
    }

    private int checkFromTop(final int x, final int startY, final List<String> treeLines, final List<List<Integer>> visibleTrees, final boolean part2) {

        int curMax = -1;
        int visible = 0;
        int startTree = Integer.parseInt(((Character)treeLines.get(startY).charAt(x)).toString());
        for (int y = startY + 1; y < treeLines.size(); y++) {
            if (curMax == 9 || (part2 && startTree <= curMax)) { // No trees will be visible past this.
                break;
            }
            int cur = Integer.parseInt(((Character)treeLines.get(y).charAt(x)).toString());
            boolean alreadyVisible = visibleTrees.get(y).get(x) != null;
            if ((!alreadyVisible && cur > curMax) || part2) { // Tree is visible.
                visibleTrees.get(y).set(x, cur);
                visible++;
                numVisibleTrees++;
            }
            curMax = Math.max(curMax, cur);
        }
        return visible;
    }

    private int checkFromBottom(final int x, final int startY, final List<String> treeLines, final List<List<Integer>> visibleTrees, final boolean part2) {

        int curMax = -1;
        int visible = 0;
        int startTree = Integer.parseInt(((Character)treeLines.get(startY).charAt(x)).toString());
        for (int y = startY - 1; y >= 0; y--) {
            if (curMax == 9 || (part2 && startTree <= curMax)) { // No trees will be visible past this.
                break;
            }
            int cur = Integer.parseInt(((Character)treeLines.get(y).charAt(x)).toString());
            boolean alreadyVisible = visibleTrees.get(y).get(x) != null;
            if ((!alreadyVisible && cur > curMax) || part2) { // Tree is visible.
                visibleTrees.get(y).set(x, cur);
                visible++;
                numVisibleTrees++;
            }
            curMax = Math.max(curMax, cur);
        }
        return visible;
    }
}
