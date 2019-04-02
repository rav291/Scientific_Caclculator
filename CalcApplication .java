import javax.swing.*;
import java.awt.*;

class CalcApplication {
    public static void main(String args[]) {
        JFrame  mainFrame = new JFrame();
        mainFrame.setSize(400, 300);
        Container  contentPane=mainFrame.getContentPane();
        CalculatorPanel panel=new CalculatorPanel();
        contentPane.add(panel);
        mainFrame.setVisible(true);
    }
}