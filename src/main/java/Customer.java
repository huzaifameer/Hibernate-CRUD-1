import javax.persistence.*;

@Entity
public class Customer {
    @Id
    private long id;
    private String name;
    private String address;
    private double salary;
    private String dob;
    /*-------------------*/
    @OneToOne(mappedBy = "customer")
    private Vehicle vehicle;
    /*-------------------*/

    public Customer() {
    }
    public Customer(long id, String name, String address, double salary, String dob) {
        this.setId(id);
        this.setName(name);
        this.setAddress(address);
        this.setSalary(salary);
        this.setDob(dob);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", salary=" + salary +
                ", dob='" + dob + '\'' +
                '}';
    }
}
