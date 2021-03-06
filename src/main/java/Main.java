import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import account.*;
import stock.*;
import bank.*;

public class Main {

    public static void main(String[] args) {
        Account acc1 = Account.createAccount("John", 3000, null);
        Account acc2 = Account.createAccount("Marry", 4000, null);
        for (Account account: Bank.getDB()) {
            System.out.println(account.getName());
        }
        /*
        Account acc1 = Account.createAccount("John", 3000, null);
        Account acc2 = Account.createAccount("Marry", 4000, null);
        System.out.println("First account: " + acc1.getName() + " with the id: " + acc1.getId());
        System.out.println("Second account: " + acc2.getName() + " with the id: " + acc2.getId());
        acc1.setAmount(500);
        acc2.setAmount(3000);

        System.out.println("Bank loan before giving loan to Acc1: " + Bank.getLoan());
        acc1.debtWithBank(1000, Mode.DEBIT);
        System.out.println("Acc1 current fund after getting loan from bank: " + acc1.getAmount());
        System.out.println("Acc1 current debt after getting loan from bank: " + acc1.getDebt());
        System.out.println("Bank fund after giving loan to Acc1: " + Bank.getBankFund());
        System.out.println("Bank loan after giving loan to Acc1: " + Bank.getLoan());
        System.out.println("Bank checking Acc1's debt: " + Bank.checkIndividualDebt(acc1));
        System.out.println();
        acc2.debtWithBank(2000, Mode.DEBIT);
        System.out.println("Acc2 current fund after getting loan from bank: " + acc2.getAmount());
        System.out.println("Acc2 current debt after getting loan from bank: " + acc2.getDebt());
        System.out.println("Bank fund after giving loan to Acc2: " + Bank.getBankFund());
        System.out.println("Bank loan after giving loan to Acc2: " + Bank.getLoan());
        System.out.println("Bank checking Acc2's debt: " + Bank.checkIndividualDebt(acc2));
        System.out.println();
        acc1.debtWithBank(500, Mode.CREDIT);
        System.out.println("Acc1 current fund after paying bank: " + acc1.getAmount());
        System.out.println("Acc2 current debt after paying bank: " + acc1.getDebt());
        System.out.println("Bank fund after receiving payment from Acc1: " + Bank.getBankFund());
        System.out.println("Bank loan after receiving payment from Acc1: " + Bank.getLoan());
        System.out.println("Bank checking Acc1's debt: " + Bank.checkIndividualDebt(acc1));
         */

        /*
        for (Account account: Bank.getDB()) {
            System.out.println(account.getName());
            System.out.println(account.getId());
            System.out.println(account.getAmount());
        }

        acc1.transfer(acc2, 500);
        for (Account account: Bank.getDB()) {
            System.out.println(account.getName());
            System.out.println(account.getId());
            System.out.println(account.getAmount());
        }
        */
        /*
        bank.deleteAccount(acc1);
        bank.deleteAccount(acc2);
        System.out.println(Bank.getDB());

        acc1.debtWithBank(1000, Mode.DEBIT);
        System.out.println("Acc1 current debt with bank: " + acc1.getDebt());
        System.out.println("Acc1 current amount of fund: " + acc1.getAmount());
        System.out.println("Bank current fund: " + Bank.getBankFund());
        acc1.debtWithBank(100, Mode.CREDIT);
        System.out.println("Acc1 current debt with bank: " + acc1.getDebt());
        System.out.println("Acc1 current amount of fund: " + acc1.getAmount());
        System.out.println("Bank current fund: " + Bank.getBankFund());
         */

        /*
        List<Stock> stock_list = List.of(
                new Stock("apple", StockType.HIGH, 1000),
                new Stock("apple2", StockType.HIGH, 2000),
                new Stock("apple3", StockType.HIGH, 3000),
                new Stock("banana", StockType.LOW, 5000),
                new Stock("banana2", StockType.MEDIUM, 4000),
                new Stock("orange", StockType.LOW, 6000)
        );
        StockMarket.populateStockMarket(stock_list);
        Stock bestStock = StockMarket.computeBestStock();
        Optional<Stock> bestStockOpt = Optional.ofNullable(bestStock);
        if (bestStockOpt.isPresent()) {
            acc2.buyStock(bestStock.getName(), bestStock.getType());
        } else {
            System.out.println("Stock buying failed");
        }
        System.out.println("Acc2 current fund: " + acc2.getAmount());
        System.out.println("Acc2 curent stock inventory: " + acc2.getMyStocks());

        System.out.println("Stock Market: ");
        StockMarket.getStock_pool().forEach(stock -> {
            System.out.println(stock.getName());
        });

        acc2.sellStock("apple3", StockType.HIGH);
        System.out.println("Acc2's stock inventory after selling the stock: " + acc2.getMyStocks());
        System.out.println("Acc2's fund after selling the stock: " + acc2.getAmount());
        System.out.println("Sold stock list: ");
        for (int i = 0; i < StockMarket.getSold_stocks().size(); i++) {
            Map<Stock, Account> stockIndex = StockMarket.getSold_stocks().get(i);
            for (Map.Entry<Stock, Account> entry: stockIndex.entrySet()) {
                Stock targeted_stock = entry.getKey();
                Account targeted_account = entry.getValue();
                StockMarket.setSold_stocks(targeted_account, targeted_stock, StockOperationType.BUY);
                System.out.println("Stock : " + targeted_stock.getName() + ", Account: " + targeted_account.getName());
            }
        }

        System.out.println(acc1.viewAvailableSoldStock()); */
        /*
        acc1.buySoldStock("apple3", StockType.HIGH, 300);
        System.out.println("Acc1 current fund: " + acc1.getAmount());
        System.out.println("Acc1 current stock inventory: ");
        for (String stock: acc1.getMyStocks()) {
            System.out.println(stock);
        }
        System.out.println("Acc2 current fund (after Acc1 bought the sold stock): " + acc2.getAmount());
        System.out.println("Sold stock list: ");
        for (int i = 0; i < StockMarket.getSold_stocks().size(); i++) {
            Map<Stock, Account> stockIndex = StockMarket.getSold_stocks().get(i);
            for (Map.Entry<Stock, Account> entry: stockIndex.entrySet()) {
                Stock targeted_stock = entry.getKey();
                Account targeted_account = entry.getValue();
                StockMarket.setSold_stocks(targeted_account, targeted_stock, StockOperationType.BUY);
                System.out.println("Stock : " + targeted_stock.getName() + ", Account: " + targeted_account.getName());
            }
        }
        System.out.println("Stock market: ");
        StockMarket.getStock_pool().forEach(stock -> {
            System.out.println(stock.getName());
        });

         */
    }
}
