package com.tipdaddy.adventofcode.util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class FileReader {

    public static String readSingleLineFile(final String year, final String day) {

        return readMultiLineFile(year, day).get(0);
    }

    public static List<String> readMultiLineFile(final String year, final String day) {

        final String fileName = String.format("%s/day%s.txt", year, day);
        final ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        final URL url = classloader.getResource(fileName);
        if (url == null) {
            throw new IllegalArgumentException(String.format("file: %s not found.", fileName));
        } else {
            List<String> lines = Collections.emptyList();
            try {
                lines = Files.readAllLines(Paths.get(url.toURI()));
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
            return lines;
        }
    }
}
