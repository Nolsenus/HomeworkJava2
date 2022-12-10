import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Task2 {

    static int[] getNumArray(int length, int minValue, int maxValue) {
        int[] result = new int[length];
        ThreadLocalRandom rnd = ThreadLocalRandom.current();
        for (int i = 0; i < length; i++) {
            result[i] = rnd.nextInt(minValue, maxValue + 1);
        }
        return result;
    }

    static String intArrayToString(int[] array) {
        int length = array.length;
        if (length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length - 1; i++) {
            sb.append(array[i]);
            sb.append(", ");
        }
        sb.append(array[length - 1]);
        return sb.toString();
    }

    static void printIntArray(int[] array) {
        System.out.println(intArrayToString(array));
    }

    static void bubbleSortLogged(int[] array, String logFileName) throws IOException {
        Logger log = Logger.getLogger(Task2.class.getName());
        FileHandler fh = new FileHandler(logFileName);
        SimpleFormatter sf = new SimpleFormatter();
        fh.setFormatter(sf);
        log.addHandler(fh);
        log.info("Start");
        log.info(intArrayToString(array));
        int len = array.length;
        if (len < 2) {
            log.info(intArrayToString(array));
            return;
        }
        for (int i = len; i > 0; i--) {
            for (int j = 1; j < i; j++) {
                if (array[j - 1] > array[j]) {
                    int temp = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = temp;
                }
                log.info(intArrayToString(array));
            }
        }
        log.info("End");
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int length;
        while (true) {
            System.out.print("Введите длину массива: ");
            while (!in.hasNextInt()) {
                System.out.println("Вы ввели не целое число. Попробуйте снова.");
                in.nextLine();
                System.out.print("Введите длину массива: ");
            }
            length = in.nextInt();
            if (length > 0) {
                break;
            }
            System.out.println("Длина массива должна быть больше нуля. Попробуйте снова.");
        }
        System.out.print("Введите минимальное значение, которое могут принимать элементы массива: ");
        while (!in.hasNextInt()) {
            System.out.println("Вы ввели не целое число. Попробуйте снова.");
            in.nextLine();
            System.out.print("Введите минимальное значение, которое могут принимать элементы массива: ");
        }
        int minValue = in.nextInt();
        System.out.print("Введите максимальное значение, которое могут принимать элементы массива: ");
        while (!in.hasNextInt()) {
            System.out.println("Вы ввели не целое число. Попробуйте снова.");
            in.nextLine();
            System.out.print("Введите максимальное значение, которое могут принимать элементы массива: ");
        }
        int maxValue = in.nextInt();
        int[] array = getNumArray(length, minValue, maxValue);
        printIntArray(array);
        try {
            bubbleSortLogged(array, "log2.txt");
        } catch (IOException e) {
            System.out.println("Произошла ошибка: ");
            e.printStackTrace();
        }
        printIntArray(array);
    }
}
