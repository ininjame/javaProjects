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


    public static void main(String[] args) {
        int size = 5;

        Squarelotron testMatrix = new Squarelotron(size);
        for (int row = 0; row < size; row++) {
            System.out.println(Arrays.toString(testMatrix.matrix[row]));
        }


        int[][] flipped = testMatrix.UpDownFlip(2);
        for (int row = 0; row < size; row++) {
            System.out.println(Arrays.toString(flipped[row]));
        }   
    }
}