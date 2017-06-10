package com.github.shmvanhouten.lesson7;

import com.github.shmvanhouten.lesson5.IntegerOutOfRangeException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CustomerServicePoint {
    private int userServiceChoice = 0;
    private int serviceStep = 0;

    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String city;
    private String country;

    public static void main(String[] args) {
        CustomerServicePoint service = new CustomerServicePoint();
        CustomerHandler handler = new CustomerHandler();

        service.startUserCommunication();
        service.startInputStream(handler);
    }

    private void startUserCommunication() {
        System.out.println("Welcome!");
        System.out.println("Would you like to:");
        System.out.println("Register as a new customer? (enter 1)");
        System.out.println("Report an address change? (enter 2)");
    }

    private void startInputStream(CustomerHandler handler) {
        InputStream in = System.in;
        InputStreamReader isr = new InputStreamReader(in);
        try (BufferedReader reader = new BufferedReader(isr)) {
            String inputLine = reader.readLine();
            while (inputLine != null && userServiceChoice == 0) {
                checkInitialUserInput(inputLine);
                inputLine = reader.readLine();
            }
            while (inputLine != null && userServiceChoice == 1) {
                registerNewCustomer(handler, inputLine);
                inputLine = reader.readLine();
            }
            while (inputLine != null && userServiceChoice == 2) {
                changeCustomerAddress(handler, inputLine);
                inputLine = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void changeCustomerAddress(CustomerHandler handler, String userInput) {

    }

    private void registerNewCustomer(CustomerHandler handler, String userInput) {
        switch (serviceStep) {
            case 0:
                firstName = userInput;
                serviceStep++;
                printQuestion("last Name");
                break;
            case 1:
                lastName = userInput;
                serviceStep++;
                printQuestion("email");
                break;
            case 2:
                email = userInput;
                serviceStep++;
                printQuestion("address");
                break;
            case 3:
                address = userInput;
                serviceStep++;
                printQuestion("city");
                break;
            case 4:
                city = userInput;
                serviceStep++;
                printQuestion("country");
                break;
            case 5:
                country = userInput;
                System.out.println("Thank you for registering!");
                serviceStep = 0;
                userServiceChoice = -1;
                handler.addCustomer(firstName, lastName, email, address, city, country);
                break;
        }
    }

    private void printQuestion(String input) {
        System.out.println("Please enter your " + input);
    }

    private void checkInitialUserInput(String readLine) {
        int userInput = 0;
        try {
            userInput = Integer.parseInt(readLine);
            if (userInput < 1 || userInput > 2) {
                throw new IntegerOutOfRangeException();
            }
            userServiceChoice = userInput;
        } catch (NumberFormatException | IntegerOutOfRangeException ex) {
            System.out.println("Please enter a 1 or a 2");
        }
        if (userInput == 1) {
            printQuestion("first name");
        }
    }
}
