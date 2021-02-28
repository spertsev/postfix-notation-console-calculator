package main.java.ru.firm;

import java.util.Scanner;

public class ConsoleInteraction {
    private Scanner scanner;
    private ParsingForValidation parsingForValidation;

    public ConsoleInteraction() {
        this.scanner = new Scanner(System.in);
        this.parsingForValidation = new ParsingForValidation();
    }

    public String enterExpression() {
        String enteredExpression = "";
        boolean isToInputExpressionNeeded = true;
        do {
            System.out.print("Input an expression to calculate it or input 'end' to stop the program: ");
            enteredExpression = scanner.nextLine();
            if (enteredExpression.equals("end")) {
                System.out.println("The program is closing.");
                break;
            }
            if (!parsingForValidation.validateExpression(enteredExpression)) {
                System.out.println("The entered expression is incorrect, please try again.");
                continue;
            }
            isToInputExpressionNeeded = false;
        } while (isToInputExpressionNeeded);
        return enteredExpression;
    }

}
