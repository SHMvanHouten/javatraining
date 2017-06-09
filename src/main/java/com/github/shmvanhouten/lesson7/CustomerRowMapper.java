package com.github.shmvanhouten.lesson7;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerRowMapper implements RowMapper<Customer> {
    @Override
    public Customer mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Integer id = resultSet.getInt("CustomerId");
        String firstName = resultSet.getString("FirstName");
        String lastName = resultSet.getString("LastName");
        String email = resultSet.getString("Email");
        String address = resultSet.getString("Address");
        String city = resultSet.getString("City");
        String country = resultSet.getString("Country");
        return new Customer(id, firstName, lastName, email, address, city, country);
    }
}
