import java.util.Scanner;

public class TicTacToeComputerVsPlayerc {
    private static char[][] board = new char[3][3];
    private static char currentPlayer = 'X';

    public static void main(String[] args) {
        initializeBoard();
        printBoard();
        while (true) {
            playerMove();
            if (isGameFinished()) break;
            printBoard();
            computerMove();
            if (isGameFinished()) break;
            printBoard();
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
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    private static void playerMove() {
        Scanner scanner = new Scanner(System.in);
        int row = -1, col = -1;
        while (true) {
            System.out.println("Enter your move (row and column: 1 1, 1 2, etc.): ");
            row = scanner.nextInt() - 1;
            col = scanner.nextInt() - 1;
            if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ') {
                board[row][col] = 'X';
                break;
            } else {
                System.out.println("This move is not valid.");
            }
        }
    }

    private static void computerMove() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    board[i][j] = 'O';
                    return;
                }
            }
        }
    }

    private static boolean isGameFinished() {
        if (hasContestantWon(currentPlayer)) {
            printBoard();
            System.out.println("Game over! " + currentPlayer + " wins!");
            return true;
        }
        if (isBoardFull()) {
            printBoard();
            System.out.println("Game over! It's a draw!");
            return true;
        }
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        return false;
    }

    private static boolean hasContestantWon(char contestant) {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == contestant && board[i][1] == contestant && board[i][2] == contestant) {
                return true;
            }
        }
        // Check columns
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == contestant && board[1][i] == contestant && board[2][i] == contestant) {
                return true;
            }
        }
        // Check diagonals
        if (board[0][0] == contestant && board[1][1] == contestant && board[2][2] == contestant) {
            return true;
        }
        if (board[0][2] == contestant && board[1][1] == contestant && board[2][0] == contestant) {
            return true;
        }
        return false;
    }

    private static boolean isBoardFull() {
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


// u want computer wins

// private static void computerMove() {
//     // Try to win
//     if (tryToWinOrBlock('O')) {
//         return;
//     }
//     // Try to block the player
//     if (tryToWinOrBlock('X')) {
//         return;
//     }
//     // Take the first available spot
//     for (int i = 0; i < 3; i++) {
//         for (int j = 0; j < 3; j++) {
//             if (board[i][j] == ' ') {
//                 board[i][j] = 'O';
//                 return;
//             }
//         }
//     }
// }

// private static boolean tryToWinOrBlock(char symbol) {
//     // Check rows and columns
//     for (int i = 0; i < 3; i++) {
//         if (board[i][0] == symbol && board[i][1] == symbol && board[i][2] == ' ') {
//             board[i][2] = 'O';
//             return true;
//         }
//         if (board[i][0] == symbol && board[i][2] == symbol && board[i][1] == ' ') {
//             board[i][1] = 'O';
//             return true;
//         }
//         if (board[i][1] == symbol && board[i][2] == symbol && board[i][0] == ' ') {
//             board[i][0] = 'O';
//             return true;
//         }
//         if (board[0][i] == symbol && board[1][i] == symbol && board[2][i] == ' ') {
//             board[2][i] = 'O';
//             return true;
//         }
//         if (board[0][i] == symbol && board[2][i] == symbol && board[1][i] == ' ') {
//             board[1][i] = 'O';
//             return true;
//         }
//         if (board[1][i] == symbol && board[2][i] == symbol && board[0][i] == ' ') {
//             board[0][i] = 'O';
//             return true;
//         }
//     }
//     // Check diagonals
//     if (board[0][0] == symbol && board[1][1] == symbol && board[2][2] == ' ') {
//         board[2][2] = 'O';
//         return true;
//     }
//     if (board[0][0] == symbol && board[2][2] == symbol && board[1][1] == ' ') {
//         board[1][1] = 'O';
//         return true;
//     }
//     if (board[1][1] == symbol && board[2][2] == symbol && board[0][0] == ' ') {
//         board[0][0] = 'O';
//         return true;
//     }
//     if (board[0][2] == symbol && board[1][1] == symbol && board[2][0] == ' ') {
//         board[2][0] = 'O';
//         return true;
//     }
//     if (board[0][2] == symbol && board[2][0] == symbol && board[1][1] == ' ') {
//         board[1][1] = 'O';
//         return true;
//     }
//     if (board[1][1] == symbol && board[2][0] == symbol && board[0][2] == ' ') {
//         board[0][2] = 'O';
//         return true;
//     }
//     return false;
// }
