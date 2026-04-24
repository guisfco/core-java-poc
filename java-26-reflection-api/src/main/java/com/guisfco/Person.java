package com.guisfco;

public class Person {

    public String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Person(String name) {
        this.name = name;
    }

    private Person() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void introduce() {
        System.out.printf("Hello, my name is %s and I'm %d years old!%n", name, age);
    }

    private void tellSecret() {
        System.out.println("Sushi is better than barbecue");
    }
}
