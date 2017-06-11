package com.github.shmvanhouten.lesson7;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.sql.Driver;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerHandler {

    private SimpleJdbcInsert insert;
    private JdbcTemplate jdbcTemplate;
    private DataSource dataSource;

    public CustomerHandler(){
        try {
            Driver driver = new com.mysql.cj.jdbc.Driver();

            String url = "jdbc:mysql://localhost/Chinook?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=GMT&nullNamePatternMatchesAll=true";
            String user = "root";
            dataSource = new SimpleDriverDataSource(driver, url, user, Password.getPassword());

            jdbcTemplate = new JdbcTemplate(dataSource);

            insert = new SimpleJdbcInsert(dataSource)
                    .withTableName("Customer")
                    .usingColumns("CustomerId", "FirstName", "LastName", "Email", "Address", "City", "Country");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    List<Customer> getCustomerList() {
        String sql = "SELECT * FROM Customer";
        return jdbcTemplate.query(sql, new CustomerRowMapper());
    }

    Integer getNewCustomerId() {
        String sql = "SELECT MAX(CustomerId) FROM Customer";
        return jdbcTemplate.queryForObject(sql, Integer.class) + 1;
    }

    public void addCustomer(String firstName, String lastName, String email) {
        Map<String, Object> values = getBasicCustomerValueMap(firstName, lastName, email);

        values.put("Address", null);
        values.put("City", null);
        values.put("Country", null);


        insert.execute(values);
    }

    public void addCustomer(String firstName, String lastName, String email, String address, String city, String country) {
        Map<String, Object> values = getBasicCustomerValueMap(firstName, lastName, email);

        values.put("Address", address);
        values.put("City", city);
        values.put("Country", country);

        insert.execute(values);
    }

    private Map<String, Object> getBasicCustomerValueMap(String firstName, String lastName, String email) {
        Map<String, Object> values = new HashMap<>();
        values.put("CustomerId", getNewCustomerId());
        values.put("FirstName", firstName);
        values.put("LastName", lastName);
        values.put("Email", email);
        return values;
    }

    public void deleteCustomer(Integer customerId) {
        String sql = "DELETE FROM Customer WHERE CustomerId = ?";
        jdbcTemplate.update(sql, customerId);
    }


    public void changeCustomerAddress(Integer customerId, String address, String city, String country) {
        String sql = "UPDATE Customer SET Address = ? , City = ? , Country = ? WHERE customerId = ?";
        jdbcTemplate.update(sql, address, city, country, customerId);
    }

    public Integer findCustomerId(String email) {
        String sql = "SELECT * FROM Customer WHERE Email = ?";
        String[] args = {email};
//        return jdbcTemplate.queryForObject(sql, args, Integer.class); doesn't work..
        List<Customer> customers = jdbcTemplate.query(sql, args, new CustomerRowMapper());
        if(customers.size() >1){
            return 0;
        }else{
            return customers.get(0).getCustomerId();
        }
    }

    public Customer findCustomer(String email) {
        String sql = "SELECT * From Customer WHERE Email = ?";
        String[] args = {email};
        List<Customer> customers = jdbcTemplate.query(sql, args, new CustomerRowMapper());
        if(customers.size() != 1){
            return null;
        }else{
            return customers.get(0);
        }
    }

    public void unsafeDeleteCustomer(String email){
        String sql = "DELETE FROM Customer WHERE Email = '" + email + "'";
        jdbcTemplate.execute(sql);
    }
}
