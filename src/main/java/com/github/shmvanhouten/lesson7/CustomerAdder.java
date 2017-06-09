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

public class CustomerAdder {

    private SimpleJdbcInsert insert;
    private JdbcTemplate jdbcTemplate;
    private DataSource dataSource;

    public CustomerAdder(){
        try {
            Driver driver = new com.mysql.cj.jdbc.Driver();

            String url = "jdbc:mysql://localhost/Chinook?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=GMT&nullNamePatternMatchesAll=true";
            String user = "root";
            dataSource = new SimpleDriverDataSource(driver, url, user, Password.getPassword());

            jdbcTemplate = new JdbcTemplate(dataSource);

            insert = new SimpleJdbcInsert(dataSource)
                    .withTableName("Customer")
                    .usingColumns("CustomerId", "FirstName", "LastName", "Email");
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
        Map<String, Object> values = new HashMap<>();
        values.put("CustomerId", getNewCustomerId());
        values.put("FirstName", firstName);
        values.put("LastName", lastName);
        values.put("Email", email);

        insert.execute(values);
    }

    public void deleteCustomer(Integer customerId) {
        String sql = "DELETE FROM Customer Where CustomerId = ?";
        jdbcTemplate.update(sql, customerId);
    }
}
