package unitTests;

import ch.obermuhlner.math.big.BigDecimalMath;
import com.github.nylle.javafixture.Fixture;
import org.example.logariphmic.Ln;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;
import static java.math.MathContext.DECIMAL128;
import static java.math.RoundingMode.HALF_EVEN;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class lnTest {
    private static final BigDecimal DEFAULT_PRECISION = new BigDecimal("0.0001");
    private static final Fixture fixture = new Fixture();

    @Test
    void shouldNotCalculateForZero() {
        final Ln ln = new Ln();
        assertThrows(ArithmeticException.class, () -> ln.calculate(ZERO, DEFAULT_PRECISION));
    }

    @Test
    void shouldCalculateForOne() {
        final Ln ln = new Ln();
        assertEquals(ZERO, ln.calculate(ONE, DEFAULT_PRECISION));
    }

    @Test
    void shouldCalculateForAnyPositive() {
        final Ln ln = new Ln();
        var x = BigDecimal.valueOf(Math.abs(fixture.create(Float.class)));
        var expected = BigDecimalMath.log(x, DECIMAL128);

        assertEquals(expected.setScale(DEFAULT_PRECISION.scale(), HALF_EVEN), ln.calculate(x, DEFAULT_PRECISION));
    }

    @Test
    void shouldNotCalculateForAnyNegative() {
        final Ln ln = new Ln();
        var x = BigDecimal.valueOf(-Math.abs(fixture.create(Float.class)));

        assertThrows(ArithmeticException.class, () -> ln.calculate(x, DEFAULT_PRECISION));
    }
}
