package tech.marcusvieira;

public class BankAccount {

    private Integer id;
    private Double balance;

    public BankAccount(Integer id) {
        this.id = id;
        this.balance = 0D;
    }

    public BankAccount(Integer id, Double balance) {
        this.id = id;
        this.balance = balance;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
