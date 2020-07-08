package example.micronaut;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.annotation.MicronautTest;
import java.util.List;
import java.util.Map.Entry;
import javax.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@MicronautTest
public class HelloControllerTest {

    @Client("/")
    @Inject
    RxHttpClient client;

    @Test
    public void helloHeadTest(){
        HttpRequest<?> request = HttpRequest.HEAD("/hello")
            .accept(MediaType.APPLICATION_OCTET_STREAM_TYPE);

        HttpResponse<byte[]> response = client.toBlocking().exchange(request, byte[].class);
        assertEquals(HttpStatus.OK, response.getStatus());
        Assertions.assertNotNull(response.getHeaders().get("content-length"));
        Assertions.assertTrue(response.getHeaders().contentLength().getAsLong() == 37);

    }

    @Test
    public void helloGetTest(){
        HttpRequest<?> request = HttpRequest.GET("/hello")
            .accept(MediaType.APPLICATION_OCTET_STREAM_TYPE);

        HttpResponse<byte[]> response = client.toBlocking().exchange(request, byte[].class);

        assertEquals(HttpStatus.OK, response.getStatus());
        byte[] byteResponse = response.getBody().get();
        Assertions.assertTrue(byteResponse.length > 0);
        Assertions.assertNotNull(response.getHeaders().get("content-length"));
        Assertions.assertTrue(response.getHeaders().contentLength().getAsLong() == 37);
    }
}
