package stock;

public interface StockOperations {

    void buyStock(String name, StockType type);

    void sellStock(String name, StockType type);
}
