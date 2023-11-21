package view;

import java.util.Scanner;

public class GuessWord {

    public static String getUserAttempt() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your guess:");
        return sc.nextLine();
    }


}
