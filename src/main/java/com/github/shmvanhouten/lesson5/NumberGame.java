package com.github.shmvanhouten.lesson5;


import java.io.*;
import static java.util.concurrent.ThreadLocalRandom.current;


public class NumberGame {
    private int numberToGuess;
    private int guessCounter = 0;
    private static boolean gameOver = false;

    public NumberGame(int secretNumber){
        numberToGuess = secretNumber;
    }
    public NumberGame(boolean b){
        if(b){
            numberToGuess = current().nextInt(1,100);
        }
    }

    public static void main(String[] args) {
//        NumberGame game = new NumberGame(35);
        NumberGame game = new NumberGame(true);

        startInputStream(game);
    }

    private static void startInputStream(NumberGame game) {
        InputStream in = System.in;
        InputStreamReader inputStreamReader = new InputStreamReader(in);

        try(BufferedReader reader = new BufferedReader(inputStreamReader)) {
            String inputLine = reader.readLine();
            while (inputLine != null){
                if(!isInteger(inputLine)){
                    inputLine = "101";
                }
                String answer = game.checkIfNumberIsHigherOrLower(Integer.parseInt(inputLine));
                System.out.println(answer);
                if(gameOver){
                    inputLine = null;
                }else {
                    inputLine = reader.readLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean isInteger(String inputLine) {
        for (char character : inputLine.toCharArray()) {
            if(!Character.isDigit(character)){
                return false;
            }
        }
        return true;
    }


    public String checkIfNumberIsHigherOrLower(int guess) {

        StringBuilder answer = new StringBuilder("");

        if(guess >100 || guess < 1){
            return "Please enter an integer number between 1 and 100.";
        }
        guessCounter++;

        if (guess > numberToGuess){
            answer.append("Too High!");
        }
        if (guess < numberToGuess){
            answer.append("Too Low!");
        }
        if (guess == numberToGuess){
            answer.append("Congratulations!");
            gameOver = true;
        }else if (guessCounter >=10){
            answer.append("\nOut of Guesses! :(");
            gameOver = true;
        }
        return answer.toString();
    }

}
