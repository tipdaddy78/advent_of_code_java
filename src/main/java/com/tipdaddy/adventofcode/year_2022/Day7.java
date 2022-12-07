package com.tipdaddy.adventofcode.year_2022;

import com.tipdaddy.adventofcode.util.Day;
import com.tipdaddy.adventofcode.util.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day7 implements Day {

    final List<Long> directorySizes = new ArrayList<>();

    @Override
    public void run() {

        final List<String> lines = FileReader.readMultiLineFile("2022", "7");

        final TreeNode rootNode = new TreeNode("/", null, new HashMap<>(), 0L, false);
        TreeNode curNode = rootNode;
        for (String line : lines) {
//            System.out.printf("Current line is \"%s\"\n", line);
            final String[] lineParts = line.split(" ");
            // Executing command. Only care to do something if it's a "$ cd"
            if (line.startsWith("$")) {
                // Changing Directory
                if (line.startsWith("$ cd")) {

                    if (lineParts[2].equals("..")) { // Moving up tree
                        curNode = curNode.parent();
                    } else { // Moving down
                        // First time seeing key. Create it and add references for current directory.
                        if (!curNode.children().containsKey(lineParts[2])) {
                            final TreeNode childNode = new TreeNode(lineParts[2], curNode, new HashMap<>(), 0L, false);
                            curNode.addChild(lineParts[2], childNode);
                        }
                        curNode = curNode.children().get(lineParts[2]);
                    }
                }
            } else { // Listing files for current dir

                if (lineParts[0].equals("dir")) {

                    // New dir under this one.
                    if (!curNode.children().containsKey(lineParts[1])) {
                        final TreeNode childNode = new TreeNode(lineParts[1], curNode, new HashMap<>(), 0L, false);
                        curNode.addChild(lineParts[1], childNode);
                    }
                } else {

                    // Dealing with a file.
                    if (!curNode.children().containsKey(lineParts[1])) {
                        final TreeNode childNode = new TreeNode(lineParts[1], curNode, null, Long.parseLong(lineParts[0]), true);
                        curNode.addChild(lineParts[1], childNode);
                    }
                }
            }
        }

        // Traverse tree and get sizes.
        final long rootSize = getDirectorySize(rootNode);
        directorySizes.add(rootSize);
        long sizeSum = 0L;
        final long totalSpace = 70000000;
        final long currentUnused = totalSpace - rootSize;
        final long desiredUnused = 30000000;
        List<Long> deletionOptions = new ArrayList<>();
        for (Long dirSize : directorySizes) {
            if (dirSize <= 100000) {
                sizeSum += dirSize;
            }
            if (dirSize + currentUnused >= desiredUnused) {
                deletionOptions.add(dirSize);
            }
        }
        Collections.sort(deletionOptions);
        System.out.printf("Part 1 is %d\n", sizeSum);
        System.out.printf("Part 2 is %d\n", deletionOptions.get(0));
    }

    private record TreeNode(String id, TreeNode parent, Map<String, TreeNode> children, long size, boolean isFile) {

        public void addChild(final String childId, final TreeNode child) {

            this.children.put(childId, child);
        }
    }

    private long getDirectorySize(TreeNode curNode) {

        long size = 0L;
        for (TreeNode childNode : curNode.children().values()) {
            if (childNode.isFile()) {
                size += childNode.size();
            } else {
                final long childSize = getDirectorySize(childNode);
                directorySizes.add(childSize);
                size += childSize;
            }
        }
        return size;

    }
}
