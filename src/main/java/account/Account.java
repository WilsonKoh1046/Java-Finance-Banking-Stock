package account;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import stock.*;

public class Account implements Transaction, StockOperations {

    private final String name;
    private final UUID id;
    private int amount;
    private List<Stock> myStocks;

    public static Account createAccount(String name) {
        return new Account(name, UUID.randomUUID());
    }

    public Account(String name, UUID id) {
        this.name = name;
        this.id = id;
        this.amount = 0;
        this.myStocks = new ArrayList<>();
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
    }
}
