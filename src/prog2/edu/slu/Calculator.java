package prog2.edu.slu;

import prog2.edu.slu.pregroup01.Fraction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame implements ActionListener {

    private JLabel lblExpression;
    private JLabel lblInput;
    private JLabel lblDoubleResult;

    private Fraction op1 = null;
    private String currentOperator = "";
    private boolean isNewInput = true;

    private Fraction currentResult = null;
    private boolean displayAsMixed = false;

    private String rawInput = "0";
    private String rawExpression = "";

    public Calculator() {
        setTitle("2-Way Fraction Calculator");
        setSize(650, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel pnlDisplay = new JPanel(new BorderLayout());
        pnlDisplay.setBackground(Color.WHITE);
        pnlDisplay.setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, Color.DARK_GRAY));

        lblExpression = new JLabel("");
        lblExpression.setFont(new Font("SansSerif", Font.PLAIN, 16));
        lblExpression.setHorizontalAlignment(SwingConstants.RIGHT);
        lblExpression.setBorder(BorderFactory.createEmptyBorder(5, 5, 0, 5));
        lblExpression.setForeground(Color.DARK_GRAY);

        lblInput = new JLabel(toFractionHtml("0"));
        lblInput.setFont(new Font("SansSerif", Font.BOLD, 32));
        lblInput.setHorizontalAlignment(SwingConstants.RIGHT);
        lblInput.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));

        lblDoubleResult = new JLabel("DEC: 0.0");
        lblDoubleResult.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblDoubleResult.setHorizontalAlignment(SwingConstants.RIGHT);
        lblDoubleResult.setForeground(Color.GRAY);
        lblDoubleResult.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 5));

        RoundedButton btnSD = new RoundedButton("S<=>D", 10);
        btnSD.setFont(new Font("SansSerif", Font.BOLD, 12));
        btnSD.setBackground(new Color(255, 204, 153));
        btnSD.setForeground(Color.BLACK);
        btnSD.setFocusPainted(false);
        btnSD.addActionListener(this);

        JPanel pnlBottomDisplay = new JPanel(new BorderLayout());
        pnlBottomDisplay.setBackground(Color.WHITE);
        pnlBottomDisplay.add(lblDoubleResult, BorderLayout.CENTER);
        pnlBottomDisplay.add(btnSD, BorderLayout.EAST);

        pnlDisplay.add(lblExpression, BorderLayout.NORTH);
        pnlDisplay.add(lblInput, BorderLayout.CENTER);
        pnlDisplay.add(pnlBottomDisplay, BorderLayout.SOUTH);

        add(pnlDisplay, BorderLayout.NORTH);

        JPanel pnlButtons = new JPanel();
        pnlButtons.setLayout(new GridLayout(4, 5, 8, 8));
        pnlButtons.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        pnlButtons.setBackground(Color.DARK_GRAY);

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

    class RoundedButton extends JButton {
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

    private void setRawInput(String val) {
        rawInput = val;
        lblInput.setText(toFractionHtml(val));
    }

    private void setRawExpression(String val) {
        rawExpression = val;
        lblExpression.setText(toExpressionHtml(val));
    }

    private String toFractionHtml(String val) {
        if (val == null || val.isEmpty()) return "";
        if (val.equals("Math Error") || val.equals("Syntax Error")) return val;

        if (val.contains("_")) {
            String[] parts = val.split("_");
            String whole = parts[0];
            String fracPart = parts.length > 1 ? parts[1] : "";
            if (fracPart.contains("/")) {
                String[] frac = fracPart.split("/");
                String num = frac[0];
                String den = frac.length > 1 ? frac[1] : "";
                return "<html><table style='font-size:24pt; color:#000000;' cellpadding='0' cellspacing='0'><tr><td rowspan='2' valign='middle'>" + whole + "&nbsp;</td><td align='center' style='border-bottom:2px solid black;'>" + num + "</td></tr><tr><td align='center'>" + den + "</td></tr></table></html>";
            }
        } else if (val.contains("/")) {
            String[] frac = val.split("/");
            String num = frac[0];
            String den = frac.length > 1 ? frac[1] : "";
            return "<html><table style='font-size:24pt; color:#000000;' cellpadding='0' cellspacing='0'><tr><td align='center' style='border-bottom:2px solid black;'>" + num + "</td></tr><tr><td align='center'>" + den + "</td></tr></table></html>";
        }
        return "<html><span style='font-size:24pt; color:#000000;'>" + val + "</span></html>";
    }

    private String toExpressionHtml(String expr) {
        if (expr.isEmpty()) return "";
        String[] tokens = expr.split(" ");
        StringBuilder html = new StringBuilder("<html><table style='font-size:16pt; color:#404040;' cellpadding='0' cellspacing='0'><tr>");
        for (String token : tokens) {
            if (token.matches(".*[0-9].*") && (token.contains("/") || token.contains("_"))) {
                html.append("<td valign='middle'>");
                if (token.contains("_")) {
                    String[] p = token.split("_");
                    String w = p[0];
                    String[] f = p.length > 1 ? p[1].split("/") : new String[]{""};
                    String n = f.length > 0 ? f[0] : "";
                    String d = f.length > 1 ? f[1] : "";
                    html.append("<table style='font-size:14pt; color:#404040;' cell-padding='0' cell-spacing='0'><tr><td rowspan='2' valign='middle'>").append(w).append("&nbsp;</td><td align='center' style='border-bottom:1px solid #404040;'>").append(n).append("</td></tr><tr><td align='center'>").append(d).append("</td></tr></table>");
                } else if (token.contains("/")) {
                    String[] f = token.split("/");
                    String n = f.length > 0 ? f[0] : "";
                    String d = f.length > 1 ? f[1] : "";
                    html.append("<table style='font-size:14pt; color:#404040;' cellpadding='0' cellspacing='0'><tr><td align='center' style='border-bottom:1px solid #404040;'>").append(n).append("</td></tr><tr><td align='center'>").append(d).append("</td></tr></table>");
                }
                html.append("</td><td valign='middle'>&nbsp;</td>");
            } else {
                html.append("<td valign='middle'>&nbsp;").append(token).append("&nbsp;</td>");
            }
        }
        html.append("</tr></table></html>");
        return html.toString();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        try {
            if (cmd.matches("[0-9]+")) {
                if (isNewInput) {
                    setRawInput("");
                    isNewInput = false;
                    currentResult = null;
                }
                if (rawInput.equals("0")) {
                    setRawInput(cmd);
                } else {
                    setRawInput(rawInput + cmd);
                }
            }
            else if (cmd.equals("Whole (_)")) {
                if (isNewInput) { setRawInput("0"); isNewInput = false; currentResult = null; }
                if (!rawInput.contains("_")) {
                    setRawInput(rawInput + "_");
                }
            }
            else if (cmd.equals("x / y")) {
                if (isNewInput) { setRawInput("0"); isNewInput = false; currentResult = null; }
                if (!rawInput.contains("/")) {
                    setRawInput(rawInput + "/");
                }
            }
            else if (cmd.equals(".")) {
                if(isNewInput) {
                    setRawInput("0.");
                    isNewInput = false;
                    currentResult = null;
                } else if (!rawInput.contains(".")) {
                    setRawInput(rawInput + ".");
                }
            }
            else if (cmd.equals("CA")) {
                resetCalculator();
            }
            else if (cmd.equals("DEL")) {
                if (rawInput.length() > 0 && !isNewInput) {
                    setRawInput(rawInput.substring(0, rawInput.length() - 1));
                    if (rawInput.isEmpty()) setRawInput("0");
                }
            }
            else if (cmd.equals("+") || cmd.equals("-") || cmd.equals("x") || cmd.equals("÷")) {
                int valid = 0;
                if (!rawInput.isEmpty() && !rawInput.contains("Error")) {
                    if (op1 != null && !currentOperator.isEmpty() && !isNewInput) {
                        valid = 1;
                        Fraction op2 = parseToFraction(rawInput);
                        Fraction result = null;
                        switch (currentOperator) {
                            case "+": result = op1.add(op2); break;
                            case "-": result = op1.subtract(op2); break;
                            case "x": result = op1.multiplyBy(op2); break;
                            case "÷": result = op1.divideBy(op2); break;
                        }

                        if (result != null) {
                            setRawExpression(rawExpression + " " + rawInput + " = " + getImproperString(result));

                            currentResult = result;
                            displayAsMixed = false;
                            updateDisplay();

                            op1 = result;
                            currentOperator = "";
                        }
                    }
                    op1 = parseToFraction(rawInput);
                    currentOperator = cmd;
                    if (valid != 1) {
                        setRawExpression(rawInput + " " + currentOperator);
                        isNewInput = true;
                    }
                    else {
                        setRawExpression(rawExpression + " " + currentOperator);
                        setRawInput(getImproperString(op1));
                        isNewInput = true;
                    }
                }
            }
            else if (cmd.equals("=")) {
                if (op1 != null && !currentOperator.isEmpty() && !isNewInput) {
                    Fraction op2 = parseToFraction(rawInput);
                    Fraction result = null;

                    switch (currentOperator) {
                        case "+": result = op1.add(op2); break;
                        case "-": result = op1.subtract(op2); break;
                        case "x": result = op1.multiplyBy(op2); break;
                        case "÷": result = op1.divideBy(op2); break;
                    }

                    if (result != null) {
                        setRawExpression(rawExpression + " " + rawInput + " =");

                        currentResult = result;
                        displayAsMixed = false;
                        updateDisplay();

                        op1 = result;
                        currentOperator = "";
                        isNewInput = true;
                    }
                }
            }
            else if (cmd.equals("S<=>D")) {
                if (currentResult == null) {
                    currentResult = parseToFraction(rawInput);
                }
                displayAsMixed = !displayAsMixed;
                updateDisplay();
                isNewInput = true;
            }
        } catch (ArithmeticException ex) {
            setRawInput("Math Error");
            isNewInput = true;
        } catch (Exception ex) {
            setRawInput("Syntax Error");
            isNewInput = true;
        }
    }

    private String getImproperString(Fraction f) {
        int n = f.getNumerator();
        int d = f.getDenominator();
        return (d == 1) ? String.valueOf(n) : n + "/" + d;
    }

    private void updateDisplay() {
        if (currentResult == null) return;

        if (displayAsMixed) {
            setRawInput(new MixedNumber(currentResult).toString());
        } else {
            setRawInput(getImproperString(currentResult));
        }

        lblDoubleResult.setText("DEC: " + String.format("%.4f", currentResult.toDouble()));
    }

    private Fraction parseToFraction(String input) throws Exception {
        input = input.trim();
        if (input.isEmpty() || input.contains("Error")) throw new Exception();

        if (input.contains("_")) {
            String[] parts = input.split("_");
            int whole = Integer.parseInt(parts[0]);
            String[] fracParts = parts[1].split("/");
            return new MixedNumber(whole, Integer.parseInt(fracParts[0]), Integer.parseInt(fracParts[1]));

        } else if (input.contains("/")) {
            String[] parts = input.split("/");
            return new Fraction(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));

        } else if (input.contains(".")) {
            String[] parts = input.split("\\.");
            String whole = parts[0];
            String fractional = parts.length > 1 ? parts[1] : "0";
            int denominator = (int) Math.pow(10, fractional.length());
            int numerator = Integer.parseInt(whole + fractional);
            return new Fraction(numerator, denominator);

        } else {
            return new Fraction(Integer.parseInt(input), 1);
        }
    }

    private void resetCalculator() {
        setRawInput("0");
        setRawExpression("");
        lblDoubleResult.setText("DEC: 0.0");
        op1 = null;
        currentOperator = "";
        isNewInput = true;

        currentResult = null;
        displayAsMixed = false;
    }

    public static void main(String[] args) {
        try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); }
        catch (Exception e) {  }

        SwingUtilities.invokeLater(() -> {
            Calculator calc = new Calculator();
            calc.setLocationRelativeTo(null);
            calc.setVisible(true);
        });
    }
}