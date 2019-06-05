/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaoito;

/**
 *
 * @author User
 */
public class LambdaTeste {
    
    final static String salutation = "Hello! ";
    
    public static void main(String args[]) {
      //with type declaration
      MathOperation addition = (int a, int b) -> a + b;
		
      //with out type declaration
      MathOperation subtraction = (a, b) -> a - b;
		
      //with return statement along with curly braces
      MathOperation multiplication = (int a, int b) -> { return a * b; };
		
      //without return statement and without curly braces
      MathOperation division = (int a, int b) -> a / b;
		
      System.out.println("10 + 5 = " + operate(10, 5, addition));
      System.out.println("10 - 5 = " + operate(10, 5, subtraction));
      System.out.println("10 x 5 = " + operate(10, 5, multiplication));
      System.out.println("10 / 5 = " + operate(10, 5, division));
		
      //without parenthesis
      GreetingService greetService1 = message ->
      System.out.println("Hello " + message);
		
      //with parenthesis
      GreetingService greetService2 = (message) ->
      System.out.println("Hello " + message);
		
      greetService1.sayMessage("Mahesh");
      greetService2.sayMessage("Suresh");
      
      GreetingService greetService3 = message -> 
      System.out.println(salutation + message);
      greetService3.sayMessage("Mahesh");
   }
	
   interface MathOperation {
      int operation(int a, int b);
   }
	
   interface GreetingService {
      void sayMessage(String message);
   }
	
   private static int operate(int a, int b, MathOperation mathOperation) {
      return mathOperation.operation(a, b);
   }
    
}
