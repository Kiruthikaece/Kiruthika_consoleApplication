import java.util.Scanner;

public class TicTacToeComputerVsPlayer {
     
    char[][] board=new char[3][3];
    char currentPlayer='X';

     public static void main(String[] args) {
        TicTacToeComputerVsPlayer obj=new TicTacToeComputerVsPlayer();

        for(int i=0;i<3;i++)
        for(int j=0;j<3;j++)
        obj.board[i][j]=' ';
        obj.printBoard();
        obj.initializeGame();
     }

    public void initializeGame() {
       while(true) {
          playPlayer();
          printBoard();
          if(gameWon())
          break;
          playComputer();
          printBoard();
          if(gameWon())
          break;
       }
    }


    public void playPlayer() {
        int row=-1,col=-1;
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter position(row and col)");
        row=sc.nextInt()-1;
        col=sc.nextInt()-1;
        if(row>=0 && row<3 && col>=0 && col<3 && board[row][col]==' ')
        board[row][col]='X';
        else 
        System.out.println("Invalid  moves");
        
    }

    public void playComputer() {
        for(int i=0;i<3;i++) {
            for(int j=0;j<3;j++)
            if(board[i][j]==' ') {
                board[i][j]='O';
                return;
            }   
        }
    }

   public void printBoard() {
    System.out.println("------------");
        for(int i=0;i<3;i++) {
            System.out.print("|");
        for(int j=0;j<3;j++) 
            System.out.print(board[i][j]+" | ");
            System.out.println();
            System.out.println("-------------");       
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

    public boolean isBoardFull() {
        for(int i=0;i<3;i++) {
            for(int j=0;j<3;j++)
            if(board[i][j]==' ')
            return false;
        }
        return true;
    }

    public boolean isWonGame(char currentPlayer) {
        //check rows
        for(int i=0;i<3;i++)
        if(board[i][0]==currentPlayer && board[i][1]==currentPlayer && board[i][2]==currentPlayer)
        return true;

        //check col
        for(int i=0;i<3;i++)
        if(board[0][i]==currentPlayer && board[1][i]==currentPlayer && board[2][i]==currentPlayer)
        return true;

        //check diagonals
        if(board[0][0]==currentPlayer && board[1][1]==currentPlayer && board[2][2]==currentPlayer)
        return true;

        if(board[0][2]==currentPlayer && board[1][1]==currentPlayer && board[2][0]==currentPlayer)
        return true;

        return false;
    }

}
