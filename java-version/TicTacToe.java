import java.util.Scanner;
import java.util.Random;

public class TicTacToe {

    private static char[][] board = new char[3][3];
    private static int playerScore = 0;
    private static int aiScore = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char currentPlayer = 'X';
        boolean playAgain = true;

        while (playAgain) {
            initializeBoard();
            boolean gameWon = false;

            while (true) {
                printBoard();

                int[] move;
                if (currentPlayer == 'X') {
                    move = getPlayerMove(scanner);
                    if (board[move[0]][move[1]] != ' ') {
                        System.out.println("This cell is already occupied. Try again.");
                        continue;
                    }
                } else {
                    System.out.println("AI is making a move...");
                    move = getAIMove();
                }

                board[move[0]][move[1]] = currentPlayer;

                if (checkWinner(currentPlayer)) {
                    printBoard();
                    if (currentPlayer == 'X') {
                        System.out.println("Player X wins!");
                        playerScore++;
                    } else {
                        System.out.println("AI wins!");
                        aiScore++;
                    }
                    break;
                }

                if (checkDraw()) {
                    printBoard();
                    System.out.println("The game is a draw!");
                    break;
                }

                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            }

            System.out.println("Scores: Player - " + playerScore + ", AI - " + aiScore);
            System.out.print("Do you want to play again? (y/n): ");
            String response = scanner.next().toLowerCase();
            playAgain = response.equals("y");
        }

        System.out.println("Thanks for playing!");
    }

    private static void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    private static void printBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j]);
                if (j < 2) {
                    System.out.print("|");
                }
            }
            System.out.println();
            if (i < 2) {
                System.out.println("-----");
            }
        }
    }

    private static int[] getPlayerMove(Scanner scanner) {
        int row = -1;
        int col = -1;

        while (true) {
            System.out.println("Enter your move (row and column 1-3): ");
            if (scanner.hasNextInt()) {
                row = scanner.nextInt() - 1;
                col = scanner.nextInt() - 1;
            } else {
                System.out.println("Invalid input. Please enter numbers between 1 and 3.");
                scanner.next();
                continue;
            }
            if (row >= 0 && row < 3 && col >= 0 && col < 3) {
                return new int[]{row, col};
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }
    }

    private static int[] getAIMove() {
        Random random = new Random();
        int row, col;
        while (true) {
            row = random.nextInt(3);
            col = random.nextInt(3);
            if (board[row][col] == ' ') {
                return new int[]{row, col};
            }
        }
    }

    private static boolean checkWinner(char player) {
        // Check rows, columns, and diagonals
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
                return true;
            }
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) {
                return true;
            }
        }

        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true;
        }

        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            return true;
        }

        return false;
    }

    private static boolean checkDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
}
