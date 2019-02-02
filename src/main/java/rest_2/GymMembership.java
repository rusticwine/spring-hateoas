package rest_2;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

@Getter
public class GymMembership {

    private final long id;

    @JsonIgnore private Person owner;

    private String name;

    private long cost;

    public GymMembership(long id) {
        this.id = id;
    }

    public GymMembership(final long id, final Person owner, final String name, final long cost) {
        this(id);
        this.owner = owner;
        this.name = name;
        this.cost = cost;
    }
}
