package vn.funix.FX22859.java.Asm03.models;

import vn.funix.FX22859.java.Asm03.Utils;

import java.util.UUID;

public class Transaction {
    private String id;
    private String accountNumber;
    private double amount;
    private String time;
    private boolean status;

    public Transaction(String accountNumber, double amount, boolean status) {
        this.id = String.valueOf(UUID.randomUUID()).substring(0, 6);
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.time = Utils.getDateTime();
        this.status = status;
    }


    public String getId() {
        return id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getAmount() {
        return amount;
    }

    public String getTime() {
        return time;
    }

    public boolean isStatus() {
        return status;
    }

    public void displayTransaction() {
        System.out.println("[GD]   " + accountNumber + " | " + Utils.formatBalance(this.getAmount()) + "    |   " + this.getTime() + "        |       " + (this.isStatus() ? "Giao dịch thành công" : "Giao dịch không thành công"));
    }
}
