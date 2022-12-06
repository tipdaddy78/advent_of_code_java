package com.tipdaddy.adventofcode.year_2022;

import com.tipdaddy.adventofcode.util.Day;
import com.tipdaddy.adventofcode.util.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day6 implements Day {

    @Override
    public void run() {

        final String signal = FileReader.readSingleLineFile("2022", "6");
        final List<String> currentPacket = new ArrayList<>();
        final List<String> currentMessage = new ArrayList<>();
        boolean givenPart1 = false;
        for (int i = 0; i < signal.length(); i++) {
            String cur = ((Character) signal.charAt(i)).toString();
            currentPacket.add(cur);
            currentMessage.add(cur);
            if (currentPacket.size() > 4) {
                currentPacket.remove(0);
            }
            if (currentMessage.size() > 14) {
                currentMessage.remove(0);
            }
            if (currentPacket.size() == 4 && !givenPart1) {
                if (isSubsetUnique(currentPacket)) {
                    System.out.printf("Part 1 is %d\n", i + 1);
                    givenPart1 = true;
                }
            }
            if (currentMessage.size() == 14) {
                if (isSubsetUnique(currentMessage)) {
                    System.out.printf("Part 2 is %d\n", i + 1);
                    break;
                }
            }
        }
    }

    private boolean isSubsetUnique(List<String> subSet) {

        Set<String> subSetSet = new HashSet<>(subSet);
        return subSetSet.size() == subSet.size();
    }
}
