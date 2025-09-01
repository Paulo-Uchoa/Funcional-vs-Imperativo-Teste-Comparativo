package algorithms.orderingsystem;

public class Order {
    String category;
    double value;

    @Override
    public String toString() {
        return "Order{" +
                "category='" + category + '\'' +
                ", value=" + value +
                '}';
    }

    public Order(String category, double value){
        this.category = category;
        this.value = value;
    }
}
