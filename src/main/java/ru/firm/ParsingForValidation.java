package main.java.ru.firm;

import java.util.ArrayList;
import java.util.List;

public class ParsingForValidation {
    List<Character> allowedOperators;

    public ParsingForValidation() {
        this.allowedOperators = new ArrayList<>();
        allowedOperators.add('+');
        allowedOperators.add('-');
        allowedOperators.add('*');
        allowedOperators.add('/');
    }

    public boolean validateExpression(String infixFormExpression) {
        boolean isExpressionValid = true;
        if (searchForDuplicatedOperators(infixFormExpression)) {
            isExpressionValid = false;
        }
        return isExpressionValid;
    }

    private boolean searchForDuplicatedOperators(String expression) {
        boolean isDuplicatedOperatorFound = false;
        for (int i = 0; i < expression.length() - 1; i++) {
            if (allowedOperators.contains(expression.charAt(i)) && allowedOperators.contains(expression.charAt(i + 1))) {
                isDuplicatedOperatorFound = true;
            }
        }
        return isDuplicatedOperatorFound;
    }

}
