package stock;

import java.util.List;

public class StockMarket {
    private static List<Stock> stock_pool;

    public StockMarket(List<Stock> stock_pool) {
        this.stock_pool = stock_pool;
    }

    public static List<Stock> getStock_pool() {
        return stock_pool;
    }
}
