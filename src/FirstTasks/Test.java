package FirstTasks;

import java.util.Arrays;
import java.util.Scanner;

public class Test {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Введите размерность массива: ");
        int masLen = scanner.nextInt();
        PrintMas(InputElements(GetMas(masLen)));
    }

    public static int[] GetMas(int a) {
        return new int[a];
    }

    public static int[] InputElements(int[] mas) {
        int counter = 0;

        do {
            System.out.println("Введите элемент: ");
            mas[counter] = scanner.nextInt();
            counter++;

        }while (counter != mas.length);

        return mas;
    }

    public static void PrintMas(int[] mas) {
        System.out.print("Массив = ");
        for(int i : mas) {
            System.out.print(" " + i);
        }
    }

}
