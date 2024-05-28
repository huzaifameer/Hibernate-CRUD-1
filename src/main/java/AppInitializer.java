import java.sql.*;
import java.util.Date;

import static sun.reflect.misc.ReflectUtil.forName;

public class AppInitializer {
    public static void main(String[] args) {
        //save
        try{
            Customer customer = new Customer(1001,"Nimal Silva","Panadura",12000,new Date());
            if (saveCustomer(customer)){
                System.out.println("Saved Successfully......!");
            }else{
                System.out.println("Please try again.......!");
            }
        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }

    }
    private static boolean saveCustomer(Customer customer) throws ClassNotFoundException,SQLException{
        String sql = "INSERT INTO customer (id, name, address, salary, dob) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
        preparedStatement.setLong(1,customer.getId());
        preparedStatement.setString(2,customer.getName());
        preparedStatement.setString(3,customer.getAddress());
        preparedStatement.setDouble(4,customer.getSalary());
        preparedStatement.setObject(5,customer.getDob());

        return preparedStatement.executeUpdate()>0;
    }
    private static Connection getConnection() throws ClassNotFoundException,SQLException{
        //loading the driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        //creating the connection
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/hibernate_db","root","1234");
    }
}
