package main.java.ru.firm;

public class ProgramControl {
    public void launchProgram () {
        ConsoleInteraction consoleInteraction = new ConsoleInteraction();
        String expression;
        ParsingForCalculation parsingForCalculation = new ParsingForCalculation();
        String expressionWithReplacedMinus;
        String expressionInPostfixForm;
        Calculation calculation = new Calculation();
        double expressionResult;
        expression = consoleInteraction.enterExpression();
        if (expression.equals("end")){
            return;
        }
        expressionWithReplacedMinus = parsingForCalculation.replaceMinusAndParenthesesByM(expression);
        expressionInPostfixForm = parsingForCalculation.translateInfixNotationToPostfix(expressionWithReplacedMinus);
        expressionResult = calculation.calculatePostfixNotationExpression(expressionInPostfixForm);
        System.out.println("Result: " + expressionResult);
    }
}
