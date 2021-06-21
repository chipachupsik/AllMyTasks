package KSyerra.WaterFire;

import java.util.ArrayList;

public class SimpleDotCom {
    private String name;
    private ArrayList<String> locationCells;

    public void setLocationCells(ArrayList<String> locations) {
        locationCells = locations;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String checkYourself(String guess) {
        String result = "Мимо";
        int index = locationCells.indexOf(guess);
            if (index >= 0) {
                locationCells.remove(index);

                if (locationCells.isEmpty()) {
                    result = "Потопил";
                    System.out.println("Ой! Вы потопили" + name + " : ( ");
                } else {
                    result = "Попал";
                }
            }

        return result;
    }

}

