package ATM;

import java.util.ArrayList;
import java.util.List;

public class Solution2 {
    public volatile static int proposal = 0;

    public static void main(String[] args) {
        new AcceptProposal().start();
        new MakeProposal().start();
    }

    public static class MakeProposal extends Thread {
        @Override
        public void run() {
            int thisЗroposal = proposal;

            while(proposal < 10) {
                System.out.println("Сделано предложение №" + (thisЗroposal + 1));
                proposal = ++thisЗroposal;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static class AcceptProposal extends Thread {
        @Override
        public void run() {
            int thisProposal = proposal;

            while(thisProposal < 10) {
                if(thisProposal != proposal) {
                    System.out.println("Принято предложение №" + proposal);
                    thisProposal = proposal;
                }
            }
        }
    }

}
