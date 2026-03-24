import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.*;

public class CalculatorGUI extends JFrame {
    private final SciCalculator sciCalculator;
    private final JTextField inputField;
    private final JTextArea outputArea;

    private final JTextField degreeField, coefficientsField, startField, endField, stepField;
    private final JTextField algebraInputField;

    private double lastAnswer = 0.0;
    private final HashMap<String, Double> memory = new HashMap<>();
    private boolean degreeMode = true; // for trig mode

    public CalculatorGUI() {
        super("Scientific Calculator");
        sciCalculator = new SciCalculator("Casio fx-991ES PLUS C");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        add(mainPanel);

        // Top I/O
        JPanel ioPanel = new JPanel(new BorderLayout());
        inputField = new JTextField();
        outputArea = new JTextArea(10, 50);
        outputArea.setEditable(false);
        ioPanel.add(inputField, BorderLayout.NORTH);
        ioPanel.add(new JScrollPane(outputArea), BorderLayout.CENTER);
        mainPanel.add(ioPanel, BorderLayout.NORTH);

        // Buttons panel
        JPanel buttonsPanel = new JPanel(new GridLayout(7, 6, 6, 6));
        String[] buttons = {
            "sin","cos","tan","Deg/Rad","^","√",
            "7","8","9","Del","(",")",
            "4","5","6","*","/","Store",
            "1","2","3","+","-","Recall",
            ".","0","π","Ans","=","Clear",
            "csc","sec","cot","e","abs","exp",
            "1/x","n!","ln","log","y√x","d/dx",
        };

        for (String label : buttons) {
            JButton btn = new JButton(label);
            btn.addActionListener(e -> handleButton(label));
            buttonsPanel.add(btn);
        }
        mainPanel.add(buttonsPanel, BorderLayout.CENTER);

        // Right panel with advanced features
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

        // Algebra Solver
        JPanel algebraPanel = new JPanel();
        algebraPanel.setBorder(BorderFactory.createTitledBorder("Algebra Solver (Ax + B = C)"));
        algebraInputField = new JTextField(15);
        JButton algebraButton = new JButton("Solve");
        algebraButton.addActionListener(e -> solveAlgebra());
        algebraPanel.add(algebraInputField);
        algebraPanel.add(algebraButton);
        rightPanel.add(algebraPanel);

        // Polynomial Table Generator
        JPanel tablePanel = new JPanel(new GridLayout(6, 2));
        tablePanel.setBorder(BorderFactory.createTitledBorder("Polynomial Table Generator"));
        degreeField = new JTextField(3);
        coefficientsField = new JTextField(20);
        startField = new JTextField(5);
        endField = new JTextField(5);
        stepField = new JTextField(5);
        JButton tableButton = new JButton("Generate Table");
        tableButton.addActionListener(e -> generateTable());

        tablePanel.add(new JLabel("Degree:"));
        tablePanel.add(degreeField);
        tablePanel.add(new JLabel("Coefficients (comma):"));
        tablePanel.add(coefficientsField);
        tablePanel.add(new JLabel("Start:"));
        tablePanel.add(startField);
        tablePanel.add(new JLabel("End:"));
        tablePanel.add(endField);
        tablePanel.add(new JLabel("Step:"));
        tablePanel.add(stepField);
        tablePanel.add(new JLabel(" "));
        tablePanel.add(tableButton);
        rightPanel.add(tablePanel);

        // Derivative Button
        JButton derivativeButton = new JButton("Find Derivative");
        derivativeButton.addActionListener(e -> generateDerivative());
        rightPanel.add(Box.createVerticalStrut(10));
        rightPanel.add(derivativeButton);

        mainPanel.add(rightPanel, BorderLayout.EAST);
        inputField.addActionListener(e -> evaluate());
        Font customFont = new Font("Times New Romans", Font.PLAIN, 16);
        inputField.setFont(customFont);
        outputArea.setFont(customFont);
        JButton btn = new JButton("font");
        btn.setFont(customFont);

        setVisible(true);
    }

    private void handleButton(String command) {
        switch (command) {
            case "=":
                evaluate();
                break;

            case "Clear":
                inputField.setText("");
                lastAnswer = 0.0;
                break;

            case "Ans":
                inputField.setText(inputField.getText() + lastAnswer);
                break;

            case "√":
                inputField.setText(inputField.getText() + "√(");
                break;

            case "Store": {
                String varName = JOptionPane.showInputDialog(this, "Enter variable name:");
                if (varName != null && !varName.isEmpty()) {
                    memory.put(varName, lastAnswer);
                }
                break;
            }

            case "Recall": {
                String recallName = JOptionPane.showInputDialog(this, "Recall variable:");
                if (recallName != null && memory.containsKey(recallName)) {
                    inputField.setText(inputField.getText() + memory.get(recallName));
                } else {
                    outputArea.setText("No value stored with that name.");
                }
                break;
            }

            case "Deg/Rad":
                degreeMode = !degreeMode;
                outputArea.setText("Mode: " + (degreeMode ? "Degrees" : "Radians"));
                break;

            case "sin":
            case "cos":
            case "tan": {
                try {
                    double angle = Double.parseDouble(inputField.getText());
                    double radians = degreeMode ? Math.toRadians(angle) : angle;
                    double result = 0.0;

                    if (command.equals("sin")) {
                        result = Math.sin(radians);
                    } else if (command.equals("cos")) {
                        result = Math.cos(radians);
                    } else if (command.equals("tan")) {
                        double cos = Math.cos(radians);
                        if (Math.abs(cos) < 1e-10) {
                            outputArea.setText("Error: tan(" + angle + ") is undefined.");
                            return;
                        }
                        result = Math.tan(radians);
                    }

                    lastAnswer = result;
                    outputArea.setText(command + "(" + angle + ") = " + result);
                } catch (NumberFormatException e) {
                    outputArea.setText("Invalid angle input.");
                }
                break;
            }

            case "sec":
            case "csc":
            case "cot": {
                try {
                    double angle = Double.parseDouble(inputField.getText());
                    double radians = degreeMode ? Math.toRadians(angle) : angle;
                    double result = 0.0;

                    if (command.equals("sec")) {
                        double cos = Math.cos(radians);
                        if (Math.abs(cos) < 1e-10) {
                            outputArea.setText("Error: sec(" + angle + ") is undefined.");
                            return;
                        }
                        result = 1 / cos;
                    } else if (command.equals("csc")) {
                        double sin = Math.sin(radians);
                        if (Math.abs(sin) < 1e-10) {
                            outputArea.setText("Error: csc(" + angle + ") is undefined.");
                            return;
                        }
                        result = 1 / sin;
                    } else if (command.equals("cot")) {
                        double tan = Math.tan(radians);
                        if (Math.abs(tan) < 1e-10) {
                            outputArea.setText("Error: cot(" + angle + ") is undefined.");
                            return;
                        }
                        result = 1 / tan;
                    }

                    lastAnswer = result;
                    outputArea.setText(command + "(" + angle + ") = " + result);
                } catch (NumberFormatException e) {
                    outputArea.setText("Invalid angle input.");
                }
                break;
            }

            case "log": {
                try {
                    double input = Double.parseDouble(inputField.getText());
                    if (input <= 0) {
                        outputArea.setText("Error: log input must be positive.");
                        break;
                    }
                    double result = Math.log10(input);
                    lastAnswer = result;
                    outputArea.setText("log(" + input + ") = " + result);
                } catch (NumberFormatException e) {
                    outputArea.setText("Invalid input for log.");
                }
                break;
            }

            case "ln": {
                try {
                    double input = Double.parseDouble(inputField.getText());
                    if (input <= 0) {
                        outputArea.setText("Error: ln input must be positive.");
                        break;
                    }
                    double result = Math.log(input);
                    lastAnswer = result;
                    outputArea.setText("ln(" + input + ") = " + result);
                } catch (NumberFormatException e) {
                    outputArea.setText("Invalid input for ln.");
                }
                break;
            }

            case "y√x": {
                try {
                    String[] parts = inputField.getText().split(",");
                    if (parts.length != 2) {
                        outputArea.setText("Format: base,root");
                        break;
                    }
                    double base = Double.parseDouble(parts[0].trim());
                    double root = Double.parseDouble(parts[1].trim());
                    if (root == 0) {
                        outputArea.setText("Root cannot be zero.");
                        break;
                    }
                    double result = Math.pow(base, 1.0 / root);
                    lastAnswer = result;
                    outputArea.setText(root + "√" + base + " = " + result);
                } catch (NumberFormatException e) {
                    outputArea.setText("Invalid input for root.");
                }
                break;
            }

            case "1/x": {
                try {
                    double input = Double.parseDouble(inputField.getText());
                    if (input == 0) {
                        outputArea.setText("Error: Division by zero.");
                        break;
                    }
                    double result = 1.0 / input;
                    lastAnswer = result;
                    outputArea.setText("1/" + input + " = " + result);
                } catch (NumberFormatException e) {
                    outputArea.setText("Invalid input for 1/x.");
                }
                break;
            }

            case "n!": {
                try {
                    int input = Integer.parseInt(inputField.getText());
                    if (input < 0) {
                        outputArea.setText("Error: Factorial of negative number.");
                        break;
                    }
                    long result = 1;
                    for (int i = 2; i <= input; i++) {
                        result *= i;
                    }
                    lastAnswer = result;
                    outputArea.setText(input + "! = " + result);
                } catch (NumberFormatException e) {
                    outputArea.setText("Invalid input for factorial.");
                }
                break;
            }

            case "abs": {
                try {
                    double input = Double.parseDouble(inputField.getText());
                    double result = Math.abs(input);
                    lastAnswer = result;
                    outputArea.setText("|" + input + "| = " + result);
                } catch (NumberFormatException e) {
                    outputArea.setText("Invalid input for abs.");
                }
                break;
            }

            case "exp": {
                try {
                    double input = Double.parseDouble(inputField.getText());
                    double result = Math.exp(input);
                    lastAnswer = result;
                    outputArea.setText("exp(" + input + ") = " + result);
                } catch (NumberFormatException e) {
                    outputArea.setText("Invalid input for exp.");
                }
                break;
            }

            case "e":
                inputField.setText(inputField.getText() + Math.E);
                break;

            case "Del": {
                String current = inputField.getText();
                if (!current.isEmpty()) {
                    inputField.setText(current.substring(0, current.length() - 1));
                }
                break;
            }

            case "π":
                inputField.setText(inputField.getText() + Math.PI);
                break;

            default:
                inputField.setText(inputField.getText() + command);
                break;
        }
    }

    private void solveAlgebra() {
        try {
            String eq = algebraInputField.getText();
            String result = sciCalculator.Algebra(eq);
            outputArea.setText("Solution: " + result);
        } catch (Exception e) {
            outputArea.setText("Error: " + e.getMessage());
        }
    }

    private void generateTable() {
        try {
            int degree = Integer.parseInt(degreeField.getText().trim());
            String[] coeffStrs = coefficientsField.getText().split(",");
            ArrayList<Double> coefficients = new ArrayList<>();
            for (String s : coeffStrs) coefficients.add(Double.parseDouble(s.trim()));
            double start = Double.parseDouble(startField.getText().trim());
            double end = Double.parseDouble(endField.getText().trim());
            double step = Double.parseDouble(stepField.getText().trim());

            ArrayList<String> table = sciCalculator.table(degree, coefficients, start, end, step);
            StringBuilder sb = new StringBuilder();
            for (String line : table) sb.append(line).append("\n");
            outputArea.setText(sb.toString());
        } catch (Exception e) {
            outputArea.setText("Error: " + e.getMessage());
        }
    }

    private void generateDerivative() {
        try {
            int degree = Integer.parseInt(degreeField.getText().trim());
            String[] coeffStrs = coefficientsField.getText().split(",");
            ArrayList<Double> coeffs = new ArrayList<>();

            for (String s : coeffStrs) coeffs.add(Double.parseDouble(s.trim()));

            if (coeffs.size() != degree + 1) {
                outputArea.setText("Mismatch: Need " + (degree + 1) + " coefficients.");
                return;
            }

            StringBuilder sb = new StringBuilder("f'(x) = ");
            boolean firstTerm = true;

            for (int i = 0; i < degree; i++) {
                double coeff = coeffs.get(i);
                int power = degree - i;

                double derivedCoeff = coeff * power;
                if (derivedCoeff == 0) continue;

                if (!firstTerm) {
                    sb.append(derivedCoeff > 0 ? " + " : " - ");
                } else if (derivedCoeff < 0) {
                    sb.append("-");
                }

                sb.append(Math.abs(derivedCoeff));

                if (power - 1 > 0) {
                    sb.append("x");
                    if (power - 1 > 1) sb.append("^").append(power - 1);
                }

                firstTerm = false;
            }

            outputArea.setText(sb.toString());

        } catch (Exception e) {
            outputArea.setText("Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CalculatorGUI::new);
    }

    //======================================<EVALUATION>=========================================

    private void evaluate() {
        try {
            String expr = inputField.getText()
                .replace("Ans", Double.toString(lastAnswer))
                .replace("π", Double.toString(Math.PI));

            expr = processSquareRoots(expr); // helper to process all √(...)

            double result = sciCalculator.evaluateExpression(expr);
            lastAnswer = result;
            outputArea.setText("Result: " + result);
        } catch (Exception e) {
            outputArea.setText("Error: " + e.getMessage());
        }
    }

    private String processSquareRoots(String expr) throws Exception {
        int index = expr.indexOf("√(");
        while (index != -1) {
            int start = index + 1; // position of '('
            int end = findMatchingParen(expr, start);
            if (end == -1) throw new Exception("Mismatched parentheses in square root.");

            String inner = expr.substring(start + 1, end);
            double value = sciCalculator.evaluateExpression(processSquareRoots(inner));

            expr = expr.substring(0, index) + Math.sqrt(value) + expr.substring(end + 1);
            index = expr.indexOf("√(");
        }
        return expr;
    }

    private int findMatchingParen(String expr, int openIndex) {
        if (expr.charAt(openIndex) != '(') return -1;
        int count = 0;
        for (int i = openIndex; i < expr.length(); i++) {
            if (expr.charAt(i) == '(') count++;
            else if (expr.charAt(i) == ')') {
                count--;
                if (count == 0) return i;
            }
        }
        return -1;
    }
}
