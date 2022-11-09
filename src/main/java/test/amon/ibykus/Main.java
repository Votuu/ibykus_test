package test.amon.ibykus;

import org.slf4j.Logger;
import test.amon.ibykus.core.*;
import test.amon.ibykus.ibykus.IbykusCore;

import static java.lang.System.getLogger;
import static java.lang.System.out;
import static test.amon.ibykus.ibykus.IbykusCore.*;
import static test.amon.ibykus.util.Math.msToSeconds;

import java.util.Scanner;

public class Main {

    private static IbykusCore core = null;

    public static void main(String[] args) {
        createCore("food_order-1", new FoodOrderT1());
        createCore("food_order-2", new FoodOrderT2());

        if (args.length == 0) {
            Scanner scanner = new Scanner(System.in);

            out.println("Please enter the core you want to use");
            StringBuilder stringBuilder = new StringBuilder("Available cores: ");
            for (String s : IbykusCore.coreName()) {
                stringBuilder.append(s).append(" ");
            }
            out.println(stringBuilder);
            core = core(scanner.next());
        } else {
            IbykusCore.core(args[0]);
        }

        long l1 = System.currentTimeMillis();

        assert core != null;
        core.preBuild();
        long l2 = System.currentTimeMillis();
        core.start();

        out.println("Ran core in " + msToSeconds(System.currentTimeMillis() - l2) + "s");

        out.println("Finished process in " + msToSeconds(System.currentTimeMillis() - l1) + "s");
    }
}
