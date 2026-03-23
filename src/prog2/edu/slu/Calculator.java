package prog2.edu.slu;

import prog2.edu.slu.pregroup01.Fraction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JButton;

public class Calculator extends JFrame implements ActionListener {

    // 2-Way Display fields
    private JTextField txtExpression; // Top line: shows full equation
    private JTextField txtInput;      // Bottom line: shows current input or result
    private JLabel lblDoubleResult;   // Bottom label: shows decimal equivalent

    // Calculation variables
    private Fraction op1 = null;
    private String currentOperator = "";
    private boolean isNewInput = true;

    public Calculator() {
        setTitle("2-Way Fraction Calculator");
        setSize(650, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // --- Top: 2-Way Display Area ---
        JPanel pnlDisplay = new JPanel(new BorderLayout());
        pnlDisplay.setBackground(Color.WHITE);
        pnlDisplay.setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, Color.DARK_GRAY));

        txtExpression = new JTextField("");
        txtExpression.setEditable(false);
        txtExpression.setFont(new Font("Monospaced", Font.PLAIN, 16));
        txtExpression.setHorizontalAlignment(JTextField.RIGHT);
        txtExpression.setBorder(BorderFactory.createEmptyBorder(5, 5, 0, 5));
        txtExpression.setForeground(Color.DARK_GRAY);

        txtInput = new JTextField("0");
        txtInput.setEditable(false);
        txtInput.setFont(new Font("Monospaced", Font.BOLD, 32));
        txtInput.setHorizontalAlignment(JTextField.RIGHT);
        txtInput.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));

        lblDoubleResult = new JLabel("DEC: 0.0");
        lblDoubleResult.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblDoubleResult.setHorizontalAlignment(SwingConstants.RIGHT);
        lblDoubleResult.setForeground(Color.GRAY);
        lblDoubleResult.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 5));

        pnlDisplay.add(txtExpression, BorderLayout.NORTH);
        pnlDisplay.add(txtInput, BorderLayout.CENTER);
        pnlDisplay.add(lblDoubleResult, BorderLayout.SOUTH);

        add(pnlDisplay, BorderLayout.NORTH);

        // --- Bottom: Numpad Grid ---
        JPanel pnlButtons = new JPanel();
        pnlButtons.setLayout(new GridLayout(4, 5, 8, 8));
        pnlButtons.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        pnlButtons.setBackground(Color.DARK_GRAY);

        // Numpad Layout simulating a scientific calculator
        String[] buttonLabels = {
                "7", "8", "9", "DEL", "CA",
                "4", "5", "6", "x", "÷",
                "1", "2", "3", "+", "-",
                "0", ".", "x / y", "Whole (_)", "="
        };

        for (String label : buttonLabels) {
            RoundedButton btn = new RoundedButton(label, 20);
            btn.setFont(new Font("SansSerif", Font.BOLD, 18));
            btn.setFocusPainted(false);

            // Color coding buttons for intuitive use
            if (label.matches("[0-9]+") || label.equals(".")) {
                btn.setBackground(Color.WHITE);
            } else if (label.equals("CA") || label.equals("DEL")) {
                btn.setBackground(new Color(255, 102, 102));
                btn.setForeground(Color.BLACK);
            } else if (label.equals("=")) {
                btn.setBackground(new Color(51, 153, 255));
                btn.setForeground(Color.BLACK);
            } else {
                btn.setBackground(Color.LIGHT_GRAY);
            }

            btn.addActionListener(this);
            pnlButtons.add(btn);
        }

        add(pnlButtons, BorderLayout.CENTER);
    }

    class RoundedButton extends JButton { // Rounded Corners Button
        private int radius;

        public RoundedButton(String label, int radius){
            super(label);
            this.radius = radius;
            setContentAreaFilled(false);
            setFocusPainted(false);
            setBorderPainted(false);
            setOpaque(false);
        }
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);

            super.paintComponent(g2);
            g2.dispose();

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        try {
            // 1. Handle Numbers
            if (cmd.matches("[0-9]+")) {
                if (isNewInput) {
                    txtInput.setText("");
                    isNewInput = false;
                }
                if (txtInput.getText().equals("0")) {
                    txtInput.setText(cmd);
                } else {
                    txtInput.setText(txtInput.getText() + cmd);
                }
            }
            // 2. Handle Fraction Formats
            else if (cmd.equals("Whole (_)")) {
                if (isNewInput) { txtInput.setText("0"); isNewInput = false; }
                if (!txtInput.getText().contains("_")) {
                    txtInput.setText(txtInput.getText() + "_");
                }
            }
            else if (cmd.equals("x / y")) {
                if (isNewInput) { txtInput.setText("0"); isNewInput = false; }
                if (!txtInput.getText().contains("/")) {
                    txtInput.setText(txtInput.getText() + "/");
                }
            }

            // 3. For Decimal Points

            else if (cmd.equals(".")) {
                if(isNewInput) {
                    txtInput.setText("0.");
                    isNewInput = false;
                } else if (!txtInput.getText().contains(".")) {
                    txtInput.setText(txtInput.getText() + ".");
                }
            }


            // 4. Handle Clear and Delete
            else if (cmd.equals("CA")) {
                resetCalculator();
            }
            else if (cmd.equals("DEL")) {
                String current = txtInput.getText();
                if (current.length() > 0 && !isNewInput) {
                    txtInput.setText(current.substring(0, current.length() - 1));
                    if (txtInput.getText().isEmpty()) txtInput.setText("0");
                }
            }
            // 5. Handle Operators
            else if (cmd.equals("+") || cmd.equals("-") || cmd.equals("x") || cmd.equals("÷")) {
                int valid = 0;
                if (!txtInput.getText().isEmpty() && !txtInput.getText().equals("Error")) {
                    if (op1 != null && !currentOperator.isEmpty() && !isNewInput) {
                        valid = 1;
                        Fraction op2 = parseToFraction(txtInput.getText());
                        Fraction result = null;
                        switch (currentOperator) {
                            case "+": result = op1.add(op2); break;
                            case "-": result = op1.subtract(op2); break;
                            case "x": result = op1.multiplyBy(op2); break;
                            case "÷": result = op1.divideBy(op2); break;
                        }

                        if (result != null) {
                            txtExpression.setText(txtExpression.getText() + " " + txtInput.getText() + " = " + result);
                            // Update Bottom Screen with result
                            String resultStr = result.toString();
                            // If it's a MixedNumber formatting, fix the string if needed (optional based on your MixedNumber toString implementation)
                            txtInput.setText(resultStr);

                            // Update Decimal label
                            lblDoubleResult.setText("DEC: " + String.format("%.4f", result.toDouble()));

                            // Prepare for chained calculations
                            op1 = result;
                            currentOperator = "";
                        }
                    }
                    op1 = parseToFraction(txtInput.getText());
                    currentOperator = cmd;
                    if (valid != 1) {
                        txtExpression.setText(txtInput.getText() + " " + currentOperator);
                        isNewInput = true;
                    }
                    else {
                        txtExpression.setText(txtExpression.getText() + " " + currentOperator);
                        txtInput.setText(op1 + "");
                        isNewInput = true;
                    }
                }
            }
            // 6. Handle Calculation
            else if (cmd.equals("=")) {
                if (op1 != null && !currentOperator.isEmpty() && !isNewInput) {
                    Fraction op2 = parseToFraction(txtInput.getText());
                    Fraction result = null;

                    // Execute operation utilizing your custom classes
                    switch (currentOperator) {
                        case "+": result = op1.add(op2); break;
                        case "-": result = op1.subtract(op2); break;
                        case "x": result = op1.multiplyBy(op2); break;
                        case "÷": result = op1.divideBy(op2); break;
                    }

                    if (result != null) {
                        // Update Top Screen with full equation
                        txtExpression.setText(txtExpression.getText() + " " + txtInput.getText() + " =");

                        // Update Bottom Screen with result
                        String resultStr = result.toString();
                        // If it's a MixedNumber formatting, fix the string if needed (optional based on your MixedNumber toString implementation)
                        txtInput.setText(resultStr);

                        // Update Decimal label
                        lblDoubleResult.setText("DEC: " + String.format("%.4f", result.toDouble()));

                        // Prepare for chained calculations
                        op1 = result;
                        currentOperator = "";
                        isNewInput = true;
                    }
                }
            }
        } catch (Exception ex) {
            txtInput.setText("Syntax Error");
            isNewInput = true;
        }
    }

    /**
     * Instantiates the proper class (MixedNumber or Fraction) based on user input.
     * This fulfills the requirement to utilize the classes you created.
     */
    // Inside Calculator.java - updated parseToFraction
    private Fraction parseToFraction(String input) throws Exception {
        input = input.trim();
        if (input.isEmpty() || input.equals("Error")) throw new Exception();

        if (input.contains("_")) { // Mixed Number: 1_1/2
            String[] parts = input.split("_");
            int whole = Integer.parseInt(parts[0]);
            String[] fracParts = parts[1].split("/");
            return new MixedNumber(whole, Integer.parseInt(fracParts[0]), Integer.parseInt(fracParts[1]));

        } else if (input.contains("/")) { // Simple Fraction: 1/2
            String[] parts = input.split("/");
            return new Fraction(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));

        } else if (input.contains(".")) { // Decimal Number
            // Convert decimal into a fraction (1.5 -> 15/10)
            String[] parts = input.split("\\.");
            String whole = parts[0];
            String fractional = parts.length > 1 ? parts[1] : "0";
            int denominator = (int) Math.pow(10, fractional.length());
            int numerator = Integer.parseInt(whole + fractional);
            return new Fraction(numerator, denominator);

        } else { // Whole number: 5
            return new Fraction(Integer.parseInt(input), 1);
        }
    }

    private void resetCalculator() {
        txtInput.setText("0");
        txtExpression.setText("");
        lblDoubleResult.setText("DEC: 0.0");
        op1 = null;
        currentOperator = "";
        isNewInput = true;
    }



    public static void main(String[] args) {
        // Sets look and feel to system default to look more like a native app
        try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); }
        catch (Exception e) { /* Ignored */ }

        SwingUtilities.invokeLater(() -> {
            Calculator calc = new Calculator();
            calc.setLocationRelativeTo(null);
            calc.setVisible(true);
        });
    }
}