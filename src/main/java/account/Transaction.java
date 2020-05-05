package account;

public interface Transaction {

    void withdrawn(int amount);

    void deposit(int amount);

    void transfer(Account account, int amount);

    // loan or pay debt with bank
    void debtWithBank(int amount, Mode mode);

}
