package KSyerra;

public class Solution {
    public static void main(String[] args) {
        Tv homeTv = new Tv();
        homeTv.changePower();
        homeTv.changeChannel(3);
        homeTv.addVolume(15);
        homeTv.changeChannel(5);
        homeTv.isPowerButton();
        homeTv.decreaseVolume(15);
        homeTv.shutdownPower();
        homeTv.isPowerButton();
    }
}
