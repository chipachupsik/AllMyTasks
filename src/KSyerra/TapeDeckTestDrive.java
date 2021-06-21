package KSyerra;

public class TapeDeckTestDrive {
    public static void main(String[] args) {
        Echo e1 = new Echo();
        Echo e2 = e1;

        int x = 0;

        while(x < 4) {
            e1.hello();
            e1.counter = e1.counter + 1;

            if(x == 3) {
                e2.counter = e2.counter + 1;
            }

            if(x > 0) {
                e2.counter = e2.counter + e1.counter;
            }

            x = x + 1;
        }

        System.out.println(e2.counter);
    }
}

class Echo {
    int counter = 0;

    void hello() {
        System.out.println("привееееет");
    }
}



