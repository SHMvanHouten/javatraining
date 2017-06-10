package com.github.shmvanhouten.lesson7;

import org.junit.Ignore;
import org.junit.Test;
import java.util.List;
import java.util.Objects;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class CustomerHandlerTest {

    @Test
    public void itShouldGiveAListOfCustomers() throws Exception {
        CustomerHandler handler = new CustomerHandler();
        List<Customer> customers = handler.getCustomerList();
        printCustomerList(customers);
    }

    @Test
    public void itShouldSayWhichCustomerIdIsTheHighest() throws Exception {
        CustomerHandler handler = new CustomerHandler();
        assertThat(handler.getNewCustomerId(), is(60));
    }

    @Test@Ignore
    public void itShouldAddANewCustomer() throws Exception {
        CustomerHandler handler = new CustomerHandler();
        handler.addCustomer("John", "Doe", "John_Doe9292@hotmail.com");
        List<Customer> customers = handler.getCustomerList();
        assertThat(customers.get(customers.size() -  1).getLastName(), is("Doe"));
        //delete him again after the test
        handler.deleteCustomer(customers.size());
    }

    @Test@Ignore
    public void itShouldChangeTheCustomersAddress() throws Exception {
        CustomerHandler handler = new CustomerHandler();
        handler.addCustomer("John", "Doe", "John_Doe9292@hotmail.com", "KalverStraat 3032B", "Amsterdam", "Netherlands");
        List<Customer> customers = handler.getCustomerList();
        if(!Objects.equals(customers.get(59).getLastName(), "Doe") || customers.get(59).getCustomerId() != 60){
            throw new Exception();
        }
        assertThat(customers.get(customers.size() -  1).getAddress(), is("KalverStraat 3032B"));
        //now change the Address:
        handler.changeCustomerAddress(60, "Dorpsstraat 2223-3", "Ons Dorp", "Netherlands");
        customers = handler.getCustomerList();
        assertThat(customers.get(customers.size() -  1).getAddress(), is("Dorpsstraat 2223-3"));
        assertThat(customers.get(customers.size() -  1).getCity(), is("Ons Dorp"));
        handler.deleteCustomer(60);
    }

    @Test
    public void itShouldFindACustomerId() throws Exception {
        CustomerHandler handler = new CustomerHandler();
        assertThat(handler.findCustomerId("luisg@embraer.com.br"), is(1));
    }

    @Test
    public void itShouldFindACustomer() throws Exception {
        CustomerHandler handler = new CustomerHandler();
        Customer luis = handler.findCustomer("luisg@embraer.com.br");
        Customer leonie = handler.findCustomer("leonekohler@surfeu.de");
        assertThat(luis.getCustomerId(), is(1));
        assertThat(leonie.getCustomerId(), is(2));
    }

    private void printCustomerList(List<Customer> customers) {
        for (Customer customer : customers) {
            System.out.println(customer.getCustomerId() + "-->" + customer.getFirstName() + " " + customer.getLastName()
                    + ": " + customer.getEmail() + ", " + customer.getAddress());
        }
    }
}