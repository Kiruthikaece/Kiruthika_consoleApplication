import java.util.Scanner;

public class TicTacToePlayers {

    private static final char EMPTY = ' ';
    private static final char PLAYER_X = 'X';
    private static final char PLAYER_O = 'O';
    private static char currentPlayer = PLAYER_X;
    private static char[][] board = {
        { EMPTY, EMPTY, EMPTY },
        { EMPTY, EMPTY, EMPTY },
        { EMPTY, EMPTY, EMPTY }
    };

    public static void main(String[] args) {
        boolean gameWon = false;
        while (!gameWon && !isBoardFull()) {
            printBoard();
            playerMove();
            gameWon = checkForWin();
            if (!gameWon) {
                switchPlayer();
            }
        }
        printBoard();
        if (gameWon) {
            System.out.println("Player " + currentPlayer + " wins!");
        } else {
            System.out.println("The game is a tie!");
        }
    }

    private static void printBoard() {
        System.out.println("  0 1 2");
        for (int row = 0; row < 3; row++) {
            System.out.print(row + " ");
            for (int col = 0; col < 3; col++) {
                System.out.print(board[row][col]);
                if (col < 2) {
                    System.out.print("|");
                }
            }
            System.out.println();
            if (row < 2) {
                System.out.println("  -----");
            }
        }
    }

    private static void playerMove() {
        Scanner scanner = new Scanner(System.in);
        int row, col;
        while (true) {
            System.out.println("Player " + currentPlayer + ", enter your move (row and column): ");
            row = scanner.nextInt();
            col = scanner.nextInt();
            if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == EMPTY) {
                board[row][col] = currentPlayer;
                break;
            } else {
                System.out.println("This move is not valid");
            }
        }
    }

    private static void switchPlayer() {
        currentPlayer = (currentPlayer == PLAYER_X) ? PLAYER_O : PLAYER_X;
    }

    private static boolean isBoardFull() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean checkForWin() {
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) ||
                (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer)) {
                return true;
            }
        }
        // Check diagonals
        if ((board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) ||
            (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer)) {
            return true;
        }
        return false;
    }
}
