package com.masmovil.it.ticketing.controller;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;

@Controller("/hello") // <1>
public class HelloController {
    @Get("/") // <2>
    @Produces(MediaType.TEXT_PLAIN) // <3>
    public String index() {
        return "Hello World"; // <4>
    }
    

    @Get("/{name}") // <2>
    @Produces(MediaType.TEXT_PLAIN) // <3>
    public String helloName(String name) {
        return "Hello " + name; // <4>
    }
}
