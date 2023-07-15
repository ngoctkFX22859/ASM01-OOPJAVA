package vn.funix.FX22859.java.Asm03.models;

import vn.funix.FX22859.java.Asm02.Account;
import vn.funix.FX22859.java.Asm02.Customer;

import java.awt.*;
import java.util.Objects;

public class DigitalCustomer extends Customer {
    public DigitalCustomer(String name, String customerID) {
        super(name, customerID);
    }

    @Override
    public void displayInformation() {
        super.displayInformation();
        System.out.println("anh Tanhs");
    }

    @Override
    public void addAccount(Account newAccount) {
        super.addAccount(newAccount);
    }
}
