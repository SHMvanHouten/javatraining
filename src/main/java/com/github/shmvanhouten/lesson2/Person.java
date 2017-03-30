package com.github.shmvanhouten.lesson2;

import java.time.LocalDate;

public class Person implements Comparable<Person> {

    private final String firstName;
    private final String lastName;
    private final LocalDate dateOfBirth;


    public Person(String firstName, String lastName, LocalDate dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }


    @Override
    public boolean equals(Object obj) {

        System.out.println("Equals called to compare " + this + " with " +  obj);

        if (obj instanceof Person) {
            Person other = (Person) obj;
            if (this.firstName.equals(other.getFirstName())
                    && this.lastName.equals(other.getLastName())
                    && this.dateOfBirth.equals(other.getDateOfBirth())) {
                return true;
            }

        }

        return false;

    }

    @Override
    public int hashCode() {
        System.out.println("hashCode calculated for " + this);
        return this.firstName.charAt(0);
    }


    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }

    @Override
    public int compareTo(Person other) {
        return this.dateOfBirth.compareTo(other.getDateOfBirth());
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
}
