package com.github.shmvanhouten.lesson1;

public class EnglishGreeter extends AbstractGreeter {

    protected String getLocalizedGreetWord() {
        return "Hello";
    }

    public static void main(String[] args) {
        Greeter greeter = new EnglishGreeter();

        for (String name : args) {
            System.out.println(greeter.greet(name));
        }

    }


}
