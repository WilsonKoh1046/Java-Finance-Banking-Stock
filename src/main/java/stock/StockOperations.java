package stock;

import java.util.List;

public interface StockOperations {

    void buyStock(String name, StockType type);

    void sellStock(String name, StockType type);

    void buySoldStock(String name, StockType type, int price);

    List<String> viewAvailableSoldStock();
}
