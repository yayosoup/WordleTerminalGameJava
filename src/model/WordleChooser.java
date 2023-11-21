package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class WordleChooser {
    public static void RandomWordle() {
        // Specify the file path
        String filePath = "wordle.txt";

        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            ArrayList<String> words = new ArrayList<>();

            // Read file and store words in ArrayList
            while (scanner.hasNext()) {
                words.add(scanner.next());
            }
            scanner.close();

            // Choose a random word
            Random random = new Random();
            String randomWord = words.get(random.nextInt(words.size()));
            System.out.println("Random Word: " + randomWord);
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}