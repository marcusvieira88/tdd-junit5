package tech.marcusvieira;

public class BankAccountService {

    public BankAccount create(Integer id) {

        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Bank account invalid id.");
        }
        return new BankAccount(id);
    }

    public void deposit(BankAccount bankAccount, Double amount) {

        validateBankAccount(bankAccount);

        if (amount == null || amount <= 0) {
            throw new IllegalArgumentException("Invalid amount.");
        }

        double newBalance = bankAccount.getBalance() + amount;
        bankAccount.setBalance(newBalance);
    }

    public void withdrawal(BankAccount bankAccount, Double amount) {

        validateBankAccount(bankAccount);

        if (amount == null || amount <= 0 || amount.compareTo(bankAccount.getBalance()) > 0) {
            throw new IllegalArgumentException("Invalid amount.");
        }
        double newBalance = bankAccount.getBalance() - amount;
        bankAccount.setBalance(newBalance);
    }

    private void validateBankAccount(BankAccount bankAccount) {
        if (bankAccount == null) {
            throw new IllegalArgumentException("BankAccount is invalid.");
        }
    }
}
