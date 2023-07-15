package vn.funix.FX22859.java.Asm02;

import java.text.DecimalFormat;

public class Account {

    protected String accountNumber;
    protected double balance;

    public Account(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
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
        String className = this.getClass().getSimpleName();
        String subStr = className.substring(0, className.length() - 7).toUpperCase();
        DecimalFormat decimalFormat = new DecimalFormat("#,##0đ");
        return " " + accountNumber + "            " + subStr + "     " + decimalFormat.format(balance);
    }
}
