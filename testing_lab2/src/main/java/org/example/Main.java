package org.example;

import org.example.function.ApproximatedFunction;
import org.example.function.FunctionsSystem;
import org.example.logariphmic.Ln;
import org.example.logariphmic.Log;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        final Sin sin = new Sin();
        final Sec csc = new Sec();
        final Ln ln = new Ln();
        final Log log2 = new Log(2);
        final Log log3 = new Log(3);
        final Log log5 = new Log(5);
        final Log log10 = new Log(10);
        final FunctionsSystem functionsSystem = new FunctionsSystem();

        writeToCsv(
                "csv/sin.csv",
                sin,
                new BigDecimal(-1),
                new BigDecimal(1),
                new BigDecimal("0.1"),
                new BigDecimal("0.0000000001"));
        writeToCsv(
                "csv/sec.csv",
                sec,
                new BigDecimal(1),
                new BigDecimal(2),
                new BigDecimal("0.1"),
                new BigDecimal("0.0000000001"));
        writeToCsv(
                "csv/ln.csv",
                ln,
                new BigDecimal(1),
                new BigDecimal(20),
                new BigDecimal("0.1"),
                new BigDecimal("0.0000000001"));
        writeToCsv(
                "csv/log2.csv",
                log2,
                new BigDecimal(1),
                new BigDecimal(15),
                new BigDecimal("0.1"),
                new BigDecimal("0.00000000001"));
        writeToCsv(
                "csv/log3.csv",
                log3,
                new BigDecimal(1),
                new BigDecimal(15),
                new BigDecimal("0.1"),
                new BigDecimal("0.00000000001"));
        writeToCsv(
                "csv/log5.csv",
                log5,
                new BigDecimal(1),
                new BigDecimal(20),
                new BigDecimal("0.1"),
                new BigDecimal("0.00000000001"));
        writeToCsv(
                "csv/log10.csv",
                log10,
                new BigDecimal(1),
                new BigDecimal(20),
                new BigDecimal("0.1"),
                new BigDecimal("0.00000000001"));
        writeToCsv(
                "csv/func.csv",
                functionsSystem,
                new BigDecimal(-3),
                new BigDecimal(3),
                new BigDecimal("0.11"),
                new BigDecimal("0.00000000001"));
    }

    private static void writeToCsv(
            String filename,
            ApproximatedFunction function,
            BigDecimal from,
            BigDecimal to,
            BigDecimal step,
            BigDecimal precision
    ) throws IOException {
        final Path path = Paths.get(filename);
        final File file = new File(path.toUri());
        if (file.exists()) file.delete();
        file.createNewFile();
        final PrintWriter pw = new PrintWriter(file);
        for (BigDecimal current = from; current.compareTo(to) <= 0; current = current.add(step)) {
            pw.println(current + "," + function.calculate(current, precision));
        }
        pw.close();
    }
}