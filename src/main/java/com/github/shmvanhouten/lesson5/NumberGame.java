package com.github.shmvanhouten.lesson5;


import java.io.*;
import static java.util.concurrent.ThreadLocalRandom.current;


public class NumberGame {
    private int numberToGuess;
    private int guessCounter = 0;
    private static boolean gameOver = false;
    private int[] guessScope = {1,100};

    public NumberGame(int secretNumber){
        numberToGuess = secretNumber;
    }
    public NumberGame(boolean b){
        if(b){
            numberToGuess = current().nextInt(guessScope[0], guessScope[1]);
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
                String answer = game.checkIfNumberIsHigherOrLower(inputLine);
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



    public String checkIfNumberIsHigherOrLower(String inputString) {
        boolean correctInput = isCorrectInput(inputString);
        if(!correctInput){
            return "Please enter an integer number between " + guessScope[0] + " and " + guessScope[1] + ".";
        }

        StringBuilder answer = new StringBuilder("");
        int guess = Integer.parseInt(inputString);
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

    private boolean isCorrectInput(String inputString) {
        if(!isInteger(inputString) || Integer.parseInt(inputString)<guessScope[0]|| Integer.parseInt(inputString)>guessScope[1]){
            return false;
        }
        return true;
    }

    private static boolean isInteger(String inputLine) {
        for (char character : inputLine.toCharArray()) {
            if(!Character.isDigit(character)){
                return false;
            }
        }
        return true;
    }

}
