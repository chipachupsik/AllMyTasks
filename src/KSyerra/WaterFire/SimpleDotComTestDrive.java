package KSyerra.WaterFire;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class SimpleDotComTestDrive {
        GameHelper helper = new GameHelper(); //объект отвечающий за приём ввода данных пользователем
        ArrayList<SimpleDotCom> dotComList = new ArrayList<>();
        int numOfGuesses = 0; //переменная в которой считается число попыток играющего

        public void setUpGame(){
            SimpleDotCom firstObj = new SimpleDotCom();
            firstObj.setName("Pets.com");
            SimpleDotCom secondObj = new SimpleDotCom();
            secondObj.setName("eToys.com");
            SimpleDotCom thirdObj = new SimpleDotCom();
            thirdObj.setName("Go2.com");
            dotComList.add(firstObj);
            dotComList.add(secondObj);
            dotComList.add(thirdObj);

            System.out.println("Ваша цель - потопить три сайта.");
            System.out.println("Pets.com, eToys.com, Go2.com");
            System.out.println("Попытайтесь потопить их за минимальное количество ходов");

            for (SimpleDotCom dotComToSet : dotComList) {
                ArrayList<String> newLocation = helper.placeDotCom(3); //запрашиваем у вспомогательного объекта адрес сайта
                dotComToSet.setLocationCells(newLocation); //вызываем сеттер чтобы передать местоположение полученное от вспомогательного объекта
            }
        }

        public void startPlaying(){
            while(!dotComList.isEmpty()) {
                String result = helper.getUserInput("Сделайте ход: ");
                checkUserGuess(result);
            }

            finishGame();
        }

        public void checkUserGuess(String userGuese) {
            numOfGuesses++; //инкрементируем  кол-во попыток пользователя
            String result = "Мимо"; //подразумеваем промах пока не докажем обратного

            for(SimpleDotCom dotComToTest : dotComList) { //Повторяем это для всех объектов в списке
                result = dotComToTest.checkYourself(userGuese); //Просим симплдотком проверить код пользователя, ищем попадания (или потопления)
                if(result.equals("Попал")) { //если попал выходим из цикла раньше времени
                    break;
                }

                if(result.equals("Потопил")) {
                    dotComList.remove(dotComToTest); //если потопил удаляем объект из списка
                    break;
                }
            }

            System.out.println(result);
        }

        private void finishGame() {
            System.out.println("Все сайты ушли ко дну! Ваши акции теперь ничего не стоят.");
            if(numOfGuesses <= 18) {
                System.out.println("Это заняло всего " + numOfGuesses + " попыток." );
                System.out.println("Вы успели выбраться до того, как ваши вложения утонули");
            } else {
                System.out.println("Это заняло у  вас довольно много времени." + numOfGuesses + " попыток.");
                System.out.println("Рыюыводят хороводы вокруг ваших вложений.");
            }
        }

    public static void main(String[] args) {
        SimpleDotComTestDrive game = new SimpleDotComTestDrive();
        game.setUpGame();
        game.startPlaying();
    }

}
