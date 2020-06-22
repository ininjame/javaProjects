
package Helloworld;

import java.util.*;

public class HelloWorld{    
    public static void main(String[] arg) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input a positive integer: ");
        int userInput = scanner.nextInt();
        
        int potentialFactor = 2;
        while (userInput % potentialFactor != 0) {
            potentialFactor++; //Incrementing
        }
        if (potentialFactor == userInput) {
            System.out.println(userInput + " is a prime number.");
        }
        else {
            System.out.println("This number is not prime. " + "The factor is " + potentialFactor);
        }
    }
}