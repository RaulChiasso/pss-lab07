package it.unibo.bank.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions;

import it.unibo.bank.api.AccountHolder;
import it.unibo.bank.api.BankAccount;

public class TestSimpleBankAccount {
    private AccountHolder aRossi;
    private AccountHolder aBianchi;
    private BankAccount accountRossi;
    private BankAccount accountBianchi;

    @BeforeEach
    public void setUp() {
        this.aRossi = new AccountHolder("Andrea", "Rossi", 1);
        this.aBianchi = new AccountHolder("Alex", "Bianchi", 2);
        this.accountRossi = new SimpleBankAccount(aRossi, 0.0);
        this.accountBianchi = new SimpleBankAccount(aBianchi, 0.0);
    }

    @Test
    public void testNewSimpleBankAccount() {
        Assertions.assertEquals(0.0, this.accountRossi.getBalance());
        Assertions.assertEquals(0, this.accountRossi.getTransactionsCount());
        Assertions.assertEquals(0.0, this.accountBianchi.getBalance());
        Assertions.assertEquals(0, this.accountBianchi.getTransactionsCount());
        Assertions.assertSame(this.aRossi, this.accountRossi.getAccountHolder());
        Assertions.assertSame(this.aBianchi, this.accountBianchi.getAccountHolder());
    }    

    @Test
    public void testDeposit(){
        double depositPositive = 1000;
        double depositNegative = -1000;
        accountRossi.deposit(1, depositPositive);
        accountBianchi.deposit(2, depositPositive);
        Assertions.assertEquals(accountRossi.getBalance(), 1000);
        Assertions.assertEquals(accountBianchi.getBalance(), 1000);
        accountRossi.deposit(1, depositNegative);
        accountBianchi.deposit(2, depositNegative);
        Assertions.assertEquals(accountRossi.getBalance(), 1000);
        Assertions.assertEquals(accountBianchi.getBalance(), 1000);
        accountRossi.deposit(2, depositPositive);
        accountBianchi.deposit(1, depositPositive);
        Assertions.assertNotEquals(accountRossi.getBalance(), 2000);
        Assertions.assertNotEquals(accountBianchi.getBalance(), 2000);
        Assertions.assertEquals(accountRossi.getTransactionsCount(), 1);
        Assertions.assertEquals(accountBianchi.getTransactionsCount(), 1);
    }

    @Test
    public void testDepositFromATM(){
        double depositPositive = 1000;
        double depositNegative = -1000;
        accountRossi.depositFromATM(1, depositPositive);
        accountBianchi.depositFromATM(2, depositPositive);
        Assertions.assertEquals(accountRossi.getBalance(), 1000-SimpleBankAccount.ATM_TRANSACTION_FEE);
        Assertions.assertEquals(accountBianchi.getBalance(), 1000-SimpleBankAccount.ATM_TRANSACTION_FEE);
        accountRossi.deposit(1, depositNegative);
        accountBianchi.deposit(2, depositNegative);
        Assertions.assertEquals(accountRossi.getBalance(), 1000-SimpleBankAccount.ATM_TRANSACTION_FEE);
        Assertions.assertEquals(accountBianchi.getBalance(), 1000-SimpleBankAccount.ATM_TRANSACTION_FEE);
        accountRossi.deposit(2, depositPositive);
        accountBianchi.deposit(1, depositPositive);
        Assertions.assertNotEquals(accountRossi.getBalance(), 2000-2*SimpleBankAccount.ATM_TRANSACTION_FEE);
        Assertions.assertNotEquals(accountBianchi.getBalance(), 2000-2*SimpleBankAccount.ATM_TRANSACTION_FEE);
        Assertions.assertEquals(accountRossi.getTransactionsCount(), 1);
        Assertions.assertEquals(accountBianchi.getTransactionsCount(), 1);
    }

    @Test
    public void testWithdraw(){
        double withdrawPositive = 500;
        double withdrawNegative = -500;
        double depositPositive = 1000;
        accountRossi.deposit(1, depositPositive);
        accountBianchi.deposit(2, depositPositive);
        accountRossi.withdraw(1, withdrawPositive);
        accountBianchi.withdraw(2, withdrawPositive);
        Assertions.assertEquals(accountRossi.getBalance(), 500);
        Assertions.assertEquals(accountBianchi.getBalance(), 500);
        accountRossi.withdraw(1, withdrawNegative);
        accountBianchi.withdraw(2, withdrawNegative);
        Assertions.assertEquals(accountRossi.getBalance(), 500);
        Assertions.assertEquals(accountBianchi.getBalance(), 500);
        accountRossi.withdraw(2, withdrawPositive);
        accountBianchi.withdraw(1, withdrawPositive);
        Assertions.assertNotEquals(accountRossi.getBalance(), 0);
        Assertions.assertNotEquals(accountBianchi.getBalance(), 0);
        Assertions.assertEquals(accountRossi.getTransactionsCount(), 2);
        Assertions.assertEquals(accountBianchi.getTransactionsCount(), 2);
    }

    @Test
    public void testWithdrawFromATM(){
        double withdrawPositive = 500;
        double withdrawNegative = -500;
        double depositPositive = 1000;
        accountRossi.deposit(1, depositPositive);
        accountBianchi.deposit(2, depositPositive);
        accountRossi.withdrawFromATM(1, withdrawPositive);
        accountBianchi.withdrawFromATM(2, withdrawPositive);
        Assertions.assertEquals(accountRossi.getBalance(), 500-SimpleBankAccount.ATM_TRANSACTION_FEE);
        Assertions.assertEquals(accountBianchi.getBalance(), 500-SimpleBankAccount.ATM_TRANSACTION_FEE);
        accountRossi.withdrawFromATM(1, withdrawNegative);
        accountBianchi.withdrawFromATM(2, withdrawNegative);
        Assertions.assertEquals(accountRossi.getBalance(), 500-SimpleBankAccount.ATM_TRANSACTION_FEE);
        Assertions.assertEquals(accountBianchi.getBalance(), 500-SimpleBankAccount.ATM_TRANSACTION_FEE);
        accountRossi.withdrawFromATM(2, withdrawPositive);
        accountBianchi.withdrawFromATM(1, withdrawPositive);
        Assertions.assertNotEquals(accountRossi.getBalance(), 0);
        Assertions.assertNotEquals(accountBianchi.getBalance(), 0);
        Assertions.assertEquals(accountRossi.getTransactionsCount(), 2);
        Assertions.assertEquals(accountBianchi.getTransactionsCount(), 2);
    }

    @Test
    public void testGetAccountHolder(){
        Assertions.assertEquals(accountRossi.getAccountHolder(), aRossi);
        Assertions.assertEquals(accountBianchi.getAccountHolder(), aBianchi);
    }

    @Test
    public void testChargeManagementFees(){
        double depositPositive = 1000;
        accountRossi.deposit(1, depositPositive);
        accountBianchi.deposit(2, depositPositive);
        accountRossi.chargeManagementFees(1);
        accountBianchi.chargeManagementFees(2);
        Assertions.assertEquals(accountRossi.getBalance(), 1000-SimpleBankAccount.MANAGEMENT_FEE);
        Assertions.assertEquals(accountBianchi.getBalance(), 1000-SimpleBankAccount.MANAGEMENT_FEE);
        Assertions.assertEquals(accountRossi.getTransactionsCount(), 0);
        Assertions.assertEquals(accountBianchi.getTransactionsCount(), 0);
    }
}
