import java.util.List;

public class Order {
    private String orderId;
    private List<Pair<Product, Integer>> products;
    private double totalAmount;

    public Order(String orderId, List<Pair<Product, Integer>> products, double price) {
        this.orderId = orderId;
        this.products = products;
        this.totalAmount = price;
    }

    public List<Pair<Product, Integer>> getProducts() {
        return products;
    }

    public String getOrderId() {
        return orderId;
    }

    public void addProduct(Pair<Product, Integer> product) {
        products.add(product);
        totalAmount += product.getFirst().getPrice();
    }
}
