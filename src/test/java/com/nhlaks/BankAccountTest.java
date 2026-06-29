package com.nhlaks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BankAccountTest {
    private BankAccount account;

    @BeforeEach
    @SuppressWarnings("unused")
    void setUp() {
        account = new BankAccount("12345", "John Doe", 500.0);
    }

    @Test
    void testInitialBalance() {
        assertEquals(500.0, account.getBalance());
    }

    @Test
    void testDeposit() {
        account.deposit(150.0);
        assertEquals(650.0, account.getBalance());
    }

    @Test
    void testInvalidDeposit() {
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> account.deposit(-50.0));
        assertEquals(IllegalArgumentException.class, exception.getClass());
    }

    @Test
    void testWithdrawalSuccess() {
        account.withdraw(200.0);
        assertEquals(300.0, account.getBalance());
    }

    @Test
    void testWithdrawalInsufficientFunds() {
        InsufficientFundsException exception =
                assertThrows(InsufficientFundsException.class, () -> account.withdraw(600.0));
        assertEquals(InsufficientFundsException.class, exception.getClass());
    }

    @Test
    void testWithdrawalNegativeAmount() {
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> account.withdraw(-20.0));
        assertEquals(IllegalArgumentException.class, exception.getClass());
    }
}