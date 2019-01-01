package rest_2.rest;


import lombok.Getter;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import rest_2.entity.GymMembership;

@Getter
public class GymMembershipResource extends ResourceSupport {

    private final GymMembership gymMembership;

    public GymMembershipResource(final GymMembership gymMembership) {
        this.gymMembership = gymMembership;
        final long membershipId = gymMembership.getId();
        final long personId = gymMembership.getOwner().getId();
        add(new Link(String.valueOf(membershipId), "membership-id"));
        add(ControllerLinkBuilder.linkTo(
                ControllerLinkBuilder.methodOn(GymMembershipController.class).all(personId)).withRel("memberships"));
        add(ControllerLinkBuilder.linkTo(
                ControllerLinkBuilder.methodOn(PersonController.class).get(personId)).withRel("owner"));
        add(ControllerLinkBuilder.linkTo(
                ControllerLinkBuilder.methodOn(GymMembershipController.class).get(personId, membershipId)).withSelfRel());
    }
}
