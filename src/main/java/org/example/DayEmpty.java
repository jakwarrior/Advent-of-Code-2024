package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class DayEmpty implements Part {
    public static List<String> readPuzzle(String file) {
        List<String> puzzle = new ArrayList<>();

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line;

            while ((line = reader.readLine()) != null) {
                puzzle.add(line);
            }

            reader.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return puzzle;
    }

    public void part1() {
        String file = "src/main/resources/advent_of_code_1.txt";
        List<String> puzzle = readPuzzle(file);
    }

    public void part2() {
        String file = "src/main/resources/advent_of_code_1_2.txt";
        List<String> puzzle = readPuzzle(file);
    }
}
