package IntegrationTests;

import ch.obermuhlner.math.big.BigDecimalMath;
import com.github.nylle.javafixture.Fixture;
import org.example.trigonometry.Cos;
import org.example.trigonometry.Sin;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.MathContext;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;
import static java.math.MathContext.DECIMAL128;
import static java.math.RoundingMode.HALF_EVEN;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CosTest {

    private static final BigDecimal DEFAULT_PRECISION = new BigDecimal("0.0001");
    private static final Fixture fixture = new Fixture();

    @Test
    void shouldCalculateForZero() {
        final Cos cos = new Cos();

        assertEquals(ONE.setScale(DEFAULT_PRECISION.scale(), HALF_EVEN), cos.calculate(ZERO, DEFAULT_PRECISION));
    }

    @Test
    void shouldCalculateForPiDividedByTwo() {
        final Cos cos = new Cos();
        final MathContext mc = new MathContext(DECIMAL128.getPrecision());
        final BigDecimal arg =
                BigDecimalMath.pi(mc).divide(new BigDecimal(2), DECIMAL128.getPrecision(), HALF_EVEN);

        assertEquals(ZERO.setScale(DEFAULT_PRECISION.scale(), HALF_EVEN) , cos.calculate(arg, DEFAULT_PRECISION));
    }

    @Test
    void shouldCalculateForAnyValue() {
        final Cos cos = new Cos();
        var x = BigDecimal.valueOf(fixture.create(Float.class));
        var expected = BigDecimalMath.cos(x, DECIMAL128);

        assertEquals(expected.setScale(DEFAULT_PRECISION.scale(), HALF_EVEN), cos.calculate(x, DEFAULT_PRECISION));
    }
}
