package com.tipdaddy.adventofcode.year_2015;

import org.apache.commons.codec.digest.DigestUtils;

public class Day4 {

    private static final String SECRET = "ckczppom";

    public static String getHash(final Integer n) {

        return DigestUtils.md5Hex(SECRET + n);
    }

    public static void main(String[] args) {

        int n = 0;
        // Part 2 is 6 0's, just change below.
        final String desiredPrefix = "00000";
        while (true) {

            String hash = getHash(n);
            if (hash.startsWith(desiredPrefix)) {
                System.out.println(n);
                break;
            } else {
                n++;
            }
        }
    }
}
