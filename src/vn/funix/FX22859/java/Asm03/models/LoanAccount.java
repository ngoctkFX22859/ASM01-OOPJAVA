package vn.funix.FX22859.java.Asm03.models;

import vn.funix.FX22859.java.Asm02.Account;
import vn.funix.FX22859.java.Asm03.Report;
import vn.funix.FX22859.java.Asm03.Utils;
import vn.funix.FX22859.java.Asm03.Withdraw;

public class LoanAccount extends Account implements Withdraw, Report {

    public LoanAccount(String accountNumber, double balance) {
        super(accountNumber, balance);
    }

    @Override
    public boolean withDraw(double amount) {
        double newBalance;
        if (isAccepted(amount)) {
            newBalance = getBalance() - (amount + (amount * getTransactionFee()));
            setBalance(newBalance);
            return true;
        }
        return false;
    }

    @Override
    public boolean isAccepted(double amount) {
        if (amount <= 1000000000 && minBalance(getBalance() - amount)) {
            return true;
        } else {
            if (amount > 1000000000) {
                System.out.println("Số tiền đã vượt qua hạn mức 100.000.000đ. Vui lòng nhập lại số tiền cần rút: ");
            }
            if (!minBalance(getBalance() - amount)) {
                System.out.println("Số dư sau khi rút không thể dưới 50.000đ. Vui lòng nhập lại số tiền cần rút: ");
            }
        }
        return false;
    }

    public double getTransactionFee() {
        if (!isPremium()) {
            return 0.05;
        } else {
            return 0.01;
        }
    }

    @Override
    public void log(double amount) {
        System.out.println(Utils.getDivider());
        System.out.printf("%30s%n", Utils.getTitle() + " SAVING");
        System.out.printf("NGAY G/D: %28s%n", Utils.getDateTime());
        System.out.printf("ATM ID: %30s%n", "DIGITAL-BANK-ATM 2023");
        System.out.printf("SO TK: %31s%n", getAccountNumber());
        System.out.printf("SO TIEN: %29s%n", Utils.formatBalance(amount));
        System.out.printf("SO DU: %31s%n", Utils.formatBalance(getBalance()));
        System.out.printf("PHI + VAT: %27s%n", Utils.formatBalance(0.0));
        System.out.println(Utils.getDivider());
    }
}
