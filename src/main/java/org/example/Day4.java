package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Day4 implements Part {
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
        String file = "src/main/resources/advent_of_code_4.txt";
        List<String> puzzle = readPuzzle(file);
        List<String> puzzle2 = new ArrayList<>();

        int originalSize = puzzle.getFirst().length();
        puzzle2.addFirst(".".repeat(originalSize + 2));
        int lineNb = 0;
        for (String line : puzzle) {
            puzzle2.add("." + line + ".");
            lineNb++;
        }
        puzzle2.add(".".repeat(originalSize + 2));

        char[][] array = new char[lineNb + 2][];
        for (int i = 0; i < puzzle2.size(); i++) {
            array[i] = puzzle2.get(i).toCharArray();
        }

        int count = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] == 'X') {
                    if (array[i][j + 1] == 'M' && array[i][j + 2] == 'A' && array[i][j + 3] == 'S') {
                        count++;
                    }
                    if (array[i][j - 1] == 'M' && array[i][j - 2] == 'A' && array[i][j - 3] == 'S') {
                        count++;
                    }
                    if (array[i + 1][j] == 'M' && array[i + 2][j] == 'A' && array[i + 3][j] == 'S') {
                        count++;
                    }
                    if (array[i - 1][j] == 'M' && array[i - 2][j] == 'A' && array[i - 3][j] == 'S') {
                        count++;
                    }
                    if (array[i - 1][j - 1] == 'M' && array[i - 2][j - 2] == 'A' && array[i - 3][j - 3] == 'S') {
                        count++;
                    }
                    if (array[i + 1][j + 1] == 'M' && array[i + 2][j + 2] == 'A' && array[i + 3][j + 3] == 'S') {
                        count++;
                    }
                    if (array[i - 1][j + 1] == 'M' && array[i - 2][j + 2] == 'A' && array[i - 3][j + 3] == 'S') {
                        count++;
                    }
                    if (array[i + 1][j - 1] == 'M' && array[i + 2][j - 2] == 'A' && array[i + 3][j - 3] == 'S') {
                        count++;
                    }
                }
            }
        }
        System.out.println(count);
    }

    public void part2() {
        String file = "src/main/resources/advent_of_code_4_2.txt";
        List<String> puzzle = readPuzzle(file);
        List<String> puzzle2 = new ArrayList<>();

        int originalSize = puzzle.getFirst().length();
        puzzle2.addFirst(".".repeat(originalSize + 2));
        int lineNb = 0;
        for (String line : puzzle) {
            puzzle2.add("." + line + ".");
            lineNb++;
        }
        puzzle2.add(".".repeat(originalSize + 2));

        char[][] array = new char[lineNb + 2][];
        for (int i = 0; i < puzzle2.size(); i++) {
            array[i] = puzzle2.get(i).toCharArray();
        }

        int count = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] == 'A') {
                    if (((array[i - 1][j - 1] == 'M' && array[i + 1][j + 1] == 'S')
                            || (array[i - 1][j - 1] == 'S' && array[i + 1][j + 1] == 'M'))
                            && ((array[i - 1][j + 1] == 'S' && array[i + 1][j - 1] == 'M')
                            || (array[i - 1][j + 1] == 'M' && array[i + 1][j - 1] == 'S'))) {
                        count++;
                    }
                }
            }
        }
        System.out.println(count);
    }
}
