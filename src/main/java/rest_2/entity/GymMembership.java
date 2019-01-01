package rest_2.entity;

import lombok.Getter;

@Getter
public class GymMembership {

    private final Long id;

    private final Person owner;

    public GymMembership(Long id, Person owner) {
        this.id = id;
        this.owner = owner;
    }
}
