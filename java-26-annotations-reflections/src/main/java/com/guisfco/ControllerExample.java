package com.guisfco;

import java.util.Collections;
import java.util.List;

public class ControllerExample {

    @GetMapping(value = "/hello")
    public String helloWorld() {
        return "Hello, World!";
    }

    @GetMapping(value = "/users", enabled = false)
    public List<User> getUsers() {
        return Collections.emptyList();
    }

    @GetMapping("/users/{id}")
    public User getUser() {
        return new User("Guilherme");
    }

    public void internalMethod() {
        IO.println("Random method not exposed.");
    }
}
