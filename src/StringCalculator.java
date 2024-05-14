import java.util.Scanner;

public class StringCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите выражение: ");
        String expression = scanner.nextLine();
        try {
            String result = calculate(expression);
            System.out.println("Результат: " + result);
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    public static String calculate(String expression) throws Exception {
        String[] parts = expression.split(" ");
        if (parts.length != 3) {
            throw new Exception("Неверное выражение");
        }

        String operand1 = parts[0];
        String operator = parts[1];
        String operand2 = parts[2];

        if (!operand1.startsWith("\"") || !operand1.endsWith("\"")) {
            throw new Exception("Первым операндом должна быть строка");
        }
        operand1 = operand1.substring(1, operand1.length() - 1);

        if (operand1.length() > 10) {
            throw new Exception("Длина строки не должна превышать 10 символов");
        }

        switch (operator) {
            case "+":
                if (!operand2.startsWith("\"") || !operand2.endsWith("\"")) {
                    throw new Exception("Второй операнд должен быть строкой для операции сложения");
                }
                operand2 = operand2.substring(1, operand2.length() - 1);
                if (operand2.length() > 10) {
                    throw new Exception("Длина строки не должна превышать 10 символов");
                }
                return formatResult(operand1 + operand2);
            case "-":
                if (!operand2.startsWith("\"") || !operand2.endsWith("\"")) {
                    throw new Exception("Второй операнд должен быть строкой для операции вычитания");
                }
                operand2 = operand2.substring(1, operand2.length() - 1);
                if (operand2.length() > 10) {
                    throw new Exception("Длина строки не должна превышать 10 символов");
                }
                return formatResult(operand1.replace(operand2, ""));
            case "*":
                int multiplier;
                try {
                    multiplier = Integer.parseInt(operand2);
                } catch (NumberFormatException e) {
                    throw new Exception("Второй операнд должен быть числом для операции умножения");
                }
                if (multiplier < 1 || multiplier > 10) {
                    throw new Exception("Число должно быть от 1 до 10");
                }
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < multiplier; i++) {
                    sb.append(operand1);
                }
                return formatResult(sb.toString());
            case "/":
                int divisor;
                try {
                    divisor = Integer.parseInt(operand2);
                } catch (NumberFormatException e) {
                    throw new Exception("Второй операнд должен быть числом для операции деления");
                }
                if (divisor < 1 || divisor > 10) {
                    throw new Exception("Число должно быть от 1 до 10");
                }
                int endIndex = operand1.length() / divisor;
                return formatResult(operand1.substring(0, endIndex));
            default:
                throw new Exception("Неподдерживаемая операция");
        }
    }

    private static String formatResult(String result) {
        if (result.length() > 40) {
            return result.substring(0, 40) + "...";
        }
        return result;
    }
}
