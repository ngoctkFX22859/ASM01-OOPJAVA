package vn.funix.FX22859.java.Asm03.models;

import vn.funix.FX22859.java.Asm02.Account;
import vn.funix.FX22859.java.Asm02.Customer;
import vn.funix.FX22859.java.Asm03.Withdraw;

import java.awt.*;
import java.util.List;
import java.util.Objects;

public class DigitalCustomer extends Customer {
//    public List<Withdraw> withdraws;
//
//    public List<Withdraw> getWithdraws() {
//        return withdraws;
//    }

    public DigitalCustomer(String name, String customerID) {
        super(name, customerID);
    }

    public Withdraw getWithdraw(String accNumber) {
        for (Account acc : getAccounts()) {
            if (acc.getAccountNumber().equals(accNumber)) {
                return (Withdraw) acc;
            }
        }
        return null;
    }

    @Override
    public void displayInformation() {
        String isPre = isPremium() ? "Premium" : "Normal";
        System.out.println(getCustomerID() + "  " + getName() + "  " + isPre + "  " + getBalance());

        int index = 1;
        for (Account account : accounts) {
            System.out.println(index + "  " + account.toString());
            index++;
        }
    }

    public void withDraw(String accountNumber, double amount) {
    }
}
