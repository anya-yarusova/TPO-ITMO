package task1;

import com.annyarusova.task1.MathExtensions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MathExtensionsTests {
    @ParameterizedTest(name = "arcsin({0})")
    @DisplayName("Check [-1, 1]")
    @ValueSource(doubles = {-1, -0.9, -0.8, -0.7, -0.6, -0.5, -0.4, -0.3, -0.2, -0.1, 0, 0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1})
    void checkValidPoints(double param) {
        assertEquals(Math.asin(param), MathExtensions.arcsin(param, 10000), 0.05);
    }

    @ParameterizedTest(name = "arcsin({0})")
    @DisplayName("Check (-inf, -1) U (1, inf)")
    @ValueSource(doubles = {-10, -1.1, 2, 100})
    void checkInvalidPoints(double param) {
        assertEquals(Double.NaN, MathExtensions.arcsin(param, 10000));
    }
}
