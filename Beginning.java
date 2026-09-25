package FoodInventorySystem;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
public class Beginning {
    public static void main(String[]args)
    {
        JFrame frame=new JFrame("Good Afternoon");
        frame.setSize(500,200);
        frame.setVisible(true);
        JLabel label=new JLabel("Hai");
        JButton b=new JButton("OK");
        label.setBounds(10,15,15,15);
        b.setBounds(50,100,90,100);
        frame.add(label);
        frame.add(b);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}


