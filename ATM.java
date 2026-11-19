import java.util.Scanner;

public class ATM {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // ---------- 1. AUTHENTICATION ----------
        final int CORRECT_PIN = 1234;   // the stored (predefined) PIN
        final int MAX_ATTEMPTS = 3;
        int attempts = 0;
        boolean loggedIn = false;

        // keep asking until PIN is correct OR attempts run out
        while (attempts < MAX_ATTEMPTS && !loggedIn) {
            System.out.print("Enter your PIN: ");
            int pin = input.nextInt();
            attempts++;

            if (pin == CORRECT_PIN) {
                loggedIn = true;
                System.out.println("Login successful!");
            } else {
                System.out.println("Wrong PIN. Attempts left: " + (MAX_ATTEMPTS - attempts));
            }
        }

        if (!loggedIn) {
            System.out.println("Too many wrong attempts. Card blocked.");
            return; // stop the program
        }

        // ---------- 2. DATA: balance + transaction history ----------
        double balance = 0.0;
        String[] transactions = new String[100]; // array that stores history
        int count = 0;                            // how many are stored so far

        // ---------- 3. MAIN MENU LOOP ----------
        int choice = 0;
        while (choice != 5) {   // repeat until user picks Exit
            System.out.println("\n===== ATM MENU =====");
            System.out.println("1. Check balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. View transactions");
            System.out.println("5. Exit");
            System.out.print("Choose: ");
            choice = input.nextInt();

            switch (choice) {
                case 1: // CHECK BALANCE
                    System.out.println("Your balance: " + balance);
                    break;

                case 2: // DEPOSIT
                    System.out.print("Amount to deposit: ");
                    double dep = input.nextDouble();
                    if (dep > 0) {
                        balance += dep;                          // add to balance
                        transactions[count] = "Deposit: " + dep; // save in array
                        count++;
                        System.out.println("Deposited. New balance: " + balance);
                    } else {
                        System.out.println("Invalid amount.");
                    }
                    break;

                case 3: // WITHDRAW
                    System.out.print("Amount to withdraw: ");
                    double wd = input.nextDouble();
                    if (wd <= 0) {
                        System.out.println("Invalid amount.");
                    } else if (wd <= balance) {                  // enough money?
                        balance -= wd;                           // deduct
                        transactions[count] = "Withdraw: " + wd; // record
                        count++;
                        System.out.println("Withdrawn. New balance: " + balance);
                    } else {
                        System.out.println("Error: Insufficient balance!");
                    }
                    break;

                case 4: // VIEW TRANSACTIONS (for loop over the array)
                    if (count == 0) {
                        System.out.println("No transactions yet.");
                    } else {
                        for (int i = 0; i < count; i++) {
                            System.out.println((i + 1) + ". " + transactions[i]);
                        }
                    }
                    break;

                case 5: // EXIT
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid option, try again.");
            }
        }
        input.close();
    }
}
