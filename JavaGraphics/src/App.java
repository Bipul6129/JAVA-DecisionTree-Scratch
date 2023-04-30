import javax.swing.*;
import java.awt.*;
public class App {
    public static void main(String[] args) throws Exception {
        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,400);
        frame.setTitle("TITLE here");

        Container c = frame.getContentPane();
        c.setLayout(null);

        JButton b1 = new JButton("oNE");

        b1.setBounds(20,20,80,30);

        c.add(b1);


    }
}
