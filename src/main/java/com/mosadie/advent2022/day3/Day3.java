package com.mosadie.advent2022.day3;

import com.mosadie.advent2022.Day;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.*;

public class Day3 extends Day {
    private List<Rucksack> rucksacks;
    
    public Day3() {
        super(3);
    }
    
    public String execute1(String[] args) {
        if (!checkArgs(args)) {
            return null;
        }

        try {
            Path filePath = Path.of(args[1]);

            if (!Files.exists(filePath)) {
                System.out.println("File does not exist!");
                return null;
            }

            rucksacks = new LinkedList<>();

            Scanner input = new Scanner(filePath);

            while (input.hasNextLine()) {
                rucksacks.add(new Rucksack(input.nextLine()));
//                System.out.println();
            }
            
            int sum = 0;

            for (Rucksack rucksack : rucksacks) {
                sum += charToPriority(rucksack.getDupe());
            }

            System.out.println("Total Dupe Sum: " + sum);
            return String.valueOf(sum);

        } catch (InvalidPathException e) {
            System.out.println("Failed to parse path!");
            return null;
        } catch (IOException e) {
            System.out.println("Failed to open file! " + e.getMessage());
            return null;
        }
    }

    public String execute2(String[] args) {
        if (!checkArgs(args)) {
            return null;
        }

        try {
            Path filePath = Path.of(args[1]);

            if (!Files.exists(filePath)) {
                System.out.println("File does not exist!");
                return null;
            }

            rucksacks = new LinkedList<>();

            Scanner input = new Scanner(filePath);

            while (input.hasNextLine()) {
                rucksacks.add(new Rucksack(input.nextLine()));
            }

            int sum = 0;

            for (int i = 0; i < rucksacks.size(); i += 3) {
                String dupe12 = findDupe(rucksacks.get(i).getComparements(), rucksacks.get(i+1).getComparements());
                String fullDupe = findDupe(dupe12, rucksacks.get(i+2).getComparements());

                System.out.println("Badge Located: '" + fullDupe + "'");
                sum += charToPriority(fullDupe.charAt(0));
            }

            System.out.println("Total Sum: " + sum);
            return String.valueOf(sum);

        } catch (InvalidPathException e) {
            System.out.println("Failed to parse path!");
            return null;
        } catch (IOException e) {
            System.out.println("Failed to open file! " + e.getMessage());
            return null;
        }
    }

    public static int charToPriority(char in) {
        if (Character.isUpperCase(in)) {
            return ((int) in) - 38;
        } else {
            return ((int) in) - 96;
        }
    }

    public static String findDupe(String s1, String s2) {
        StringBuilder builder = new StringBuilder();

        Set<Character> used = new HashSet<>();

        for (char c1 : s1.toCharArray()) {
            if (!used.contains(c1)) {
                for (char c2 : s2.toCharArray()) {
                    if (c1 == c2) {
                        builder.append(c1);
                        break;
                    }
                }
                used.add(c1);
            }
        }

        return builder.toString();
    }
}
