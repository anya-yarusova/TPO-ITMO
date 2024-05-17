package org.example.logariphmic;

import org.example.function.IterationsCalculatedFunction;

import java.math.BigDecimal;

import static java.lang.String.format;
import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;
import static java.math.MathContext.DECIMAL128;
import static java.math.RoundingMode.HALF_EVEN;
import static java.math.RoundingMode.HALF_UP;

public class Ln extends IterationsCalculatedFunction {

    public Ln() {
        super();
    }

    @Override
    public BigDecimal calculate(final BigDecimal x, final BigDecimal precision) {
        validate(x, precision);
        if (x.compareTo(ZERO) <= 0) {
            throw new ArithmeticException(format("Function value for argument %s doesn't exist", x));
        }
        if (x.compareTo(ONE) == 0) {
            return ZERO;
        }

        double X = x.doubleValue();

        BigDecimal curValue = ZERO, prevValue;
        int i = 1;

        if (Math.abs(X - 1) <= 1) {
            do {
                prevValue = curValue;
                curValue = curValue.add(
                        (
                                (BigDecimal.valueOf(-1).pow(i - 1))
                                        .multiply(BigDecimal.valueOf(X - 1).pow(i))
                        )
                                .divide(BigDecimal.valueOf(i), DECIMAL128.getPrecision(), HALF_UP)
                );
                i++;
            } while (new BigDecimal("0.1").pow(DECIMAL128.getPrecision()).compareTo((prevValue.subtract(curValue)).abs()) < 0 && i < maxIterations);
            return curValue.add(prevValue).divide(BigDecimal.valueOf(2), HALF_EVEN).setScale(precision.scale(), HALF_EVEN);
        } else {
            do {
                prevValue = curValue;
                curValue = curValue.add(
                        (
                                BigDecimal.valueOf(-1).pow(i - 1)
                                        .divide(BigDecimal.valueOf(X - 1).pow(i), DECIMAL128.getPrecision(), HALF_UP)
                        )
                                .divide(BigDecimal.valueOf(i), DECIMAL128.getPrecision(), HALF_UP)
                );
                i++;
            } while (new BigDecimal("0.1").pow(DECIMAL128.getPrecision()).compareTo((prevValue.subtract(curValue)).abs()) < 0 && i < maxIterations);

            curValue = curValue.add(calculate(BigDecimal.valueOf(X - 1), precision));
        }

        return curValue.setScale(precision.scale(), HALF_EVEN);
    }

}
