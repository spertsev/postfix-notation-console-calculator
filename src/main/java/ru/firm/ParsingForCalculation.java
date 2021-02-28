package main.java.ru.firm;

import java.util.Deque;
import java.util.ArrayDeque;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class ParsingForCalculation {
    public String replaceMinusAndParenthesesByM(String expressionWithMinusParentheses) {
        StringBuilder stringBuilder = new StringBuilder(expressionWithMinusParentheses);
        Pattern minusWithParenthesesPattern = Pattern.compile("\\(-.*?\\)");
        Matcher matcher = minusWithParenthesesPattern.matcher(stringBuilder);
        while (matcher.find()) {
            int indexOfOpeningMinusParenthesis = matcher.start();
            int indexOfClosingMinusParenthesis = matcher.end() - 1;
            stringBuilder.deleteCharAt(indexOfClosingMinusParenthesis);
            stringBuilder.replace(indexOfOpeningMinusParenthesis, indexOfOpeningMinusParenthesis + 2, "M");
            matcher.reset();
        }
        return stringBuilder.toString();
    }

    public String translateInfixNotationToPostfix(String infixFormOfExpression) {
        String postfixFormOfExpression = "";
        Deque<Character> stackForNotNumbers = new ArrayDeque<>();
        char currentSymbol;
        int currentSymbolPriority;

        for (int i = 0; i < infixFormOfExpression.length(); i++) {
            currentSymbol = infixFormOfExpression.charAt(i);
            currentSymbolPriority = getSymbolPriority(currentSymbol);

            if (currentSymbolPriority == 0) {
                postfixFormOfExpression += currentSymbol;
            } else if (currentSymbolPriority == 1) {
                if (infixFormOfExpression.charAt(i + 1) == '-') {
                    postfixFormOfExpression += currentSymbol;
                } else {
                    stackForNotNumbers.push(currentSymbol);
                }
            } else if (currentSymbolPriority > 1) {
                postfixFormOfExpression += " ";
                while (!stackForNotNumbers.isEmpty()) {
                    if (getSymbolPriority(stackForNotNumbers.peek()) >= currentSymbolPriority) {
                        //postfixFormOfExpression += " ";
                        postfixFormOfExpression += stackForNotNumbers.pop();
                        postfixFormOfExpression += " ";
                    } else {
                        break;
                    }
                }
                stackForNotNumbers.push(currentSymbol);
            } else if (currentSymbolPriority == -1) {
                postfixFormOfExpression += " ";
                while (getSymbolPriority(stackForNotNumbers.peek()) != 1) {
                    postfixFormOfExpression += stackForNotNumbers.pop();
                }
                stackForNotNumbers.pop();
            }
        }

        while (!stackForNotNumbers.isEmpty()) {
            postfixFormOfExpression += " ";
            postfixFormOfExpression += stackForNotNumbers.pop();
        }

        postfixFormOfExpression = postfixFormOfExpression.replace('M', '-');
        return postfixFormOfExpression;
    }

    private int getSymbolPriority(char expressionSymbol) {
        int priority;
        if (expressionSymbol == '*' || expressionSymbol == '/') {
            priority = 3;
        } else if (expressionSymbol == '+' || expressionSymbol == '-') {
            priority = 2;
        } else if (expressionSymbol == '(') {
            priority = 1;
        } else if (expressionSymbol == ')') {
            priority = -1;
        } else {
            priority = 0;
        }
        return priority;
    }

}
