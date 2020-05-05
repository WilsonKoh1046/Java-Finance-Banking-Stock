package stock;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class StockMarket {
    private static List<Stock> stock_pool;

    public StockMarket(List<Stock> stock_pool) {
        this.stock_pool = stock_pool;
    }

    public static List<Stock> getStock_pool() {
        return stock_pool;
    }

    public String computeBestStock() {
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

        String bestStock;
        if (bestStockHigh.isPresent()) {
            bestStock = bestStockHigh.get().getName();
            return "The best stock currently: " + bestStock;
        }
        if (bestStockMedium.isPresent()) {
            bestStock = bestStockMedium.get().getName();
            return "The best stock currently: " + bestStock;
        }
        if (bestStockLow.isPresent()) {
            bestStock = bestStockLow.get().getName();
            return "The best stock currently: " + bestStock;
        }
        return "We can't determine the best stock currently";
    }
}
