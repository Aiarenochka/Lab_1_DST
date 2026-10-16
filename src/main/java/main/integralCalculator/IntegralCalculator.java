package main.integralCalculator;

import main.function.Function;

public class IntegralCalculator {
    private double totalSum = 0;
    private int finished = 0;

    public double calculate(double a, double b, int n, Function f) {
        double h = (b - a) / n;
        double sum = 0;
        for (int i = 0; i < n; i++) {
            sum += f.calculate(a + i * h + h / 2.0);
        }
        return sum * h;
    }

    public double calculate(double a, double b, int n, Function f, int numberOfThreads) {
        totalSum = 0;
        finished = 0;

        double delta = (b - a) / numberOfThreads;
        for (int i = 0; i < numberOfThreads; i++) {
            double ai = a + i * delta;
            double bi = ai + delta;
            int ni = n / numberOfThreads;

            RunnableIntegralCalculator task = new RunnableIntegralCalculator(ai, bi, ni, f, this);
            new Thread(task).start();
        }

        try {
            synchronized (this) {
                while (finished < numberOfThreads) {
                    wait();
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return totalSum;
    }

    public synchronized void sendResult(double result) {
        totalSum += result;
        finished++;
        notify();
    }
}