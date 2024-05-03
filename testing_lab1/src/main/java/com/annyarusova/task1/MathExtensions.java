package com.annyarusova.task1;

public class MathExtensions {
    public static double arcsin(double x, int n) {
        if (Math.abs(x) > 1)
            return Double.NaN;

        double res = 0;
        for (int i = 0; i <= n; i++) {
            res += evenFactorialFraction(i) * Math.pow(x, 2 * i + 1) / (2 * i + 1);
        }
        return res;
    }

    private static double evenFactorialFraction(int n) {
        if (n < 0)
            return Double.NaN;

        double res = 1;
        for (int i = 1; i <= 2 * n; i += 2){
            res *= i / (i + 1d);
        }
        return res;
    }
}
