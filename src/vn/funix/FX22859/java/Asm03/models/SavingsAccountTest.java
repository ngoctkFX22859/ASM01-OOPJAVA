package vn.funix.FX22859.java.Asm03.models;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

class SavingsAccountTest {
    private SavingsAccount sa;

    @org.junit.Before
    public void setup() {
        sa = new SavingsAccount("077000000001", "111111", 20000000);
    }

    @Test
    void withDraw() {
        assertFalse(sa.withDraw(-50000));// False vì nhập số âm
        assertTrue(sa.withDraw(50000));// True vì nhập ít nhất 50K và là bội của 10K, nhỏ hơn số dư hiện tại.
        assertFalse(sa.withDraw(100000000));// False vượt 5tr tối đa 1 lần rút và khi rút ra se không đủ 50K số dư còn lại
        assertFalse(sa.withDraw(450001));// False vì ko chia hết cho 10K
        assertTrue(sa.withDraw(5000000));// True vì chưa vượt hạn mưc tối đa 1 lần rút và khi rút ra  vẫn còn hơn 50K số dư còn lại
        assertFalse(sa.withDraw(5000001)); // false vì số vượt 5tr và số ko chia hết cho 10K

    }

    @org.junit.jupiter.api.Test
    void transfers() {
    }
}