import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

class Myframe2 extends JFrame implements KeyListener{
    Container c;
    JTextField tx1;
    Myframe2(){
        c = this.getContentPane();
        c.setLayout(new FlowLayout());

        tx1 = new JTextField();
        tx1.setPreferredSize(new Dimension(100,30));

        c.add(tx1);
        tx1.addKeyListener(this);

    }

    @Override
    public void keyPressed(KeyEvent e){
        System.out.println("its pressed");
        System.out.println(e.getKeyChar());
    }

    @Override
    public void keyReleased(KeyEvent e){
        System.out.println("it's released");
    }

    @Override
    public void keyTyped(KeyEvent e){
        System.out.println("typing...");
    }
}


public class KeyListn {
    public static void main(String[] args) {
        Myframe2 frame = new Myframe2();
        frame.setDefaultCloseOperation(Myframe2.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(500,500);

    }
    
}
