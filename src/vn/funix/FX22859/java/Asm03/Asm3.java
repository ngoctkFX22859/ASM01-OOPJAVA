package vn.funix.FX22859.java.Asm03;

import vn.funix.FX22859.java.Asm02.Customer;
import vn.funix.FX22859.java.Asm03.models.DigitalBank;
import vn.funix.FX22859.java.Asm03.models.DigitalCustomer;
import vn.funix.FX22859.java.Asm03.models.LoanAccount;
import vn.funix.FX22859.java.Asm03.models.SavingsAccount;

import java.util.Scanner;

public class Asm3 {
    private static final Integer EXIT_COMMAND_CODE = 0;
    private static final Integer EXIT_ERROR_CODE = -1;
    public static final Scanner scanner = new Scanner(System.in);
    private static final DigitalBank activeBank = new DigitalBank();
    private static final String CUSTOMER_ID = "077123456789";
    private static final String CUSTOMER_NAME = "ESTHER";
    public static String AUTHOR = "FX22859";
    public static String VERSION = "V3.0.0";

    public static void main(String[] args) {
        addCustomer();
        menu();
    }

    private static void menu() {
        System.out.println("+----------+----------------------+--------+");
        System.out.println("| NGÂN HÀNG SỐ | " + AUTHOR + "@" + VERSION + "            |");
        System.out.println("+----------+----------------------+--------+");
        System.out.println(" 1. Xem Thông tin khách hàng");
        System.out.println(" 2. Thêm tài khoản ATM");
        System.out.println(" 3. Thêm tài khoản tín dụng");
        System.out.println(" 4. Rút tiền");
        System.out.println(" 5. Tra cứu lịch sử giao dịch");
        System.out.println(" 0. Thoát");
        System.out.println("+----------+----------------------+--------+");
        System.out.println("Chức năng: ");
        function();
    }

    public static void function() {
        Scanner sc = new Scanner(System.in);
        int input;
        try {
            while (true) {
                input = sc.nextInt();
                if (input == 1) {
                    showCustomer();
                    menu();
                }
                if (input == 2) {
                    addSavingsAccount();
                    menu();
                }
                if (input == 3) {
                    addLoanAccount();
                    menu();
                }
                if (input == 4) {
                    withDraw();
                    menu();
                }
                if (input == 5) {
                    transactionHistory();
                    menu();
                }
                if (input == 0) {
                    System.out.println("Exit!");
                    System.exit(0); // Kết thúc chương trình
                }
                System.out.println("Số bạn nhập không đúng, vui lòng nhập lại");
            }
        } catch (Exception e) {
            e.printStackTrace(); //phương thức printStackTrace() sẽ in ra thông báo lỗi vào console, giúp xác định nguyên nhân và vị trí của lỗi trong chương trình.
            sc.nextLine();
            System.out.println("Lỗi nhập liệu hoặc số bạn nhập không hợp lệ. Vui lòng nhập lại.");
        }
    }

    private static void addCustomer() {
        activeBank.addCustomer(CUSTOMER_NAME, CUSTOMER_ID);
    }

    private static void showCustomer() {
        Customer customer = activeBank.getCustomerById(CUSTOMER_ID);
        if (customer != null) {
            customer.displayInformation();
        }
    }

    private static void addSavingsAccount() {
        System.out.println("Nhập số CCCD của khách hàng: ");
        String customerId = scanner.next();
        boolean isExisted = activeBank.isCustomerExisted(customerId);
        while (!isExisted) {
            System.out.println("Số CCCD không tồn tại.");
            System.out.println("Vui lòng nhập lại CCCD: ");
            customerId = scanner.nextLine();
            isExisted = activeBank.isCustomerExisted(customerId);
        }
        System.out.println("Nhập số TK gồm 6 chữ số: ");
        String accNumber = scanner.next();
        while (!activeBank.checkAccNumber(accNumber)) {
            accNumber = scanner.next();
        }
        boolean isExistedAccount = activeBank.isAccountExisted(accNumber);
        while (isExistedAccount) {
            System.out.println("Số TK bị trùng");
            System.out.println("Vui lòng nhập lại số TK: ");
            accNumber = scanner.next();
            isExistedAccount = activeBank.isAccountExisted(accNumber);
        }

        System.out.println("Nhập số dư: ");
        double balance = scanner.nextDouble();
        while (balance < 50000) {
            System.out.println("Số dư tối thiểu là 50.000: ");
            System.out.println("Vui lòng nhập lại: ");
            balance = scanner.nextDouble();
        }
        SavingsAccount account = new SavingsAccount(accNumber, balance);
        activeBank.addAccount(customerId, account);
    }


    private static void addLoanAccount() {
        System.out.println("Nhập số CCCD của khách hàng: ");
        String customerId = scanner.next();
        boolean isExisted = activeBank.isCustomerExisted(customerId);
        while (!isExisted) {
            System.out.println("Số CCCD không tồn tại.");
            System.out.println("Vui lòng nhập lại CCCD: ");
            customerId = scanner.nextLine();
            isExisted = activeBank.isCustomerExisted(customerId);
        }
        System.out.println("Nhập số TK gồm 6 chữ số: ");
        String accNumber = scanner.next();
        while (!activeBank.checkAccNumber(accNumber)) {
            accNumber = scanner.next();
        }
        boolean isExistedAccount = activeBank.isAccountExisted(accNumber);
        while (isExistedAccount) {
            System.out.println("Số TK bị trùng");
            System.out.println("Vui lòng nhập lại số TK: ");
            accNumber = scanner.next();
            isExistedAccount = activeBank.isAccountExisted(accNumber);
        }

        double balance = 100000000;
        LoanAccount account = new LoanAccount(accNumber, balance);
        activeBank.addAccount(customerId, account);
    }

    private static void withDraw() {
        String accNumber, customerId;
        double amount;
        System.out.println("Nhập CCCD của khách hàng: ");
        customerId = scanner.next();
        boolean isExisted = activeBank.isCustomerExisted(customerId);
        while (!isExisted) {
            System.out.println("Số CCCD không tồn tại.");
            System.out.println("Vui lòng nhập lại CCCD: ");
            customerId = scanner.nextLine();
            isExisted = activeBank.isCustomerExisted(customerId);
        }

        System.out.println("Nhập số tài khoản của khách hàng: ");
        accNumber = scanner.next();
        while (!activeBank.checkAccNumber(accNumber)) {
            accNumber = scanner.next();
        }
        boolean isExistedAccount = activeBank.isAccountExisted(accNumber);
        while (!isExistedAccount) {
            System.out.println("Số TK không tồn tại");
            System.out.println("Vui lòng nhập lại số TK: ");
            accNumber = scanner.next();
            isExistedAccount = activeBank.isAccountExisted(accNumber);
        }
        System.out.println("Nhập số tiền cần rút: ");
        amount = scanner.nextDouble();

        DigitalCustomer cus = activeBank.getCustomerById(customerId);
        Withdraw wd = cus.getWithdraw(accNumber);
        if (wd.withDraw(amount)) {
            System.out.println("Giao dich thanh cong");
        } else {
            System.out.println("giao dich khong thanh cong");
        }
    }

    private static void transactionHistory() {
    }


}
