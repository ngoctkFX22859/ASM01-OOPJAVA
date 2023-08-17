package vn.funix.FX22859.java.Asm04.dao;

import vn.funix.FX22859.java.Asm02.Account;
import vn.funix.FX22859.java.Asm02.Customer;
import vn.funix.FX22859.java.Asm04.service.BinaryFileService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AccountDao {
    private final static String FILE_PATH = "/Users/cuccung/FUNIX/Github/PRO192x_ASM_ngoctkFX22859/src/vn/funix/FX22859/java/Asm04/store/accounts.dat";

    //function lưu danh sách tài khoản vào file
    public static void save(List<Account> accounts) {
        BinaryFileService.writeFile(FILE_PATH, accounts);
    }

    //function lấy ra danh sách tài khoản từ file
    public static List<Account> list() {
        return BinaryFileService.readFile(FILE_PATH);
    }

    // func cập nhật số dư cho tài khoản
    public static void update(Account editAccount) {
        List<Account> accounts = list();
        boolean hasExist = accounts.stream().anyMatch(account -> account.getAccountNumber().equals(editAccount.getAccountNumber()));
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
