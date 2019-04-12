package be.valuya.bob.core.util.print;

import be.valuya.bob.core.BobCompany;
import be.valuya.bob.core.BobPeriod;

public class BobThePrinter {

    public static void printPeriod(BobPeriod bobPeriod) {
        String label = bobPeriod.getLabel();
        System.out.println(label + ": " + bobPeriod.getStatus());
    }

    public static void printCompany(BobCompany bobCompany) {
        String cid = bobCompany.getcId();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(cid);

        bobCompany.getcName1Optional()
                .ifPresent(stringBuilder::append);
        bobCompany.getcName2Optional()
                .ifPresent(stringBuilder::append);

        System.out.println(stringBuilder);
    }
}
