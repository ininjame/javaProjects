import java.util.Arrays;

public class Squarelotron {
    int[][] matrix;
    int size;

    public Squarelotron(int size) {
        this.size = size;
        int[][] matrixT = new int[size][size];
        int i = 1;
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                matrixT[row][col] = i;
                i++;
            }
        }
        
        this.matrix = matrixT;
    }

    public Squarelotron DiagonalFlip(int ring) {
        System.out.println("Flipping matrix diagonally...");
        Squarelotron newMatrix = new Squarelotron(size);
        //int[][] matrixFlipped = new int[size][size];
        //for (int row = 0; row < size; row++) {
            //for (int col = 0; col < size; col++) {
                //matrixFlipped[row][col] = matrix[row][col];
            //}
        //}

        int sizeR = size-1;
        for (int i = ring -1; i < (size-(ring-1)); i++) {
            // matrixFlipped[ring-1][i] = matrix[i][ring-1]; //flipping row 1 & col 1
            // matrixFlipped[i][ring-1] = matrix[ring-1][i]; //flipping col 1 & row 1
            // matrixFlipped[sizeR-(ring-1)][i] = matrix[i][sizeR-(ring-1)]; //flipping row n & col n
            // matrixFlipped[i][sizeR-(ring-1)] = matrix[sizeR-(ring-1)][i]; //flipping col n & row n
            newMatrix.matrix[ring-1][i] = matrix[i][ring-1]; //flipping row 1 & col 1
            newMatrix.matrix[i][ring-1] = matrix[ring-1][i]; //flipping col 1 & row 1
            newMatrix.matrix[sizeR-(ring-1)][i] = matrix[i][sizeR-(ring-1)]; //flipping row n & col n
            newMatrix.matrix[i][sizeR-(ring-1)] = matrix[sizeR-(ring-1)][i]; //flipping col n & row n
        }

        return newMatrix;
    }

    public Squarelotron UpDownFlip(int ring) {
        System.out.println("Flipping matrix up and down...");
        Squarelotron newMatrix = new Squarelotron(size);
        // int[][] matrixFlipped = new int[size][size];
        // for (int row = 0; row < size; row++) {
        //     for (int col = 0; col < size; col++) {
        //         matrixFlipped[row][col] = matrix[row][col];
        //     }
        // }

        int sizeR = size-1;
        for (int i = ring -1; i < (size-(ring-1)); i++) {
            newMatrix.matrix[ring-1][i] = matrix[sizeR-(ring-1)][i]; //flipping row 1 & row n
            newMatrix.matrix[sizeR-(ring-1)][i] = matrix[ring-1][i]; //flipping row n & row 1
            newMatrix.matrix[i][ring-1] = matrix[sizeR-i][ring-1]; //flipping col 1
            newMatrix.matrix[sizeR-i][ring-1] = matrix[i][ring-1]; //flipping col 1
            newMatrix.matrix[i][sizeR-(ring-1)] = matrix[sizeR-i][sizeR-(ring-1)]; //flipping col n
            newMatrix.matrix[sizeR-i][sizeR-(ring-1)] = matrix[i][sizeR-(ring-1)]; //flipping col n
        }

        return newMatrix;
    }

    public void RightTurn(int turns) {
        System.out.println("Turning matrix " + turns + " times...");
        // Squarelotron matrixTurned = new Squarelotron(size);
        int sizeR = size-1;
        //int ring = 0;
        if (turns % 4 == 1 || turns % 4 == -3) {
            Squarelotron matrixTurned = new Squarelotron(size);
            for (int ring = 0; ring <= ((size-size%2)/2); ring++) {
                for (int i = ring; i < (size-ring); i++) {

                    matrix[i][sizeR-ring] = matrixTurned.matrix[ring][i]; //turning row 1
                    matrix[sizeR-ring][sizeR-i] = matrixTurned.matrix[i][sizeR-ring]; //turning col n
                    matrix[sizeR-i][ring] = matrixTurned.matrix[sizeR-ring][sizeR-i]; //turning row n
                    matrix[ring][i] = matrixTurned.matrix[sizeR-i][ring]; //turning col 1
                }
                //if (ring == 0){
                //    break;
                //}
            }
        } else if (turns % 4 == 2 || turns % 4 == -2) {
            Squarelotron matrixTurned = new Squarelotron(size);
            for (int ring = 0; ring <= ((size-size%2)/2); ring++) {
                for (int i = ring; i < (size-ring); i++) {
                    matrix[ring][i] = matrixTurned.matrix[sizeR-ring][sizeR-i]; //turning row n
                    matrix[i][sizeR-ring] = matrixTurned.matrix[sizeR-i][ring]; //turning col 1
                    matrix[sizeR-ring][sizeR-i] = matrixTurned.matrix[ring][i]; //turning row 1
                    matrix[sizeR-i][ring] = matrixTurned.matrix[i][sizeR-ring]; //turning col n
                }
            }    
        } else if (turns % 4 == 3 || turns % 4 == -1) {
            Squarelotron matrixTurned = new Squarelotron(size);
            for (int ring = 0; ring <= ((size-size%2)/2); ring++) {
                for (int i = ring; i < (size-ring); i++) {
                    matrix[ring][i] = matrixTurned.matrix[i][sizeR-ring]; //turning col n
                    matrix[i][sizeR-ring] = matrixTurned.matrix[sizeR-ring][sizeR-i]; //turning row n
                    matrix[sizeR-ring][sizeR-i] = matrixTurned.matrix[sizeR-i][ring]; //turning col 1
                    matrix[sizeR-i][ring] = matrixTurned.matrix[ring][i]; //turning row 1
                }
            }
        } //else {
        //     for (int row = 0; row < size; row++) {
        //         for (int col = 0; col < size; col++) {
        //             matrixTurned[row][col] = matrix[row][col];
        //         }
        //     }
        // }

          
    }

    public static void main(String[] args) {
        int size = 3;

        Squarelotron testMatrix = new Squarelotron(size);
        for (int row = 0; row < size; row++) {
            System.out.println(Arrays.toString(testMatrix.matrix[row]));
        }


        Squarelotron flipped = testMatrix.DiagonalFlip(1);
        for (int row = 0; row < size; row++) {
            System.out.println(Arrays.toString(flipped.matrix[row]));
        }
        
        testMatrix.RightTurn(5);
        for (int row = 0; row < size; row++) {
            System.out.println(Arrays.toString(testMatrix.matrix[row]));
        }
    }
}