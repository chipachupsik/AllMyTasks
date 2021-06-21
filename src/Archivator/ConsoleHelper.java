package Archivator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class ConsoleHelper {
    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws IOException {
        String result = bis.readLine();
        return result;
    }

    public static int readInt() throws IOException {
        String result = bis.readLine();

        return Integer.parseInt(result);
    }
}
