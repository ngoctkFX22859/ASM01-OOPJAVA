package vn.funix.FX22859.java.Asm02;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Pattern;

import static vn.funix.FX22859.java.Asm02.Asm2.bank;

public class Bank {

    private List<Customer> customers;

    public Bank() {
        this.customers = new ArrayList<Customer>();
    }

    public List<Customer> getCustomers() {

        return customers;
    }

    public boolean isCustomerExisted(String customerId) {
        for (Customer customer : customers) {
            if (Objects.equals(customerId, customer.getCustomerID())) {
                return true;
            }
        }
        return false;
    }

    // ****** Chức năng thêm khách hàng: ******
    public void addCustomer() {
        Scanner sc = new Scanner(System.in);
        String name, customerId;
        System.out.println("Nhập tên khách hàng: ");
        name = sc.next();
        System.out.println("Vui lòng nhập CCCD (12 ký tự): ");
        customerId = sc.next();
        while (customerId.length() != 12 || !User.isFirstThreeDigitsValid(customerId)
                || customerId.matches(".*[a-zA-Z].*")) {
            System.out.println(
                    "CCCD không hợp lệ. Vui lòng kiểm tra và nhập lại hoặc 'no' để thoát.");
            customerId = sc.next();
            if (customerId.equals("no") || customerId.equals("No")) {
                System.out.println("Thoát!");
                System.exit(0); // Kết thúc chương trình
            }
        }

        // ****** Kiểm tra số CCCD đã tồn tại hay chưa: ******

        boolean isExisted = bank.isCustomerExisted(customerId);
        while (isExisted) {
            System.out.println("Số CCCD bị trùng");
            System.out.println("Vui lòng nhập lại CCCD: ");
            customerId = sc.next();
            isExisted = bank.isCustomerExisted(customerId);
        }
        Customer customer = new Customer(name, customerId);
        customers.add(customer);
        System.out.println("Đã thêm khách hàng " + customerId + " vào danh sách.");
    }

    // ****** Chức năng thêm tài khoản cho khách hàng ******
    public void addAccountForCustomer() {
        Scanner sc = new Scanner(System.in);
        String cusId;
        String accNumbers;
        double balance;
        System.out.println("Nhập số CCCD của khách hàng: ");
        cusId = sc.nextLine();
        boolean isExisted = bank.isCustomerExisted(cusId);
        //Check CCCD
        while (!isExisted) {
            System.out.println("Số CCCD không tồn tại.");
            System.out.println("Vui lòng nhập lại CCCD: ");
            cusId = sc.nextLine();
            isExisted = bank.isCustomerExisted(cusId);
        }
        // ****** Check số tài khoản ******
        System.out.println("Nhập số TK gồm 6 chữ số: ");
        accNumbers = sc.nextLine();
        Pattern pattern = Pattern.compile("^\\d{6}$");
        while (!(pattern.matcher(accNumbers).find())) {
            System.out.println("Số TK không đúng. Vui lòng nhập lại: ");
            accNumbers = sc.nextLine();
        }

        // ****** Chỉ thêm tài khoản vào nếu khách hàng tồn tại ******
        Customer currentCustomer = null;
        for (Customer customer : bank.getCustomers()) {
            boolean isExistedAccount = customer.isAccountExisted(accNumbers);
            while (isExistedAccount) {
                System.out.println("Số TK bị trùng");
                System.out.println("Vui lòng nhập lại số TK: ");
                accNumbers = sc.next();
                isExistedAccount = customer.isAccountExisted(accNumbers);
            }
            if (customer.getCustomerID().equals(cusId)) {
                currentCustomer = customer;
            }
        }
        System.out.println("Nhập số dư: ");
        balance = sc.nextDouble();
        while (balance < 50000) {
            System.out.println("Số dư tối thiểu là 50.000: ");
            System.out.println("Vui lòng nhập lại: ");
            balance = sc.nextDouble();
        }


        Account account = new Account(accNumbers, balance);
        if (currentCustomer != null) {
            currentCustomer.addAccount(account);
        }
        System.out.println("Thêm tài khoản thành công!");
    }

    // ****** Chức năng hiển thị thông tin khách hàng ******
    public void showCustomersList() {
        for (Customer customer : bank.getCustomers()) {
            customer.displayInformation();
        }
    }

    // ****** Chức năng tìm kiếm khách hàng chính xác theo CCCD ******
    public void findByCCCD() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Vui lòng nhập số CCCD cần tìm");
        String cusId = sc.nextLine();
        boolean found = false;
        for (Customer customer : bank.getCustomers()) {
            if (customer.getCustomerID().equals(cusId)) {
                customer.displayInformation();
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Không tìm thấy tài khoản. Vui lòng kiểm tra lại CCCD đã nhập");
        }
    }

    // ****** Tìm kiếm khách hàng gần đúng theo tên ******
    public void findByName() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Vui lòng nhập tên cần tìm:");
        String name = sc.nextLine().toLowerCase();
        boolean found = false;
        for (Customer customer : bank.getCustomers()) {
            if (customer.getName().toLowerCase().contains(name)) {
                customer.displayInformation();
                found = true;
            }
        }
        if (!found) {
            System.out.println("Không tìm thấy tài khoản. Vui lòng kiểm tra lại tên đã nhập");
        }
    }
}
