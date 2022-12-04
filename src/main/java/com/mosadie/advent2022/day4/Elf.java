package com.mosadie.advent2022.day4;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Elf {

    private final Set<Integer> assignments;

    public static Elf StringToElf(String input) {
        String[] split = input.split("-");

        try {
            int start = Integer.parseInt(split[0]);
            int end = Integer.parseInt(split[1]);

            return new Elf(start, end);
        } catch (NumberFormatException e) {
            System.out.println("Failed to parse integer!");
            return null;
        }
    }

    public Elf(int start, int end) {
        assignments = new HashSet<>();

        for (int i = start; i <= end; i++) {
            assignments.add(i);
        }
    }

    public boolean contains(int id) {
        return assignments.contains(id);
    }

    public boolean containsAll(List<Integer> ids) {
        for (Integer id : ids) {
            if (!contains(id)) {
                return false;
            }
        }

        return true;
    }

    public boolean containsAny(List<Integer> ids) {
        for (Integer id : ids) {
            if (contains(id)) {
                return true;
            }
        }

        return false;
    }

    public List<Integer> getAssignments() {
        return assignments.stream().toList();
    }


}
