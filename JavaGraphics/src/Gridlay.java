import java.awt.*;
import javax.swing.*;
public class Gridlay {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,400);
        frame.setVisible(true);

        Container c = frame.getContentPane();
        c.setLayout(new GridLayout(2,0));

        JButton b1 = new JButton("B1");
        JButton b2 = new JButton("B2");

        c.add(b1);
        c.add(b2);


    }
}
