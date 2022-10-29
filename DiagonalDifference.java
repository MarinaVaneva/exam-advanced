import java.util.Arrays;
import java.util.Scanner;

public class DiagonalDifference {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int rows = Integer.parseInt(scanner.nextLine());

        int[][] matrix = new int [rows][];
        for (int i = 0; i < matrix.length; i++) {
             int [] numbers = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
          matrix[i] = numbers;

        }
int sumFirst = 0;
int sumSecond = 0;

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                int currentElement = matrix[row][col];
                if(row==col){
                    sumFirst+=currentElement;
                }
            }

        }

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                int currentElement = matrix[row][col];
                if(col== matrix.length - row - 1){
                    sumSecond+=currentElement;
                }
            }

        }


int diff = Math.abs(sumFirst-sumSecond);
        System.out.println(diff);
    }
}
