import java.util.Date;

public class Customer {
    private long id;
    private String name;
    private String address;
    private double salary;
    private Date dob;

    public Customer() {
    }
    public Customer(long id, String name, String address, double salary, Date dob) {
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

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }
}
