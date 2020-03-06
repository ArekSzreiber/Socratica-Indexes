package com.szreiber.TableGenerator;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Person {

    private String firstName;
    private String lastName;
    private LocalDate birthday;

    public Person() {
        this.firstName = Generator.getRandomFirstName();
        this.lastName = Generator.getRandomLastName();
        this.birthday = RandomDates.createRandomDate(1950, 2020);
    }

}
