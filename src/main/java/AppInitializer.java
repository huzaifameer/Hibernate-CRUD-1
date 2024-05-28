import java.sql.*;
import java.util.Date;

import static sun.reflect.misc.ReflectUtil.forName;

public class AppInitializer {
    public static void main(String[] args) {
        //save
        /*try{
            Customer customer = new Customer(1001,"Nimal Silva","Panadura",12000,"1988-12-21");
            if (saveCustomer(customer)){
                System.out.println("Saved Successfully......!");
            }else{
                System.out.println("Please try again.......!");
            }
        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }*/
        //save
        try{
            Customer customer = findById(1001);
            if (customer != null){
                System.out.println("Customer : "+customer.toString());
            }else{
                System.out.println("Customer Doesn't exists !");
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
        preparedStatement.setString(5,customer.getDob());

        return preparedStatement.executeUpdate()>0;
    }
    private static Customer findById(long id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM customer WHERE id=?";
        PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
        preparedStatement.setLong(1,id);

        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            long customerId = resultSet.getLong("id");
            String name = resultSet.getString("name");
            String address = resultSet.getString("address");
            double salary = resultSet.getDouble("salary");
            String dob = resultSet.getString("dob");
            return new Customer(customerId, name, address, salary, dob);
        }
        return null;
    }
    private static Connection getConnection() throws ClassNotFoundException,SQLException{
        //loading the driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        //creating the connection
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/hibernate_db","root","1234");
    }
}
