package vn.funix.FX22859.java.Asm02;

import vn.funix.FX22859.java.Asm03.Utils;
import vn.funix.FX22859.java.Asm03.models.Transaction;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Account {

    protected String accountNumber;
    protected double balance;
    private List<Transaction> transactions;

    public Account(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.transactions = new ArrayList<>();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void addTransaction(String accountNumber, double amount, boolean status) {
        this.transactions.add(new Transaction(accountNumber, amount, status));
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public static boolean minBalance(double balance) {
        return balance >= 50000;
    }

    public boolean isPremium() {
        return getBalance() >= 10000000;
    }

    public String toString() {
        DecimalFormat decimalFormat = new DecimalFormat("#,##0đ");
        return " " + accountNumber + "                    " + decimalFormat.format(balance);
    }

    public void transactionInfo() {
        for (Transaction transaction : transactions) {
            transaction.displayTransaction();
        }
    }


}
