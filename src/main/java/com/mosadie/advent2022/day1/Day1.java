package com.mosadie.advent2022.day1;

import com.mosadie.advent2022.Day;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Day1 extends Day {


    public Day1() {
        super(1);
    }

    @Override
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

            Scanner input = new Scanner(filePath);
            List<Elf> elfs = new LinkedList<>();

            Elf currentElf = new Elf();

            while (input.hasNextLine()) {
                String line = input.nextLine();

                if (line.equals("")) {
                    elfs.add(currentElf);

                    currentElf = new Elf();
                } else {
                    int item = Integer.parseInt(line);
                    currentElf.addItem(item);
                }
            }

            elfs.add(currentElf);

            elfs.sort(null);

            System.out.println("Top Elf: " + elfs.get(elfs.size()-1).getSum());

            System.out.println();

            return String.valueOf(elfs.get(elfs.size()-1).getSum());

        } catch (InvalidPathException e) {
            System.out.println("Failed to parse path!");
            return null;
        } catch (IOException e) {
            System.out.println("Failed to open file! " + e.getMessage());
            return null;
        }
    }

    @Override
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

            Scanner input = new Scanner(filePath);
            List<Elf> elfs = new LinkedList<>();

            Elf currentElf = new Elf();

            while (input.hasNextLine()) {
                String line = input.nextLine();

                if (line.equals("")) {
                    elfs.add(currentElf);

                    currentElf = new Elf();
                } else {
                    int item = Integer.parseInt(line);
                    currentElf.addItem(item);
                }
            }

            elfs.add(currentElf);

            elfs.sort(null);

            System.out.println("Top 3 Calories: ");
            System.out.println("1) " + elfs.get(elfs.size()-1).getSum());
            System.out.println("2) " + elfs.get(elfs.size()-2).getSum());
            System.out.println("3) " + elfs.get(elfs.size()-3).getSum());

            System.out.println();

            int sum = elfs.get(elfs.size()-1).getSum() + elfs.get(elfs.size()-2).getSum() + elfs.get(elfs.size()-3).getSum();
            return String.valueOf(sum);

        } catch (InvalidPathException e) {
            System.out.println("Failed to parse path!");
            return null;
        } catch (IOException e) {
            System.out.println("Failed to open file! " + e.getMessage());
            return null;
        }
    }
}