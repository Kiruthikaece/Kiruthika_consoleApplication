package game_console;

import java.util.*;
import java.util.Map.Entry;

public class SnakeAndLadder {

	int position=0;
	int dice=0;
	boolean gameWon=false;
	Scanner sc=new Scanner(System.in);
	
	Map<Integer,Integer> snake=new HashMap<>();
	Map<Integer,Integer> ladder=new HashMap<>();
	
	public static void main(String[] args) {
		SnakeAndLadder obj=new SnakeAndLadder();
		obj.initializeBoard();
		obj.startGame();
	}
	
	private void initializeBoard() {
		  snake.put(3,27);
		  snake.put(18,45);
		  snake.put(33,77);
		  snake.put(28,85);
		  snake.put(67,98);
		  
		  ladder.put(5,25);
		  ladder.put(15,52);
		  ladder.put(28,65);
		  ladder.put(45,85);
		  ladder.put(50,98);
		  
	}

	private void startGame() {
		while(!gameWon) {
		System.out.println("Press 'r' to roll the dice.");
	    String input = sc.next();
		throwDice(input);
		
		 if (position + dice <= 100) {
             checkSnakeOrLadder(position + dice);
         } else {
             System.out.println("You need an exact roll to win. Your current position: " + position);
         }

		checkGameWon();
		}
	}

	private void checkGameWon() {
		if(position==100) {
			gameWon=true;
			System.out.println("You won the match!");
		}
	}

	private void checkSnakeOrLadder(int dice) {
		 if(snake.containsValue(dice)) {
			 for (Map.Entry<Integer, Integer> entry : snake.entrySet()) {
				    if (entry.getValue().equals(dice)) {
				        int key = entry.getKey();
				        position = key;
				        break;
				    }
		 } 		
			 
	   System.out.println("Ohh no you are bitten by snake!.. you are current position"+position);
	  }else if(ladder.containsKey(dice)) {
		 position =ladder.get(dice);
		 System.out.println("Excellent! you found ladder ,now time for climb up!.. you are current position"+position);
	 } else {
		 position=dice;
		 System.out.println("you are now square!.. you are current position"+position);

	 }
	}
	private void throwDice(String msg) {
		
			if(msg.equals("r")) {
				int min=1,max=6;
				dice=(int)(Math.random()*6)+1;
				System.out.println("you are dice value"+dice);
			}
			 else {
	                System.out.println("Invalid input, please press 'r' to roll the dice.");
	            }
	}

	

}
