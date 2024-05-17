package IntegrationTests;

import ch.obermuhlner.math.big.BigDecimalMath;
import com.github.nylle.javafixture.Fixture;
import org.example.trigonometry.Cos;
import org.example.trigonometry.Sec;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.MathContext;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;
import static java.math.MathContext.DECIMAL128;
import static java.math.RoundingMode.HALF_EVEN;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SecTest {
    private static final BigDecimal DEFAULT_PRECISION = new BigDecimal("0.0001");
    private static final Fixture fixture = new Fixture();

    @Test
    void shouldCalculateForZero() {
        final Sec sec = new Sec();

        assertEquals(
                ONE.setScale(DEFAULT_PRECISION.scale(), HALF_EVEN),
                sec.calculate(ZERO, DEFAULT_PRECISION));
    }

    @Test
    void shouldNotCalculateForPiDividedByTwo() {
        final Sec sec = new Sec();
        final MathContext mc = new MathContext(DECIMAL128.getPrecision());
        final BigDecimal arg =
                BigDecimalMath.pi(mc).divide(new BigDecimal(2), DECIMAL128.getPrecision(), HALF_EVEN);

        assertThrows(ArithmeticException.class, () -> sec.calculate(arg, DEFAULT_PRECISION));
    }

    @Test
    void shouldCalculateForAnyValue() {
        final Sec sec = new Sec();
        var x = BigDecimal.valueOf(fixture.create(Float.class));
        var expected = ONE.divide(BigDecimalMath.cos(x, DECIMAL128), DEFAULT_PRECISION.scale(), HALF_EVEN);

        assertEquals(expected.setScale(DEFAULT_PRECISION.scale(), HALF_EVEN), sec.calculate(x, DEFAULT_PRECISION));
    }
}
