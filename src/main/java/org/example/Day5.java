package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Day5 implements Part {
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
        String file = "src/main/resources/advent_of_code_5.txt";
        List<String> puzzle = readPuzzle(file);

        boolean readRules = true;
        List<String> rules = new ArrayList<>();
        List<String> updates = new ArrayList<>();
        List<String> updatesOk = new ArrayList<>();

        for (String line : puzzle) {
            if (line.equals("")) {
                readRules = false;
            }

            if (readRules) {
                rules.add(line);
            } else if (!line.equals("")) {
                updates.add(line);
            }
        }

        for (String update : updates) {
            var split = update.split(",");

            boolean ok = true;
            for (int i = 0; i < split.length; i++) {
                for (int j = i + 1; j < split.length; j++) {
                    if (!rules.contains(split[i] + "|" + split[j])) {
                        ok = false;
                    }
                }
            }

            if (ok) updatesOk.add(update);
        }

        int count = 0;
        for (String update : updatesOk) {
            System.out.println(update);
            var split = update.split(",");
            System.out.println(split[(split.length / 2)]);
            count += Integer.parseInt(split[(split.length / 2)]);
        }

        System.out.println(count);
    }

    public void part2() {
        String file = "src/main/resources/advent_of_code_5_2.txt";
        List<String> puzzle = readPuzzle(file);

        boolean readRules = true;
        List<String> rules = new ArrayList<>();
        List<String> updates = new ArrayList<>();
        List<List<String>> updatesNotOk = new ArrayList<>();
        Map<List<String>, List<String>> badrules = new HashMap<>();

        for (String line : puzzle) {
            if (line.equals("")) {
                readRules = false;
            }

            if (readRules) {
                rules.add(line);
            } else if (!line.equals("")) {
                updates.add(line);
            }
        }

        for (String update : updates) {
            var split = update.split(",");

            boolean ok = true;
            List<String> tmp = new ArrayList<>();
            for (int i = 0; i < split.length; i++) {
                for (int j = i + 1; j < split.length; j++) {
                    if (!rules.contains(split[i] + "|" + split[j])) {
                        ok = false;
                        tmp.add(split[i] + "|" + split[j]);
                    }
                }
            }

            if (!ok) {
                updatesNotOk.add(Arrays.asList(update.split(",")));
                badrules.put(Arrays.asList(update.split(",")), new ArrayList<>(tmp));
            }
            tmp.clear();
        }

        System.out.println(badrules);
        List<List<String>> okList = new ArrayList<>();

        for (List<String> list : updatesNotOk) {
            var tmp = badrules.get(list);
            List<String> copy = new ArrayList<>(list);

            for (String s : tmp) {
                System.out.println(s);
                var split = s.split("\\|");
                copy.remove(split[1]);
                copy.add(copy.indexOf(split[0]), split[1]);
                System.out.println(copy);
            }
            System.out.println(isUpdateOk(copy, rules));

            if (isUpdateOk(copy, rules).isEmpty()) {
                okList.add(copy);
            } else {
                while (!isUpdateOk(copy, rules).isEmpty()) {
                    var plop = isUpdateOk(copy, rules);
                    copy = sort(plop, copy);
                    System.out.println(copy);
                }
                okList.add(copy);
            }

        }
        System.out.println(okList);

        int count = 0;
        for (List<String> lst : okList) {
            count += Integer.parseInt(lst.get(lst.size() / 2));
        }
        System.out.println(count);
    }

    private List<String> isUpdateOk(List<String> update, List<String> rules) {
        boolean ok = true;
        List<String> tmp = new ArrayList<>();
        for (int i = 0; i < update.size(); i++) {
            for (int j = i + 1; j < update.size(); j++) {
                if (!rules.contains(update.get(i) + "|" + update.get(j))) {
                    ok = false;
                    tmp.add(update.get(i) + "|" + update.get(j));
                }
            }
        }
        return tmp;
    }

    private List<String> sort(List<String> badrules, List<String> list) {
        List<String> copy = new ArrayList<>(list);

        for (String s : badrules) {
            var split = s.split("\\|");
            copy.remove(split[1]);
            copy.add(copy.indexOf(split[0]), split[1]);
        }

        return copy;
    }

}
