package com.github.shmvanhouten.lesson1;

import com.github.shmvanhouten.lesson1.DutchGreeter;
import com.github.shmvanhouten.lesson1.EnglishGreeter;
import com.github.shmvanhouten.lesson1.Greeter;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class GreeterTest {

    public String text = "Appel";
    protected  String text2 = "Peer";
    String pakketje = "InhetPakket";
    private String secret = "geheim";

    @Test
    public void itShouldGreetInEnglish() throws Exception {

        Greeter greeter = new EnglishGreeter();

        assertThat(
                greeter.greet("Marco"),
                is("Hello Marco"));

    }

    @Test
    public void itShouldGreetInDutch() throws Exception {

        Greeter greeter = new DutchGreeter();

        assertThat(
                greeter.greet("Marco"),
                is("Hallo Marco"));
    }
}
