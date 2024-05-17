package org.example.logariphmic;

import org.example.function.IterationsCalculatedFunction;

import java.math.BigDecimal;

import static java.lang.String.format;
import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;
import static java.math.MathContext.DECIMAL128;
import static java.math.RoundingMode.HALF_EVEN;

public class Log extends IterationsCalculatedFunction {

    private final Ln ln;
    private final int base;

    public Log(int base) {
        super();
        this.ln = new Ln();
        this.base = base;
    }

    public Log(Ln ln, int base) {
        super();
        this.ln = ln;
        this.base = base;
    }

    @Override
    public BigDecimal calculate(final BigDecimal x, final BigDecimal precision) {
        validate(x, precision);
        if (x.compareTo(ZERO) <= 0) {
            throw new ArithmeticException(format("Function value for argument %s doesn't exist", x));
        }
        else if (x.equals(ONE)) {
            return ZERO.setScale(precision.scale(), HALF_EVEN);
        }

        return ln
                .calculate(x, precision)
                .divide(
                        ln.calculate(new BigDecimal(base), precision),
                        DECIMAL128.getPrecision(),
                        HALF_EVEN)
                .setScale(precision.scale(), HALF_EVEN);
    }

}
