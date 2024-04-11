import java.util.List;

public class User {
    private String userId;
    private String name;

    User(String userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getUserId() {
        return userId;
    }

    // create Order
    public Order createOrder(String orderId, List<Product> products, double totalAmount) {
        return new Order(orderId, products, totalAmount);
    }
}