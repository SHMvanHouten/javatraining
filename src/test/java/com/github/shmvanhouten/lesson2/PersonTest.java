package com.github.shmvanhouten.lesson2;

import org.junit.Test;

import java.util.*;

import static java.time.LocalDate.of;
import static java.time.Month.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

public class PersonTest {

    @Test
    public void itShouldHaveFirstNameLastNameAndDateOfBirth() throws Exception {

        Person person = new Person("Marco", "Beelen", of(1977, JUNE, 27));

        assertThat(person.getFirstName(), is("Marco"));
        assertThat(person.getLastName(), is("Beelen"));
        assertThat(person.getDateOfBirth().getYear(), is(1977));

    }


    @Test
    public void demonstrateEquals() throws Exception {

        Person marco = new Person("Marco", "Beelen", of(1977, JUNE, 27));
        Person casper = new Person("Casper", "Beelen", of(2015, APRIL, 17));
        Person mcbeelen = new Person("Marco", "Beelen", of(1977, JUNE, 27));
        Person sjoerd = new Person("Sjoerd", "Houten, van", of(1983, MARCH, 12));
        Person simone = new Person("Simone", "Kamer", of(1981, JULY, 1));

        assertThat(marco, is(mcbeelen));
        assertThat(marco, not(is(sjoerd)));

        List<Person> persons = new ArrayList<>();
        persons.add(casper);
        persons.add(marco);
        persons.add(sjoerd);
        persons.add(simone);

        printPersons(persons);


        PersonByLastNameComparator personByLastNameComparator = new PersonByLastNameComparator();
        Collections.sort(persons, personByLastNameComparator);
        System.out.println("Op achternaam");
        printPersons(persons);


        System.out.println("Volgens Person zelf (dob)");
        Collections.sort(persons);
        printPersons(persons);


        System.out.println("Werken met SETS");
        Set<Person> uniquePersons = new HashSet<>(persons);
        uniquePersons.add(mcbeelen);
        System.out.println(uniquePersons.size());
        printPersons(uniquePersons);


        System.out.println("Sorted by Compartor");
        SortedSet<Person> sortedSet = new TreeSet<>(personByLastNameComparator);
        sortedSet.addAll(persons);


        printPersons(sortedSet);

        System.out.println("MAP");

        Map<String, Person> personMap = new HashMap<>();
        for (Person uniquePerson : uniquePersons) {
            personMap.put(uniquePerson.getLastName(), uniquePerson);
        }

        printPersons(personMap.values());


        mcbeelen = null;

        System.gc();

    }

    private void printPersons(Collection<Person> persons) {
        for (Person person : persons) {
            System.out.println(person);
        }
    }
}