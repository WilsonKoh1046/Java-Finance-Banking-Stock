import java.util.Arrays;
import java.util.List;
import account.*;
import stock.*;
import bank.*;

public class Main {

    public static void main(String[] args) {
        Bank bank = new Bank();
        Account acc1 = Account.createAccount("John");
        Account acc2 = Account.createAccount("Marry");
        System.out.println("First account: " + acc1.getName() + " with the id: " + acc1.getId());
        System.out.println("Second account: " + acc2.getName() + " with the id: " + acc2.getId());

        for (Account account: bank.getDB()) {
            System.out.println(account.getName());
        }

        acc1.setAmount(500);
        acc2.setAmount(2000);

        /*
        bank.deleteAccount(acc1);
        bank.deleteAccount(acc2);
        System.out.println(bank.getDB());

        acc1.debtWithBank(1000, Mode.DEBIT);
        System.out.println("Acc1 current debt with bank: " + acc1.getDebt());
        System.out.println("Acc1 current amount of fund: " + acc1.getAmount());
        System.out.println("Bank current fund: " + bank.getBankFund());
        acc1.debtWithBank(100, Mode.CREDIT);
        System.out.println("Acc1 current debt with bank: " + acc1.getDebt());
        System.out.println("Acc1 current amount of fund: " + acc1.getAmount());
        System.out.println("Bank current fund: " + bank.getBankFund());
         */

        List<Stock> stock_list = List.of(
                // new Stock("apple", StockType.HIGH, 1000),
                // new Stock("apple2", StockType.HIGH, 2000),
                // new Stock("apple3", StockType.HIGH, 3000),
                new Stock("banana", StockType.LOW, 5000),
                new Stock("banana2", StockType.MEDIUM, 4000),
                new Stock("orange", StockType.LOW, 6000)
        );

        StockMarket stockMarket = new StockMarket(stock_list);
        System.out.println(stockMarket.computeBestStock());
    }
}
