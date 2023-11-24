package controller;

import model.WordleModel;
import view.WordleView;


public class WordleController {
    private WordleModel model;
    private WordleView view;

    public WordleController(WordleModel model, WordleView view) {
        this.model = model;
        this.view = view;
    }

    public void startGame() {
        view.displayWelcomeMessage();
        while (!model.isGameOver()) {
            String guess = view.getUserInput();
            if (!model.isValidGuess(guess)) {
                view.displayInvalidInputMessage();
            } else {
                // Update the sets of correct and incorrect letters
                model.updateLetterPositions(guess);

                if (model.getSecretWord().equals(guess)) {
                    view.displayGameOver(true, model.getSecretWord());
                    break;
                } else {
                    // Display feedback
                    displayFeedback();
                }
            }
            if (model.isGameOver()) {
                view.displayGameOver(false, model.getSecretWord());
            }
        }
    }

    private void displayFeedback() {
        System.out.println("Current Word: " + model.getCorrectLetterPositions());
        System.out.println("Letters not in Word: " + model.getIncorrectLetters());
    }
}