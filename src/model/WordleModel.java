package model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class WordleModel {
    private String secretWord;
    private List<String> previousGuesses;
    private int attempts;
    private Set<Character> correctLetters;
    private Set<Character> incorrectLetters;
    private char[] correctLetterPositions;


    public WordleModel() {
        this.attempts = 0;
        this.previousGuesses = new ArrayList<>();
        this.secretWord = loadRandomWord();
        this.correctLetters = new HashSet<>();
        this.incorrectLetters = new HashSet<>();
        this.correctLetterPositions = new char[5];
        Arrays.fill(correctLetterPositions, '_');
    }

    private String loadRandomWord() {
        try {
            List<String> words = Files.readAllLines(Paths.get("wordle.txt"));
            return words.get(new Random().nextInt(words.size()));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean isValidGuess(String guess) {
        // Check if guess is 5 letters and not using disallowed letters
        if (guess.length() != 5 || containsDisallowedLetters(guess)) {
            return false;
        }
        this.previousGuesses.add(guess);
        this.attempts++;
        return true;
    }

    private boolean containsDisallowedLetters(String guess) {
        for (String previousGuess : previousGuesses) {
            for (char c : previousGuess.toCharArray()) {
                if (!secretWord.contains(String.valueOf(c)) && guess.contains(String.valueOf(c))) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isGameOver() {
        // Check if any guesses have been made
        if (previousGuesses.isEmpty()) {
            // No guesses have been made yet, so the game cannot be over due to a correct guess
            return attempts >= 6;
        } else {
            // Check if the last guess is the secret word or if the maximum number of attempts has been reached
            String lastGuess = previousGuesses.get(previousGuesses.size() - 1);
            return secretWord.equals(lastGuess) || attempts >= 6;
        }
    }

    public String getSecretWord() {
        return isGameOver() ? secretWord : "";
    }
    public void updateLetterSets(String guess) {
        for (int i = 0; i < guess.length(); i++) {
            char c = guess.charAt(i);
            if (secretWord.contains(String.valueOf(c))) {
                correctLetters.add(c);
            } else {
                incorrectLetters.add(c);
            }
        }
    }

    public Set<Character> getCorrectLetters() {
        return correctLetters;
    }

    public Set<Character> getIncorrectLetters() {
        return incorrectLetters;
    }

    public void updateLetterPositions(String guess) {
        assert guess.length() == secretWord.length(); // Add this line to ensure the guess is the correct length

        for (int i = 0; i < secretWord.length(); i++) {
            char guessedLetter = guess.charAt(i);
            char secretLetter = secretWord.charAt(i);

            // Check if the letter at the current position matches the secret word
            if (guessedLetter == secretLetter) {
                correctLetterPositions[i] = guessedLetter;
            } else if (!secretWord.contains(String.valueOf(guessedLetter))) {
                // If the guessed letter is not in the secret word at all, add it to incorrect letters
                incorrectLetters.add(guessedLetter);
            }
            // Do nothing if the guessed letter is in the secret word but not in the correct position
        }
    }

    public String getCorrectLetterPositions() {
        return new String(correctLetterPositions);
    }

}

