package vn.funix.FX22859.java.Asm04.dao;

import vn.funix.FX22859.java.Asm02.Account;
import vn.funix.FX22859.java.Asm02.Customer;
import vn.funix.FX22859.java.Asm04.service.BinaryFileService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AccountDao {

  private final static String FILE_PATH = "/src/vn/funix/FX22859/java/Asm04/store/accounts.dat";

  private static String getFilePath() {
    String projectPath = System.getProperty("user.dir");
    return projectPath + FILE_PATH;
  }

  //function lưu danh sách tài khoản vào file
  public static void save(List<Account> accounts) {
    BinaryFileService.writeFile(getFilePath(), accounts);
  }

  //function lấy ra danh sách tài khoản từ file
  public static List<Account> list() {
    return BinaryFileService.readFile(getFilePath());
  }

  public static void addAccount(Account account) {
    List<Account> accounts = list();
    List<Account> updatedAccounts = new ArrayList<>(accounts);
    updatedAccounts.add(account);
    save(updatedAccounts);
  }

  // func cập nhật số dư cho tài khoản
  public static void update(Account editAccount) {
    List<Account> accounts = list();
    boolean hasExist = accounts.stream()
        .anyMatch(account -> account.getAccountNumber().equals(editAccount.getAccountNumber()));
    List<Account> updatedAccounts;
    if (!hasExist) {
      updatedAccounts = new ArrayList<>(accounts);
      updatedAccounts.add(editAccount);
    } else {
      updatedAccounts = new ArrayList<>();
      for (Account account : accounts) {
        if (account.getAccountNumber().equals(editAccount.getAccountNumber())) {
          updatedAccounts.add(editAccount);
        } else {
          updatedAccounts.add(account);
        }
      }
    }
    save(updatedAccounts);
  }
}
