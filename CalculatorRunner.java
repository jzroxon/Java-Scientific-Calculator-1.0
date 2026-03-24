import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class CalculatorRunner {
    private static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        boolean exactAns = false;
        boolean inRad = false;
        String useRad = "";
        String useExact = "";
        String name = "";
        int mode = 0;
        int type = 0;
        String again;
        String Calculator = "CASIO fx-991ES PLUS";
        String userInput;
        double pwr;
        double base;
        double memory;
        String start = readLine("Would you like to start the Calculator? [y/n]: ");//Determins if calculator is to be turned on or not
        // String start ="y";//for debugging puposes
        if (start.equals("y"))
        {
            type = readInt("Choose Calculator Type:\n[1] Console\n[2] GUI\n>>>");//user decieds if the want to use the console or the GUI to 
            // type = 2; // used for debugging
        }
        else if (start.equals("n"))
        {
            System.out.print("\033[H\033[2J");
            System.out.flush();
        
            System.out.println("[!] Suspending");
            System.exit(0);
            
        }
        else
        {
            System.out.println("INVALID INPUT");
            String cont = readLine("");
            System.out.print("\033[H\033[2J");
            System.out.flush();
            try {
                // Pause for 1 second (1000 milliseconds)
                Thread.sleep(1000);
                // Or use TimeUnit for readability
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                // Handle interruption if necessary
                e.printStackTrace();
            }
            System.out.println("[!] Suspending");
        }
        String INPUT;
        String test = readLine("Would you like to run a test? [y/n]: ");
        if (test.equals("y"))
        {
            Calculator testCalc = new Calculator(Calculator);
            System.out.println("Testing basic Operations");
            double sum = testCalc.Add(1293, 8);
            System.out.println("Sum (1293 + 8): " + sum);

            double divide = testCalc.Divide(10,5);
            System.out.println("division (10/5): " + divide);

            double product = testCalc.Multiply(12, 4);
            System.out.println("Product (12*4): " + product);

            double diff = testCalc.Subtract(5, 2);
            System.out.println("Difference(5-2): " + diff);
        
        
            SciCalculator sciCalc = new SciCalculator(Calculator);
        
            System.out.println();System.out.println();System.out.println();
            System.out.println("Scientific Calc: \n"+sciCalc+"\n\n");
            System.out.println("Regular Calc: \n"+testCalc);
        
            sciCalc.sqroot(16);
        
            System.out.println();System.out.println();System.out.println();
            System.out.println("Table Test");
            sciCalc.ConsoleTable();
            System.out.println();System.out.println();System.out.println();
            System.out.println("Algebra Test");
            System.out.println("6 + 3(x) = 12\n AnswerKey: x = 2");
            sciCalc.consoleAlgebra("6 + 3(x) = 12");
        }
        String info = readLine("Would you like the instructions? [y/n]: ");
            if (info.equals("y"))
            {
                System.out.println();System.out.println();System.out.println();
                System.out.println("Select the operation you would like to preform by selecting the related number.\nFollow the instructions of each functionality\n\nTo save something to memory. type in your answer, press enter, then type 'M+'\nType 'M-' to remove something from memory and when prompted select the variable you wish to clear.\nType 'M' to call something from memory. enter the option when promted.\n To switch modes type 'm' and press enter, select the respective mode when promted\n\n\nIn table mode, follow the promts, after one complete use of the mode, you can press enter to go again or you can enter anything else and you will be taken to mode select again\n\n\nIn algebra mode type in your equaion when promted and when you want t go back to mode select enter anything or do another equation by pressing enter without typing anything");
            }
            
        System.out.println("Output Selections");
        String next = readLine("\n\n\n>>>Press enter to Start:\n>>> ");
        
        if (type == 1)
        {
            useRad = readLine("Do you want to use radians by default? [y/n]\n");
            useExact = readLine("Do you want to use exact answers by default? [y/n]\n");
            name = readLine("What Calculator are you using: ");
            if (name.equals(""))
            {
                name = Calculator;
            }
            System.out.print("\033[H\033[2J");
            System.out.println("============Scientific-Calculator-V2============");
            if (useRad.equals("y"))
            {
                inRad = true;
            }
            if (useExact.equals("y"))
            {
                exactAns = true;
            }
            mode = ModeSelect(inRad, exactAns, name);
        SciCalculator calc = new SciCalculator(name);
        while (true)
        {
            if (mode == 1)
            {
                calc.ConsoleTable();
                again = readLine("");
                if (!again.equals(""))
                {
                    useRad = readLine("Do you want to use radians by default? [y/n]\n");
                    useExact = readLine("Do you want to use exact answers by default? [y/n]\n");
                    mode = ModeSelect(inRad, exactAns, name);
                }
            }
            else if (mode == 2)
            {
                INPUT = readLine("Enter Algebraic Equation (eqn: ");
                calc.consoleAlgebra(INPUT);
                again = readLine("");
                if (!again.equals(""))
                {
                    useRad = readLine("Do you want to use radians by default? [y/n]\n");
                    useExact = readLine("Do you want to use exact answers by default? [y/n]\n");
                    mode = ModeSelect(inRad, exactAns, name);
                }
            }
            
            else
            {
                System.out.println("\n\n\nWhich opperation would you like to do?");
                System.out.println("[q] Addition\n"); 
                System.out.println("[w] Subtract\n");
                System.out.println("[e] Multiply\n");
                System.out.println("[r] Divide\n");
                System.out.println("[t] Power\n");
                System.out.println("[y] Sqroot\n");
                System.out.println("[u] Sin\n");
                System.out.println("[i] Cos\n");
                System.out.println("[o] Tan\n");
                System.out.println("[M] Memory\n");
                System.out.println("[M+] Store Memory\n");
                System.out.println("[M-] Clear Memory\n");
                userInput = readLine("Select an Operation (enter '0' to power off): ");
            if (userInput.equals("0"))
            {
                System.out.print("\033[H\033[2J");
                System.out.println(name);
                break;
            }
//========================================================================================            
else if (userInput.equals("q")) // Addition
    {
        double sum = 0;
        while (true)
        {
            String input = readLine("Enter a number to add (or 'd' to finish): ");
            if (input.equals("d")) break;
            try {
                sum += Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number.");
            }
        }
        System.out.println("Sum: " + sum);
    }
//========================================================================================
    else if (userInput.equals("w")) // Subtraction
    {
        String input = readLine("Enter the starting number: ");
        double result;
        try {
            result = Double.parseDouble(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid number.");
            continue;
        }
        while (true)
        {
            input = readLine("Enter a number to subtract (or 'd' to finish): ");
            if (input.equals("d")) break;
            try {
                result -= Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number.");
            }
        }
        System.out.println("Result: " + result);
    }
//========================================================================================
    else if (userInput.equals("e")) // Multiply
    {
        double product = 1;
        while (true)
        {
            String input = readLine("Enter a number to multiply (or 'd' to finish): ");
            if (input.equals("d")) break;
            try {
                product *= Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number.");
            }
        }
        System.out.println("Product: " + product);
    }
//========================================================================================
    else if (userInput.equals("r")) // Divide
    {
        String input = readLine("Enter the starting number: ");
        double result;
        try {
            result = Double.parseDouble(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid number.");
            continue;
        }
        while (true)
        {
            input = readLine("Enter a number to divide by (or 'd' to finish): ");
            if (input.equals("d")) break;
            try {
                double divisor = Double.parseDouble(input);
                if (divisor == 0) {
                    System.out.println("Cannot divide by zero.");
                    continue;
                }
                result /= divisor;
            } catch (NumberFormatException e) {
                System.out.println("Invalid number.");
            }
        }
        System.out.println("Result: " + result);
    }
//=========================================================================================
        else if (userInput.equals("t"))//expornnets
            {
                base = readDouble ("base: ");
                pwr = readDouble ("power: ");
                
                System.out.println(base+"^"+pwr+" = "+calc.power(base, pwr));
            }
//========================================================================================
            else if (userInput.equals("y"))//square root
            {
                double numberBeingRooted = readDouble("base: ");
                System.out.println("√"+numberBeingRooted+" = "+calc.sqroot(numberBeingRooted));
            }
//====================================<MEMORY>===========================================
            else if (userInput.equals("M") || userInput.equals("M+") || userInput.equals("M-"))
            {
                memory = calc.Memory(userInput);
                System.out.println(memory);
            }
//========================================================================================
            else if(userInput.equals("u"))//Sin
            {
                double angle = readDouble("Enter the angle: ");
                double result = Math.sin(Math.toRadians(angle)); 
                System.out.println("Sin of " + angle + " = " + result);
            }
            else if(userInput.equals("i"))//Cos
            {
                double angle = readDouble("Enter the angle: ");
                double result = Math.cos(Math.toRadians(angle)); 
                System.out.println("Cos of " + angle + " = " + result);
            }
            else if(userInput.equals("o"))//Tan
            {
                double angle = readDouble("Enter the angle: ");
                double result = Math.tan(Math.toRadians(angle));
                System.out.print("Tan of " + angle + " = " + result);
            }
        }
            
    }
}
    else
    {
        try {
            for (UIManager.LookAndFeelInfo inf : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(inf.getName())) {
                    UIManager.setLookAndFeel(inf.getClassName());
                    break;
                }
            }
} catch (Exception e) {
    e.printStackTrace();
}
        SwingUtilities.invokeLater(() -> {
            CalculatorGUI gui = new CalculatorGUI();
            gui.setVisible(true);
            });
        }
}
//===================================================================================================

    public static String readLine(String prompt)
    {
        System.out.print(prompt);
        return input.nextLine();
    }
    public static int readInt(String prompt)
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
    public static double readDouble(String prompt)
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
    public static int ModeSelect(boolean Rads, boolean exact, String name)
    {
        
        System.out.println("Select Mode:\n[q] Scientific Mode\n[w] Table Mode\n[e] Algebra Mode");
        String modeSelect = readLine("Enter Selection: ");
        if (modeSelect.equals("q"))
        {
            System.out.println(">> Scientific Mode selected.");
            return 0;
        }
        else if (modeSelect.equals("w"))
        {
            System.out.println(">> Table Mode selected.");
            return 1;
        }
        else if (modeSelect.equals("e"))
        {
            System.out.println(">> Algebra Mode selected.");
            return 2;
        }
        else
        {
            System.out.println("Invalid input. Defaulting to Scientific Mode.");
            return -1;
        }
    }
}