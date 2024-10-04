import java.util.Scanner;

public class TicTacToe {

    private static char[][] board = new char[3][3];

    public static void main(String[] args) {
        initializeBoard();
        char currentPlayer = 'X';
        boolean gameWon = false;

        while (true) {
            printBoard();
            int[] move = getPlayerMove(currentPlayer);

            if (board[move[0]][move[1]] == ' ') {
                board[move[0]][move[1]] = currentPlayer;
            } else {
                System.out.println("This cell is already occupied. Try again.");
                continue;
            }

            gameWon = checkWinner(currentPlayer);
            if (gameWon) {
                printBoard();
                System.out.println("Player " + currentPlayer + " wins!");
                break;
            }

            if (checkDraw()) {
                printBoard();
                System.out.println("The game is a draw!");
                break;
            }

            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        }
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

    private static int[] getPlayerMove(char player) {
        Scanner scanner = new Scanner(System.in);
        int row = -1;
        int col = -1;

        while (true) {
            System.out.println("Player " + player + ", enter your move (row and column): ");
            row = scanner.nextInt() - 1;
            col = scanner.nextInt() - 1;

            if (row >= 0 && row < 3 && col >= 0 && col < 3) {
                break;
            } else {
                System.out.println("Invalid input. Please enter numbers between 1 and 3.");
            }
        }

        return new int[]{row, col};
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
