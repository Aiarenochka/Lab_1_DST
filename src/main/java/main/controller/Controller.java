package main.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import main.function.Function;
import main.integralCalculator.IntegralCalculator;

public class Controller {
    @FXML
    private TextField countN, countThreads;
    @FXML
    private TextFlow outputText;

    @FXML
    public void controller() {
        Function function = new Function();
        IntegralCalculator calculator = new IntegralCalculator();

        double a = 0.0;
        double b = Math.PI / 2.0;

        int n = Integer.parseInt(countN.getText());
        int numberOfThreads = Integer.parseInt(countThreads.getText());

        long startTime = System.currentTimeMillis();
        double result = calculator.calculate(a, b, n, function, numberOfThreads);
        long endTime = System.currentTimeMillis();

        printResult(result, endTime - startTime);
    }

    void printResult(double result, long time) {
        outputText.getChildren().clear();
        Text resultText = new Text("Result = " + result + "\n");
        Text timeText = new Text("Time = " + time + " ms\n");
        outputText.getChildren().addAll(resultText, timeText);
    }
}