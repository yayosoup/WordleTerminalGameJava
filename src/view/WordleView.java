package view;

import java.util.Scanner;

public class WordleView {
    private Scanner scanner;

    public WordleView() {
        this.scanner = new Scanner(System.in);
    }

    public void displayWelcomeMessage() {
        System.out.println("=======================================================================================");
        System.out.println("Welcome to Wordle.");
        System.out.println("Enter a Five Letter to guess the Wordle. Use Correct Letters as clues for the Wordle.");
        System.out.println("Incorrect letters are not used in the Wordle, use these as clues!");
        System.out.println("Good Luck!");
        System.out.println("=======================================================================================");
    }

    public String getUserInput() {
        return scanner.nextLine().trim();
    }

    public void displayInvalidInputMessage() {
        System.out.println("Invalid input. Please enter a 5-letter word without disallowed letters.");
    }

    public void displayGameOver(boolean win, String secretWord) {
        if (win) {
            System.out.println("Congratulations! You guessed the word: " + secretWord);
        } else {
            System.out.println("Game over. The word was: " + secretWord);
        }
    }
}
