package IntegrationTests;

import ch.obermuhlner.math.big.BigDecimalMath;
import com.github.nylle.javafixture.Fixture;
import org.example.logariphmic.Log;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;
import static java.math.MathContext.DECIMAL128;
import static java.math.RoundingMode.HALF_EVEN;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LogTest {
    private static final BigDecimal DEFAULT_PRECISION = new BigDecimal("0.0001");
    private static final Fixture fixture = new Fixture();

    @Test
    void shouldNotCalculateForZero() {
        final Log log = new Log(Math.abs(fixture.create(Integer.class)));

        assertThrows(ArithmeticException.class, () -> log.calculate(ZERO, DEFAULT_PRECISION));
    }

    @Test
    void shouldCalculateForOne() {
        final Log log = new Log(Math.abs(fixture.create(Integer.class)));

        assertEquals(
                ZERO.setScale(DEFAULT_PRECISION.scale(), HALF_EVEN), log.calculate(ONE, DEFAULT_PRECISION));
    }

    @Test
    void shouldCalculateForAnyPositive() {
        final Log log = new Log(10);
        var x = BigDecimal.valueOf(Math.abs(fixture.create(Float.class)));
        var expected = BigDecimalMath.log10(x, DECIMAL128);

        assertEquals(expected.setScale(DEFAULT_PRECISION.scale(), HALF_EVEN), log.calculate(x, DEFAULT_PRECISION));
    }

    @Test
    void shouldNotCalculateForAnyNegative() {
        final Log log = new Log(Math.abs(fixture.create(Integer.class)));
        var x = BigDecimal.valueOf(-Math.abs(fixture.create(Float.class)));

        assertThrows(ArithmeticException.class, () -> log.calculate(x, DEFAULT_PRECISION));
    }
}
