package vn.funix.FX22859.java.Asm03;

import vn.funix.FX22859.java.Asm02.Customer;
import vn.funix.FX22859.java.Asm03.models.DigitalBank;

import java.util.Scanner;

public class Asm3 {
    private static final Integer EXIT_COMMAND_CODE = 0;
    private static final Integer EXIT_ERROR_CODE = -1;
    private static final Scanner scanner = new Scanner(System.in);
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
                input = sc.nextInt(); // Người dùng nhập giá trị từ bàn phím và lưu vào biến input
                if (input == 1) {
                    showCustomer();
                    menu();
                }
                if (input == 2) {
                    addAtmAccount();
                    menu();
                }
                if (input == 3) {
                    addCreditAccount();
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
        Customer customer = new Customer(CUSTOMER_NAME, CUSTOMER_ID);
        activeBank.addCustomer(customer);
    }

    private static void showCustomer() {
        Customer customer = activeBank.getCustomerById(CUSTOMER_ID);
    }

    private static void addAtmAccount() {
    }

    private static void addCreditAccount() {
    }

    private static void withDraw() {
    }

    private static void transactionHistory() {
    }


}
