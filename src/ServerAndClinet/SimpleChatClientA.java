package ServerAndClinet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SimpleChatClientA {

    JTextField outgoing;
    PrintWriter writer;
    Socket sock;
    JTextArea incoming;
    BufferedReader reader;

    public static void main(String[] args) {
        new SimpleChatClientA().go();
    }

    public void go() {
        JFrame frame = new JFrame("Simple Chat Client by ChipaChip");
        JPanel mainPanel = new JPanel();
        outgoing = new JTextField(20);//создаём текстовое поле ввода шириной в 20 символов
        JButton sendButton = new JButton("Send");//создаём кнопку для отправления сообщений
        sendButton.addActionListener(new SendButtonListener());
        mainPanel.add(outgoing);//добавляем в созданную раннее панель текстовое поле
        mainPanel.add(sendButton);//добавляем в созданную раннее панель кнопку для отправления сообщений
        setUpNetWorking();

        Thread readerThread = new Thread(new IncomingReader());
        readerThread.start();

        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);//добавляем панель на созданный раннее frame
        frame.setSize(400, 500);
        frame.setVisible(true);
    }

    public void setUpNetWorking() {
        try {
            sock = new Socket("127.0.0.1", 5000);
            InputStreamReader streamReader = new InputStreamReader(sock.getInputStream());
            reader = new BufferedReader(streamReader);
            writer = new PrintWriter(sock.getOutputStream());
            System.out.println("Соединение установлено");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public class SendButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                writer.println(outgoing.getText());//получаем текст, введёный в текстовое поле
                writer.flush();

            } catch (Exception ex) {
                ex.printStackTrace();
            }
            outgoing.setText("");
            outgoing.requestFocus();
        }
    }

    public class IncomingReader implements Runnable {
        @Override
        public void run() {
            String message;
            try {
                while((message = reader.readLine()) != null) {
                    System.out.println("read " + message);
                    incoming.append(message + "\n");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
