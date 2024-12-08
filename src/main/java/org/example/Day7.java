package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Day7 implements Part {
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

    private static long getCompute(String combinaison) {
        long compute = 0;
        //System.out.println(combinaison);

        var split = combinaison.split(" ");
        for (int i = 2; i < split.length; i = i + 2) {
            long a;
            if (i == 2) {
                a = Long.parseLong(split[i - 2]);
            } else {
                a = compute;
            }

            long b = Long.parseLong(split[i]);
            String operator = split[i - 1];
            //System.out.println(a + " " + b + " " + operator);

            if (operator.equals("+")) {
                compute = a + b;
            }
            if (operator.equals("*")) {
                compute = a * b;
            }
        }
        return compute;
    }

    public void part1() {
        String file = "src/main/resources/advent_of_code_7.txt";
        List<String> puzzle = readPuzzle(file);
        List<String> operators = List.of("+", "*");
        int count = 0;

        for (String line : puzzle) {
            var split = line.split(": ");
            String key = split[0];
            List<String> value = Arrays.asList(split[1].split(" "));
            long result = Long.parseLong(key);

            String[] comb = Arrays.stream(value.toArray())
                    .map(e -> Arrays.stream(operators.toArray())
                            // append each substring
                            // with possible combinations
                            .map(v -> e + " " + v + " ")
                            // return Stream<String[]>
                            .toArray(String[]::new))
                    // reduce stream of arrays to a single array
                    // by sequentially multiplying array pairs
                    .reduce((arr1, arr2) -> Arrays.stream(arr1)
                            .flatMap(str1 -> Arrays.stream(arr2)
                                    .map(str2 -> str1 + str2))
                            .toArray(String[]::new))
                    .orElse(null);

            //Arrays.stream(comb).forEach(System.out::println);
            Set<String> trueComb = new HashSet<>();
            for (String combinaison : comb) {
                combinaison = combinaison.substring(0, combinaison.length() - 2);
                trueComb.add(combinaison);
            }

            for (String combinaison : trueComb) {
                long compute = getCompute(combinaison);
                System.out.println(compute);

                if (compute == result) {
                    count += result;
                    System.out.println("###################################");
                    break;
                }
            }
        }
        System.out.println(count);
    }

    public void part2() {
        String file = "src/main/resources/advent_of_code_1_2.txt";
        List<String> puzzle = readPuzzle(file);
    }
}
