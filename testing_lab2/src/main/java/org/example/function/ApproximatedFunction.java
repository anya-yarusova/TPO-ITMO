package org.example.function;

import java.math.BigDecimal;

public interface ApproximatedFunction {
    BigDecimal calculate(BigDecimal x, BigDecimal precision);
}
