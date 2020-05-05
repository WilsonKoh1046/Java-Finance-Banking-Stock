package stock;

public class Stock {

    private final String name;
    private final StockType type;
    private int price;

    public Stock(String name, StockType type, int price) {
        this.name = name;
        this.type = type;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public StockType getType() {
        return type;
    }

    public int getPrice() {
        return price;
    }
}
