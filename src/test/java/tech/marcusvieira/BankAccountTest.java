package tech.marcusvieira;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class BankAccountTest {

    private static final Double INITIAL_BANK_ACCOUNT_BALANCE = 0D;

    private static BankAccountService bankAccountService;

    @BeforeAll
    public static void init() {
        bankAccountService = new BankAccountService();
    }

    //Bank account create tests
    @ParameterizedTest
    @ValueSource(ints = {1, 200, 3000})
    public void shouldCreateBankAccountWhenProvidedId(Integer bankAccountId) {
        BankAccount bankAccount = bankAccountService.create(bankAccountId);
        assertEquals(bankAccountId, bankAccount.getId());
        assertEquals(INITIAL_BANK_ACCOUNT_BALANCE, bankAccount.getBalance());
    }

    @Test
    public void shouldThrowsExceptionWhenProvidedNullId() {
        assertThrows(IllegalArgumentException.class, () -> bankAccountService.create(null));
    }

    @Test
    public void shouldThrowsExceptionWhenProvidedNegativeId() {
        assertThrows(IllegalArgumentException.class, () -> bankAccountService.create(-1));
    }

    //Bank account deposit tests
    @ParameterizedTest
    @ValueSource(doubles = {100, 500, 1000})
    public void shouldIncreaseBalanceWhenDepositMade(Double amount) {
        BankAccount bankAccount = new BankAccount(1, 1000D);
        bankAccountService.deposit(bankAccount, amount);
        assertEquals(bankAccount.getBalance(), Double.valueOf(1000D + amount));
    }

    @Test
    public void shouldThrowsExceptionWhenDepositAmountIsNull() {
        BankAccount bankAccount = new BankAccount(1);
        assertThrows(IllegalArgumentException.class, () -> bankAccountService.deposit(bankAccount, null));
    }

    @Test
    public void shouldThrowsExceptionWhenDepositAmountIsNegative() {
        BankAccount bankAccount = new BankAccount(1);
        assertThrows(IllegalArgumentException.class, () -> bankAccountService.deposit(bankAccount, -1D));
    }

    @Test
    public void shouldThrowsExceptionWhenDepositAccountIsNull() {
        assertThrows(IllegalArgumentException.class, () -> bankAccountService.deposit(null, 100D));
    }

    //Bank account withdrawal tests 
    @ParameterizedTest
    @ValueSource(doubles = {150, 200, 350})
    public void shouldDecreaseBalanceWhenWithdrawalMade(Double amount) {
        BankAccount bankAccount = new BankAccount(1, 1000D);
        bankAccountService.withdrawal(bankAccount, amount);
        assertEquals(bankAccount.getBalance(), Double.valueOf(1000D - amount));
    }

    @Test
    public void shouldThrowsExceptionWhenWithdrawalAmountIsNull() {
        BankAccount bankAccount = new BankAccount(1);
        assertThrows(IllegalArgumentException.class, () -> bankAccountService.withdrawal(bankAccount, null));
    }

    @Test
    public void shouldThrowsExceptionWhenWithdrawalAmountIsNegative() {
        BankAccount bankAccount = new BankAccount(1);
        assertThrows(IllegalArgumentException.class, () -> bankAccountService.withdrawal(bankAccount, -1D));
    }

    @Test
    public void shouldThrowsExceptionWhenWithdrawalAccountIsNull() {
        assertThrows(IllegalArgumentException.class, () -> bankAccountService.withdrawal(null, 100D));
    }

    @Test
    public void shouldThrowsExceptionWhenWithdrawalAmountIsHigherThanBalance() {
        BankAccount bankAccount = new BankAccount(1, 100D);
        assertThrows(IllegalArgumentException.class, () -> bankAccountService.withdrawal(bankAccount, 200D));
    }
}
