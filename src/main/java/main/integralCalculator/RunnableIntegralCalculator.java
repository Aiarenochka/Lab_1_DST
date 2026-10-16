package main.integralCalculator;

import main.function.Function;

public class RunnableIntegralCalculator implements Runnable {
    private final double a, b;
    private final int n;
    private final Function f;
    private final IntegralCalculator calculator;

    public RunnableIntegralCalculator(double a, double b, int n, Function f, IntegralCalculator calculator) {
        this.a = a;
        this.b = b;
        this.n = n;
        this.f = f;
        this.calculator = calculator;
    }

    @Override
    public void run() {
        double result = calculator.calculate(a, b, n, f);
        calculator.sendResult(result);
    }
}