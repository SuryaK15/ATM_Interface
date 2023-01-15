package bank_work;

import java.util.*;

public class Account {
    private String userId;
    private String pin;
    private String name;
    private double balance;
    private List<String> transactions;

    public Account(String userId, String pin, String name, double balance) {
        this.userId = userId;
        this.pin = pin;
        this.name = name;
        this.balance = balance;
        this.transactions = new ArrayList<>();
    }

    public String getUserId() {
        return userId;
    }

    public String getPin() {
        return pin;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void addTransaction(String type, double amount) {
        transactions.add(type + ": " + amount);
    }
    public String getTransactionsString() {
        return String.join("\n", transactions);
    }


    public String getTransactions() {
        StringBuilder str = new StringBuilder();
        for (String transaction : transactions) {
            str.append(transaction + "\n");
        }
        return str.toString();
    }
}
