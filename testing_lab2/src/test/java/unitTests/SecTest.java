package unitTests;

import com.github.nylle.javafixture.Fixture;
import org.example.trigonometry.Cos;
import org.example.trigonometry.Sec;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Spy;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class SecTest {
    private static final BigDecimal DEFAULT_PRECISION = new BigDecimal("0.0001");
    private static final Fixture fixture = new Fixture();

    @Mock
    private Cos mockCos = mock(Cos.class);
    @Spy
    private Cos spyCos = spy(Cos.class);

    @Test
    void shouldCallCosFunction() {
        Sec csc = new Sec(spyCos);

        csc.calculate(fixture.create(BigDecimal.class), DEFAULT_PRECISION);

        verify(spyCos, atLeastOnce()).calculate(any(BigDecimal.class), any(BigDecimal.class));
    }

    @Test
    void shouldCalcWithMockCos() {
        BigDecimal arg = new BigDecimal(4);
        when(mockCos.calculate(eq(arg), any(BigDecimal.class))).thenReturn(new BigDecimal("-0.6537"));
        Sec csc = new Sec(mockCos);
        BigDecimal expectedResult = new BigDecimal("-1.5298");


        assertEquals(expectedResult, csc.calculate(arg, DEFAULT_PRECISION));
    }
}
