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

    public int[][] DiagonalFlip(int ring) {
        System.out.println("Flipping matrix diagonally...");
        int[][] matrixFlipped = new int[size][size];
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                matrixFlipped[row][col] = matrix[row][col];
            }
        }

        int sizeR = size-1;
        for (int i = ring -1; i < (size-(ring-1)); i++) {
            matrixFlipped[ring-1][i] = matrix[i][ring-1]; //flipping row 1 & col 1
            matrixFlipped[i][ring-1] = matrix[ring-1][i]; //flipping col 1 & row 1
            matrixFlipped[sizeR-(ring-1)][i] = matrix[i][sizeR-(ring-1)]; //flipping row n & col n
            matrixFlipped[i][sizeR-(ring-1)] = matrix[sizeR-(ring-1)][i]; //flipping col n & row n
        }

        return matrixFlipped;
    }

    public int[][] UpDownFlip(int ring) {
        System.out.println("Flipping matrix up and down...");
        int[][] matrixFlipped = new int[size][size];
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                matrixFlipped[row][col] = matrix[row][col];
            }
        }

        int sizeR = size-1;
        for (int i = ring -1; i < (size-(ring-1)); i++) {
            matrixFlipped[ring-1][i] = matrix[sizeR-(ring-1)][i]; //flipping row 1 & row n
            matrixFlipped[sizeR-(ring-1)][i] = matrix[ring-1][i]; //flipping row n & row 1
            matrixFlipped[i][ring-1] = matrix[sizeR-i][ring-1]; //flipping col 1
            matrixFlipped[sizeR-i][ring-1] = matrix[i][ring-1]; //flipping col 1
            matrixFlipped[i][sizeR-(ring-1)] = matrix[sizeR-i][sizeR-(ring-1)]; //flipping col n
            matrixFlipped[sizeR-i][sizeR-(ring-1)] = matrix[i][sizeR-(ring-1)]; //flipping col n
        }

        return matrixFlipped;
    }

    public int[][] RightTurn(int turns) {
        System.out.println("Turning matrix...");
        int[][] matrixTurned = new int[size][size];
        int sizeR = size-1;
        //int ring = 0;
        if (turns % 4 == 1 || turns % 4 == -3) {
            for (int ring = 0; ring <= ((size-size%2)/2); ring++) {
                for (int i = ring; i < (size-ring); i++) {

                    matrixTurned[i][sizeR-ring] = matrix[ring][i]; //turning row 1
                    matrixTurned[sizeR-ring][sizeR-i] = matrix[i][sizeR-ring]; //turning col n
                    matrixTurned[sizeR-i][ring] = matrix[sizeR-ring][sizeR-i]; //turning row n
                    matrixTurned[ring][i] = matrix[sizeR-i][ring]; //turning col 1
                }
                //if (ring == 0){
                //    break;
                //}
            }
        } else if (turns % 4 == 2 || turns % 4 == -2) {
            for (int ring = 0; ring <= ((size-size%2)/2); ring++) {
                for (int i = ring; i < (size-ring); i++) {
                    matrixTurned[ring][i] = matrix[sizeR-ring][sizeR-i]; //turning row n
                    matrixTurned[i][sizeR-ring] = matrix[sizeR-i][ring]; //turning col 1
                    matrixTurned[sizeR-ring][sizeR-i] = matrix[ring][i]; //turning row 1
                    matrixTurned[sizeR-i][ring] = matrix[i][sizeR-ring]; //turning col n
                }
            }    
        } else if (turns % 4 == 3 || turns % 4 == -1) {
            for (int ring = 0; ring <= ((size-size%2)/2); ring++) {
                for (int i = ring; i < (size-ring); i++) {
                    matrixTurned[ring][i] = matrix[i][sizeR-ring]; //turning col n
                    matrixTurned[i][sizeR-ring] = matrix[sizeR-ring][sizeR-i]; //turning row n
                    matrixTurned[sizeR-ring][sizeR-i] = matrix[sizeR-i][ring]; //turning col 1
                    matrixTurned[sizeR-i][ring] = matrix[ring][i]; //turning row 1
                }
            }
        } else {
            for (int row = 0; row < size; row++) {
                for (int col = 0; col < size; col++) {
                    matrixTurned[row][col] = matrix[row][col];
                }
            }
        }

        return matrixTurned;
          
    }

    public static void main(String[] args) {
        int size = 4;

        Squarelotron testMatrix = new Squarelotron(size);
        for (int row = 0; row < size; row++) {
            System.out.println(Arrays.toString(testMatrix.matrix[row]));
        }


        int[][] flipped = testMatrix.UpDownFlip(2);
        for (int row = 0; row < size; row++) {
            System.out.println(Arrays.toString(flipped[row]));
        }
        
        int[][] turned = testMatrix.RightTurn(3);
        for (int row = 0; row < size; row++) {
            System.out.println(Arrays.toString(turned[row]));
        }
    }
}