package com.tipdaddy.adventofcode.year_2015;

import com.tipdaddy.adventofcode.util.Day;
import org.apache.commons.codec.digest.DigestUtils;

public class Day4 implements Day {

    private static final String SECRET = "ckczppom";

    public String getHash(final Integer n) {

        return DigestUtils.md5Hex(SECRET + n);
    }

    @Override
    public void run() {

        int n = 0;
        final String desiredPrefix1 = "00000";
        final String desiredPrefix2 = "000000";
        boolean gavePart1 = false;
        while (true) {

            String hash = getHash(n);
            if (hash.startsWith(desiredPrefix1) && !gavePart1) {
                gavePart1 = true;
                System.out.printf("Part 1: %d\n", n);
            } else if (hash.startsWith(desiredPrefix2)) {
                System.out.printf("Part 2: %d\n", n);
                break;
            }else {
                n++;
            }
        }
    }
}
