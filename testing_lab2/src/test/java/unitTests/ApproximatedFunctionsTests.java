package unitTests;

import com.github.nylle.javafixture.Fixture;
import org.example.function.ApproximatedFunction;
import org.example.logariphmic.Ln;
import org.example.logariphmic.Log;
import org.example.trigonometry.Cos;
import org.example.trigonometry.Sec;
import org.example.trigonometry.Sin;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static java.math.BigDecimal.ONE;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApproximatedFunctionsTests {
    private static final BigDecimal DEFAULT_PRECISION = new BigDecimal("0.0001");
    private static final Fixture fixture = new Fixture();

    @ParameterizedTest
    @MethodSource("functions")
    void shouldNotAcceptNullArgument(final ApproximatedFunction function) {
        assertThrows(NullPointerException.class, () -> function.calculate(null, DEFAULT_PRECISION));
    }

    @ParameterizedTest
    @MethodSource("functions")
    void shouldNotAcceptNullPrecision(final ApproximatedFunction function) {
        assertThrows(NullPointerException.class, () -> function.calculate(ONE, null));
    }

    private static Stream<Arguments> functions() {
        return Stream.of(
                Arguments.of(new Sin()),
                Arguments.of(new Cos()),
                Arguments.of(new Sec()),
                Arguments.of(new Ln()),
                Arguments.of(new Log(Math.abs(fixture.create(Byte.class))))
        );
    }
}
