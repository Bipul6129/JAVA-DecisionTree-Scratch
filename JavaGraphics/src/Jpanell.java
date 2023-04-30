import javax.swing.*;
import java.awt.*;

public class Jpanell {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        frame.setVisible(true);

        Container c = frame.getContentPane();
        c.setLayout(null);

        JPanel p1 = new JPanel();
        p1.setBounds(0,0,300,300);
        p1.setBackground(Color.yellow);
        p1.setLayout(new BorderLayout());
        c.add(p1);

        JButton b1 = new JButton("button");
        p1.add(b1,BorderLayout.PAGE_END);

        


    }
}
