package rest_2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping(value = "/memberships", produces = "application/hal+json")
public class GymMembershipController {


    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private GymMembershipRepository gymMembershipRepository;


    public ResponseEntity<Resources<GymMembershipResource>> all(@PathVariable final long personId) {
        final List<GymMembershipResource> collection = getMembershipsForPerson(personId);
        final Resources<GymMembershipResource> resources = new Resources<>(collection);
        final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
        resources.add(new Link(uriString, "self"));
        return ResponseEntity.ok(resources);
    }

    private List<GymMembershipResource> getMembershipsForPerson(final long personId) {
        return personRepository
                .findById(personId)
                .map(
                        p ->
                                p.getMemberships()
                                        .stream()
                                        .map(GymMembershipResource::new)
                                        .collect(Collectors.toList()))
                .orElseThrow(() -> new PersonNotFoundException(personId));
    }

}
