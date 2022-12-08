package com.mosadie.advent2022.day2;

import com.mosadie.advent2022.Day;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.Scanner;

public class Day2 extends Day {

    private static final String OPT_ROCK = "A";
    private static final String OPT_PAPER = "B";
    private static final String OPT_SCISSORS = "C";

    private static final String GOAL_LOSE = "X";
    private static final String GOAL_DRAW = "Y";
    private static final String GOAL_WIN = "Z";

    private static final String SELF_ROCK = "X";
    private static final String SELF_PAPER = "Y";
    private static final String SELF_SCISSORS = "Z";

    private static final int VALUE_ROCK = 1;
    private static final int VALUE_PAPER = 2;
    private static final int VALUE_SCISSORS = 3;

    private static final int VALUE_WIN = 6;
    private static final int VALUE_DRAW = 3;
    private static final int VALUE_LOSE = 0;

    public Day2() {
        super(2);
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

            int score = 0;

            Scanner input = new Scanner(filePath);

            while (input.hasNextLine()) {
                score += calcRoundScore1(input.nextLine());
            }

            System.out.println("Total Score: " + score);
            return String.valueOf(score);

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

            int score = 0;

            Scanner input = new Scanner(filePath);

            while (input.hasNextLine()) {
                score += calcRoundScore2(input.nextLine());
            }

            System.out.println("Total Score: " + score);
            return String.valueOf(score);

        } catch (InvalidPathException e) {
            System.out.println("Failed to parse path!");
            return null;
        } catch (IOException e) {
            System.out.println("Failed to open file! " + e.getMessage());
            return null;
        }
    }

    private int calcRoundScore1(String round) {
        Scanner input = new Scanner(round);

        int roundScore = 0;
        int shapeScore = 0;

        String opponent = input.next();
        String self = input.next();

        switch (self) {
            case SELF_ROCK:
                shapeScore = VALUE_ROCK;
                break;

            case SELF_PAPER:
                shapeScore = VALUE_PAPER;
                break;

            case GOAL_WIN:
                shapeScore = VALUE_SCISSORS;
                break;
        }

        switch(opponent) {
            case OPT_ROCK:
                switch (self) {
                    case SELF_ROCK:
                        roundScore = VALUE_DRAW;
                        break;

                    case SELF_PAPER:
                        roundScore = VALUE_WIN;
                        break;

                    case SELF_SCISSORS:
                        roundScore = VALUE_LOSE;
                        break;
                }
                break;

            case OPT_PAPER:
                switch (self) {
                    case SELF_ROCK:
                        roundScore = VALUE_LOSE;
                        break;

                    case SELF_PAPER:
                        roundScore = VALUE_DRAW;
                        break;

                    case SELF_SCISSORS:
                        roundScore = VALUE_WIN;
                        break;
                }
                break;

            case OPT_SCISSORS:
                switch (self) {
                    case SELF_ROCK:
                        roundScore = VALUE_WIN;
                        break;

                    case SELF_PAPER:
                        roundScore = VALUE_LOSE;
                        break;

                    case SELF_SCISSORS:
                        roundScore = VALUE_DRAW;
                        break;
                }
                break;
        }

        System.out.println("Round Score: " + (shapeScore + roundScore) + " (" + shapeScore + " + " + roundScore + ")");
        return shapeScore + roundScore;
    }

    private int calcRoundScore2(String round) {
        Scanner input = new Scanner(round);

        int roundScore = 0;

        String opponent = input.next();
        String self = input.next();

        switch (self) {
            case GOAL_LOSE:
                roundScore = 0;
                break;

            case GOAL_DRAW:
                roundScore = 3;
                break;

            case GOAL_WIN:
                roundScore = 6;
                break;
        }

        int shapeScore = 0;

        switch(opponent) {
            case OPT_ROCK:
                switch (self) {
                    case GOAL_LOSE:
                        shapeScore = VALUE_SCISSORS;
                        break;

                    case GOAL_DRAW:
                        shapeScore = VALUE_ROCK;
                        break;

                    case GOAL_WIN:
                        shapeScore = VALUE_PAPER;
                        break;
                }
                break;

            case OPT_PAPER:
                switch (self) {
                    case GOAL_LOSE:
                        shapeScore = VALUE_ROCK;
                        break;

                    case GOAL_DRAW:
                        shapeScore = VALUE_PAPER;
                        break;

                    case GOAL_WIN:
                        shapeScore = VALUE_SCISSORS;
                        break;
                }
                break;

            case OPT_SCISSORS:
                switch (self) {
                    case GOAL_LOSE:
                        shapeScore = VALUE_PAPER;
                        break;

                    case GOAL_DRAW:
                        shapeScore = VALUE_SCISSORS;
                        break;

                    case GOAL_WIN:
                        shapeScore = VALUE_ROCK;
                        break;
                }
                break;
        }

        System.out.println("Round Score: " + (shapeScore + roundScore) + " (" + shapeScore + " + " + roundScore + ")");
        return shapeScore + roundScore;
    }
}
