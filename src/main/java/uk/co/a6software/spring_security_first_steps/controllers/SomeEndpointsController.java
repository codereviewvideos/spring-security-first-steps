package uk.co.a6software.spring_security_first_steps.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SomeEndpointsController {

    @GetMapping("/open-to-all")
    public String openToAll() {
        return "Welcome one and all!";
    }

    @GetMapping("/restricted-to-authenticated-users")
    public String hello() {
        return "Hello, authenticated user!";
    }
}
