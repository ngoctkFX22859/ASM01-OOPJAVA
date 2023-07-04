package vn.funix.FX22859.java.asm02;

import java.text.DecimalFormat;

public class Account {

  private String accountNumber;
  private double balance;

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

  public boolean isPremium() {
    return getBalance() >= 10000000;
  }

  public String toString() {
    DecimalFormat decimalFormat = new DecimalFormat("#,##0đ");
    return " " + accountNumber + "               " + decimalFormat.format(balance);
  }
}
