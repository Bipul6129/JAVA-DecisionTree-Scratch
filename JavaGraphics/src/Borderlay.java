import java.awt.*;
import javax.swing.*;

public class Borderlay {
    public static void main(String args[]){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(400,400);

        Container c = frame.getContentPane();
        c.setLayout(new BorderLayout());

        JButton b1 = new JButton("pagestart");
        JButton b2 = new JButton("pagestart2");

        c.add(b1,BorderLayout.PAGE_START);
        c.add(b2,BorderLayout.PAGE_END);
    }
}
