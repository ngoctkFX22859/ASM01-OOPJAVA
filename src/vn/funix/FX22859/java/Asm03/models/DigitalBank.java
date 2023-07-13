package vn.funix.FX22859.java.Asm03.models;

import vn.funix.FX22859.java.Asm02.Bank;
import vn.funix.FX22859.java.Asm02.Customer;

public class DigitalBank extends Bank {
    public
    public Customer getCustomerById(String cusId) {
        for (Customer customer : getCustomers())
            if (cusId.equals(customer.getCustomerID())) {
                return customer;
            }
        return null;
    }
}
