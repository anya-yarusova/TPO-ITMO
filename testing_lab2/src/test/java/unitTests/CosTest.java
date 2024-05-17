package unitTests;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;
import static java.math.MathContext.DECIMAL128;
import static java.math.RoundingMode.HALF_EVEN;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import ch.obermuhlner.math.big.BigDecimalMath;
import java.math.BigDecimal;
import java.math.MathContext;

import com.github.nylle.javafixture.Fixture;
import org.example.trigonometry.Cos;
import org.example.trigonometry.Sin;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CosTest {
    private static final BigDecimal DEFAULT_PRECISION = new BigDecimal("0.0001");
    private static final Fixture fixture = new Fixture();

    @Mock private Sin mockSin;
    @Spy private Sin spySin;

    @Test
    void shouldCallSinFunction() {
        final Cos cos = new Cos(spySin);

        cos.calculate(fixture.create(BigDecimal.class), DEFAULT_PRECISION);

        verify(spySin, atLeastOnce()).calculate(any(BigDecimal.class), any(BigDecimal.class));
    }

    @Test
    void shouldCalculateWithMockSin() {
        final BigDecimal arg = new BigDecimal(5);
        final MathContext mc = new MathContext(DECIMAL128.getPrecision());
        final BigDecimal correctedArg =
                BigDecimalMath.pi(mc)
                        .divide(new BigDecimal(2), DECIMAL128.getPrecision(), HALF_EVEN)
                        .subtract(arg);
        final BigDecimal sinResult = new BigDecimal("0.2837");
        when(mockSin.calculate(eq(correctedArg), any(BigDecimal.class))).thenReturn(sinResult);

        final Cos cos = new Cos(mockSin);

        assertEquals(sinResult, cos.calculate(arg, DEFAULT_PRECISION));
    }
}
