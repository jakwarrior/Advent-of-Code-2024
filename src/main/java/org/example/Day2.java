package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Day2 implements Part {
    public void part1() {
        String file = "src/main/resources/advent_of_code_2.txt";

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line;
            List<List<Integer>> puzzle = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                puzzle.add(Stream.of(line.split(" ")).map(Integer::parseInt).toList());
            }

            int count = 0;
            for (List<Integer> report : puzzle) {
                if (isSafe(report)) {
                    System.out.println(report);
                    count++;
                }
            }
            System.out.println(count);

            reader.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isSafe(List<Integer> report) {
        boolean isIncreasing = report.get(1) > report.get(0);
        boolean isEqual = report.get(1) - report.get(0) == 0;
        for (int i = 1; i < report.size(); i++) {
            if (isEqual) {
                return false;
            } else if (isIncreasing) {
                if (report.get(i) - report.get(i - 1) < 1 || report.get(i) - report.get(i - 1) > 3)
                    return false;
            } else {
                if (report.get(i - 1) - report.get(i) < 1 || report.get(i - 1) - report.get(i) > 3)
                    return false;
            }
            isIncreasing = report.get(i) > report.get(i - 1);
            isEqual = report.get(i) - report.get(i - 1) == 0;
        }
        return true;
    }

    public void part2() {
        String file = "src/main/resources/advent_of_code_2_2.txt";

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line;
            List<List<Integer>> puzzle = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                puzzle.add(Stream.of(line.split(" ")).map(Integer::parseInt).toList());
            }

            int count = 0;
            for (List<Integer> report : puzzle) {
                if (isSafe(report)) {
                    count++;
                } else {
                    for (int i = 0; i < report.size(); i++) {
                        List<Integer> newStrs = new ArrayList<>(report);
                        newStrs.remove(i);
                        if (isSafe(newStrs)) {
                            count++;
                            break;
                        }
                    }
                }
            }
            System.out.println(count);

            reader.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
