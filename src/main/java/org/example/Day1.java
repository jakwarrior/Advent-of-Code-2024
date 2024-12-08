package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Day1 implements Part {
    public void part1() {
        String file = "src/main/resources/advent_of_code_1.txt";

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line;
            List<Integer> list1 = new ArrayList<>(), list2 = new ArrayList<>();
            var total = 0;

            while ((line = reader.readLine()) != null) {
                var split = line.split("   ");

                list1.add(Integer.parseInt(split[0]));
                list2.add(Integer.parseInt(split[1]));
            }

            Collections.sort(list1);
            Collections.sort(list2);

            System.out.println("Total : " + calculateDistance(list1, list2));

            reader.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int calculateDistance(List<Integer> list1, List<Integer> list2) {
        int distance = 0;
        Iterator<Integer> it1 = list1.iterator();
        Iterator<Integer> it2 = list2.iterator();
        while (it1.hasNext() && it2.hasNext()) {
            distance += Math.abs(it1.next() - it2.next());
        }
        return distance;
    }

    public void part2() {
        String file = "src/main/resources/advent_of_code_1_2.txt";

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line;
            List<Integer> list1 = new ArrayList<>(), list2 = new ArrayList<>();
            var total = 0;

            while ((line = reader.readLine()) != null) {
                var split = line.split("   ");

                list1.add(Integer.parseInt(split[0]));
                list2.add(Integer.parseInt(split[1]));
            }

            Map<Integer, Integer> rightCount = new HashMap<>();
            for (int num : list2) {
                rightCount.put(num, rightCount.getOrDefault(num, 0) + 1);
            }

            for (int num : list1) {
                int count = rightCount.getOrDefault(num, 0);
                total += num * count;
            }

            System.out.println("Total : " + total);

            reader.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
