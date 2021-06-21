package KSyerra;

public class Tv {
    int channel;
    int volume;
    boolean powerButton;

    public Tv() { //конструктор который включает телевизор и устанавливает ему начальные значения переменных
    }

    public void changePower() {//метод включения телевизора
        powerButton = true;
        System.out.println("Включаем телевизор!");
    }

    public void shutdownPower() { //метод выключения телевизора
        powerButton = false;
        System.out.println("Выключаем телевизор");
    }

    public void isPowerButton() {//метод проверки включен ли телевизор
        if(powerButton == true) {
            System.out.println("Телевизор работает.");
        } else
            System.out.println("Телевизор выключен");
    }

    public void changeChannel(int channel) {
        this.channel = channel;
        System.out.println("Переключаем на " + channel + " канал");
    }

    public void decreaseVolume(int volume) {
        if(this.volume <= 0) {
            System.out.println("Звук выключен.");
        } else {
            this.volume -= volume;
            System.out.println("Громкость уменьшена на " + volume);
        }
    }

    public void addVolume(int volume) {
        if(this.volume >= 100) {
            System.out.println("Звук выкручен на максимум!");
        } else {
            this.volume += volume;
            System.out.println("Громкость прибавлена на " + volume);
        }
    }
}
