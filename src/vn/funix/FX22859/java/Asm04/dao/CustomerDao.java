package vn.funix.FX22859.java.Asm04.dao;

import vn.funix.FX22859.java.Asm02.Customer;
import vn.funix.FX22859.java.Asm04.service.BinaryFileService;

import java.io.IOException;
import java.util.List;

public class CustomerDao {
    private final static String FILE_PATH = "/Users/cuccung/FUNIX/Github/PRO192x_ASM_ngoctkFX22859/src/vn/funix/FX22859/java/Asm04/store/customers.dat";

    //function lưu danh sách khách hàng vào file
    public static void save(List<Customer> customers) throws IOException {
        BinaryFileService.writeFile(FILE_PATH, customers);
    }

    //function lấy ra danh sách khách hàng từ file
    public static List<Customer> list() {
        return BinaryFileService.readFile(FILE_PATH);
    }
}
