package com.guisfco;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class Main {

    static void main() throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        var clazz = Person.class;
        IO.println(clazz.getName());
        IO.println(clazz.getSimpleName());

        /*
         * The methods getFields, getMethods, getConstructors returns only items with public access level, including the inherited ones.
         * Now, the methods such as getDeclaredFields, getDeclaredMethods and getDeclaredConstructors returns all access levels, but excludes the inherited ones.
         * */

        IO.println("\nReading fields:");
        readingFields(clazz);

        IO.println("\nReading methods:");
        readingMethods(clazz);

        IO.println("\nReading constructors:");
        readingConstructors(clazz);

        // Instance that will be used in the tests below
        var person = new Person("Guilherme", 25);

        IO.println("\nReading fields:");
        readingFields(clazz, person);

        IO.println("\nInvoking methods:");
        invoke(clazz, person);

        IO.println("\nModifying:");
        modify(clazz, person);
    }

    private static void readingFields(Class<Person> clazz) {
        for (var field : clazz.getDeclaredFields()) {
            IO.println("\t" + field.getName());
        }
    }

    private static void readingMethods(Class<Person> clazz) {
        for (var method : clazz.getDeclaredMethods()) {
            IO.println("\t" + method.getName());
        }
    }

    private static void readingConstructors(Class<Person> clazz) {
        for (var constructor : clazz.getDeclaredConstructors()) {
            IO.println("\t" + constructor.getName() + Arrays.toString(constructor.getParameters()));
        }
    }

    private static void readingFields(Class<Person> clazz, Person person) throws NoSuchFieldException, IllegalAccessException {
        var nameField = clazz.getDeclaredField("name");
        IO.println(nameField.get(person));

        // Getting private field (with no getter method available) and changing its visibility
        var ageField = clazz.getDeclaredField("age");
        ageField.setAccessible(true);
        IO.println(ageField.get(person));
    }

    private static void invoke(Class<Person> clazz, Person person) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        var introduceMethod = clazz.getDeclaredMethod("introduce");
        introduceMethod.invoke(person);

        // Invoking private method by changing its visibility
        var tellSecretMethod = clazz.getDeclaredMethod("tellSecret");
        tellSecretMethod.setAccessible(true);
        tellSecretMethod.invoke(person);
    }

    private static void modify(Class<Person> clazz, Person person) throws NoSuchFieldException, IllegalAccessException {
        var nameField = clazz.getDeclaredField("name");
        var ageField = clazz.getDeclaredField("age");

        nameField.setAccessible(true);
        ageField.setAccessible(true);

        nameField.set(person, "Gui");
        ageField.set(person, 26);

        person.introduce();
    }
}
