package com.example;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit5.ArquillianExtension;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@ExtendWith(ArquillianExtension.class)
@RunAsClient
public class HelloResourceTest {

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "helloworld.war")
                .addClasses(HelloApplication.class, HelloResource.class)
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @ArquillianResource
    private URL baseURL;

    @Test
    public void testHelloEndpointReturns200AndGreeting() throws Exception {
        URI endpointUri = baseURL.toURI().resolve("api/hello");

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(endpointUri)
                .GET()
                .build();

        HttpResponse<String> response = client.send(
                request,
                HttpResponse.BodyHandlers.ofString()
        );

        Assertions.assertEquals(200, response.statusCode(),
                "Expected HTTP 200 but got: " + response.statusCode());

        Assertions.assertEquals("Hello, World!", response.body(),
                "Endpoint said something unexpected: " + response.body());

        System.out.println("\n🎉 WildFly 35 says: " + response.body());
    }
}
