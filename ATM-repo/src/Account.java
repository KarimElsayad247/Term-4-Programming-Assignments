import java.util.ArrayList;

public class Account {

    private double balance;

    public String getCardNumber() {
        return cardNumber;
    }

    String getBalance() {
        return String.valueOf(balance);
    }

    private String cardNumber;
    ArrayList<Transaction> transactionHistory = new ArrayList<Transaction>();
    private int lastTransactionIndex = -1;
    public int currentIndex = -1;

    Account(double balance, String cardNumber) {
        this.balance = balance;
        this.cardNumber = cardNumber;
    }
    AlertBox transactionError = new AlertBox();
    AlertBox transactionSuccess = new AlertBox();

    void withdraw(double value) {
        //System.out.println("Balance before: " + this.balance);
        if (value > balance) {
            transactionError.display("Transaction Error", "Insufficient funds!");
            return;
        } else {
            balance = balance - value;
            addToHistory("Withdraw", value);
            //System.out.println("Balance after: " + this.balance);

            return;
        }

    }

    void deposit(double value) {

        balance = balance + value;
        addToHistory("Deposit", value);
        //System.out.println("Balance after: " + this.balance);
    }

    void addToHistory(String type, double value) {

        Transaction successfulTransaction = new Transaction(type, value);
        if (lastTransactionIndex >= 4) {
            transactionHistory.remove(0);
            lastTransactionIndex--;
        }
        lastTransactionIndex++;
        transactionHistory.add(successfulTransaction);
        transactionSuccess.display("Successful!", "Transaction completed successfully!");
        currentIndex = lastTransactionIndex;

    }



    String nextTransaction() {
        if (currentIndex >= lastTransactionIndex) {
            //error: no recorded transaction
            return "No more recorded Transactions or no recorded Transactions!";
        } else {

            currentIndex++;
            String type = transactionHistory.get(currentIndex).getType();
            Double value = transactionHistory.get(currentIndex).getValue();
            return "Type: " + type + ", Amount: " + value;
        }

    }

    String prevTransaction() {
        if (currentIndex < 0 ) {
            //error: no recorded transaction
            return "No more recorded Transactions or no recorded Transactions!";
        } else {

            String type = transactionHistory.get(currentIndex).getType();
            Double value = transactionHistory.get(currentIndex).getValue();
            currentIndex--;
            return "Type: " + type + ", Amount: " + value;
        }

    }

}


