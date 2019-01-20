package rest_2.entity;

import lombok.Getter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Calendar;
import java.util.List;

@Getter
public class Person {

    @Getter
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String firstName;
    private String lastName;
    private Calendar birthDate;

    private List<GymMembership> memberships;

//    public Person(long id) {
//        this.id = id;
//    }

    public Person(final Person person) {
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        this.birthDate = person.getBirthDate();
        this.memberships = person.getMemberships();
    }

    public Person(final Person person, long personId) {
        this.id = personId;
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        this.birthDate = person.getBirthDate();
        this.memberships = person.getMemberships();
    }

    public Person(long id, String firstName, String lastName, Calendar birthDate, List<GymMembership> memberships) {
//        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.memberships = memberships;
    }
}
