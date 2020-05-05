import java.util.Arrays;
import java.util.List;
import account.*;
import stock.*;

public class Main {

    public static void main(String[] args) {
        Account acc1 = Account.createAccount("John");
        Account acc2 = Account.createAccount("Marry");
        System.out.println("First account: " + acc1.getName() + " with the id: " + acc1.getId());
        System.out.println("Second account: " + acc2.getName() + " with the id: " + acc2.getId());

        acc1.setAmount(500);
        acc2.setAmount(2000);

        List<Stock> stock_list = List.of(
                new Stock("apple", StockType.HIGH, 1000),
                new Stock("banana", StockType.MEDIUM, 500),
                new Stock("orange", StockType.LOW, 200)
        );

        StockMarket stockMarket = new StockMarket(stock_list);
        acc2.buyStock("banana", StockType.MEDIUM);
        acc2.buyStock("orange", StockType.LOW);
        System.out.println(acc2.getMyStocks());
        System.out.println(acc2.getAmount());
    }
}
