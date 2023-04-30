import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

class Myframe extends JFrame implements ActionListener{
    Container c;
    JButton button;
    JTextField tx1;
    public Myframe(){
        c=this.getContentPane();
        c.setLayout(new FlowLayout());
        
        tx1 = new JTextField();
        tx1.setPreferredSize(new Dimension(100,30));;
        c.add(tx1);

        button=new JButton("click1");
        c.add(button);
        button.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e){
        String output = tx1.getText().toString();
        System.out.println(Integer.parseInt(output)+4);
    }
}


public class ActionList {
    public static void main(String[] args) {
        Myframe frame = new Myframe();
        frame.setSize(500,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }
}
