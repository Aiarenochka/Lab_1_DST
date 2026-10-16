package main.function;

import main.integralCalculator.IntegralCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.offset;

class FunctionTest {
    IntegralCalculator integralCalculator;
    Function function;
    double EPS = 1e-4;

    @BeforeEach
    void setUp() {
        function = new Function();
        integralCalculator = new IntegralCalculator();
    }

    @ParameterizedTest
    @CsvSource({
            "0.0, 0.0",
            "0.785398, -0.707106",
            "1.570796, 0.0"
    })
    void functionTest(double x, double expected) {
        double result = function.calculate(x);
        assertThat(result).isCloseTo(expected, offset(EPS));
    }

    @ParameterizedTest
    @CsvSource({
            "0.0, 1.570796, 100, -0.4",
            "0.0, 1.570796, 1000, -0.4",
            "0.0, 0.0, 100, 0.0"
    })
    void integralCalculatorTest(double a, double b, int n, double expected) {
        double result = integralCalculator.calculate(a, b, n, function);
        assertThat(result).isCloseTo(expected, offset(EPS));
    }
}