package KSyerra;

import java.io.*;
import java.util.ArrayList;

public class ArrayListMagnet {
    public static void main(String[] args) throws Exception {
        FileOutputStream fos = new FileOutputStream(new File("Foo.ser"));
        ObjectOutputStream os = new ObjectOutputStream(fos);

        ArrayList<String> a = new ArrayList<>();
        a.add(0, "ноль");
        a.add(1, "один");
        a.add(2, "два");
        a.add(3, "три");
        printAl(a);
        a.remove(2);

        if(a.contains("три")) {
            a.add("четыре");
        }
        printAl(a);

        if(a.indexOf("четыре") != 4) {
            a.add(4, "4.2");
        }
        printAl(a);

        if(a.contains("два")) {
            a.add("2.2");
        }
        printAl(a);

    }

    public static void printAl(ArrayList<String> al) {
        for(String element : al) {
            System.out.print(element + " ");
        }
        System.out.println("");
    }
}
