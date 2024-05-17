package org.example.trigonometry;

import org.example.function.IterationsCalculatedFunction;

import java.math.BigDecimal;

import static java.lang.String.format;
import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;
import static java.math.MathContext.DECIMAL128;
import static java.math.RoundingMode.HALF_EVEN;

public class Sec extends IterationsCalculatedFunction {
    private final Cos cos;
    public Sec() {
        super();
        this.cos = new Cos();
    }

    public Sec(Cos cos) {
        super();
        this.cos = cos;
    }

    @Override
    public BigDecimal calculate(BigDecimal x, BigDecimal precision) throws ArithmeticException {
        validate(x, precision);
        BigDecimal cosValue = cos.calculate(x, precision);

        if (cosValue.compareTo(ZERO) == 0) {
            throw new ArithmeticException(format("Function value for argument %s doesn't exist", x));
        }

        BigDecimal result = ONE.divide(cosValue, DECIMAL128.getPrecision(), HALF_EVEN);
        return result.setScale(precision.scale(), HALF_EVEN);
    }
}
