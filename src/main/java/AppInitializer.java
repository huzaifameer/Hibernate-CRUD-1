import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class AppInitializer {
    public static void main(String[] args) {
        //save
        /*try{
            Customer customer = new Customer(1002,"Namal Silva","Colombo",15000,"1988-12-21");
            if (saveCustomer(customer)){
                System.out.println("Saved Successfully......!");
            }else{
                System.out.println("Please try again.......!");
            }
        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }*/
        //get by ID
        /*try{
            Customer customer = findById(1002);
            if (customer != null){
                System.out.println("Customer : "+customer.toString());
            }else{
                System.out.println("Customer Doesn't exists !");
            }
        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }*/
        //get all
        List<Customer> customers = findAll();
        if (!customers.isEmpty()){
            System.out.println("Success...");
            for (Customer c: customers){
                System.out.println(c.toString());
            }
        }else{
            System.out.println("Customer Doesn't exists !");
        }
        //update
        /*try{
            Customer customer = new Customer(1001,"Nimal Siripala","Kalutara",120000,"1988-12-21");
            if (updateCustomer(customer)){
                System.out.println("Updated Successfully......!");
                System.out.println(customer.toString());
            }else{
                System.out.println("Please try again.......!");
            }
        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }*/
        //delete by id
        /*try{
            if (deleteCustomer(1001)){
                System.out.println("Deletion Success.......!");
            }else{
                System.out.println("Customer Doesn't exists !");
            }
        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }*/
    }
    private static boolean saveCustomer(Customer customer) throws ClassNotFoundException,SQLException{
        /*String sql = "INSERT INTO customer (id, name, address, salary, dob) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
        preparedStatement.setLong(1,customer.getId());
        preparedStatement.setString(2,customer.getName());
        preparedStatement.setString(3,customer.getAddress());
        preparedStatement.setDouble(4,customer.getSalary());
        preparedStatement.setString(5,customer.getDob());

        return preparedStatement.executeUpdate()>0;*/

        try(Session session = new HibernateUtil().openSession()){
            Transaction transaction = session.beginTransaction();
            session.save(customer);
            transaction.commit();
        }
        return true;
    }
    private static Customer findById(long id) throws SQLException, ClassNotFoundException {
        /*String sql = "SELECT * FROM customer WHERE id=?";
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
        return null;*/
        try(Session session = new HibernateUtil().openSession()){
            return session.get(Customer.class, id);
        }
    }
    private static List<Customer> findAll(){
        /*List<Customer> customerList = new ArrayList<>();
        String sql = "SELECT * FROM customer";
        PreparedStatement preparedStatement = getConnection().prepareStatement(sql);

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            long customerId = resultSet.getLong("id");
            String name = resultSet.getString("name");
            String address = resultSet.getString("address");
            double salary = resultSet.getDouble("salary");
            String dob = resultSet.getString("dob");
            customerList.add(new Customer(customerId, name, address, salary, dob));
        }
        return customerList;*/
        try(Session session = new HibernateUtil().openSession()){
            String hql = "FROM Customer";
            Query<Customer> query = session.createQuery(hql, Customer.class);
            return query.list();
        }

    }
    private static boolean updateCustomer(Customer customer){
        /*String sql = "UPDATE customer SET name=?, address=?, salary=?, dob=? WHERE id=?";
        PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
        preparedStatement.setString(1,customer.getName());
        preparedStatement.setString(2,customer.getAddress());
        preparedStatement.setDouble(3,customer.getSalary());
        preparedStatement.setString(4,customer.getDob());
        preparedStatement.setLong(5,customer.getId());


        return preparedStatement.executeUpdate()>0;*/
        try(Session session = new HibernateUtil().openSession()){
            Transaction transaction = session.beginTransaction();
            Customer selectedCustomer = session.get(Customer.class,customer.getId());
            if (selectedCustomer==null){
                return false;
            }
            selectedCustomer.setName(customer.getName());
            selectedCustomer.setAddress(customer.getAddress());
            selectedCustomer.setSalary(customer.getSalary());
            selectedCustomer.setDob(customer.getDob());
            transaction.commit();
            return true;
        }
    }

    private static boolean deleteCustomer(long customerId) throws ClassNotFoundException,SQLException{
        /*String sql = "DELETE FROM customer WHERE id=?";
        PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
        preparedStatement.setLong(1,customerId);
        return preparedStatement.executeUpdate()>0;*/
        try(Session session = new HibernateUtil().openSession()){
            Transaction transaction = session.beginTransaction();
            Customer selectedCustomer = session.get(Customer.class,customerId);
            if (selectedCustomer==null){
                return false;
            }
            session.delete(selectedCustomer);
            transaction.commit();
            return true;
        }
    }
}
