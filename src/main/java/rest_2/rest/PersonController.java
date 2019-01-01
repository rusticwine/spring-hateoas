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
import rest_2.repository.PersonRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping(value = "/people", produces = "application/hal+json")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @GetMapping
    public ResponseEntity<Resources<PersonResource>> all() {
//        final List<PersonResource> collection =
//                personRepository.findAll().stream().map(PersonResource::new).collect(Collectors.toList());
        final List<PersonResource> collection = StreamSupport.stream(personRepository.findAll().spliterator(), false)
                .map(PersonResource::new).collect(Collectors.toList());
        final Resources<PersonResource> resources = new Resources<>(collection);
        final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
        resources.add(new Link(uriString, "self"));
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonResource> get(@PathVariable final long id) {
        return personRepository
                .findById(id)
                .map(p -> ResponseEntity.ok(new PersonResource(p)))
                .orElseThrow(() -> new IllegalArgumentException("Person not found: " + id));
                //.orElseThrow(() -> new PersonNotFoundException(id));
    }

}
