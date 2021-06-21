package ServerAndClinet;

import java.io.*;
import java.net.*;

public class DailyAdviceServer {
    String[] adviceList = {"Курите травку", "Почитайте Стивена Кинга. Да, он точно интересный писатель", "Всё получится - других вариантов нет",
    "Пожалуй пора написать новый хит и возрвать чарты"};

    public void go() {
        try {
            ServerSocket serverSocket = new ServerSocket(4242);

            while(true) {
                Socket sock = serverSocket.accept();

                PrintWriter writer = new PrintWriter(sock.getOutputStream());
                String advice = getAdvice();
                writer.println(advice);
                writer.close();
                System.out.println(advice);
            }


        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public String getAdvice() {
        int random = (int) (Math.random() * adviceList.length);
        return adviceList[random];
    }

    public static void main(String[] args) {
        DailyAdviceServer server =  new DailyAdviceServer();
        server.go();
    }
}
