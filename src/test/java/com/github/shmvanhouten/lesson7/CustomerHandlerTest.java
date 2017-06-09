package com.github.shmvanhouten.lesson7;

import org.junit.Test;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class CustomerAdderTest {

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

    @Test
    public void itShouldAddANewCustomer() throws Exception {
        CustomerAdder adder = new CustomerAdder();
        adder.addCustomer("John", "Doe", "John_Doe9292@hotmail.com");
        List<Customer> customers = adder.getCustomerList();
        assertThat(customers.get(customers.size() -  1).getLastName(), is("Doe"));
        //delete him again after the test
        adder.deleteCustomer(customers.size() -1);
    }

    private void printCustomerList(List<Customer> customers) {
        for (Customer customer : customers) {
            System.out.println(customer.getCustomerId() + "-->" + customer.getFirstName() + " " + customer.getLastName() + ": " + customer.getEmail());
        }
    }
}