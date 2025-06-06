package game_console;

import java.util.*;

class SnakeFeedGame {
	
	char[][] board=new char[5][5];
	char head='@';
	char body='#';
	char food='O';
	int point=0;
	List<int[]> snake=new ArrayList<>();
	Scanner sc=new Scanner(System.in);
	
	public static void main(String[] args) {
		 SnakeFeedGame obj=new SnakeFeedGame();
		 
		 for(int i=0;i<5;i++) {
			 for(int j=0;j<5;j++)
				 obj.board[i][j]=' ';
		 }
		 
		
		 obj.init();
		 
	}

	private void init() {
		placesnakeition();
		placeFoodPosition();
		printBoard();
		startGame();
	}

	private void placesnakeition() {
		int min=0,max=4;
		
		int snakeRow=(int)(Math.random()*max+min-min);
		int snakeCol=(int)(Math.random()*max+min-min);
		board[snakeRow][snakeCol]=head;
		snake.add(new int[]{snakeRow,snakeCol});
	}

   private void placeFoodPosition() {
	   int min=0,max=4;
		
		int foodRow=(int)(Math.random()*max+min-min);
		int foodCol=(int)(Math.random()*max+min-min);
		
		if(board[foodRow][foodCol]==' ')
			board[foodRow][foodCol]=food; 
			
		else
			placeFoodPosition();
	}
   
    private void printBoard() {
    	
    	 System.out.println("-------");
		 for(int i=0;i<5;i++) {
			 System.out.print("|");
			 for(int j=0;j<5;j++)
				 System.out.print(board[i][j]);
			 System.out.print("|");
			 System.out.println();
		 }
		 System.out.println("-------");
	}

    private void startGame() {
		
		while(true) {
			moveSnake();
			printBoard();
		}
	}

	private void moveSnake() {
		int[] head=snake.get(0);
		int newRow=head[0];
		int newCol=head[1];
		System.out.println("Enter position(L/R/T/B):");
		char position=sc.next().charAt(0);
		
		switch(position) {
		  case 'L':newCol=newCol-1;
		  			break;
		  case 'R':newCol=newCol+1;
		  			break;
		  case 'T':newRow=newRow-1;
		  			break;
		  case 'B':newRow=newRow+1;
		  			break;
		}
		
		if(gameWon(newRow,newCol)) {
			System.out.println("Game over! you are point is:"+point);
			System.exit(0);
		}
		
		snakeGrow(newRow,newCol);
		
	}

	private boolean gameWon(int newRow, int newCol) {
		
		   if(newRow<0 && newRow>4 && newCol<0 && newRow>4)
			   return true;
		   
		   for(int[] part:snake)
			   if(part[0]==newRow && part[1]==newCol)
				   return true;
		   
		   return false;		   
	}

	private void snakeGrow(int newRow, int newCol) {
		if(board[newRow][newCol]==food) {
			point++;
			System.out.println("Your point is:"+point);
			placeFoodPosition();
			snake.add(0,new int[] {newRow,newCol});
		}else {
			int[] tail=snake.remove(snake.size()-1);
			snake.add(0,new int[] {newRow,newCol});
			board[tail[0]][tail[1]]=' ';
			
		}
		
		updateBoard();
	}

	private void updateBoard() {
		for(int i=0;i<5;i++) {
			for(int j=0;j<5;j++)
				if(board[i][j]!=food)
				board[i][j]=' ';
		}
		
		for(int i=0;i<snake.size();i++) {
			int[] parts=snake.get(i);
			if(i==0)
		    board[parts[0]][parts[1]]=head;
			else
			board[parts[0]][parts[1]]=body;	
		}
		
	}

	
}