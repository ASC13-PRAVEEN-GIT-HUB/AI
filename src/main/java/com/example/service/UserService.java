import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

interface Operation {
    double execute(double a, double b);
}

class Add implements Operation {
    public double execute(double a, double b) {
        return a + b;
    }
}

class Subtract implements Operation {
    public double execute(double a, double b) {
        return a - b;
    }
}

class Multiply implements Operation {
    public double execute(double a, double b) {
        return a * b;
    }
}

class Divide implements Operation {
    public double execute(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Cannot divide by zero");
        }
        return a / b;
    }
}

class Power implements Operation {
    public double execute(double a, double b) {
        return Math.pow(a, b);
    }
}

class Modulus implements Operation {
    public double execute(double a, double b) {
        return a % b;
    }
}

class History {
    private final List<String> records = new ArrayList<>();

    public void addRecord(String record) {
        records.add(record);
    }

    public void showHistory() {
        System.out.println("\n===== Calculation History =====");
        if (records.isEmpty()) {
            System.out.println("No calculations performed yet.");
            return;
        }

        for (String record : records) {
            System.out.println(record);
        }
    }
}

public class AdvancedCalculator {

    private static final Scanner scanner = new Scanner(System.in);
    private static final History history = new History();

    public static void main(String[] args) {

        while (true) {
            printMenu();

            int choice = getChoice();

            if (choice == 8) {
                System.out.println("Exiting Calculator...");
                break;
            }

            if (choice == 7) {
                history.showHistory();
                continue;
            }

            try {
                System.out.print("Enter first number: ");
                double num1 = scanner.nextDouble();

                System.out.print("Enter second number: ");
                double num2 = scanner.nextDouble();

                Operation operation = getOperation(choice);

                if (operation == null) {
                    System.out.println("Invalid choice.");
                    continue;
                }

                double result = operation.execute(num1, num2);

                String output = num1 + " " + getOperator(choice) + " " + num2 + " = " + result;

                System.out.println("Result: " + result);

                history.addRecord(output);

            } catch (ArithmeticException e) {
                System.out.println("Error: " + e.getMessage());

            } catch (Exception e) {
                System.out.println("Invalid input.");
                scanner.nextLine();
            }
        }

        scanner.close();
    }

    private static void printMenu() {

        System.out.println("\n========== ADVANCED CALCULATOR ==========");
        System.out.println("1. Addition");
        System.out.println("2. Subtraction");
        System.out.println("3. Multiplication");
        System.out.println("4. Division");
        System.out.println("5. Power");
        System.out.println("6. Modulus");
        System.out.println("7. View History");
        System.out.println("8. Exit");
        System.out.print("Select an option: ");
    }

    private static int getChoice() {
        while (!scanner.hasNextInt()) {
            System.out.print("Please enter a valid number: ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private static Operation getOperation(int choice) {

        switch (choice) {
            case 1:
                return new Add();

            case 2:
                return new Subtract();

            case 3:
                return new Multiply();

            case 4:
                return new Divide();

            case 5:
                return new Power();

            case 6:
                return new Modulus();

            default:
                return null;
        }
    }

    private static String getOperator(int choice) {

        switch (choice) {
            case 1:
                return "+";

            case 2:
                return "-";

            case 3:
                return "*";

            case 4:
                return "/";

            case 5:
                return "^";

            case 6:
                return "%";

            default:
                return "?";
        }
    }
}