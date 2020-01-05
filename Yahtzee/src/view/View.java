package view;
import java.util.*;

import model.Dice;

public class View {
	Scanner scan = new Scanner(System.in);

	public int startupText(){
		System.out.println("Welcome! How many players are you?");
		int players = scan.nextInt();
		return players;
	}

	public int startupText2(){
		System.out.println("How many computer players do you want?");
		int computers = scan.nextInt();
		return computers;
	}

	public String getPlayerName(){
		scan.nextLine();		// fixing the state issue of the scanner.
		String name;
		System.out.println("What is the players name?");
		name = scan.nextLine();
		return name;
	}
	public void printDiceResults(Dice[] arr){
		String results = "";
		for (int i = 0; i < arr.length; i++){
			results = results+""+arr[i].getSum()+", ";
		}
		
		System.out.println(results);
		
	}
	public void playersTurn(String s){
		System.out.println("Its "+s+"'s turn to play");
	}

	public int SetupMenu(){
		System.out.println("Welcome! ");
		System.out.println("Press 1 to start a new game.");
		System.out.println("Press 2 to load a previous game. ");
		System.out.println("Press 0 to quit.");
		int choice = scan.nextInt();
		return choice;
	}
	public int saveDie(){
		System.out.println("Which die do you wish to save? Dice 1,2,3,4,5? Press 9 to return");
		int choice = scan.nextInt()-1;
		return choice;
	}
	public int unlockDie(){
		System.out.println("Which die do you wish to unlock? Dice 1,2,3,4,5? Press 9 to return");
		int choice = scan.nextInt()-1 ;
		return choice;
	}
	
	public int optionsMenu(){
		System.out.println("Options: ");
		System.out.println("Press 1 to continue");
		System.out.println("Press 2 to save");
		System.out.println("Press 0 to see the scoreboard");
		int choice = scan.nextInt();
		return choice;
	}
	public int rollOptions(int i){
		System.out.println(i+"/3 Rolls. Press 1 to roll, Press 2 to save dice, press 3 to unlock dice, press 0 to enter options menu.");
		int choice = scan.nextInt();
		return choice;
	}
	
	public String scoreable(int i){
		String[] str = new String[19];
		str[0] = "[0] Ones";
		str[1] = "[1] Two's";
		str[2] = "[2] Three's";
		str[3] = "[3] Fours";
		str[4] = "[4] Fives";
		str[5] = "[5] Sixes";
		str[6] = "[6] Upper score";
		str[7] = "[7] Bonus";
		str[8] = "[8] Pair";
		str[9] = "[9] Two pairs";
		str[10] = "[10] Three of a kind";
		str[11] = "[11] Four of a kind";
		str[12] = "[12] Full house";
		str[13] = "[13] Small Straight";
		str[14] = "[14] Large straight";
		str[15] = "[15] Chance";
		str[16] = "[16] Yahtzee";
		str[17] = "[17] Lower score";
		str[18] = "[18] Total";
		return str[i];
	}
	public int afterRollText(){
		System.out.println("Which do you wish to score? Write 99 to put 0 on a score.");
		int choice = scan.nextInt();
		return choice;
	}
	public void printAvailable(int arr[]){
		for (int i = 0; i < arr.length; i++){
			if(arr[i] == -1){
				System.out.println(scoreable(i));
			}
		}
	}
	public int zeroOption(){
		System.out.println("Which score do you wanna set to 0?");
		int choice = scan.nextInt();
		return choice;
	}
	public void printShortList(String name, int score){
		System.out.println("Player: "+name);
		System.out.println("Total score: "+score);
	}
	public void printDetailedList(String name, int score, int[] arr){
		System.out.println("Player: "+name);
		for(int i = 0; i < arr.length; i++){
			String scoreString = "";
			int value = 0;
			if (arr[i] == -1){
				value = 0;
			}
			else{ 
				value = arr[i];
			}
			scoreString += scoreable(i)+": "+value ;
			System.out.println(scoreString);
		}
		System.out.println("Total score: "+score);
	}
	public void printWinner(String Name, int Score){
		System.out.println("Congratulations "+Name+"!");
		System.out.println("You won with an impressive score of "+Score);
	}

}
