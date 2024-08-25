import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Transaction {
   float amount;
   String type;
   long accountNumber;

    public Transaction(float amount, String type, long accountNumber) {
        this.amount = amount;
        this.type = type;
        this.accountNumber = accountNumber;
    }
}

class ATM {

    float balance;
    int PIN = 4353;
    List<Transaction> transactions = new ArrayList<>();
    long accountNumber = 987654321L;
    float VALID_AMOUNT = 100.0F;


    public void menuList() {

        System.out.println("\n======================================\n");
        System.out.println("Please select your choice : ");
        System.out.println("1. Balance Inquiry");
        System.out.println("2. Cash Withdrawal");
        System.out.println("3. Cash Deposit");
        System.out.println("4. PIN Change");
        System.out.println("5. Transaction History");
        System.out.println("\n======================================\n");

        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();

        switch (choice) {
            case 1 :
                balanceInquiry();
            case 2 :
                cashWithdrawal();
            case 3 :
                cashDeposit();
            case 4 :
                pinNumberChange();
            case 5 :
                transactionHistory();
            default:
                System.out.println("Thank You");
                System.exit(1);
        }
    }

    public void balanceInquiry() {
        if(pinNumberValidation())
            System.out.println("Balance Amount is : " + balance);
        else {
            System.out.println("Thank You");
            System.exit(1);
        }
        menuList();
    }

    public void cashWithdrawal() {
        if(pinNumberValidation()) {
            System.out.println("Please enter the amount to withdrawal");
            Scanner sc = new Scanner(System.in);
            float enteredWithdrawalAmount;
            boolean isValidAmount;
            while (true) {
                enteredWithdrawalAmount = sc.nextFloat();
                isValidAmount = isValidAmount(enteredWithdrawalAmount);
                if(!isValidAmount) {
                    System.out.println("Please enter more than Rs.100");
                } else {
                    break;
                }
            }
            if (enteredWithdrawalAmount > balance) {
                System.out.println("Insufficient Balance in your account");
            } else {
                balance = balance - enteredWithdrawalAmount;
                System.out.println("Amount = " + enteredWithdrawalAmount + " withdrawal successful");
                transactions.add(new Transaction(enteredWithdrawalAmount, "WITHDRAWAL", accountNumber));
            }
            menuList();
        }
        else {
            System.out.println("Thank You");
            System.exit(1);
        }
    }

    public void cashDeposit() {
        if(pinNumberValidation()) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Please enter the amount to deposit");
            float enteredDepositAmount;
            boolean isValidAmount;
            while (true) {
                enteredDepositAmount = sc.nextFloat();
                isValidAmount = isValidAmount(enteredDepositAmount);
                if(!isValidAmount) {
                    System.out.println("Please enter more than Rs.100");
                } else {
                    break;
                }
            }
            balance = balance + enteredDepositAmount;
            transactions.add(new Transaction(enteredDepositAmount, "DEPOSIT", accountNumber));
            System.out.println("Amount = " + enteredDepositAmount + " deposit successful");
            menuList();
        }
        else {
            System.out.println("Thank You");
            System.exit(1);
        }
    }

    public boolean pinNumberValidation() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter your PIN");
        int enteredPinNumber = sc.nextInt();
        if (enteredPinNumber != PIN) {
            System.out.println("Invalid Pin Number");
            return false;
        } else {
            return true;
        }
    }

    public void transactionHistory() {
        if(pinNumberValidation()) {
            if (!transactions.isEmpty()) {
                for (Transaction transaction : transactions) {
                    System.out.println("Account Number = " + transaction.accountNumber + ", Type = " + transaction.type + ", Amount = " + transaction.amount);
                }
            } else {
                System.out.println("No transactions found for the account number " + accountNumber);
            }
            menuList();
        } else {
            System.out.println("Thank You");
            System.exit(1);
        }
    }

    public void pinNumberChange() {
        if (pinNumberValidation()) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter new pin number to change ");
            int enteredPinNumber = sc.nextInt();
            System.out.println("Re-enter new pin number to change ");
            int reenteredPinNumber = sc.nextInt();
            if(enteredPinNumber == reenteredPinNumber) {
                PIN = enteredPinNumber;
                System.out.println("PIN Number change successful");
            } else {
                System.out.println("Both entered pin number seems different. Please enter same pin number");
                System.out.println("Thank You");
                System.exit(1);
            }
            menuList();
        }
    }

    public boolean isValidAmount(float enteredAmount) {
        return !(enteredAmount < VALID_AMOUNT);
    }

}

public class ATMSimulation {
    public static void main(String[] args) {
        ATM atmObj = new ATM();
        atmObj.menuList();
    }
}
