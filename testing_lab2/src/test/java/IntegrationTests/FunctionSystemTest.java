package IntegrationTests;

import ch.obermuhlner.math.big.BigDecimalMath;
import com.github.nylle.javafixture.Fixture;
import org.example.function.FunctionsSystem;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.stream.Stream;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;
import static java.math.MathContext.DECIMAL128;
import static java.math.RoundingMode.HALF_EVEN;
import static java.math.RoundingMode.HALF_UP;
import static org.junit.jupiter.api.Assertions.*;

public class FunctionSystemTest {
    private static final BigDecimal DEFAULT_PRECISION = new BigDecimal("0.0001");
    private static final Fixture fixture = new Fixture();

    @Test
    void shouldNotAcceptNullArgument() {
        final FunctionsSystem system = new FunctionsSystem();

        assertThrows(NullPointerException.class, () -> system.calculate(null, DEFAULT_PRECISION));
    }

    @Test
    void shouldNotAcceptNullPrecision() {
        final FunctionsSystem system = new FunctionsSystem();

        assertThrows(NullPointerException.class, () -> system.calculate(fixture.create(BigDecimal.class), null));
    }

    @ParameterizedTest
    @MethodSource("illegalPrecisions")
    void shouldNotAcceptIncorrectPrecisions(final BigDecimal precision) {
        final FunctionsSystem system = new FunctionsSystem();

        assertThrows(ArithmeticException.class, () -> system.calculate(fixture.create(BigDecimal.class), precision));
    }

    @Test
    void shouldCalculateForZero() {
        final FunctionsSystem system = new FunctionsSystem();

        assertEquals(ONE.setScale(DEFAULT_PRECISION.scale(), HALF_UP), system.calculate(ZERO, DEFAULT_PRECISION));
    }

    @Test
    void shouldCalculateForOne() {
        final FunctionsSystem system = new FunctionsSystem();

        assertEquals(ZERO.setScale(DEFAULT_PRECISION.scale(), HALF_UP), system.calculate(ONE, DEFAULT_PRECISION));
    }

    @Test
    void shouldNotCalculateForMinusPiDividedByTwo() {
        final FunctionsSystem system = new FunctionsSystem();
        final MathContext mc = new MathContext(DECIMAL128.getPrecision());
        final BigDecimal arg =
                BigDecimalMath.pi(mc).divide(new BigDecimal(-2), DECIMAL128.getPrecision(), HALF_EVEN);

        assertThrows(ArithmeticException.class, () -> system.calculate(arg, DEFAULT_PRECISION));
    }

    @Test
    void shouldCalculateForAnyValue() {
        final FunctionsSystem system = new FunctionsSystem();
        var x = BigDecimal.valueOf(fixture.create(Float.class));

        assertDoesNotThrow(() -> system.calculate(x, DEFAULT_PRECISION));
    }

    private static Stream<Arguments> illegalPrecisions() {
        return Stream.of(
                Arguments.of(BigDecimal.valueOf(1)),
                Arguments.of(BigDecimal.valueOf(0)),
                Arguments.of(BigDecimal.valueOf(1.01)),
                Arguments.of(BigDecimal.valueOf(-0.01)));
    }
}
