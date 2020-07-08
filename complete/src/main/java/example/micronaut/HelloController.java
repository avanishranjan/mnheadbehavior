package example.micronaut;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

@Controller
public class HelloController {

    @Get(value = "/hello", consumes = MediaType.APPLICATION_OCTET_STREAM, produces = MediaType.ALL)
    public byte[] hello(){
        String str1 = "aosdjfopsdjpojsdovjpsojdvpjspdvjpsjdv";
        return str1.getBytes();
    }
}
