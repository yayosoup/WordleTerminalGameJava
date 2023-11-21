import java.util.Random;

import static model.WordleChooser.RandomWordle;
import static view.IntroducePlayer.Welcome;

public class Main {
    public static void main(String[] args) {
        // Welcomes the player and explains the game.
        Welcome();

        RandomWordle();

    }
}
