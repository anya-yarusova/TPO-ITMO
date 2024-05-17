package org.example.trigonometry;

import org.example.function.IterationsCalculatedFunction;
import java.math.BigDecimal;

import static java.math.RoundingMode.HALF_EVEN;
import static java.math.RoundingMode.HALF_UP;

public class Sin extends IterationsCalculatedFunction {

    public Sin() {
        super();
    }

    @Override
    public BigDecimal calculate(final BigDecimal x, final BigDecimal precision)
            throws ArithmeticException {
        validate(x, precision);
        int i = 0;
        BigDecimal sum = new BigDecimal(0), prev;
        var arg = x.remainder(BigDecimal.valueOf(Math.PI * 2));

        do {
            prev = sum;
            sum = sum.add(minusOnePow(i).multiply(prod(arg, 2 * i + 1)));
            i++;
        } while (new BigDecimal("0.1").pow(precision.scale()).compareTo(prev.subtract(sum).abs()) < 0);

        return sum.setScale(precision.scale(), HALF_EVEN);
    }

    private static BigDecimal minusOnePow(int n){
        return BigDecimal.valueOf(1 - (n % 2) * 2);
    }

    private static BigDecimal prod(BigDecimal x, int n){
        BigDecimal accum = new BigDecimal(1);

        for (int i = 1; i <= n; i++){
            accum = accum.multiply(x.divide(BigDecimal.valueOf(i), HALF_EVEN));
        }

        return accum;
    }
}

