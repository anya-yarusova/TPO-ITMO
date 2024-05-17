package unitTests;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;
import static java.math.MathContext.DECIMAL128;
import static java.math.RoundingMode.HALF_EVEN;
import static org.junit.jupiter.api.Assertions.assertEquals;

import ch.obermuhlner.math.big.BigDecimalMath;
import java.math.BigDecimal;
import java.math.MathContext;

import com.github.nylle.javafixture.Fixture;
import org.example.trigonometry.Sin;
import org.junit.jupiter.api.Test;

class SinTest {
    private static final BigDecimal DEFAULT_PRECISION = new BigDecimal("0.0001");
    private static final Fixture fixture = new Fixture();

    @Test
    void shouldCalculateForZero() {
        final Sin sin = new Sin();

        assertEquals(ZERO.setScale(DEFAULT_PRECISION.scale(), HALF_EVEN), sin.calculate(ZERO, DEFAULT_PRECISION));
    }

    @Test
    void shouldCalculateForPiDividedByTwo() {
        final Sin sin = new Sin();
        final MathContext mc = new MathContext(DECIMAL128.getPrecision());
        final BigDecimal arg =
                BigDecimalMath.pi(mc).divide(new BigDecimal(2), DECIMAL128.getPrecision(), HALF_EVEN);

        assertEquals(
                ONE.setScale(DEFAULT_PRECISION.scale(), HALF_EVEN), sin.calculate(arg, DEFAULT_PRECISION));
    }

    @Test
    void shouldCalculateForAnyValue() {
        final Sin sin = new Sin();
        var x = BigDecimal.valueOf(fixture.create(Float.class));
        var expected = BigDecimalMath.sin(x, DECIMAL128);

        assertEquals(expected.setScale(DEFAULT_PRECISION.scale(), HALF_EVEN), sin.calculate(x, DEFAULT_PRECISION));
    }
}