import java.util.ArrayList;
// import java.util.Arrays;
// import java.util.Scanner;
public class Calculator 
{
    private double memory;
    private double previousAnswer;
    private final String CalcName;
    private final ArrayList<Double> answerHistory = new ArrayList<>();//s
    public Calculator(String CalcName)
    {
        memory = 0.0;
        this.CalcName = CalcName;
    }
//---------------------------------------------------------------------Basic Calc Functions
    public double Add(double... numbers)
    {
        double answer = 0.0;
        
        for (double num : numbers)
        {
            answer+= num;
        }
        // saveAnswer(answer);//s  
        return answer;
    }
    public double Subtract(double... numbers)
    {
        if (numbers.length == 0)
        {
            return 0.0;
        }
        double answer = numbers[0];
        
        for (int i = 1; i < numbers.length; i++)
        {
            answer-= numbers[i];
        }
        saveAnswer(answer);//s
        return answer;
    }
    public double Multiply(double... numbers)
    {
        double answer = 1.0;
        
        for (double num : numbers)
        {
            answer*= num;
        }
        saveAnswer(answer);//s
        return answer;
    }
    public double Divide(double... numbers)
    {
        if (numbers.length == 0) throw new IllegalArgumentException("No numbers provided.");
    
        double answer = numbers[0];
        for (int i = 1; i < numbers.length; i++)
        {
            if (numbers[i] == 0.0)
            {
               throw new ArithmeticException("Cannot divide by zero");
            }
            answer /= numbers[i];
        }
        saveAnswer(answer);
        return answer;
    }
//-------------------------------------------------------------------Polymorphisim & Functions
    
    
    public void SaveMemory(double mem)
    {
        memory = mem;
    }
    public void SaveMemory(int mem)
    {
        
        memory = (double)mem;
    }
    public double CallMemory()
    {
        return memory;
    }
    
    @Override
    public String toString()
    {
        return "Current Calculator: "+CalcName+"\nStored memory: "+memory+"\nPrevious answer: "+previousAnswer;
    }
    public String getCalc()
    {
        return CalcName;
    }
    public double getPrevAns()
    {
        return previousAnswer;
    }
    public void saveAnswer(double ans)
    {
        answerHistory.add(ans);
    }
    
}