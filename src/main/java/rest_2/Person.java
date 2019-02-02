package rest_2;

import lombok.Getter;

import java.util.Calendar;

@Getter
public class Person {

    private final long id;
    private String firstName;
    private String lastName;
    private Calendar dateOfBirth;

    public Person(long id) {
        this.id = id;
    }

    public Person(long id, String firstName, String lastName, Calendar dateOfBirth) {
        this(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }
}
