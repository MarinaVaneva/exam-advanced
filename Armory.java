import java.util.Scanner;

public class Armory {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        char[][] matrix = new char[n][n];
        for (int row = 0; row < n; row++) {
            matrix[row] = scanner.nextLine().replaceAll(" ", "").toCharArray();
        }

        int rowOfficer = 0;
        int colOfficer = 0;
        int priceSwords = 0;
        int rowM1 = 0;
        int colM1 = 0;
        int rowM2 = 0;
        int colM2 = 0;
        boolean weFound = false;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                char currentElement = matrix[row][col];
                if (currentElement == 'A') {
                    rowOfficer = row;
                    colOfficer = col;
                } else if (currentElement == 'M' && weFound == false) {
                    rowM1 = row;
                    colM1 = col;
                    weFound = true;
                } else if (currentElement == 'M' && weFound == true) {
                    rowM2 = row;
                    colM2 = col;
                }

            }
        }

            while (priceSwords < 65) {
                String command = scanner.nextLine();

                int oldRow = rowOfficer;
                int oldCol = colOfficer;
                switch (command) {
                    case "left":
                        colOfficer--;
                        break;
                    case "right":
                        colOfficer++;
                        break;
                    case "up":
                        rowOfficer--;
                        break;
                    case "down":
                        rowOfficer++;
                        break;
                }
                if (rowOfficer < 0 || colOfficer < 0 || rowOfficer >= matrix.length || colOfficer >= matrix.length) {
                    matrix[oldRow][oldCol] = '-';
                    System.out.println("I do not need more swords!");
                    break;
                }

                if (rowOfficer ==rowM1 && colOfficer ==colM1) {
                    rowOfficer = rowM2;
                    colOfficer = colM2;
                    matrix[oldRow][oldCol] = '-';
                    matrix[rowM1][colM1] = '-';
                    matrix[rowOfficer][colOfficer] = 'A';
                }else if(rowOfficer ==rowM2 && colOfficer ==colM2) {
                    rowOfficer = rowM1;
                    colOfficer = colM1;
                    matrix[oldRow][oldCol] = '-';
                    matrix[rowM2][colM2] = '-';
                    matrix[rowOfficer][colOfficer] = 'A';
                }else if(matrix[rowOfficer][colOfficer] >= 49 && matrix[rowOfficer][colOfficer] <= 57){
                    priceSwords += matrix[rowOfficer][colOfficer];
                    matrix[oldRow][oldCol] = '-';
                    matrix[rowOfficer][colOfficer] = 'A';
                }else{
                    matrix[oldRow][oldCol] = '-';
                    matrix[rowOfficer][colOfficer] = 'A';
                }


                command = scanner.nextLine();
            }

        if (priceSwords >=65) {
            System.out.println("Very nice swords, I will come back for more!");
        }
            System.out.printf("The king paid %d gold coins.%n", priceSwords);
            for (int row1 = 0; row1 < matrix.length; row1++) {
                for (int col = 0; col < matrix.length; col++) {
                    System.out.print(matrix[row1][col] + " ");
                }
                System.out.println();
            }


        }
    }


