package utils;

public class Product {

    private int price;
    private int basePrice;
    private String warranty;

    public Product() {

    }

    public int getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(int basePrice) {
        this.basePrice = basePrice;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getWarranty() {
        return warranty;
    }

    public void setWarranty(String warranty) {
        this.warranty = warranty;
    }

    @Override
    public String toString() {
        return "Product{" +
                "price=" + price +
                ", basePrice=" + basePrice +
                ", warranty='" + warranty + '\'' +
                '}';
    }
}
