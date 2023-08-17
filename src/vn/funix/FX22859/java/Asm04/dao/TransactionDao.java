package vn.funix.FX22859.java.Asm04.dao;

import vn.funix.FX22859.java.Asm02.Account;
import vn.funix.FX22859.java.Asm03.models.Transaction;
import vn.funix.FX22859.java.Asm04.service.BinaryFileService;

import java.io.IOException;
import java.util.List;

public class TransactionDao {

  private final static String FILE_PATH = "/src/vn/funix/FX22859/java/Asm04/store/transactions.dat";

  private static String getFilePath() {
    String projectPath = System.getProperty("user.dir");
    return projectPath + FILE_PATH;
  }

  //function lưu danh sách tài khoản vào file
  public static void save(List<Transaction> transactions) throws IOException {
    BinaryFileService.writeFile(getFilePath(), transactions);
  }

  //function lấy ra danh sách tài khoản từ file
  public static List<Transaction> list() {
    return BinaryFileService.readFile(getFilePath());
  }
}
