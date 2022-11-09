package test.amon.ibykus.util;

public class Math {

    public static double msToSeconds(long ms) {
        long s = ms / 1000;
        ms = ms - s;
        String msS = ms + "";
        return Double.parseDouble(s + "." + msS);
    }
}
