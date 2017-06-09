package com.github.shmvanhouten.lesson7;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.sql.Driver;
import java.sql.SQLException;
import java.util.List;

public class CustomerAdder {

    private JdbcTemplate jdbcTemplate;
    private DataSource dataSource;

    public CustomerAdder(){
        try {
            Driver driver = new com.mysql.cj.jdbc.Driver();

            String url = "jdbc:mysql://localhost/Chinook?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=GMT&nullNamePatternMatchesAll=true";
            String user = "root";
            dataSource = new SimpleDriverDataSource(driver, url, user, Password.getPassword());

            jdbcTemplate = new JdbcTemplate(dataSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Customer> getCustomerList() {
        String sql = "SELECT * FROM Customer";
        return jdbcTemplate.query(sql, new CustomerRowMapper());
    }

    public Integer getNewCustomerId() {
        String sql = "SELECT MAX(CustomerId) FROM Customer";
        return jdbcTemplate.queryForObject(sql, Integer.class) + 1;
    }
}
