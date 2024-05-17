package org.example.trigonometry;

import ch.obermuhlner.math.big.BigDecimalMath;
import org.example.function.IterationsCalculatedFunction;

import java.math.BigDecimal;
import java.math.MathContext;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;
import static java.math.MathContext.DECIMAL128;
import static java.math.RoundingMode.HALF_EVEN;

public class Cos extends IterationsCalculatedFunction {
    private final Sin sin;

    public Cos() {
        super();
        this.sin = new Sin();
    }

    public Cos(final Sin sin) {
        super();
        this.sin = sin;
    }

    @Override
    public BigDecimal calculate(final BigDecimal x, final BigDecimal precision)
            throws ArithmeticException {
        validate(x, precision);

        final MathContext mc = new MathContext(DECIMAL128.getPrecision(), HALF_EVEN);
        final BigDecimal correctedX = x.remainder(BigDecimalMath.pi(mc).multiply(new BigDecimal(2)));

        final BigDecimal result =
                sin.calculate(
                        BigDecimalMath.pi(mc)
                                .divide(new BigDecimal(2), DECIMAL128.getPrecision(), HALF_EVEN)
                                .subtract(correctedX),
                        precision);
        return result.setScale(precision.scale(), HALF_EVEN);
    }
}
