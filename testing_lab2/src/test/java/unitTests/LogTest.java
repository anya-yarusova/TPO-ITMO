package unitTests;

import com.github.nylle.javafixture.Fixture;
import org.example.logariphmic.Ln;
import org.example.logariphmic.Log;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Spy;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class LogTest {
    private static final BigDecimal DEFAULT_PRECISION = new BigDecimal("0.0001");
    private static final Fixture fixture = new Fixture();

    @Mock
    private Ln mockLn = mock(Ln.class);
    @Spy
    private Ln spyLn = spy(Ln.class);

    @Test
    void shouldCallLnFunction() {
        final Log log = new Log(spyLn, Math.abs(fixture.create(Byte.class)));
        log.calculate(new BigDecimal(fixture.create(Byte.class)), DEFAULT_PRECISION);

        verify(spyLn, atLeastOnce()).calculate(any(BigDecimal.class), any(BigDecimal.class));
    }

    @Test
    void shouldCalculateWithLnMock() {
        final BigDecimal arg = new BigDecimal(44);

        when(mockLn.calculate(eq(new BigDecimal(44)), any(BigDecimal.class)))
                .thenReturn(new BigDecimal("3.7842"));
        when(mockLn.calculate(eq(new BigDecimal(4)), any(BigDecimal.class)))
                .thenReturn(new BigDecimal("1.3863"));

        final Log log = new Log(mockLn, 4);
        final BigDecimal expected = new BigDecimal("2.7297");
        assertEquals(expected, log.calculate(arg, DEFAULT_PRECISION));
    }
}
