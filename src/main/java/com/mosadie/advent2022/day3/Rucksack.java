package com.mosadie.advent2022.day3;

public class Rucksack {
    private String compartment1;
    private String compartment2;

    private char dupe;

    public Rucksack(String inventory) {
        compartment1 = inventory.substring(0, inventory.length() / 2);
        compartment2 = inventory.substring(inventory.length() / 2);

        findDupe();
    }

    private void findDupe() {
        for (char item1 : compartment1.toCharArray()) {
            for (char item2 : compartment2.toCharArray()) {
                if (item1 == item2) {
                    //System.out.println("Dupe found: " + item1 + " (" + Day3.charToPriority(item1) + ")");
                    dupe = item1;
                    return;
                }
            }
        }
    }

    public String getCompartment1() {
        return compartment1;
    }

    public String getCompartment2() {
        return compartment2;
    }

    public String getComparements() {
        return getCompartment1() + getCompartment2();
    }

    public char getDupe() {
        return dupe;
    }
}
