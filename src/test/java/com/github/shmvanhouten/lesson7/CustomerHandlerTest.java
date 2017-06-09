package com.github.shmvanhouten.lesson7;

import org.junit.Test;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class CustomerHandlerTest {

    @Test
    public void itShouldGiveAListOfCustomers() throws Exception {
        CustomerHandler adder = new CustomerHandler();
        List<Customer> customers = adder.getCustomerList();
        printCustomerList(customers);
    }

    @Test
    public void itShouldSayWhichCustomerIdIsTheHighest() throws Exception {
        CustomerHandler adder = new CustomerHandler();
        assertThat(adder.getNewCustomerId(), is(60));
    }

    @Test
    public void itShouldAddANewCustomer() throws Exception {
        CustomerHandler adder = new CustomerHandler();
        adder.addCustomer("John", "Doe", "John_Doe9292@hotmail.com");
        List<Customer> customers = adder.getCustomerList();
        assertThat(customers.get(customers.size() -  1).getLastName(), is("Doe"));
        //delete him again after the test
        adder.deleteCustomer(customers.size());
    }

    @Test
    public void itShouldChangeTheCustomersAddress() throws Exception {
        CustomerHandler adder = new CustomerHandler();
        adder.addCustomer("John", "Doe", "John_Doe9292@hotmail.com", "KalverStraat 3032B", "Amsterdam", "Netherlands");
        List<Customer> customers = adder.getCustomerList();
        assertThat(customers.get(customers.size() -  1).getLastName(), is("Doe"));
        //now change the Address:
    }

    private void printCustomerList(List<Customer> customers) {
        for (Customer customer : customers) {
            System.out.println(customer.getCustomerId() + "-->" + customer.getFirstName() + " " + customer.getLastName() + ": " + customer.getEmail());
        }
    }
}