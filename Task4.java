import java.io.IOException;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Task4 {

    static double enterDouble(String invitation, Scanner in) {
        double result;
        System.out.print(invitation);
        while (!in.hasNextDouble()) {
            in.nextLine();
            System.out.println("Вы ввели не число. Попробуйте снова.");
            System.out.print(invitation);
        }
        result = in.nextDouble();
        in.nextLine();
        return result;
    }

    static char enterSign(Scanner in) {
        char result;
        String test;
        while (true) {
            System.out.print("Введите знак операции: ");
            test = in.nextLine();
            if (test.equals("+") || test.equals("-") || test.equals("*") || test.equals("/")) {
                result = test.charAt(0);
                break;
            }
            System.out.println("Вы ввели что-то не то. Попробуйте снова.");
        }
        in.close();
        return result;
    }

    static double calc(double first, double second, char operator) {
        if (operator == '+') return first + second;
        if (operator == '-') return first - second;
        if (operator == '*') return first * second;
        return first / second;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Logger log = Logger.getLogger(Task4.class.getName());
        try {
            FileHandler fh = new FileHandler("log4.txt");
            SimpleFormatter sf = new SimpleFormatter();
            fh.setFormatter(sf);
            log.addHandler(fh);
            double first = enterDouble("Введите первое число: ", in);
            log.info(String.format("Первое число: %f", first));
            double second = enterDouble("Введите второе число: ", in);
            log.info(String.format("Второе число: %f", second));
            char sign = enterSign(in);
            log.info(String.format("Знак: %c", sign));
            in.close();
            if (sign == '/' && second == 0) {
                System.out.println("Деление на ноль не поддерживается.");
                log.info("Деление на ноль.");
            } else {
                String result = String.format("Результат: %f", calc(first, second, sign));
                System.out.println(result);
                log.info(result);
            }
        } catch (IOException e) {
            System.out.println("Произошла ошибка:");
            e.printStackTrace();
        }
    }
}
