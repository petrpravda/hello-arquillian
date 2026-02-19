package com.example;

import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

/**
 * GET http://localhost:8080/helloworld/api/hello
 * Returns: "Hello, World!"
 * Complexity: 0.   Business value: priceless.
 */
@Path("/hello")
@RequestScoped
public class HelloResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello, World!";
    }
}
