package KSyerra;


public class TestBoats {
    public static void main(String[] args) {
        Boat b1 = new Boat();
        SailBoat b2 = new SailBoat();
        Rowboat b3 = new Rowboat();
        b2.setLength(32);
        b1.move();
        b3.move();
        b2.move();
    }
}

class Boat {
    private int length;

    public void setLength(int len) {
        length = len;
    }

    public int getLength() {
        return length;
    }

    public void move() {
        System.out.print("Дрейф! ");
    }

}

class Rowboat extends Boat {
    public void rowTheBoat() {
        System.out.print("Давай, Наташа!");
    }
}

class SailBoat extends Boat {
    public void move() {
        System.out.print("Поднять паруса!");
    }
}