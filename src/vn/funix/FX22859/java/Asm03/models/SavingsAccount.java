package vn.funix.FX22859.java.Asm03.models;

import vn.funix.FX22859.java.Asm02.Account;
import vn.funix.FX22859.java.Asm02.Customer;
import vn.funix.FX22859.java.Asm03.Report;
import vn.funix.FX22859.java.Asm03.Utils;
import vn.funix.FX22859.java.Asm03.Withdraw;

public class SavingsAccount extends Account implements Withdraw, Report {
    private final double SAVINGS_ACCOUNT_MAX_WITHDRAW = 5000000;

    public SavingsAccount(String accountNumber, double balance) {
        super(accountNumber, balance);
    }

    @Override
    public boolean withDraw(double amount) {
        double newBalance;
        if (isAccepted(amount)) {
            newBalance = getBalance() - amount;
            setBalance(newBalance);
            return true;
        }
        return false;
    }

    @Override
    public boolean isAccepted(double amount) {
        double minAmount = 50000;
        if (minBalance((getBalance() - amount)) && amount % 10000 == 0 && amount >= minAmount) {
            if (!isPremium()) {
                if (amount <= SAVINGS_ACCOUNT_MAX_WITHDRAW) {
                    return true;
                } else {
                    System.out.println("Không thể rút trên 5.000.000đ trong một giao dịch. Vui lòng nhập lại số tiền cần rút: ");
                    return false;
                }
            } else {
                return true;
            }
        } else {
            if (!minBalance(getBalance() - amount)) {
                System.out.println("Số dư sau khi rút không thể dưới 50.000đ. Vui lòng nhập lại số tiền cần rút: ");
            } else if (amount % 10000 != 0) {
                System.out.println("Số tiền rút phải là bội số của 10.000đ. Vui lòng nhập lại số tiền cần rút: ");
            } else if (amount < minAmount) {
                System.out.println("Số tiền rút tối thiểu 50.000đ. Vui lòng nhập lại số tiền cần rút: ");
            }
        }
        return false;
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
