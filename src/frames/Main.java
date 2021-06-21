package frames;


import javax.swing.*;
import javax.tools.Tool;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.beans.EventHandler;

public class Main {
    static JFrame frame = getFrame();

    static JPanel panel = new JPanel();

    public static void main(String[] args) {
        frame = getFrame();
        frame.add(new MyComponent());
    }

    static class MyComponent extends JComponent {
        @Override
        public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D)g;

            Point2D point = new Point2D.Double(50, 0);
            Point2D point2 = new Point2D.Double(100, 50);

            Line2D line = new Line2D.Double(point, point2);
            g2.draw(line);

            Rectangle2D rectangle2D = new Rectangle2D.Double(100, 0, 100, 50);
            g2.draw(rectangle2D);

            RoundRectangle2D roundRectangle2D = new RoundRectangle2D.Double(200, 0, 100, 50, 20, 20);
            g2.draw(roundRectangle2D);
        }
    }


    static JFrame getFrame() {
        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        frame.setBounds(dimension.width/2 - 250, dimension.height/2 - 150, 550, 300);
        return frame;
    }
}
