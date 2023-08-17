package vn.funix.FX22859.java.Asm04;

import vn.funix.FX22859.java.Asm02.Account;
import vn.funix.FX22859.java.Asm02.Customer;
import vn.funix.FX22859.java.Asm03.models.DigitalBank;
import vn.funix.FX22859.java.Asm03.models.DigitalCustomer;
import vn.funix.FX22859.java.Asm03.models.SavingsAccount;
import vn.funix.FX22859.java.Asm04.dao.AccountDao;
import vn.funix.FX22859.java.Asm04.dao.CustomerDao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Asm04 {

  private static final Integer EXIT_COMMAND_CODE = 0;
  private static final Integer EXIT_ERROR_CODE = -1;
  public static final Scanner scanner = new Scanner(System.in);
  private static final DigitalBank activeBank = new DigitalBank();
  private static final double LOAN_ACCOUNT_MAX_BALANCE = 100000000;
  private static final String CUSTOMER_ID = "077123456789";
  private static final String CUSTOMER_NAME = "ESTHER";
  public static String AUTHOR = "FX22859";
  public static String VERSION = "V4.0.0";

  public static void main(String[] args) {
    menu();
  }

  private static void menu() {
    System.out.println("+----------+----------------------+--------+");
    System.out.println("| NGÂN HÀNG SỐ | " + AUTHOR + "@" + VERSION + "            |");
    System.out.println("+----------+----------------------+--------+");
    System.out.println(" 1. Xem danh sách khách hàng");
    System.out.println(" 2. Nhập danh sách khách hàng");
    System.out.println(" 3. Thêm tài khoản ATM");
    System.out.println(" 4. Chuyển tiền");
    System.out.println(" 5. Rút tiền");
    System.out.println(" 6. Tra cứu lịch sử giao dịch");
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
          inputCustomer();
          menu();
        }
        if (input == 3) {
          addSavingsAccount();
          menu();
        }
        if (input == 4) {
          transfer();
          menu();
        }
        if (input == 5) {
          menu();
        }
        if (input == 6) {
          menu();
        }
        if (input == 0) {
          System.out.println("Exit!");
          System.exit(EXIT_COMMAND_CODE); // Kết thúc chương trình
        }
        System.out.println("Số bạn nhập không đúng, vui lòng nhập lại");
      }
    } catch (Exception e) {
      e.printStackTrace(); //phương thức printStackTrace() sẽ in ra thông báo lỗi vào console.
      sc.next();
      System.out.println("Lỗi nhập liệu hoặc số bạn nhập không hợp lệ. Vui lòng nhập lại.");
    }
  }

  //CN1: XEM THÔNG TIN KHÁCH HÀNG
  private static void showCustomer() {
    activeBank.showCustomers();
  }

  //CN2: NHẬP DS KH
  public static void inputCustomer() {
    System.out.println("Nhập đường dẫn đến tệp: ");
    String fileName = scanner.nextLine();
    readFile(fileName);
    try {
      CustomerDao.save(activeBank.getCustomers());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  // Đọc File customers.txt
  public static void readFile(String fileName) {
    try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(fileName)))) {
      scanner.useDelimiter(",");
      while (scanner.hasNextLine()) {
        String CCCD = scanner.next();
        scanner.skip(scanner.delimiter());
        String name = scanner.nextLine();
        activeBank.addCustomer(name, CCCD);
      }
    } catch (IOException e) {
      System.out.println("Tệp không tồn tại");
    } catch (Exception e) {
      System.out.println("Dữ liệu không hợp lệ!!");
    }
  }

  //CN3: THÊM TÀI KHOẢN ATM
  private static void addSavingsAccount() {
    System.out.println("Nhập số CCCD của khách hàng: ");
    String customerId = scanner.next();
    Customer customer = CustomerDao.getCustomer(customerId);
    while (customer == null) {
      System.out.println("Số CCCD không tồn tại.");
      System.out.println("Vui lòng nhập lại CCCD: ");
      customerId = scanner.next();
      customer = activeBank.getCustomer(customerId);
    }

    System.out.println("Nhập số TK gồm 6 chữ số: ");
    String accNumber = scanner.next();
    while (!activeBank.checkAccNumber(accNumber)) {
      accNumber = scanner.next();
    }
    boolean isExistedAccount = activeBank.isAccountExisted(accNumber);
    while (isExistedAccount) {
      accNumber = scanner.next();
      isExistedAccount = activeBank.isAccountExisted(accNumber);
    }

    System.out.println("Nhập số dư: ");
    double balance = scanner.nextDouble();
    boolean isMinBalance = Account.minBalance(balance);

    while (!isMinBalance) {
      System.out.println("Số dư tối thiểu là 50.000: ");
      System.out.println("Vui lòng nhập lại: ");
      balance = scanner.nextDouble();
      isMinBalance = Account.minBalance(balance);
    }
    // Tạo đối tượng mới cho lớp SavingsAccount
    SavingsAccount account = new SavingsAccount(customerId, accNumber, balance);
//        activeBank.addAccount(customerId, account);
    AccountDao.addAccount(account);
  }

  //CN4: CHUYỂN
  public static void transfer() {
    System.out.println("Nhập số CCCD của khách hàng: ");
    String customerId = scanner.next();
    activeBank.transfers(scanner, customerId);
    try {
      CustomerDao.save(activeBank.getCustomers());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


}
