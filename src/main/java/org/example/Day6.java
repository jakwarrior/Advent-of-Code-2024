package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day6 implements Part {
    char[][] clone;
    boolean isLoop = false;
    private boolean exit = false;

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
        String file = "src/main/resources/advent_of_code_6.txt";
        List<String> puzzle = readPuzzle(file);

        char[][] array = new char[puzzle.size()][];
        for (int i = 0; i < puzzle.size(); i++) {
            array[i] = puzzle.get(i).toCharArray();
        }

        while (!exit) {
            for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < array[i].length; j++) {
                    if (array[i][j] == '^' || array[i][j] == '>' || array[i][j] == '<' || array[i][j] == 'v') {
                        if (i == 0 || i == array.length - 1 || j == 0 || j == array[i].length - 1) {
                            exit = true;
                            array[i][j] = 'X';
                        } else {
                            move(array, i, j);
                        }
                    }
                }
            }
        }

/*        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }*/

        int count = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] == 'X') {
                    count++;
                }
            }
        }
        System.out.println(count);
    }

    public void move(char[][] array, int line, int col) {
        if (array[line][col] == '^') {
            if (array[line - 1][col] == '#' || array[line - 1][col] == 'O') {
                array[line][col] = '>';
            } else {
                array[line - 1][col] = '^';
                array[line][col] = 'X';
            }
        } else if (array[line][col] == '>') {
            if (array[line][col + 1] == '#' || array[line][col + 1] == 'O') {
                array[line][col] = 'v';
            } else {
                array[line][col + 1] = '>';
                array[line][col] = 'X';
            }
        } else if (array[line][col] == 'v') {
            if (array[line + 1][col] == '#' || array[line + 1][col] == 'O') {
                array[line][col] = '<';
            } else {
                array[line + 1][col] = 'v';
                array[line][col] = 'X';
            }
        } else if (array[line][col] == '<') {
            if (array[line][col - 1] == '#' || array[line][col - 1] == 'O') {
                array[line][col] = '^';
            } else {
                array[line][col - 1] = '<';
                array[line][col] = 'X';
            }
        }

/*        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("###############################");
        System.out.println();*/
    }

    public void part2() {
        String file = "src/main/resources/advent_of_code_6_2.txt";
        List<String> puzzle = readPuzzle(file);

        char[][] array = new char[puzzle.size()][];
        char[][] original = new char[puzzle.size()][];
        for (int i = 0; i < puzzle.size(); i++) {
            array[i] = puzzle.get(i).toCharArray();
            original[i] = List.copyOf(puzzle).get(i).toCharArray();
        }

        int count = 0;
        for (int i = 0; i < original.length; i++) {
            for (int j = 0; j < original[i].length; j++) {
                if (original[i][j] != '^' && original[i][j] != '>' && original[i][j] != 'v' && original[i][j] != '<' && original[i][j] != '#') {
                    if (isLoop(original, i, j)) {
                        array[i][j] = 'O';
                        count++;
                    }
                }
            }
        }

        System.out.println(count);
    }

    private boolean isLoop(char[][] array, int line, int col) {
        isLoop = true;
//        for (int i = 0; i < array.length; i++) {
//            for (int j = 0; j < array[i].length; j++) {
//                System.out.print(array[i][j] + " ");
//            }
//            System.out.println();
//        }
//        System.out.println();

        clone = new char[array.length][];
        for (int i = 0; i < array.length; i++) {
            clone[i] = Arrays.copyOf(array[i], array[i].length);
        }
        clone[line][col] = 'O';
        System.out.println("Current : " + line + " " + col);

        long start = System.currentTimeMillis();
        long end = start + 500;
        LocalDateTime then = LocalDateTime.now();
        exit = false;
        while (!exit) {
            if (ChronoUnit.SECONDS.between(then, LocalDateTime.now()) >= 1) break;
            for (int i = 0; i < clone.length; i++) {
                for (int j = 0; j < clone[i].length; j++) {
                    if (clone[i][j] == '^' || clone[i][j] == '>' || clone[i][j] == '<' || clone[i][j] == 'v') {
                        if (i == 0 || i == clone.length - 1 || j == 0 || j == clone[i].length - 1) {
                            exit = true;
                            isLoop = false;
                            clone[i][j] = 'X';
                            break;
                        } else {
                            move(clone, i, j);
                        }
                    }
                }
            }
        }

//        for (int i = 0; i < clone.length; i++) {
//            for (int j = 0; j < clone[i].length; j++) {
//                System.out.print(clone[i][j] + " ");
//            }
//            System.out.println();
//        }
//        System.out.println();
        System.out.println(isLoop);
//        System.out.println("###############################");
//        System.out.println();


        return isLoop;
    }
}
