package com.mosadie.advent2022.day5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.*;

public class Day5 {

    public Day5() {

    }

    public void execute(String[] args) {
        if (args.length != 2) {
            System.out.println("Incorrect number of args: must be day 5 and the input file path.");
            return;
        }

        try {
            Path filePath = Path.of(args[1]);

            if (!Files.exists(filePath)) {
                System.out.println("File does not exist!");
                return;
            }

            // Init any counters
            STATE state = STATE.FIND_COL_SIZE;
            List<Column> columns = new LinkedList<>();

            Scanner input = new Scanner(filePath);

            while (input.hasNextLine()) {
                String inputLine = input.nextLine();

                switch (state) {
                    case FIND_COL_SIZE:
                        if (inputLine.contains("[")) {
                            continue;
                        } else {
                            Scanner numFinder = new Scanner(inputLine);

                            int colSize = 0;

                            while (numFinder.hasNextInt()) {
                                colSize = Math.max(colSize, numFinder.nextInt());
                            }

                            for (int i = 0; i < colSize; i++) {
                                columns.add(new Column());
                            }

                            input.close();
                            input = new Scanner(filePath);
                            state = STATE.LOAD_CRATES;
                        }
                        break;

                    case LOAD_CRATES:
                        if (inputLine.contains("1")) {
                            inputLine = input.nextLine();
                            state = STATE.SKIP_TO_INSTRUCTIONS;
                            break;
                        }

                        List<Integer> indexes = new LinkedList<>();

                        for (int i = 0; i < inputLine.length(); i++) {
                            if (inputLine.charAt(i) == '[') {
                                int index = indexToColumn(i) - 1;

                                columns.get(index).addCrateBelow(inputLine.charAt(i+1));
                            }
                        }
                        break;

                    case SKIP_TO_INSTRUCTIONS:

                        for (int i = 0; i < columns.size(); i++) {
                            System.out.println("Column " + (i+1) + ": " + columns.get(i));
                        }

                        while (!inputLine.startsWith("move ") && input.hasNextLine()) {
                            inputLine = input.nextLine();
                        }
                        // Fall to Read Instructions
                        state = STATE.READ_INSTRUCTIONS;

                    case READ_INSTRUCTIONS:
                        Scanner lineReader = new Scanner(inputLine);

                        lineReader.next();

                        int count = Integer.parseInt(lineReader.next());

                        lineReader.next();

                        int source = Integer.parseInt(lineReader.next());

                        lineReader.next();

                        int dest = Integer.parseInt(lineReader.next());

                        Stack<Character> tmpQueue = new Stack<>();

                        for (int i = 0; i < count; i++) {
                            System.out.println(inputLine);
                            System.out.println("Moving " + count + " crates from " + source + " to " + dest + " (" + columns.get(source-1).getTopCrate() + ")");
                            tmpQueue.add(columns.get(source - 1).removeTopCrate());

                        }

                        while (!tmpQueue.isEmpty()){
                            char crate = tmpQueue.pop();
                            columns.get(dest - 1).addCrate(crate);
                        }

                        for (int i = 0; i < columns.size(); i++) {
                            System.out.println("Column " + (i+1) + ": " + columns.get(i).toString());
                        }

                        break;


                }
            }

            StringBuilder answer = new StringBuilder("The top crates are: ");

            for(Column column : columns) {
                answer.append(column.getTopCrate());
            }

            System.out.println(answer);

        } catch (InvalidPathException e) {
            System.out.println("Failed to parse path!");
            return;
        } catch (IOException e) {
            System.out.println("Failed to open file! " + e.getMessage());
            return;
        }
    }

    private enum STATE {
        FIND_COL_SIZE,
        LOAD_CRATES,
        SKIP_TO_INSTRUCTIONS,
        READ_INSTRUCTIONS
    };


    // Given the '[' index locations, turns it into a column number (Note: need to subtract 1 for column index number)
    private static int indexToColumn(int index) {
        return (index / 4) + 1;
    }
}
