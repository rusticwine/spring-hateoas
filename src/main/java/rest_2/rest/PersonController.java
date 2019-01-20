package rest_2.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import rest_2.entity.Person;
import rest_2.repository.PersonRepository;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping(value = "/people", produces = "application/hal+json")
public class PersonController {

    @Autowired
    @Qualifier("memoryImpl")
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

    @PostMapping
    public ResponseEntity<PersonResource> post(@RequestBody final Person personFromRequest) {
        final Person person = personRepository.save(new Person(personFromRequest));
        final URI uri =
                MvcUriComponentsBuilder.fromController(getClass())
                        .path("/{id}")
                        .buildAndExpand(person.getId())
                        .toUri();
        return ResponseEntity.created(uri).body(new PersonResource(person));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonResource> put(
            @PathVariable("id") final long id, @RequestBody Person personFromRequest) {
        final Person person = new Person(personFromRequest, id);
        personRepository.save(person);
        final PersonResource resource = new PersonResource(person);
        final URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(uri).body(resource);
    }

}
