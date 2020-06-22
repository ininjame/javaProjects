

import java.util.*;

public class test {
    int score;
    int molesLeft;
    int attemptsLeft;
    char[][] moleGrid;

    public test(int numAttempts, int gridDimension) {
        moleGrid = new char[gridDimension][gridDimension];
        attemptsLeft = numAttempts;
        score = 0;
        for (int row = 0; row < moleGrid.length; row++) {
            Arrays.fill(moleGrid[row], '*');
        }
    }

    public static void main(String[] args) {
        Scanner gamesetup = new Scanner(System.in);
        System.out.println("Please set grid size (x*x) below: ");
        int gridD = gamesetup.nextInt();
        while (true) {
            System.out.println("How many moles should there be? ");
            int totalMole = gamesetup.nextInt();
            if (totalMole == 0) {
                System.out.println("There must be more than 0 mole!");
                continue;
            } else if (totalMole >= gridD*gridD) {
                System.out.println("There can't be more moles than holes!");
                continue;
            } else {
                break;
            }
        }
        System.out.println("How many times do you want to try? ");
        int attempts = gamesetup.nextInt();
    }
}
