package vn.funix.FX22859.java.Asm02;

import vn.funix.FX22859.java.Asm03.Withdraw;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Customer extends User {
    public List<Account> accounts;

    public Customer(String name, String customerID) {
        super(name, customerID);
        accounts = new ArrayList<Account>();
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public boolean isPremium() {
        for (Account account : accounts) {
            if (account.isPremium())
                return true;
        }
        return false;
    }

    public void addAccount(Account newAccount) {
        accounts.add(newAccount);
    }

    public boolean isAccountExisted(String accountNumber) {
        for (Account account : accounts) {
            if (Objects.equals(accountNumber, account.getAccountNumber())) {
                return true;
            }
        }
        return false;
    }


    public String getBalance() {
        double total = 0;
        for (Account account : accounts) {
            total += account.getBalance();
        }
        DecimalFormat decimalFormat = new DecimalFormat("#,##0đ");
        return decimalFormat.format(total);
    }


    public void displayInformation() {
        String isPre = isPremium() ? "Premium" : "Normal";
        System.out.println(getCustomerID() + "  " + getName() + "  " + isPre + "              " + getBalance());

        int index = 1;
        for (Account account : accounts) {
            System.out.println(index + "  " + account.toString());
            index++;
        }
    }

}
