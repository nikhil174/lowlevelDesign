import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        OrderManagement om = new OrderManagement();

        Product product1 = new Product("P1", "Laptop", 1000, 10);
        Product product2 = new Product("P2", "Phone", 500, 20);

        om.addProduct(product1);
        om.addProduct(product2);

        User customer = new User("C1", "Test User");

        List<Pair<Product, Integer>> products = new ArrayList<>();
        Pair<Product, Integer> product1Pair = new Pair<>(product1, 5);
        Pair<Product, Integer> product2Pair = new Pair<>(product2, 10);

        products.add(product1Pair);
        products.add(product2Pair);
        

        Order order = customer.createOrder("O1", products, product1Pair.getSecond() * product1Pair.getFirst().getPrice() + product2Pair.getSecond() * product2Pair.getFirst().getPrice());

        om.placeOrder(order);
    }
}
