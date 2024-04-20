import java.util.Scanner;

class SudokuSolver {

    private static int[][] board;
    private static int size;
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter size of sudoku board");
        size=sc.nextInt();
        board=new int[size][size];

        System.out.println("Enter sudoku using 0 for empty cells");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = sc.nextInt();
            }
        }

        if(isSudokuSolve()) {
            System.out.println("Answer:");
            printBoard();
        }
        else
        System.out.println("Not valid sudoku");

    }
    
    private static boolean isSudokuSolve() {
        int row=-1,col=-1;
        boolean isEmpty=true;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
               if(board[i][j]==0) {
                  row=i;
                  col=j;
                  isEmpty=false;
                  break;
               }
            }
            if(!isEmpty)
            break;
        }


        if(isEmpty)
        return true;

        for(int num=1;num<=size;num++) {
            if(isSafe(row,col,num)) {
                board[row][col]=num;
                if(isSudokuSolve())
                return true;
                else
                board[row][col]=0;
            }
        }
        return false;
    }

    private static boolean isSafe(int row, int col, int num) {
       int subgridRow=row-row%(int)Math.sqrt(size);
       int subgridCol=col-col%(int)Math.sqrt(size);

       return !isPresentRow(row,num) && !isPresentCol(col,num) && !isPresentSubgrid(subgridRow,subgridCol,num);
    }

    private static boolean isPresentRow(int row, int num) {
       for(int i=0;i<size;i++) 
        if(board[row][i]==num)
        return true;

        return false;   
    }
    private static boolean isPresentCol(int col, int num) {
       for(int i=0;i<size;i++) {
        if(board[i][col]==num)
        return true;
       }
       return false;  
    }

    private static boolean isPresentSubgrid(int subgridRow, int subgridCol, int num) {
        for (int i = 0; i < Math.sqrt(size); i++) {
            for (int j = 0; j < Math.sqrt(size); j++) {
                if (board[i + subgridRow][j + subgridCol] == num) {
                    return true;
                }
            }
        }
        return false;
    }

    private static void printBoard() {
      for(int i=0;i<size;i++) {
        for(int j=0;j<size;j++)
        System.out.print(board[i][j]+" ");
        System.out.println();
      }
    }
}