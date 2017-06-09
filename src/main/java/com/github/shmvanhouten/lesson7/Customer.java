package com.github.shmvanhouten.lesson7;


public class Customer {
    private Integer customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String city;
    private String country;

    public Customer(Integer customerId, String firstName, String lastName, String email) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Customer(Integer customerId, String firstName, String lastName, String email, String address, String city, String country){
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.city = city;
        this.country = country;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }
}
