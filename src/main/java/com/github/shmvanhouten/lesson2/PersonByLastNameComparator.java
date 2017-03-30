package com.github.shmvanhouten.lesson2;

import java.util.Comparator;

public class PersonByLastNameComparator implements Comparator<Person> {

    @Override
    public int compare(Person first, Person second) {
        int byLastName = first.getLastName().compareTo(second.getLastName());
        if (byLastName != 0) {
            return byLastName;
        }
        return first.getFirstName().compareTo(second.getFirstName());
    }
}
