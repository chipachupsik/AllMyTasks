package KSyerra.WaterFire;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class GameHelper {

    private static final String alphabet = "abcdefg";
    private int gridLength = 7;
    private int gridSize = 49;
    private int[] grid = new int[gridSize];
    private int comCount = 0;


    public String getUserInput(String prompt) {
        String inputLine = null;
        System.out.print(prompt + " ");

        try {
            BufferedReader is = new BufferedReader(new InputStreamReader(System.in));
            inputLine = is.readLine();
            if(inputLine.length() == 0)
                return null;
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }

        return inputLine.toLowerCase();
    }

    public ArrayList<String> placeDotCom(int comSize) {
        ArrayList<String> alphaCells = new ArrayList<>();
        String[] alphacoords = new String[comSize]; //хранит координаты типа f6
        String temp = null; //временная строка для конкатенации
        int[] coords = new int[comSize]; //координаты текущего сайта
        int attempts = 0; //счётчик текуших попыток
        boolean success = false; //нашли подходящее местоположение?
        int location = 0; //текущее местоположение

        comCount++; //энный сайт для размещения
        int incr = 1; //устанавливаем горизонтальный инкремент
        if((comCount & 2) == 1) { //если нечётный (размещаем вертикально)
            incr = gridLength; //устанавливаем вертикальный инкремент
        }

        while(!success & attempts++ < 200) {
            location = (int) (Math.random() * gridSize);
            int x = 0;
            success = true;

            while(success && x < comSize) {
                if(grid[location] == 0) {
                    coords[x++] = location;
                    location += incr;
                    if(location >= gridSize) {
                        success = false;
                    }
                    if(x > 0 && (location % gridLength == 0)) {
                        success = false;
                    }
                } else {
                    success = false;
                }
            }
        }

        int x = 0;
        int row = 0;
        int column = 0;

        while(x < comSize) {
            grid[coords[x]] = 1;
            row = (int) (coords[x] / gridLength);
            column = coords[x] % gridLength;
            temp = String.valueOf(alphabet.charAt(column));

            alphaCells.add(temp.concat(Integer.toString(row)));
            x++;
        }

        return alphaCells;
    }
}
