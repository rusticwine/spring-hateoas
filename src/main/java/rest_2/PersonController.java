package main.java.rest_2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rest_2.PersonRepository;

@RestController
@RequestMapping(value = "/people", produces = "application/hal+json")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;


}
