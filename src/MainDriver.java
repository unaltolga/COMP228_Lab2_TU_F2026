import java.util.Scanner;
import java.math.BigDecimal;
import java.util.InputMismatchException;

public class MainDriver {

    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        Interest[] interestObjects = new Interest[5]; // Array to hold 5 objects
        int count = 0; 

        System.out.println("--- Interest Calculator Program ---");
        System.out.println("You need to create 5 objects.");

        // Loop until 5 successful objects are created
        while (count < 5) {
            System.out.println("\n--- Object " + (count + 1) + " ---");
            
            try {
                System.out.print("Enter Principal (e.g., 1000.0): ");
                BigDecimal principal = scanner.nextBigDecimal();

                System.out.print("Enter Rate (e.g., 5.5): ");
                BigDecimal rate = scanner.nextBigDecimal();

                System.out.print("Enter Time in years (e.g., 2.0 or 2): ");
                BigDecimal time = scanner.nextBigDecimal();

                // Constructor is called (throws exception if inputs are invalid)
                Interest interestObj = new Interest(principal, rate, time);
                interestObjects[count] = interestObj;

                // --- PRINT CALCULATIONS ---
                System.out.println("\n--- Calculations for Object " + (count + 1) + " ---");
                
                // 1. BigDecimal methods
                System.out.println("[BigDecimal Method] Simple Interest: " + interestObj.calculateSimpleInterest());
                System.out.println("[BigDecimal Method] Compound Interest: " + interestObj.calculateCompoundInterest());

                // 2. Overloaded (Double) methods
                double pDouble = principal.doubleValue();
                double rDouble = rate.doubleValue();
                double tDouble = time.doubleValue();
                
                System.out.println("[Overloaded Double Method] Simple Interest: " + 
                                   interestObj.calculateSimpleInterest(pDouble, rDouble, tDouble));
                System.out.println("[Overloaded Double Method] Compound Interest: " + 
                                   interestObj.calculateCompoundInterest(pDouble, rDouble, tDouble));

                count++; // Increment counter upon success

            } catch (InputMismatchException e) {
                System.out.println("Error: String (text) input is not permitted! Please enter numeric values only.");
                scanner.nextLine(); // Clear the invalid input
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
                scanner.nextLine();
            }
        }

        System.out.println("\nAll 5 objects created successfully. Program terminating.");
        scanner.close();
    }
}
