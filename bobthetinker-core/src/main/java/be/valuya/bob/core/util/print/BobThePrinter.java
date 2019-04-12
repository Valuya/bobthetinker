package be.valuya.bob.core.util.print;

import be.valuya.bob.core.BobPeriod;

public class BobThePrinter {

    public static void printPeriod(BobPeriod bobPeriod) {
        String label = bobPeriod.getLabel();
        System.out.println(label + ": " + bobPeriod.getStatus());
    }
}
