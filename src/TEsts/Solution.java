package TEsts;

public class Solution {
    public static void main(String[] args) {
        System.out.println(Recursion(123));
    }


    private static int Recursion(int n) {
        if(n < 10) {
            return n;
        } else {

            System.out.print(n/10 + " ");
            return Recursion(n % 10);
        }
    }
}
