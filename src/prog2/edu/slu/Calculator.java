// LAUNCH UI HERE

package prog2.edu.slu;

import javax.swing.*;
import java.awt.*;

public class Calculator extends JFrame {

    public Calculator() {
        setTitle("Calculator");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JPanel btnPanel = new JPanel(new GridLayout(4, 5, 10, 10));
        JButton addBtn = new JButton("+");
        JButton subBtn = new JButton("-");
        JButton mulBtn = new JButton("\u00D7");
        JButton divBtn = new JButton("\u00F7");
        JButton btn0 = new JButton("0");
        JButton btn1 = new JButton("1");
        JButton btn2 = new JButton("2");
        JButton btn3 = new JButton("3");
        JButton btn4 = new JButton("4");
        JButton btn5 = new JButton("5");
        JButton btn6 = new JButton("6");
        JButton btn7 = new JButton("7");
        JButton btn8 = new JButton("8");
        JButton btn9 = new JButton("9");
        JButton fracBtn = new JButton("<html><sup>x</sup>&frasl;<sub>y</sub></html>");
        JButton mixedBtn = new JButton("<html>A<sup>b</sup>&frasl;<sub>c</sub></html>");
        JButton delBtn = new JButton("DEL");
        JButton calBtn = new JButton("CA");
        JButton equalsBtn = new JButton("=");
        JButton decBtn = new JButton(".");
        btnPanel.add(btn7);
        btnPanel.add(btn8);
        btnPanel.add(btn9);
        btnPanel.add(delBtn);
        btnPanel.add(calBtn);
        btnPanel.add(btn4);
        btnPanel.add(btn5);
        btnPanel.add(btn6);
        btnPanel.add(mulBtn);
        btnPanel.add(divBtn);
        btnPanel.add(btn1);
        btnPanel.add(btn2);
        btnPanel.add(btn3);
        btnPanel.add(addBtn);
        btnPanel.add(subBtn);
        btnPanel.add(btn0);
        btnPanel.add(decBtn);
        btnPanel.add(fracBtn);
        btnPanel.add(mixedBtn);
        btnPanel.add(equalsBtn);
        add(btnPanel);

        setVisible(true);
    }

    public static void main(String[] args) {
        new Calculator();
    }
}