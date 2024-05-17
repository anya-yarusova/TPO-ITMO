package org.example.function;

import org.example.logariphmic.Ln;
import org.example.logariphmic.Log;

import java.math.BigDecimal;

import static java.math.BigDecimal.ZERO;
import static java.math.RoundingMode.HALF_EVEN;

public class FunctionsSystem implements ApproximatedFunction {

    private final Sin sin;
    private final Sec sec;
    private final Log log2;
    private final Log log3;
    private final Log log5;
    private final Log log10;

    public FunctionsSystem() {
        this.sin = new Sin();
        this.sec = new Sec();
        this.log2 = new Log(2);
        this.log3 = new Log(3);
        this.log5 = new Log(5);
        this.log10 = new Log(10);
    }

    @Override
    public BigDecimal calculate(BigDecimal x, BigDecimal precision) {
        if (x.compareTo(ZERO) <= 0) {
            return sin.calculate(x, precision).add(sec.calculate(x, precision)).setScale(precision.scale(), HALF_EVEN);
        } else {
            return
                    (((((log10.calculate(x, precision)
                            .add(log2.calculate(x, precision))
                    )
                            .subtract(log3.calculate(x, precision))
                    )
                            .subtract(log3.calculate(x, precision))
                    )
                            .multiply(log3.calculate(x, precision).pow(2))
                    )
                            .subtract(
                                    (log3.calculate(x, precision).pow(2)
                                            .subtract(log5.calculate(x, precision))
                                    )
                            )
                    )
                    .setScale(precision.scale(), HALF_EVEN);
        }
    }
}
