import java.util.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class SciCalculator extends Calculator
{
    private boolean inRad = false;
    private boolean exactAns = false;
    private double[] memory = new double[6];
    private String memVar;
    private int memoryIndex = 0;
    private double prevAnswer;
    private double answer = 0;
    private Scanner input = new Scanner(System.in);
    
    public SciCalculator(String CalcName)
    {
        super(CalcName);
        this.inRad = inRad;
        this.exactAns = exactAns;
    }
    
    public double Memory(String type)
    {
        if (type.equals("M+"))
        {
            memVar = readLine("Which Variable would you like to use? \nSelect a variable in the form of [q, w, e, r, t, y]: \n[q] = "+memory[0]+"\n[w] = "+memory[1]+"\n[e] = "+memory[2]+"\n[r] = "+memory[3]+"\n[t] = "+memory[4]+"\n[y] = "+memory[5]+"\n\n").toLowerCase();
            if (memVar.equals("q"))
            {
                memory[0] = prevAnswer;
            }
            else if (memVar.equals("w"))
            {
                memory[1] = prevAnswer;
            }
            else if (memVar.equals("e"))
            {
                memory[2] = prevAnswer;
            }
            else if (memVar.equals("r"))
            {
                memory[3] = prevAnswer;
            }
            else if (memVar.equals("t"))
            {
                memory[4] = prevAnswer;
            }
            else if (memVar.equals("y"))
            {
                memory[5] = prevAnswer;
            }
            else
            {
                System.out.println("You did not enter a valid variable.");
            }
        }
        else if (type.equals("M-"))
        {
            memVar = readLine("Which Variable would you like to use? \nSelect a variable in the form of [q, w, e, r, t, y]: \n[q] = "+memory[0]+"\n[w] = "+memory[1]+"\n[e] = "+memory[2]+"\n[r] = "+memory[3]+"\n[t] = "+memory[4]+"\n[y] = "+memory[5]+"\n\n").toLowerCase();
            if (memVar.equals("q"))
            {
                memory[0] = 0;
            }
            else if (memVar.equals("w"))
            {
                memory[1] = 0;
            }
            else if (memVar.equals("e"))
            {
                memory[2] = 0;
            }
            else if (memVar.equals("r"))
            {
                memory[3] = 0;
            }
            else if (memVar.equals("t"))
            {
                memory[4] = 0;
            }
            else if (memVar.equals("y"))
            {
                memory[5] = 0;
            }
            else
            {
                System.out.println("You did not enter a valid variable.");
            }
        }
        else if (type.equals("M"))
        {
            memVar = readLine("Which Variable would you like to use? \nSelect a variable in the form of [q, w, e, r, t, y]: \n[q] = "+memory[0]+"\n[w] = "+memory[1]+"\n[e] = "+memory[2]+"\n[r] = "+memory[3]+"\n[t] = "+memory[4]+"\n[y] = "+memory[5]+"\n\n").toLowerCase();
            if (memVar.equals("q"))
            {
                memoryIndex = 0;
            }
            else if (memVar.equals("w"))
            {
                memoryIndex = 1;
            }
            else if (memVar.equals("e"))
            {
                memoryIndex = 2;
            }
            else if (memVar.equals("r"))
            {
                memoryIndex = 3;
            }
            else if (memVar.equals("t"))
            {
                memoryIndex = 4;
            }
            else if (memVar.equals("y"))
            {
                memoryIndex = 5;
            }
            else
            {
                System.out.println("You did not enter a valid variable.");
            }
        }
        else if (type.equals("MC"))
        {
            for (int i = 0; i < memory.length; i++)
            {
                memory[i] = 0;
            }
        }
        return memory[memoryIndex];
    }
    
//------------------------------------------------------Trig Functions
    public String Sin(double x)
    {
        double angle = x;
        if (!inRad)
        {
            angle = Math.toRadians(x);
        }
        return ""+Math.sin(angle);
    }
    public String Cos(double x)
    {
        double angle = x;
        if (!inRad)
        {
            angle = Math.toRadians(x);
        }
        return ""+Math.cos(angle);
    }
    public String Tan(double x)
    {
        double angle = x;
        if (!inRad)
        {
            angle = Math.toRadians(x);
        }
        return ""+Math.tan(angle);
    }
    public String CTan(double x)
    {
        double angle = x;
        if (!inRad)
        {
            angle = Math.toRadians(x);
        }
        return ""+(Math.cos(angle)/Math.sin(angle));
    }
    public String CSin(double x)
    {
        double angle = x;
        if (!inRad)
        {
            angle = Math.toRadians(x);
        }
        return ""+(Math.cos(angle)/Math.tan(angle));
    }
    public String CCos(double x)
    {
        double angle = x;
        if (!inRad)
        {
            angle = Math.toRadians(x);
        }
        return ""+(Math.sin(angle)/Math.tan(angle));
    }
    public String aSin(double x)
    {
        double angle = x;
        angle = Math.asin(x);
        if (!inRad)
        {
            angle = Math.toDegrees(angle);
        }
        return ""+angle;
    }
    public String aCos(double x)
    {
        double angle = x;
        angle = Math.acos(x);
        if (!inRad)
        {
            angle = Math.toDegrees(angle);
        }
        return ""+angle;
    }
    public String aTan(double x)
    {
        double angle = x;
        angle = Math.atan(x);
        if (!inRad)
        {
            angle = Math.toDegrees(angle);
        }
        return ""+angle;
    }
    
//---------------------------------------------------------Trig Functions with Symbolic Output
    public String SinExact(double x)
    {
        if (!inRad)
        {
            x = Math.toRadians(x);
        }
        if (x == Math.PI/6)
        {
            return "1/2";
        }
        else if (x == Math.PI/4)
        {
            return "\u221A2/2";
        }
        else if (x == Math.PI/3)
        {
            return "\u221A3/2";
        }
        else if (x == Math.PI/2)
        {
            return "1";
        }
        else if (x == Math.PI)
        {
            return "0";
        }
        else
        {
            return ""+Math.sin(x);
        }
    }
    public String CosExact(double x)
    {
        if (!inRad)
        {
            x = Math.toRadians(x);
        }
        if (x == Math.PI/6)
        {
            return "\u221A3/2";
        }
        else if (x == Math.PI/4)
        {
            return "\u221A2/2";
        }
        else if (x == Math.PI/3)
        {
            return "1/2";
        }
        else if (x == Math.PI/2)
        {
            return "0";
        }
        else if (x == Math.PI)
        {
            return "-1";
        }
        else
        {
            return ""+Math.cos(x);
        }
    }
    public String TanExact(double x)
    {
        if (!inRad)
        {
            x = Math.toRadians(x);
        }
        if (x == Math.PI/6)
        {
            return "\u221A3/3";
        }
        else if (x == Math.PI/4)
        {
            return "1";
        }
        else if (x == Math.PI/3)
        {
            return "\u221A3";
        }
        else if (x == Math.PI/2)
        {
            return "Undefined";
        }
        else if (x == Math.PI)
        {
            return "0";
        }
        else
        {
            return ""+Math.tan(x);
        }
    }
    
//-------------------------------------------------------Logarithmic Functions  
    public String Log(double x)
    {
        return ""+Math.log10(x);
    }
    public String Ln(double x)
    {
        return ""+Math.log(x);
    }
    public String LogBase(double base, double x)
    {
        return ""+Math.log(x)/Math.log(base);
    }

//-------------------------------------------------------Root and Power Functions
    public String sqroot(double x)
    {
        if (x < 0)
        {
            return "i\u221A"+(-x);
        }
        return ""+Math.sqrt(x);
    }
    public String sqroot(int x)
    {
        if (x < 0)
        {
            return "i\u221A"+(-x);
        }
        return ""+Math.sqrt(x);
    }
    public double power(double base, double exponent)
    {
        return Math.pow(base, exponent);
    }

//--------------------------------------------------------Misc Functions
    public String Factorial(int n)
    {
        if (n < 0)
        {
            return "Undefined";
        }
        long fact = 1;
        for (int i = 1; i <= n; i++)
        {
            fact *= i;
        }
        return ""+fact;
    }
    public String nPr(int n, int r)
    {
        if (n < 0 || r < 0 || r > n)
        {
            return "Undefined";
        }
        long factN = 1;
        long factNR = 1;
        for (int i = 1; i <= n; i++)
        {
            factN *= i;
        }
        for (int i = 1; i <= n - r; i++)
        {
            factNR *= i;
        }
        return ""+factN/factNR;
    }
    public String nCr(int n, int r)
    {
        if (n < 0 || r < 0 || r > n)
        {
            return "Undefined";
        }
        long factN = 1;
        long factR = 1;
        long factNR = 1;
        for (int i = 1; i <= n; i++)
        {
            factN *= i;
        }
        for (int i = 1; i <= r; i++)
        {
            factR *= i;
        }
        for (int i = 1; i <= n - r; i++)
        {
            factNR *= i;
        }
        return ""+factN/(factR*factNR);
    }

//--------------------------------------------------------Algebra and Equation Solver
    public String Algebra(String equation)
    {
        try
        {
            String[] parts = equation.split("=");
            if (parts.length != 2)
            {
                return "Invalid equation format. Please use format like '2x+3=7'.";
            }

            String lhs = parts[0].replaceAll("\\s+", "").replace("(x)", "x");
            String rhs = parts[1].replaceAll("\\s+", "");

            double rhsVal = evaluateExpression(rhs);
            String[] terms = lhs.split("(?=[+-])");

            double constSum = 0;
            double xCoeff = 0;

            for (String term : terms)
            {
                if (term.isEmpty()) continue;

                if (term.contains("x"))
                {
                    String c = term.replace("x", "");
                    if (c.equals("") || c.equals("+")) c = "1";
                    else if (c.equals("-")) c = "-1";
                    xCoeff += Double.parseDouble(c);
                }
                else
                {
                    constSum += evaluateExpression(term);
                }
            }

            if (xCoeff == 0)
            {
                return "No variable term found or coefficient is zero. Cannot solve.";
            }

            double x = (rhsVal - constSum) / xCoeff;
            double lhsCheck = constSum + xCoeff * x;
            double error = lhsCheck - rhsVal;

            StringBuilder result = new StringBuilder();
            result.append("Equation: ").append(equation).append("\n");
            result.append(String.format("Solution: x = %.6f%n", x));
            result.append(String.format("Check: LHS - RHS = %.6f%n", error));

            return result.toString();
        }
        catch (Exception e)
        {
            return "Error solving equation: " + e.getMessage();
        }
    }

    public void consoleAlgebra(String equation)
    {
        try
        {
            String[] parts = equation.split("=");
            if (parts.length != 2)
            {
                System.out.println("Invalid equation format.");
                return;
            }

            String lhs = parts[0].replaceAll("\\s+", "").replace("(x)", "x");
            String rhs = parts[1].replaceAll("\\s+", "");

            double rhsVal = evaluateExpression(rhs);
            String[] terms = lhs.split("(?=[+-])");

            double constSum = 0;
            double xCoeff = 0;

            for (String term : terms)
            {
                if (term.isEmpty()) continue;

                if (term.contains("x"))
                {
                    String c = term.replace("x", "");
                    if (c.equals("") || c.equals("+")) c = "1";
                    else if (c.equals("-")) c = "-1";
                    xCoeff += Double.parseDouble(c);
                }
                else
                {
                    constSum += evaluateExpression(term);
                }
            }

            if (xCoeff == 0)
            {
                System.out.println("No variable term found or coefficient is zero. Cannot solve.");
                return;
            }

            double x = (rhsVal - constSum) / xCoeff;
            double lhsCheck = constSum + xCoeff * x;
            double error = lhsCheck - rhsVal;

            System.out.println("Equation: " + equation);
            System.out.println("x = " + x);
            System.out.println("LHS - RHS = " + error);
        }
        catch (Exception e)
        {
            System.out.println("Error solving equation: " + e.getMessage());
        }
    }

//--------------------------------------------------------Polynomial Derivative (GUI stub)
    public String derivative(int deg, ArrayList<Double> coeff)
    {
        return "Implemented in the GUIClass";
    }
    public String derivative(String expr)
    {
        return "Derivative not implemented";
    }

//--------------------------------------------------------Table function for GUI
    public ArrayList<String> table(int degree, ArrayList<Double> coefficients, double start, double end, double step)
    {
        ArrayList<String> results = new ArrayList<>();

        if (coefficients.size() != degree + 1)
        {
            results.add("Error: Number of coefficients doesn't match degree.");
            return results;
        }

        results.add("x\tf(x)");

        for (double x = start; x <= end; x += step)
        {
            double fx = 0.0;
            for (int i = 0; i < coefficients.size(); i++)
            {
                int power = degree - i;
                fx += coefficients.get(i) * Math.pow(x, power);
            }
            results.add(String.format("%.2f\t%.2f", x, fx));
        }

        return results;
    }

//----------------------------------------------------------Table Selection Function for console (Used for logic testing)
    public void ConsoleTable()
    {
        int fxDeg = readInt("Enter Degree: ");
        ArrayList<Double> terms = new ArrayList<Double>();

        for (int i = 0; i <= fxDeg; i++)
        {
            double newTerm = readDouble("Enter coefficient for x^" + (fxDeg - i) + ": ");
            terms.add(newTerm);
        }

        double Start = readDouble("Start?\n");
        double End = readDouble("End?\n");

        while (Start > End)
        {
            System.out.println("Start must be less than or equal to End.");
            Start = readDouble("Start?\n");
            End = readDouble("End?\n");
        }

        double Step = readDouble("Step?\n");

        System.out.println("x\tf(x)");

        for (double x = Start; x <= End; x += Step)
        {
            double fx = 0.0;
            for (int i = 0; i < terms.size(); i++)
            {
                int power = fxDeg - i;
                fx += terms.get(i) * Math.pow(x, power);
            }
            System.out.printf("%.2f\t%.2f\n", x, fx);
        }
    }

//-------------------------------------------Expression evaluation (JDK-21 safe)
    public double evaluateExpression(String expression) {
        // Use internal parser to evaluate expressions reliably without ScriptEngine
        try {
            if (expression == null) return 0.0;
            // normalize expression
            String expr = expression.replaceAll("\\s+", "");
            return parseExpression(expr);
        } catch (Exception e) {
            System.out.println("Invalid expression: " + expression + " -> " + e.getMessage());
            return 0.0;
        }
    }

//-------------------------------------------Logic fixers (internal parser)

// A simple recursive-descent parser that supports +, -, *, /, ^ (right-associative), unary +/-, and parentheses.
private String parseInput;
private int parsePos;

private double parseExpression(String expr) {
    parseInput = expr;
    parsePos = 0;
    double value = parseAddSubtract();
    if (parsePos < parseInput.length()) {
        throw new IllegalArgumentException("Unexpected: " + parseInput.substring(parsePos));
    }
    return value;
}

private double parseAddSubtract() {
    double value = parseMultiplyDivide();
    while (parsePos < parseInput.length()) {
        char op = parseInput.charAt(parsePos);
        if (op == '+' || op == '-') {
            parsePos++;
            double right = parseMultiplyDivide();
            value = (op == '+') ? value + right : value - right;
        } else break;
    }
    return value;
}

private double parseMultiplyDivide() {
    double value = parsePower();
    while (parsePos < parseInput.length()) {
        char op = parseInput.charAt(parsePos);
        if (op == '*' || op == '/') {
            parsePos++;
            double right = parsePower();
            if (op == '*') value *= right; else value /= right;
        } else break;
    }
    return value;
}

private double parsePower() {
    double value = parseUnary();
    if (parsePos < parseInput.length() && parseInput.charAt(parsePos) == '^') {
        // right-associative
        parsePos++;
        double exponent = parsePower();
        value = Math.pow(value, exponent);
    }
    return value;
}

private double parseUnary() {
    if (parsePos < parseInput.length()) {
        char c = parseInput.charAt(parsePos);
        if (c == '+') { parsePos++; return parseUnary(); }
        if (c == '-') { parsePos++; return -parseUnary(); }
    }
    return parsePrimary();
}

private double parsePrimary() {
    if (parsePos >= parseInput.length()) throw new IllegalArgumentException("Unexpected end of expression");
    char c = parseInput.charAt(parsePos);
    if (c == '(') {
        parsePos++;
        double val = parseAddSubtract();
        if (parsePos >= parseInput.length() || parseInput.charAt(parsePos) != ')')
            throw new IllegalArgumentException("Missing closing parenthesis");
        parsePos++;
        return val;
    }
    // number
    int start = parsePos;
    boolean seenDot = false;
    while (parsePos < parseInput.length()) {
        char ch = parseInput.charAt(parsePos);
        if ((ch >= '0' && ch <= '9') || ch == '.') {
            if (ch == '.') {
                if (seenDot) break; else seenDot = true;
            }
            parsePos++;
        } else break;
    }
    if (start == parsePos) throw new IllegalArgumentException("Number expected at position " + start);
    String number = parseInput.substring(start, parsePos);
    return Double.parseDouble(number);
}

//------------------------------------------------------------Angle and exact-answer mode
    public String getAngleMode()
    {
        if (inRad)
        {
            return "Calculator is in Radian Mode\n\n";
        }
        return "Calculator is in Degree Mode\n\n";
    }
    public void setAngleMode()
    {
        String angleMode = readLine("Would you like to use Radians? [y/n]");
        if(angleMode.equals("y"))
        {
            this.inRad = true;
        }
        else
        {
            this.inRad = false;
        }
    }
    public String getAnswerType()
    {
        if (exactAns)
        {
            return "Calculator is in Exact Answer Mode\n\n";
        }
        return "Calculator is not in Exact Answer Mode\n\n";
    }
    public void setAnswerType()
    {
        String angleMode = readLine("Would you like to use Exact Answer? [y/n]");
        if(angleMode.equals("y"))
        {
            this.exactAns = true;
        }
        else
        {
            this.exactAns = false;
        }
    }

//---------------------------------------------------------GCD
    public double GCD(double num1, double num2)
    {
        while (num2 != 0) {
            int temp = (int)num2;
            num2 = num1 % num2;
            num1 = (double) temp;
        }
        return Math.abs(num1);
    }

//----------------------------------------------------------------------------------Logic Functionality
    public String readLine(String prompt)
    {
        System.out.print(prompt);
        return input.nextLine();
    }
    public int readInt(String prompt)
    {
        while (true)
        {
            try
            {
                System.out.print(prompt);
                return Integer.parseInt(input.nextLine());
            }
            catch (NumberFormatException e)
            {
                System.out.println("Invalid integer. Please try again.");
            }
        }
    }
    public double readDouble(String prompt)
    {
        while (true)
        {
            try
            {
                System.out.print(prompt);
                return Double.parseDouble(input.nextLine());
            }
            catch (NumberFormatException e)
            {
                System.out.println("Invalid decimal number. Please try again.");
            }
        }
    }
}
