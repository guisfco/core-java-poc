package com.guisfco;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {

    static void main() throws InvocationTargetException, IllegalAccessException {
        var person = new User("Guilherme");
        var methods = person.getClass().getDeclaredMethods();

        // Loop to get the annotated methods
        for (Method method : methods) {
            var annotation = method.getAnnotation(SpecialAnnotation.class);

            if (annotation != null) {
                IO.println(method.getName());
                method.invoke(person); // That's not the main purpose
            }
        }

        var controllerMethods = ControllerExample.class.getDeclaredMethods();

        for (Method method : controllerMethods) {
            var annotation = method.getAnnotation(GetMapping.class);

            if (annotation != null) {
                IO.println();
                IO.println("Path: " + annotation.value());
                IO.println("Enabled: " + annotation.enabled());
                IO.println("Method name: " + method.getName());
            }
        }
    }
}
