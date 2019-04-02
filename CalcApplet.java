//Create an applet for calculator

//CalcApplet.java

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalcApplet extends JApplet
{
    public void init()
    {
        Container  contentPane=getContentPane();
        CalculatorPanel panel=new CalculatorPanel();
        contentPane.add(panel);
    }
}

class CalculatorPanel extends JPanel
{
    public CalculatorPanel()
    {
        setLayout(new BorderLayout());

        result = 0;
        lastCommand = "=";
        start = true;

        display = new JTextField("0.0");
        display.setEditable(false);
        add(display,BorderLayout.NORTH);

        ActionListener insert = new InsertAction();
        ActionListener command = new CommandAction();

        panel = new JPanel();
        panel.setLayout(new GridLayout(8,4));

        addButton("sin",command);
        addButton("cos",command);
        addButton("tan",command);
        addButton("A/C",command);
        addButton("asin",command);
        addButton("acos",command);
        addButton("atan",command);
        addButton("n!",command);
        addButton("log",command);
        addButton("ln",command);
        addButton("Pi",insert);
        addButton("e",insert);
        addButton("x^(1/y)",command);
        addButton("x^y",command);
        addButton("Sqrt",command);
        addButton("/",command);
        addButton("9",insert);
        addButton("8",insert);
        addButton("7",insert);
        addButton("x",command);
        addButton("6",insert);
        addButton("5",insert);
        addButton("4",insert);
        addButton("+",command);
        addButton("3",insert);
        addButton("2",insert);
        addButton("1",insert);
        addButton("_",command);
        addButton("0",insert);
        addButton("+/-",command);
        addButton("=",command);
        addButton(".",insert);

        add(panel,BorderLayout.CENTER);
    }

    private void addButton(String label,ActionListener listener)
    {
        JButton button = new JButton(label);
        button.addActionListener(listener);
        panel.add(button);
    }


    private class InsertAction implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            String input = event.getActionCommand();
            if(start)
            {
                display.setText("");
                start = false;
            }
            if(input.equals("e"))
            {
                if(display.getText().equals("-"))
                    display.setText("-"+Math.E);
                else
                    display.setText(""+Math.E);
                start=true;
            }
            else if(input.equals("Pi"))
            {
                if(display.getText().equals("-"))
                    display.setText("-"+Math.PI);
                else
                    display.setText(""+Math.PI);
                start=true;
            }
            else
                display.setText(display.getText()+input);
        }
    }

    private class CommandAction implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            String command = event.getActionCommand();
            String co = command;
            if(co.equals("A/C"))
            {
                result = 0;
                display.setText(""+result);
                lastCommand = "=";
                start=true;
            }
            else if((co.equals("+/-"))||(co.equals("Sin"))
                    ||(co.equals("Cos"))||(co.equals("Tan"))||(co.equals("Sqrt"))
                    ||(co.equals("n!"))||(co.equals("ln"))||(co.equals("aSin"))
                    ||(co.equals("aCos"))||(co.equals("aTan"))||(co.equals("log")))
            {
                lastCommand=command;
                calculate(Double.parseDouble(display.getText()));
                lastCommand="=";
                start=true;
            }
            else if(start&&(lastCommand.equals("="))&&(result==0)
                    &&(display.getText().equals("0.0")))
            {
                if(command.equals("_"))
                {
                    display.setText("-");
                    start = false;
                }
                else
                    lastCommand = command;
            }
            else
            {
                calculate(Double.parseDouble(display.getText()));
                lastCommand=command;
                start= true;
            }
        }
    }

    public void calculate(double x)
    {
        if(lastCommand.equals("+")) result += x;
        else if(lastCommand.equals("_")) result -= x;
        else if(lastCommand.equals("x")) result *= x;
        else if(lastCommand.equals("/")) result /= x;
        else if(lastCommand.equals("+/-")) result = -x;
        else if(lastCommand.equals("Sin")) result = Math.sin(x*Math.PI/180.0);
        else if(lastCommand.equals("Cos")) result = Math.cos(x*Math.PI/180.0);
        else if(lastCommand.equals("Tan")) result = Math.tan(x*Math.PI/180.0);
        else if(lastCommand.equals("=")) result = x;
        else if(lastCommand.equals("x^y")) result = Math.pow(result,x);
        else if(lastCommand.equals("Sqrt")) result = Math.sqrt(x);
        else if(lastCommand.equals("n!")) result = fact((int)x);
        else if(lastCommand.equals("ln")) result = Math.log(x);
        else if(lastCommand.equals("aSin")) result = Math.asin(x)*180/Math.PI;
        else if(lastCommand.equals("aCos")) result = Math.acos(x)*180/Math.PI;
        else if(lastCommand.equals("aTan")) result = Math.atan(x)*180/Math.PI;
        else if(lastCommand.equals("log")) result = Math.log(x)/Math.log(10.0);
        else if(lastCommand.equals("x^(1/y)")) result = Math.pow(result,1.0/x);

        display.setText(""+result);
    }

    private int fact(int n)
    {
        if((n==0)||(n==1)) return 1;
        else return n*fact(n-1);
    }

    private JTextField display;
    private JPanel panel;
    private double result;
    private String lastCommand;
    private boolean start;
}