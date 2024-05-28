import javax.persistence.*;
import java.util.List;

@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long orderId;
    private double cost;
    /*---------------------------*/
    @ManyToOne
    private Customer customer;
    /*---------------------------*/

    public Orders() {
    }
    public Orders(long orderId, double cost) {
        this.orderId = orderId;
        this.cost = cost;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
