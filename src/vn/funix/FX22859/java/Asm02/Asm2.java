package vn.funix.FX22859.java.Asm02;

import java.util.Scanner;

public class Asm2 {


    public static final Bank bank = new Bank();
    public static String AUTHOR = "FX22859";
    public static String VERSION = "V2.0.0";

    public static void main(String[] args) {
        menu();
        chucNang();
    }

    public static void menu() {
        System.out.println("+----------+----------------------+--------+");
        System.out.println("| NGÂN HÀNG SỐ | " + AUTHOR + "@" + VERSION + "            |");
        System.out.println("+----------+----------------------+--------+");
        System.out.println(" 1. Thêm khách hàng");
        System.out.println(" 2. Thêm tài khoản cho khách hàng");
        System.out.println(" 3. Hiển thị danh sách khách hàng");
        System.out.println(" 4. Tìm theo CCCD");
        System.out.println(" 5. Tìm theo tên khách hàng");
        System.out.println(" 0. Thoát");
        System.out.println("+----------+----------------------+--------+");
        System.out.println("Chức năng: ");
        chucNang();
    }

    public static void chucNang() {
        Scanner sc = new Scanner(System.in);
        int input;
        try {
            while (true) {
                input = sc.nextInt(); // Người dùng nhập giá trị từ bàn phím và lưu vào biến input
                if (input == 1) {
                    addCustomers();
                    menu();
                }
                if (input == 2) {
                    addAccountForCustomer();
                    menu();
                }
                if (input == 3) {
                    showCustomersList();
                    menu();
                }
                if (input == 4) {
                    findByCCCD();
                    menu();
                }
                if (input == 5) {
                    findByName();
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

    // CHỨC NĂNG 1: THÊM KHÁCH HÀNG
    public static void addCustomers() {
        bank.addCustomer();
    }

    // CHỨC NĂNG 2: THÊM TÀI KHOẢN CHO KH
    public static void addAccountForCustomer() {
        bank.addAccountForCustomer();
    }

    // CHỨC NĂNG 3: HIỂN THỊ THÔNG TIN KH
    public static void showCustomersList() {
        bank.showCustomersList();
    }

    // CHỨC NĂNG 4: TÌM THEO CCCD (TÌM CHÍNH XÁC)
    public static void findByCCCD() {
        bank.findByCCCD();
    }

    // CHỨC NĂNG 5: TÌM THEO KH (TÌM GẦN ĐÚNG)
    public static void findByName() {
        bank.findByName();
    }
}


