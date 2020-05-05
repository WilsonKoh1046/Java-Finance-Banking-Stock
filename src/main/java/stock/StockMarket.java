package stock;

import account.Account;

import java.util.*;
import bank.*;

public class StockMarket {
    private static List<Stock> stock_pool = new ArrayList<>();
    private static List<Map<Stock, Account>> sold_stocks = new ArrayList<>();

    public StockMarket() {}

    public static void populateStockMarket(List<Stock> stocks) {
        for (Stock stock: stocks) {
            stock_pool.add(stock);
        }
    }

    public static List<Stock> getStock_pool() {
        return stock_pool;
    }

    public static void addNewStock(Stock stock) {
        stock_pool.add(stock);
    }

    public static void setStock_pool(Stock stock, Account account, StockOperationType stockOperationType) {
        if (stockOperationType == StockOperationType.BUY) { // Customer buy stock
            Optional<Stock> targeted_stock = stock_pool.stream()
                    .filter(s -> s.getName().equals(stock.getName()) && s.getType().equals(stock.getType()) && s.getPrice() == stock.getPrice())
                    .findFirst();

            if (targeted_stock.isPresent()) {
                stock_pool.remove(targeted_stock.get());
                Map<Stock, Account> newStockPackage = new HashMap<>();
                newStockPackage.put(stock, account);
                sold_stocks.add(newStockPackage);
            } else {
                System.out.println("No such stock found!");
            }
        } else {
            setSold_stocks(account, stock, StockOperationType.SELL);
        }
    }

    public static List<Map<Stock, Account>> getSold_stocks() {
        return sold_stocks;
    }

    public static void setSold_stocks(Account account, Stock stock, StockOperationType stockOperationType) {
        if (stockOperationType == StockOperationType.SELL) {
            Map<Stock, Account> newBid = new HashMap<Stock, Account>();
            newBid.put(stock, account);
            sold_stocks.add(newBid);
        } else { // If someone wishes to buy the stock in this bid list
            // CANNOT use forEach or for(type var: list), will cause ConcurrentModificationException
            for (int i = 0; i < sold_stocks.size(); i++) {
                for (Map.Entry<Stock, Account> entry: sold_stocks.get(i).entrySet()) {
                    Stock targeted_stock = entry.getKey();
                    Account targeted_account = entry.getValue();
                    if (targeted_stock.getName().equals(stock.getName())
                            && targeted_stock.getType().equals(stock.getType())
                            && targeted_stock.getPrice() == stock.getPrice()) {
                        sold_stocks.remove(sold_stocks.get(i));
                    }
                    break;
                }
            }
        }
    }

    public static Stock computeBestStock() {
        // I defined the "best stock" to be with type HIGH and highest price
        Optional<Stock> bestStockHigh = stock_pool.stream()
                .filter(bs -> bs.getType().equals(StockType.HIGH))
                .max(Comparator.comparing(Stock::getPrice));

        Optional<Stock> bestStockMedium = stock_pool.stream()
                .filter(bs -> bs.getType().equals(StockType.MEDIUM))
                .max(Comparator.comparing(Stock::getPrice));

        Optional<Stock> bestStockLow = stock_pool.stream()
                .filter(bs -> bs.getType().equals(StockType.LOW))
                .max(Comparator.comparing(Stock::getPrice));

        Stock bestStock;
        if (bestStockHigh.isPresent()) {
            bestStock = bestStockHigh.get();
            System.out.println("The best stock currently: " + bestStock.getName());
            return bestStock;
        }
        if (bestStockMedium.isPresent()) {
            bestStock = bestStockMedium.get();
            System.out.println("The best stock currently: " + bestStock.getName());
            return bestStock;
        }
        if (bestStockLow.isPresent()) {
            bestStock = bestStockLow.get();
            System.out.println("The best stock currently: " + bestStock.getName());
            return bestStock;
        }
        System.out.println("We can't determine the best stock currently");
        return null;
    }
}
