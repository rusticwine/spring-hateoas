package rest_2.rest;

import lombok.*;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import rest_2.entity.Person;
import rest_2.rest.GymMembershipController;
import rest_2.rest.PersonController;

@Getter
public class PersonResource extends ResourceSupport {

    private final Person person;

    public PersonResource(final Person person) {
        this.person = person;
        final long id = person.getId();
        add(ControllerLinkBuilder.linkTo(PersonController.class).withRel("people"));
        add(ControllerLinkBuilder.linkTo(
                ControllerLinkBuilder.methodOn(GymMembershipController.class).all(id)).withRel("memberships"));
        add(ControllerLinkBuilder.linkTo(
                ControllerLinkBuilder.methodOn(PersonController.class).get(id)).withSelfRel());
    }
}
