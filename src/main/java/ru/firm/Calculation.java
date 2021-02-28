package main.java.ru.firm;

import java.util.Deque;
import java.util.ArrayDeque;

public class Calculation {
    public double calculatePostfixNotationExpression(String postfixFormOfExpression) {
        String[] expressionElements = postfixFormOfExpression.split(" ");
        Deque<Double> operands = new ArrayDeque<>();
        for (String element : expressionElements) {
            if (element.equals("+")) {
                operands.push(operands.pop() + operands.pop());
            } else if (element.equals("-")) {
                Double firstElement = operands.pop();
                Double secondElement = operands.pop();
                operands.push(secondElement - firstElement);
            } else if (element.equals("*")) {
                operands.push(operands.pop() * operands.pop());
            } else if (element.equals("/")) {
                Double firstElement = operands.pop();
                Double secondElement = operands.pop();
                double divisionResult = secondElement / firstElement;
                if (divisionResult == Double.POSITIVE_INFINITY || divisionResult == Double.NEGATIVE_INFINITY) {
                    throw new ArithmeticException("Division by zero");
                }
                operands.push(divisionResult);
            } else {
                operands.push(Double.parseDouble(element));
            }
        }
        return operands.pop();
    }
}
