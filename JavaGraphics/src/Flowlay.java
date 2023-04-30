import java.awt.*;
import java.util.concurrent.Flow;

import javax.swing.*;

public class Flowlay {
    public static void main(String args[]){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Title here");
        frame.setSize(400,400);
        frame.setVisible(true);

        Container c = frame.getContentPane();
        c.setLayout(new FlowLayout(FlowLayout.LEFT));

        JButton b1 = new JButton("button1");
        JButton b2 = new JButton("button2");

        c.add(b1);
        c.add(b2,0);
    }
}
