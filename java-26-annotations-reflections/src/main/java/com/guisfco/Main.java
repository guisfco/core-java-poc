package com.guisfco;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

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

        // Registering routes
        var controller = new ControllerExample();
        var controllerMethods = controller.getClass().getDeclaredMethods();
        var routes = new HashMap<String, Method>();

        for (Method method : controllerMethods) {
            var annotation = method.getAnnotation(GetMapping.class);

            if (annotation != null) {
                IO.println();
                IO.println("Path: " + annotation.value());
                IO.println("Enabled: " + annotation.enabled());
                IO.println("Method name: " + method.getName());

                if (annotation.enabled()) {
                    routes.putIfAbsent(annotation.value(), method);
                }
            }
        }

        IO.println("\n==== Registered routes ====");
        IO.println(routes);

        var request = new Request("/hello", null);
        var route = routes.get(request.path());

        IO.println("\n==== Call ====");
        IO.println("Request: " + request);

        if (route != null) {
            var response = request.body() != null ? route.invoke(controller, request.body()) : route.invoke(controller);
            IO.println("Response: " + response);
        } else {
            IO.println("Route not found");
        }
    }
}
