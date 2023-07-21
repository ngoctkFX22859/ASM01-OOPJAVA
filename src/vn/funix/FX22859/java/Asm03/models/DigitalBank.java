package vn.funix.FX22859.java.Asm03.models;

import vn.funix.FX22859.java.Asm02.Bank;
import vn.funix.FX22859.java.Asm02.Customer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class DigitalBank extends Bank {
    public DigitalCustomer getCustomerById(String cusId) {
        for (Customer customer : getCustomers())
            if (cusId.equals(customer.getCustomerID())) {
                if (customer instanceof DigitalCustomer) {
                    return (DigitalCustomer) customer;
                }
            }
        return null;
    }

    public static boolean checkAccNumber(String accNumber) {
        Pattern pattern = Pattern.compile("^\\d{6}$");
        Matcher matcher = pattern.matcher(accNumber);
        boolean isValid = matcher.matches();

        if (!isValid) {
            System.out.println("Số TK không đúng. Vui lòng nhập lại:");
        }

        return isValid;
    }

    public boolean isAccountExisted(String accNumber) {
        for (Customer customer : customers) {
            if (customer.isAccountExisted(accNumber)) {
                return true;
            }
        }
        return false;
    }

    public void withdraw(String customerId, String accNumber, double amount) {
        DigitalCustomer cus = this.getCustomerById(customerId);
        if (cus != null) {
            cus.withdraw(accNumber, amount);
        }
    }

    @Override
    public void addCustomer(String customerName, String customerId) {
        DigitalCustomer customer = new DigitalCustomer(customerName, customerId);
        customers.add(customer);
    }
}
