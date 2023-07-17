package vn.funix.FX22859.java.Asm02;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Bank {

    protected String ID;
    protected List<Customer> customers;

    public Bank() {
        customers = new ArrayList<Customer>();
    }

    public String getID() {
        return ID;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void addCustomer(String customerName, String customerId) {
        Customer customer = new Customer(customerName, customerId);
        customers.add(customer);
    }

    public boolean isCustomerExisted(String customerId) {
        for (Customer customer : customers) {
            if (Objects.equals(customerId, customer.getCustomerID())) {
                return true;
            }
        }
        System.out.println("Số CCCD không tồn tại.");
        System.out.println("Vui lòng nhập lại CCCD: ");
        return false;
    }

    public void addAccount(String customerId, Account account) {
        for (Customer customer : customers) {
            if (customer.getCustomerID().equals(customerId)) {
                customer.addAccount(account);
                System.out.println("Thêm tài khoản thành công!");
                return;
            }
        }
    }
}
