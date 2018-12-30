package hello;

import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/g")
public class GreetingController {

    private static final String TEMPLATE = "Hello, %s!";

    private static final String DEFAULT_GREETING = "World";

    @RequestMapping(path = "/greeting", method= RequestMethod.GET)
    public HttpEntity<Greeting> greeting(
            @RequestParam(value = "name", required = false, defaultValue = DEFAULT_GREETING) String name) {
        Greeting greeting = new Greeting(String.format(TEMPLATE, name));
        greeting.add(ControllerLinkBuilder.linkTo(
                ControllerLinkBuilder.methodOn(GreetingController.class).greeting(name)).withSelfRel());
        greeting.add(ControllerLinkBuilder.linkTo(
                ControllerLinkBuilder.methodOn(GreetingController.class).greeting(DEFAULT_GREETING)).withSelfRel());

        greeting.add(ControllerLinkBuilder.linkTo(
                ControllerLinkBuilder.methodOn(GreetingController.class).greeting(DEFAULT_GREETING + "_a")).withSelfRel());
        return new ResponseEntity<>(greeting, HttpStatus.OK);
    }
}
