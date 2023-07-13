package vn.funix.FX22859.java.Asm02;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Bank {

    private String ID;
    private List<Customer> customers;

    public Bank() {
        customers = new ArrayList<Customer>();
    }

    public String getID() {
        return ID;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void addCustomer(Customer newCustomer) {
        customers.add(newCustomer);
    }

    public boolean isCustomerExisted(String customerId) {
        for (Customer customer : customers) {
            if (Objects.equals(customerId, customer.getCustomerID())) {
                return true;
            }
        }
        return false;
    }
}
