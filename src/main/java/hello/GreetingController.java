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
    public HttpEntity<GreetingResource> greeting(
            @RequestParam(value = "name", required = false, defaultValue = DEFAULT_GREETING) String name) {
        GreetingResource greetingResource = new GreetingResource(String.format(TEMPLATE, name));
        greetingResource.add(ControllerLinkBuilder.linkTo(
                ControllerLinkBuilder.methodOn(GreetingController.class).greeting(name)).withSelfRel());
        greetingResource.add(ControllerLinkBuilder.linkTo(
                ControllerLinkBuilder.methodOn(GreetingController.class).greeting(DEFAULT_GREETING)).withSelfRel());

        greetingResource.add(ControllerLinkBuilder.linkTo(
                ControllerLinkBuilder.methodOn(GreetingController.class).greeting(DEFAULT_GREETING + "_a")).withSelfRel());
        return new ResponseEntity<>(greetingResource, HttpStatus.OK);
    }
}
