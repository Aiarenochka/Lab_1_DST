package main.function;

import java.util.function.DoubleUnaryOperator;

public class Function implements DoubleUnaryOperator {
    @Override
    public double applyAsDouble(double x) {
        return Math.sin(2 * x) * Math.cos(3 * x);
    }
}