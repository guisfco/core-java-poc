package com.guisfco;

public record User(String name) {

    @SpecialAnnotation
    void specialMethod() {
        IO.println("The special method was called!");
    }

    void nonSpecialMethod() {
        IO.println("This is not the special method.");
    }
}
