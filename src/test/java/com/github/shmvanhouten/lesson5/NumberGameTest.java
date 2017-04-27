package com.github.shmvanhouten.lesson5;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class NumberGameTest {

    @Test
    public void itShouldSayIfANumberIsHigherOrLowerThanOurSecretNumber() throws Exception {
        int secretNumber = 35;
        NumberGame game = new NumberGame(secretNumber);
        assertThat("15 is lower",game.checkIfNumberIsHigherOrLower("15"),is("Too Low!"));
        assertThat("35 is our number",game.checkIfNumberIsHigherOrLower("35"),is("Congratulations!"));
        assertThat("38 is higher",game.checkIfNumberIsHigherOrLower("38"),is("Too High!"));
    }

    @Test
    public void itShouldOnlyAllow10Guesses() throws Exception {
        int secretNumber = 35;
        NumberGame game = new NumberGame(secretNumber);
        for (int i = 0; i < 9; i++) {
            game.checkIfNumberIsHigherOrLower("23");
        }
        String reason = "10th wrong guess is a loss";
        assertThat(reason, game.checkIfNumberIsHigherOrLower("24"), is("Too Low!\nOut of Guesses! :("));
    }

    @Test
    public void the10thGuessShouldNotGiveBothGameOverMessages() throws Exception {
        int secretNumber = 35;
        NumberGame game = new NumberGame(secretNumber);
        for(int i = 0; i<9; i++){
            game.checkIfNumberIsHigherOrLower("23");
        }
        String reason = "10th wrong guess is a loss";
        assertThat(reason, game.checkIfNumberIsHigherOrLower("35"), is("Congratulations!"));
    }

    @Test
    public void itShouldNotAcceptFalseInput() throws Exception {
        NumberGame game = new NumberGame(true);

        String answer = "Please enter an integer number between 1 and 100.";

        String reason1 = "non integer chars not allowed";
        assertThat(reason1, game.checkIfNumberIsHigherOrLower("32s"), is(answer));
        String reason2 = "numbers below 1 not allowed";
        assertThat(reason2, game.checkIfNumberIsHigherOrLower("0"),is(answer));
        String reason3 = "numbers over 100 not allowed";
        assertThat(reason3, game.checkIfNumberIsHigherOrLower("101"), is(answer));
    }
}