package com.github.shmvanhouten.lesson5;


import java.io.*;
import static java.util.concurrent.ThreadLocalRandom.current;


public class NumberGame {
    private int targetNumber;
    private int guessCounter = 0;
    private static boolean gameOver = false;
    private int[] guessScope = {1,100};

    public NumberGame(int secretNumber){
        targetNumber = secretNumber;
    }
    public NumberGame(){
        setTargetNumber();
        System.out.println("Guess a number between " + guessScope[0] + " and " + guessScope[1] + ".");
    }

    public static void main(String[] args) {
        NumberGame game = new NumberGame(35);
//        NumberGame game = new NumberGame();
        game.startInputStream();
    }

    private void startInputStream() {
        InputStream in = System.in;
        InputStreamReader inputStreamReader = new InputStreamReader(in);

        try(BufferedReader reader = new BufferedReader(inputStreamReader)) {
            String inputLine = reader.readLine();
            while (inputLine != null){
                String answer = this.checkIfNumberIsHigherOrLower(inputLine);
                System.out.println(answer);
                if(gameOver){
                    if(wantsNewGame(reader)){
                        resetAllValues();
                        System.out.println("Guess Again!");
                        inputLine = reader.readLine();
                    }else {
                        System.out.println("Bye!");
                        inputLine = null;
                    }
                }else {
                    inputLine = reader.readLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    private void resetAllValues() {
        setTargetNumber();
        guessCounter = 0;
        gameOver = false;
    }


    private static boolean wantsNewGame(BufferedReader reader) throws IOException{
        System.out.println("New Game? Y/N");
        int inputChar = reader.read();
            if(inputChar == 'y' || inputChar == 'Y'){
                return true;
            }
        return false;
    }


    public String checkIfNumberIsHigherOrLower(String inputString) {
//        if(!isCorrectInput(inputString)){
//            return "Please enter an integer number between " + guessScope[0] + " and " + guessScope[1] + ".";
//        }

        StringBuilder answer = new StringBuilder("");
        int guess;
        try{
            guess = Integer.parseInt(inputString);
            if(guess <= guessScope[0] || guess >= guessScope[1]){
                throw new IntegerOutOfRangeException();
            }
        }catch(NumberFormatException | IntegerOutOfRangeException exception){
            return "Please enter an integer number between " + guessScope[0] + " and " + guessScope[1] + ".";
        }
        guessCounter++;

        if (guess > targetNumber){
            answer.append("Too High!");
        }
        if (guess < targetNumber){
            answer.append("Too Low!");
        }
        if (guess == targetNumber){
            answer.append("Congratulations! You did it in " + guessCounter + " guesses!");
            gameOver = true;
        }else if (guessCounter >=10){
            answer.append("\nOut of Guesses! :(");
            gameOver = true;
        }
        return answer.toString();
    }



    private void setTargetNumber() {
        targetNumber = current().nextInt(guessScope[0], guessScope[1]);
    }

}
