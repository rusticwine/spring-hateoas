package rest_2.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import rest_2.repository.GymMembershipRepository;
import rest_2.repository.PersonRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/people/{personId}/membership")
public class GymMembershipController {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private GymMembershipRepository gymMembershipRepository;

    @GetMapping
    public ResponseEntity<Resources<GymMembershipResource>> all(@PathVariable final long personId) {
        final List<GymMembershipResource> collection = getMembershipsForPerson(personId);
        final Resources<GymMembershipResource> resources = new Resources<>(collection);
        final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
        resources.add(new Link(uriString, "self"));
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/{membershipId}")
    public ResponseEntity<GymMembershipResource> get(
            @PathVariable final long personId, @PathVariable final long membershipId) {
        return personRepository
                .findById(personId)
                .map(
                        p ->
                                p.getMemberships()
                                        .stream()
                                        .filter(m -> m.getId().equals(membershipId))
                                        .findAny()
                                        .map(m -> ResponseEntity.ok(new GymMembershipResource(m)))
                                        .<IllegalArgumentException>orElseThrow(() -> new IllegalArgumentException("GymMembershipNotFound: " + membershipId)))
                .orElseThrow(() -> new IllegalArgumentException("PersonNotFound: " + personId));
//                                        .orElseThrow(() -> new GymMembershipNotFoundException(membershipId)))
//                .orElseThrow(() -> new PersonNotFoundException(personId));
    }



    private List<GymMembershipResource> getMembershipsForPerson(final long personId) {
        return personRepository
                .findById(personId)
                .map(                        p ->
                                p.getMemberships()
                                        .stream()
                                        .map(GymMembershipResource::new)
                                        .collect(Collectors.toList()))
                .orElseThrow(() -> new IllegalArgumentException("Person not found: " + personId));
                //.orElseThrow(() -> new PersonNotFoundException(personId));
    }
}
