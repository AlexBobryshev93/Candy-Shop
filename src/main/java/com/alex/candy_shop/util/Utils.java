package com.alex.candy_shop.util;

public class Utils {
    // used to convert money sums like X and X.X to X.00 and X.X0 formats respectively
    public static String moneyToDisplay(double amount) {
        String s = String.valueOf(Math.round(amount * 100) / 100d);
        int digitsBeforePoint = 0;

        for (int i = 0; ; i++) {
            if (s.charAt(i) == '.') break;
            digitsBeforePoint++;
        }

        while ((s.length() - digitsBeforePoint - 1 ) < 2) s = s + "0";
        return s;
    }
}
