package game_console;

import java.util.Scanner;

public class TicTacToeComputerVsPlayer {

	char[][] board=new char[3][3];
	char currentPlayer='X';
	
	Scanner sc=new Scanner(System.in);
	public static void main(String[] args) {
		TicTacToeComputerVsPlayer obj=new TicTacToeComputerVsPlayer();
		System.out.println("Welocme to Tic-TAC-Toe game");
		obj.initalize();
	}

	private void initalize() {
		
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++)
				board[i][j]=' ';
		}
		
		while(true) {
			playGame();
			printBoard();
			if(gameWon()) 
				break;
			playComputer();
			printBoard();
			if(gameWon()) 
				break;
		}
	}

	

	private void playComputer() {
		if(!tryToWinOrBlock('O')) {
			if(!tryToWinOrBlock('X'))
				pickSpace();
		}
	}

	private void pickSpace() {
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++)
				if(board[i][j]==' ') {
					board[i][j]='O';
					return;
				}
				
		}
	}

	 public boolean tryToWinOrBlock(char player) {
	        // Check rows, columns, and diagonals for a potential win/block
	        for (int i = 0; i < 3; i++) {
	            // Check rows
	            if (checkLine(player, board[i][0], board[i][1], board[i][2])) {
	                for (int j = 0; j < 3; j++) {
	                    if (board[i][j] == ' ') {
	                        board[i][j] = 'O';  // Computer places 'O'
	                        return true;
	                    }
	                }
	            }
	            // Check columns
	            if (checkLine(player, board[0][i], board[1][i], board[2][i])) {
	                for (int j = 0; j < 3; j++) {
	                    if (board[j][i] == ' ') {
	                        board[j][i] = 'O';
	                        return true;
	                    }
	                }
	            }
	        }
	        // Check diagonals
	        if (checkLine(player, board[0][0], board[1][1], board[2][2])) {
	            for (int i = 0; i < 3; i++) {
	                if (board[i][i] == ' ') {
	                    board[i][i] = 'O';
	                    return true;
	                }
	            }
	        }
	        if (checkLine(player, board[0][2], board[1][1], board[2][0])) {
	            for (int i = 0; i < 3; i++) {
	                if (board[i][2 - i] == ' ') {
	                    board[i][2 - i] = 'O';
	                    return true;
	                }
	            }
	        }
	        return false;  // No winning/blocking move found
	    }

	 public boolean checkLine(char player, char c1, char c2, char c3) {
	        return( (c1 == player && c2 == player && c3 == ' ') || 
	               (c1 == player && c3 == player && c2 == ' ') ||
	               (c2 == player && c3 == player && c1 == ' '));
	    }

	private void printBoard() {
		System.out.println("---------------");
		for(int i=0;i<3;i++) {
			System.out.print("|");
			for(int j=0;j<3;j++) 
				System.out.print(board[i][j]+"|");
				System.out.println();
				System.out.println("---------------");		
		}
		
	}

	private void playGame() {
		int row=-1,col=-1;
		System.out.println("Enter rows and colums(1-3):");
		 row=sc.nextInt()-1;
		 col=sc.nextInt()-1;
		if(row>=0 && row<3 && col>=0 && col<3 && board[row][col]==' ')
			board[row][col]='X';
		else {
			System.out.println("Invalid moves");
			playGame();
		}
	}

	
	public boolean gameWon() {
        if(isWonGame(currentPlayer)) {
            System.out.println("Game over!"+ currentPlayer+" wins the match!");
            return true;
        }
        if(isBoardFull()) {
            System.out.println("Game over draw the match!");
            return true;
        }

        currentPlayer=(currentPlayer=='X')?'O':'X';
        return false;
   }
	private boolean isBoardFull() {
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++)
				if(board[i][j]==' ')
					return false;
		}
		
		return true;
	}

	private boolean isWonGame(char currentPlayer) {
		
		 for (int i = 0; i < 3; i++) {
	            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer)
	                return true;
	        }
	        // Check columns
	        for (int i = 0; i < 3; i++) {
	            if (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer)
	                return true;
	        }
		   
		   if(board[0][0]==currentPlayer && board[1][1]==currentPlayer && board[2][2]==currentPlayer)
			   return true;
		   
		   if(board[0][2]==currentPlayer && board[1][1]==currentPlayer && board[2][0]==currentPlayer)
			   return true;
		   
		   
		   
		   return false;
			   
	}
}
