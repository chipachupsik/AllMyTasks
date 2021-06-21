package Apteka;

import java.util.*;

public class DrugsController {
    public static Map<Drug, Integer> allDrugs = new HashMap<>();

    static {
        Drug panadol = new Drug();
        panadol.setName("Панадол");
        allDrugs.put(panadol, 5);

        Drug analgin = new Drug();
        analgin.setName("Анальгин");
        allDrugs.put(analgin, 18);

        Drug placebo = new Drug();
        placebo.setName("Плацебо");
        allDrugs.put(placebo, 1);
    }

    public void sell(Drug drug, int count) {
        String name = Thread.currentThread().getName();
        if(!allDrugs.containsKey(drug)) {
            System.out.println("Нет в наличии");
        }

        synchronized(allDrugs) {
            Integer currentCount = allDrugs.get(drug);
            if (currentCount < count) {
                System.out.println(String.format("%s хочет %s %d шт. В наличии - %d", name, drug.getName(), count, currentCount));
            }
        }
    }

    public void buy(Drug drug, int count) {
        System.out.println("Закупка " + drug.getName() + " " + count);
        if(!allDrugs.containsKey(drug)) {
            allDrugs.put(drug, 0);
        }
        Integer currentCount = allDrugs.get(drug);
        synchronized(allDrugs) {
            allDrugs.put(drug, (count + currentCount));
        }
    }
}
