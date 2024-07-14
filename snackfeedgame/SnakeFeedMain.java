import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SnakeFeedMain {
    Scanner sc=new Scanner(System.in);  
    char[][] board=new char[5][5];
    char snakeHead='@';
    char snakeBody='#';
    char food='O';
    List<int[]> snake=new ArrayList<>();
    int points=0;

    public static void main(String[] args) {
        SnakeFeedMain snakeFeedMain=new SnakeFeedMain();
        snakeFeedMain.initialSetup();
    }

    public void initialSetup() {
         for(int i=0;i<5;i++) {
            for(int j=0;j<5;j++)
            board[i][j]=' ';
         }

         placeSnake();
         placeFood();
         printBoard();
         startGame();
    }



    public void placeSnake() {
        int min=0,max=4;
        int snakeRow=(int)(Math.random()*max-min+min);
        int snakeCol=(int)(Math.random()*max-min+min);
        board[snakeRow][snakeCol]=snakeHead;
        snake.add(new int[]{snakeRow,snakeCol});
    }

    public void placeFood() {

        int min=0,max=4;
        int foodRow=(int)(Math.random()*max-min+min);
        int foodCol=(int)(Math.random()*max-min+min);
        if(board[foodRow][foodCol]==' ')
        board[foodRow][foodCol]=food;
        else
        placeFood();
       
    }

    public void printBoard() {
        System.out.println("------------");
        for(int i=0;i<5;i++) {
            System.out.print("|");
            for(int j=0;j<5;j++)
            System.out.print(board[i][j]+" ");
            System.out.print("|");
            System.out.println();
        }
        System.out.println("------------");
    }

public void startGame() {
    while(true) {
        moveSnake();
        printBoard();
    }
}

public void moveSnake() {
    System.out.println("Enter direction:");
    String direction=sc.next();
    int[] head=snake.get(0);
    int newRow=head[0],newCol=head[1];
    switch(direction) {
        case "L": newCol=head[1]-1;
                break;
        case "R": newCol=head[1]+1;
                break;
        case "T": newRow=head[0]-1;
                break;
        case "B": newRow=head[0]+1;
                break;
        default:System.out.println("Enter correct direction");
                return;
    }

    if(gameWon(newRow,newCol)) {
        System.out.println("Game over! your points is"+points);
        System.exit(0);
    }

      snakeMoveAndGrow(newRow,newCol);
   }


   //check snake pull itself or snake cross region
   public boolean gameWon(int newRow,int newCol) {
         if(newRow<0 || newRow>=5 || newCol<0 || newCol>=5) 
         return true;
            
         for(int[] parts:snake) {
            if(parts[0]==newRow && parts[1]==newCol)
            return true;
         }
         return false;
   }

   public void snakeMoveAndGrow(int newRow,int newCol) {
         if(board[newRow][newCol]==food) {     
            points++;
            System.out.println("Good Job! points:" +points);
            placeFood();
            snake.add(0,new int[]{newRow,newCol});  //add already snake to new row and col
         }
         else {      // if u move but not food remove last move add current move
            int[] tail=snake.remove(snake.size()-1);
            snake.add(0,new int[]{newRow,newCol});
            board[tail[0]][tail[1]]=' ';
         }
         updateBoard();
   }

   public void updateBoard() {
    for(int i=0;i<5;i++) {
        for(int j=0;j<5;j++) {
            if(board[i][j]!=food)
            board[i][j]=' ';
        }
    }

      for(int i=0;i<snake.size();i++) {    //only first part is head so i==0 is snake head;
        int[] part=snake.get(i);
        if(i==0)
        board[part[0]][part[1]]=snakeHead;
        else
        board[part[0]][part[1]]=snakeBody;
      }

   }


}