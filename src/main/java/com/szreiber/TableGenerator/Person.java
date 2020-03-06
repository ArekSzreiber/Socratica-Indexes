package com.szreiber.TableGenerator;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
public class Person {

    @Id
    private Long id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private LocalDate birthday;

    public Person() {
        this.firstName = Generator.getRandomFirstName();
        this.lastName = Generator.getRandomLastName();
        this.birthday = RandomDates.createRandomDate(1950, 2020);
    }

}
