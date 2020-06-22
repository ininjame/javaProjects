import java.util.*;

public class WhackAMole {
    int score;
    int molesLeft;
    int attemptsLeft;
    char[][] moleGrid;

    public WhackAMole(int numAttempts, int gridDimension) {
        moleGrid = new char[gridDimension][gridDimension];
        attemptsLeft = numAttempts;
        score = 0;
        molesLeft = 0;
        for (int row = 0; row < moleGrid.length; row++) {
            Arrays.fill(moleGrid[row], '*');
        }
    }

    public boolean place(int x, int y) {
        if (moleGrid[x][y] != 'M') {
            moleGrid[x][y] = 'M';
            molesLeft ++;
            return true;
        } else {
            return false;
        }
    }

    public void autoFill(int gridSize, int moleCount) {
        Random autoFill = new Random();
        int row;
        int col;
        while (molesLeft <= moleCount) {
            row = autoFill.nextInt(gridSize);
            col = autoFill.nextInt(gridSize);
            place(row, col);
            
        }
    }

    public void whack(int x, int y) {
        if (moleGrid[x][y] == 'M') {
            score += 1;
            attemptsLeft -= 1;
            molesLeft -=1;
            moleGrid[x][y] = 'W';
            System.out.println("You whacked a mole!");
        } else {
            System.out.println("You missed, sorry :( ");
            attemptsLeft -= 1;
        }
    }

    public void printGridToUser() {
        char[][] moleGridUser = new char[moleGrid.length][moleGrid[0].length];
        for (int row = 0; row < moleGridUser.length; row++) {
            for (int col = 0; col < moleGridUser[row].length; col++) {
                if (moleGrid[row][col] != 'W') {
                    moleGridUser[row][col] = '*';
                } else {
                    moleGridUser[row][col] = moleGrid[row][col];
                }
            }
        }
        for (int row = 0; row < moleGridUser.length; row++) {
            System.out.println(Arrays.toString(moleGridUser[row]));
        }
    }

    public void printGrid() {
        for (int row = 0; row < moleGrid.length; row++) {
            System.out.println(Arrays.toString(moleGrid[row]));
        }    
    }

    public static void main(String[] args) {
        
        boolean gameOn = true;

        while (gameOn) {
            Scanner gamesetup = new Scanner(System.in);
            int totalMole2 = 0;
            System.out.println("Please set grid size (x*x) below: ");
            int gridD = gamesetup.nextInt();
            while (true) {
                System.out.println("How many moles should there be? ");
                totalMole2 = gamesetup.nextInt();
                if (totalMole2 == 0) {
                    System.out.println("There must be more than 0 mole!");
                    continue;
                } else if (totalMole2 >= gridD*gridD) {
                    System.out.println("There can't be more moles than holes!");
                    continue;
                } else {
                    break;
                }
            }
            int totalMole = totalMole2;
            System.out.println("How many times do you want to try? ");
            int attempts = gamesetup.nextInt();
            WhackAMole newGame = new WhackAMole(attempts, gridD);
            newGame.autoFill(gridD, totalMole);
            while (newGame.attemptsLeft > 0) {
                System.out.println("Enter the coordinates of the mole separate by a space," +
                " or enters -1 for both coordinates to exit the game: ");
                Scanner userInput = new Scanner(System.in);
                int corX = userInput.nextInt();
                int corY = userInput.nextInt();
                if (corX > (gridD-1) || corY > (gridD-1)
                 || corX < -1 || corY < -1 || corX*corY < 0 ) {
                    System.out.println("Inputted coordinates are out of grid range. Please input again.");
                    continue;
                }
                if (corX + corY == -1) {
                    newGame.printGrid();
                    for (int row = 0; row < newGame.moleGrid.length; row++ ) {
                        for (int col = 0; col < newGame.moleGrid[row].length; col++) {
                            if (newGame.moleGrid[row][col] == 'M') {
                                System.out.println("Mole location: " + row + ", " + col);
                            }
                        } 
                    }
                    continue;
                }
                if (corX == -1 && corY == -1 ) {
                    System.out.println("Too bad, here's this round's grid: ");
                    newGame.printGrid();
                    System.out.println("Exiting the game...");
                    gameOn = false;
                    break;
                }
                newGame.whack(corX, corY);
                if (newGame.score >= totalMole) {
                    break;
                } else {
                System.out.println("Here is the current status: ");
                newGame.printGridToUser();
                System.out.println("You have " + newGame.attemptsLeft + " attempts left");
                continue;
                }
            }

            if (newGame.score >= totalMole) {
                System.out.println("You win! You whacked all the moles!");
                System.out.println("Here's this round's grid: ");
                newGame.printGrid();
                System.out.println("Do you want to start a new round? (y/n)");
                Scanner cont = new Scanner(System.in);
                char userCont = cont.next().charAt(0);
                if (userCont == 'y') {
                    continue;
                } else {
                    System.out.println("Exiting the game...");
                    gameOn = false;
                    break;
                }
            }

            if (newGame.attemptsLeft == 0) {
                System.out.println("Too bad! You ran out of attempts.");
                System.out.println("Here's this round's grid: ");
                newGame.printGrid();
                System.out.println("Do you want to start a new game (y/n)? ");
                Scanner cont = new Scanner(System.in);
                char userCont = cont.next().charAt(0);
                if (userCont == 'y') {
                    continue;
                } else {
                    System.out.println("Exiting the game...");
                    gameOn = false;
                    break;
                }
            }
        }    
    }
    
}


