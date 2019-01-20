package hello;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.springframework.hateoas.ResourceSupport;

@Getter
public class GreetingResource extends ResourceSupport {

    private final String content;

    private final String test = ", I am no bitch.";

//    @JsonCreator
//    public GreetingResource(@JsonProperty("content") String content) {
    public GreetingResource(String content) {
        this.content = content;
    }
}
