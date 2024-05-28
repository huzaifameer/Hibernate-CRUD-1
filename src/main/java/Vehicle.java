import javax.persistence.*;

@Entity(name = "vehicle")
//@Table(name = "myName")
public class Vehicle {
    @Id
    private long vahicleId;
    @Column(name = "brand",length = 45)
    private String brand;

    /*-----------------------*/
    @OneToOne
    @JoinColumn(name = "customer",unique = true)
    private Customer customer;
    /*-----------------------*/

    public Vehicle() {
    }
    public Vehicle(long vahicleId, String brand) {
        this.vahicleId = vahicleId;
        this.brand = brand;
    }

    public long getVahicleId() {
        return vahicleId;
    }

    public void setVahicleId(long vahicleId) {
        this.vahicleId = vahicleId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
