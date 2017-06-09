package com.github.shmvanhouten.lesson7;

import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;

import static org.junit.Assert.*;

public class CustomerAdderTest {
    @Test@Ignore
    public void itShouldAddANewCustomer() throws Exception {

    }

    @Test
    public void itShouldGiveAListOfCustomers() throws Exception {
        CustomerAdder adder = new CustomerAdder();
        List<Customer> customers = adder.getCustomerList();
        printCustomerList(customers);
    }

    @Test
    public void itShouldSayWhichCustomerIdIsTheHighest() throws Exception {
        CustomerAdder adder = new CustomerAdder();
        assertThat(adder.getNewCustomerId(), is(60));
    }

    private void printCustomerList(List<Customer> customers) {
        for (Customer customer : customers) {
            System.out.println(customer.getCustomerId() + "-->" + customer.getFirstName() + " " + customer.getLastName() + ": " + customer.getEmail());
        }
    }
}