package ThreadGroup;

public class Solution4 {

    public static void main(String[] args) {
        for(int i = 0; i < 12; i++) {
            System.out.print(new MyThread().getPriority() + " ");
        }

        System.out.println();
        ThreadGroup group = new ThreadGroup("someName");
        group.setMaxPriority(7);

        for(int i = 0; i < 12; i++) {
            System.out.print(new MyThread(group, "name" + i).getPriority() + " ");
        }
    }
}
