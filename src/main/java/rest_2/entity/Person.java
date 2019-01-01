package rest_2.entity;

import lombok.Getter;

import java.util.Calendar;
import java.util.List;

@Getter
public class Person {

    @Getter
    private final long id;
    private String firstName;
    private String lastName;
    private Calendar birthDate;

    private List<GymMembership> memberships;

    public Person(long id) {
        this.id = id;
    }

    public Person(long id, String firstName, String lastName, Calendar birthDate, List<GymMembership> memberships) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.memberships = memberships;
    }
}
