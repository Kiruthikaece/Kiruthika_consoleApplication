import java.util.Scanner;

public class TicTacToeMain {
    Scanner sc=new Scanner(System.in);

    public static void main(String[] args) {
        TicTacToeMain ticTacToeMain=new TicTacToeMain();
        ticTacToeMain.getstart();
    }


    public void getstart() {
      

        char[][] mat=new char[3][3];
        char symbol=' ';

        for(int i=0;i<3;i++) {
            for(int j=0;j<3;j++) {
                mat[i][j]=symbol;
            }
           }
    
           int count=0;
           for(int i=0;i<3;i++) {
            for(int j=0;j<3;j++) {
                getInput(mat,count++,symbol);
            }
           }
    }

    public void getInput(char[][] mat,int count,char symbol) {
        if(count<=9) {
            if(count%2!=0) 
                 symbol='O';
            else
            symbol='X';
        }
          System.out.println("Enter position row");
          int row=sc.nextInt();
          System.out.println("Enter position col");
          int col=sc.nextInt();
          mat[row][col]=symbol;
          if(validPosition(mat,row,col)) {
            printBox(mat);
            checkResult(mat,row,col,symbol);
          }
          else {
            System.out.println("position already filled");
            return;
          }
        
          

    }


    private boolean validPosition(char[][] mat, int row, int col) {
       if(mat[row][col]=='X' || mat[row][col]=='O')
       return false;
       else
       return true;
    }


    private void printBox(char[][] mat) {
       for(int i=0;i<3;i++) {
        for(int j=0;j<3;j++)
        System.out.print(mat[i][j]+" ");
        System.out.println();
       }
  
    }


    public void checkResult(char[][] mat,int row,int col,char symbol) {
         if(checkrowWise(mat,row,symbol)) {
            System.out.println("Won the match");
            System.exit(0);
         }
        

         if(checkColWise(mat,col,symbol))
         {
            System.out.println("Won the match");
            System.exit(0);
         }
         
         if(checkDigonal(mat,symbol))
         {
            System.out.println("Won the match");
            System.exit(0);
         }
    }


    private boolean checkDigonal(char[][] mat, char symbol) {
        boolean isdignoal1=true;
        boolean isdignoal2=true;
         for(int i=0;i<3;i++)
         if(mat[i][i]!=symbol)
         isdignoal1=false;

         int k=2;
         for(int i=0;i<3;i++) {
            if(mat[i][k--]!=symbol)
            isdignoal2=false;
         }

         return isdignoal1 || isdignoal2;
    }


    private boolean checkColWise(char[][] mat, int col, char symbol) {
        for(int i=0;i<3;i++) {
            if(mat[i][col]!=symbol)
            return false;
          }
          return true;
    }


    private boolean checkrowWise(char[][] mat, int row, char symbol) {
    
          for(int i=0;i<3;i++) {
            if(mat[row][i]!=symbol)
            return false;
          }
          return true;
    }
}