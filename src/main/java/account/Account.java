package account;

import java.util.*;

import bank.Bank;
import stock.*;

public class Account implements Transaction, StockOperations {

    private final String name;
    private final UUID id;
    private int amount;
    private List<Stock> myStocks;
    private int debt;

    public static Account createAccount(String name) {
        Account newAccount = new Account(name, UUID.randomUUID());
        Bank.setDB(newAccount); // Add the newly created account to the bank database
        return newAccount;
    }

    public Account(String name, UUID id) {
        this.name = name;
        this.id = id;
        this.amount = 0;
        this.myStocks = new ArrayList<>();
        this.debt = 0;
    }

    public String getName() {
        return name;
    }

    public UUID getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }

    public List<String> getMyStocks() {
        List<String> stockNames = new ArrayList<>();
        for (int i = 0; i < myStocks.size(); i++) {
            stockNames.add(myStocks.get(i).getName());
        }
        return stockNames;
    }

    public void setAmount(int amount) {
        this.amount += amount;
    }

    public int getDebt() {
        return debt;
    }

    public Optional<String> checkSufficientFund(int amount) {
        Optional<String> decision;
        if (this.amount >= amount) {
            decision = Optional.of("Validated");
        } else {
            decision = Optional.ofNullable(null);
        }
        return decision;
    }

    @Override
    public void withdrawn(int amount) {
        this.amount -= amount;
    }

    @Override
    public void deposit(int amount) {
        this.amount += amount;
    }

    @Override
    public void transfer(Account account, int amount) {
        Optional<String> sufficientFund = checkSufficientFund(amount);
        if (sufficientFund.isPresent()) {
            this.amount -= amount;
            account.setAmount(amount);
        } else {
            System.out.println("Not enough fund!");
        }
    }

    @Override
    public void debtWithBank(int amount, Mode mode) {
        // DEBIT for loan with bank
        if (mode == Mode.DEBIT) {
            if (Bank.debtwithCustomer(amount, mode)) {
                this.amount += amount;
                debt += amount;
            }
        } else {
            if (debt > 0) {
                Optional<String> sufficientFund = checkSufficientFund(amount);
                if (sufficientFund.isPresent()) {
                    Bank.debtwithCustomer(amount, mode);
                    debt -= amount;
                    this.amount -= amount;
                } else {
                    System.out.println("Insufficient fund to pay the debt!");
                }
            } else {
                System.out.println("You have no debt with bank currently");
            }
        }
    }



    @Override
    public void buyStock(String name, StockType type) {
        List<Stock> stocks = StockMarket.getStock_pool();
        Optional<Stock> targeted_stock = stocks.stream()
                .filter(s -> s.getName().equals(name) && s.getType().equals(type))
                .findFirst();

        if (targeted_stock.isPresent()) {
            Optional<String> sufficientFund = checkSufficientFund(targeted_stock.get().getPrice());
            if (sufficientFund.isPresent()) {
                myStocks.add(targeted_stock.get());
                this.amount -= targeted_stock.get().getPrice();
                StockMarket.setStock_pool(targeted_stock.get(), this, StockOperationType.BUY);
            } else {
                System.out.println("No enough fund!");
            }
        } else {
            System.out.println("Can't find the stock you looking for, sorry!");
        }
    }

    @Override
    public void sellStock(String name, StockType type) {
        Optional<Stock> targeted_stock = myStocks.stream()
                .filter(s -> s.getName().equals(name) && s.getType().equals(type))
                .findFirst();

        if (targeted_stock.isPresent()) {
            StockMarket.setStock_pool(targeted_stock.get(), this, StockOperationType.SELL);
            myStocks.remove(targeted_stock.get());
        } else {
            System.out.println("No such stock in inventory!");
        }
    }

    @Override
    public void buySoldStock(String stockName, StockType stockType, int stockPrice) {
        // We only concern about the stock itself, so it doesn't matter who the seller (account) is
        // We define the price we want to buy the stock (temporarily)
        boolean found = false;
        List<Map<Stock, Account>> list_of_sold_stocks = StockMarket.getSold_stocks();
        // CANNOT use forEach or for(type var: list), will cause ConcurrentModificationException
        for (int i = 0; i < list_of_sold_stocks.size(); i++) {
            for (Map.Entry<Stock, Account> entry: list_of_sold_stocks.get(i).entrySet()) {
                Stock targeted_stock = entry.getKey();
                Account targeted_account = entry.getValue();
                if (targeted_stock.getName().equals(stockName) && targeted_stock.getType().equals(stockType)) {
                    StockMarket.setSold_stocks(targeted_account, targeted_stock, StockOperationType.BUY);
                    myStocks.add(targeted_stock);
                    transfer(targeted_account, stockPrice);
                    found = true;
                    break;
                }
            }
        }
        if (!found) {
            System.out.println("No such package found!");
        } else {
            System.out.println("Trading successful");
        }
    }

    @Override
    public List<String> viewAvailableSoldStock() {
        List<Map<Stock, Account>> list_of_sold_stocks = StockMarket.getSold_stocks();
        List<String> result = new ArrayList<>();
        for (int i = 0; i < list_of_sold_stocks.size(); i++) {
            for (Map.Entry<Stock, Account> entry: list_of_sold_stocks.get(i).entrySet()) {
                Stock stock = entry.getKey();
                String stock_for_sale = "(Name: " + stock.getName() + ", Type: " + stock.getType().toString() + ", Price: " + String.valueOf(stock.getPrice()) + ")";
                result.add(stock_for_sale);
            }
        }
        return result;
    }
}
