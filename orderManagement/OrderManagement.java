import java.util.HashMap;
import java.util.Map;

public class OrderManagement {
    private Map<String, Product> products;
    private Map<String, Order> orders;

    public OrderManagement() {
        this.products = new HashMap<String, Product>();
        this.orders = new HashMap<String, Order>();
    }
    
    public void addProduct(Product product) {
        products.put(product.getProductId(), product);
    }

    public void placeOrder(Order order) {
        for (Product product : order.getProducts()) {
            Product storedProduct = products.get(product.getProductId());
            if (storedProduct == null) {
                System.out.println("Product is not present");
                return;
            }
        }

        orders.put(order.getOrderId(), order);
        System.out.println("Order placed successfully");
    }

    public Order getOrder(String orderId) {
        return orders.get(orderId);
    }
}
