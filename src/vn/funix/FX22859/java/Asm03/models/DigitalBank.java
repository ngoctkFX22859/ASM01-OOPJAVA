package vn.funix.FX22859.java.Asm03.models;

import vn.funix.FX22859.java.Asm02.Account;
import vn.funix.FX22859.java.Asm02.Bank;
import vn.funix.FX22859.java.Asm02.Customer;
import vn.funix.FX22859.java.Asm04.dao.AccountDao;
import vn.funix.FX22859.java.Asm04.dao.CustomerDao;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


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

    public Account getAccount(String accNumber) {
        for (Customer customer : customers) {
            Account acc = customer.getAccount(accNumber);
            if (acc != null) {
                return acc;
            }
        }
        return null;
    }

    public void withdraw(String customerId, String accNumber, double amount) {
        DigitalCustomer cus = this.getCustomerById(customerId);
        if (cus != null) {
            cus.withdraw(accNumber, amount);
        }
    }

    @Override
    public void addCustomer(String customerName, String customerId) {
        // Kiểm tra xem customerId đã tồn tại trong danh sách hay chưa
        boolean customerExists = customers.stream().anyMatch(customer -> customer.getCustomerID().equals(customerId));

        if (customerExists) {
            System.out.println("Khách hàng " + customerId + " đã tồn tại trong danh sách.");
        } else {
            DigitalCustomer customer = new DigitalCustomer(customerName, customerId);
            customers.add(customer);
            System.out.println("Đã thêm khách hàng " + customerId + " vào danh sách.");
        }
    }


    public void showCustomers() {
        List<Customer> customers = CustomerDao.list();
        List<Account> accounts = AccountDao.list();
        if (customers.isEmpty()) {
            System.out.println("Chưa có khách hàng nào trong danh sách!");
        } else {
            for (Customer customer : customers) {
                List<Account> filteredAccounts = accounts.stream()
                        .filter(account -> account.getCustomerId().equals(customer.getCustomerID()))
                        .collect(Collectors.toList());
                customer.setAccounts(filteredAccounts);
                customer.displayInformation();
            }
        }
    }


    public void transfers(Scanner scanner, String customerId) {
        boolean isExisted = isCustomerExisted(customerId);
        if (!isExisted) {
            System.out.println("Khách hàng không tồn tại.");
            return;
        }

        // Hiển thị thông tin tài khoản của khách hàng
        Customer customer = getCustomerById(customerId);
        customer.displayInformation();

        System.out.println("Nhập số tài khoản gửi: ");
        String sendAccountNumber = scanner.next();
        boolean isValidSendAcc = checkAccNumber(sendAccountNumber);
        while (!isValidSendAcc) {
            sendAccountNumber = scanner.next();
        }
        Account existedSendAccount = getAccount(sendAccountNumber);
        while (existedSendAccount == null) {
            System.out.println("Số TK không tồn tại");
            System.out.println("Vui lòng nhập lại số TK: ");
            sendAccountNumber = scanner.next();
            existedSendAccount = getAccount(sendAccountNumber);
        }

        System.out.println("Nhập số tài khoản nhận: ");
        String receiveAccountNumber = scanner.next();
        boolean isValidReceiveAcc = checkAccNumber(receiveAccountNumber);
        while (!isValidReceiveAcc) {
            receiveAccountNumber = scanner.next();
        }
        Account existedReceiveAccount = getAccount(receiveAccountNumber);
        while (existedReceiveAccount == null || receiveAccountNumber != sendAccountNumber) {
            System.out.println("Số TK không tồn tại hoặc trùng với tk gửi");
            System.out.println("Vui lòng nhập lại số TK: ");
            receiveAccountNumber = scanner.next();
            existedReceiveAccount = getAccount(receiveAccountNumber);
        }
        System.out.println("Nhập số tiền cần chuyển: ");
        double amount = scanner.nextDouble();
        while (amount <= 50000) {
            System.out.println("Số tiền không hợp lệ.");
            System.out.println("Vui lòng nhập lại số tiền cần chuyển: ");
            amount = scanner.nextDouble();
        }
        System.out.print("Xác nhận thực hiện chuyển " + amount + "đ từ tài khoản [" + sendAccountNumber + "] đến tài khoản [" + receiveAccountNumber + "] (Y/N): ");
        String confirmation = scanner.next();

        if (!confirmation.toLowerCase().contains("Y")) {
            System.out.println("Giao dịch đã bị hủy.");
            return;
        } else {
            //goi transfer o SavingAccount
           /* if (isAccepted(amount)) {
                double newBalance = getBalance() - amount;
                setBalance(newBalance);
                // add Transaction
                newBalance = receiveAccount.getBalance() + amount;
                receiveAccount.setBalance(newBalance);
                System.out.println("Giao dịch thành công");
                logTransfer(receiveAccount, amount);
                return true;
            }
            */
            /*
            SavingsAccount senderAcc = (SavingsAccount) existedSendAccount;
            if (senderAcc.isAccepted(amount)) {
                double newBalance = senderAcc.getBalance() - amount;
                senderAcc.setBalance(newBalance);
                AccountDao.update(senderAcc);

                try {
                    senderAcc.createTransaction(amount, true, TransactionType.TRANSFER);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            SavingsAccount receiveAcc = (SavingsAccount) existedReceiveAccount;
            if (receiveAcc.isAccepted(amount)) {
                double newBalance = receiveAcc.getBalance() + amount;
                receiveAcc.setBalance(newBalance);
                AccountDao.update(receiveAcc);

                try {
                    receiveAcc.createTransaction(amount, true, TransactionType.DEPOSIT);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

             */


            SavingsAccount senderAcc = (SavingsAccount) existedSendAccount;
            SavingsAccount receiveAcc = (SavingsAccount) existedReceiveAccount;

            senderAcc.transfers(receiveAcc, amount);
        }

    }


}
