package account;

public interface Transaction {

    void withdrawn(int amount);

    void deposit(int amount);

    void transfer(Account account, int amount);

}
