package bank_work;

class BankTransaction {
    private String type;
    private double amount;
    private int balance;

    public BankTransaction(String type, double amount, int balance) {
        this.type = type;
        this.amount = amount;
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Type: " + type + ", Amount: " + amount + ", Balance: " + balance;
    }
}
