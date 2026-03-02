package lab1_swing_fileio.src;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class ProjectFormApp {
    public static void main(String arr[]){
        JFrame frame =new JFrame("Software Project Registration Form");
        frame.setSize(500,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel panel = new ProjectFormPanel();
        frame.add(panel);
        
        frame.setVisible(true);
    }
    

}
