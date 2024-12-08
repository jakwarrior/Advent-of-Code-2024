package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 implements Part {

    private static final String MUL_PATTERN = "mul\\(\\s*(\\d+)\\s*,\\s*(\\d+)\\s*\\)";

    public int analyzePart1(String memory) {
        Pattern pattern = Pattern.compile(MUL_PATTERN);
        Matcher matcher = pattern.matcher(memory);
        int sum = 0;
        while (matcher.find()) {
            int num1 = Integer.parseInt(matcher.group(1));
            int num2 = Integer.parseInt(matcher.group(2));
            System.out.println(num1 + " " + num2);
            sum += num1 * num2;
        }
        return sum;
    }

    public void part1() {
        String file = "src/main/resources/advent_of_code_3.txt";

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line;
            int count = 0;

            while ((line = reader.readLine()) != null) {
                count += analyzePart1(line);
            }

            System.out.println(count);
            reader.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int analyzePart2(List<String> instructions) {
        List<String[]> fileItems;
        String regex = null;
        Pattern pattern = null;
        Matcher matcher = null;
        int counter = 0;
        boolean doFlag = true;
        regex = "mul\\(\\d{1,3},\\d{1,3}\\)|do\\(\\)|don't\\(\\)";
        pattern = Pattern.compile(regex);
        for (String item : instructions) {
            matcher = pattern.matcher(item);
            while (matcher.find()) {
                String match = matcher.group();
                if (match.equals("do()")) {
                    doFlag = true;
                } else if (match.equals("don't()")) {
                    doFlag = false;
                } else {
                    if (doFlag) {
                        String numbers = match.substring(4, match.length() - 1);
                        String[] splitNumbers = numbers.split(",");
                        int first = Integer.parseInt(splitNumbers[0]);
                        int second = Integer.parseInt(splitNumbers[1]);
                        counter += first * second;
                    }
                }
            }
        }
        return counter;
    }


    public void part2() {
        String file = "src/main/resources/advent_of_code_3_2.txt";

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line;
            List<String> input = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                input.add(line);
            }

            System.out.println(analyzePart2(input));
            reader.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
