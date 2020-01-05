package controller;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import javax.xml.bind.JAXBException;

import utility.Wrapper;
import model.*;
import view.*;


public class Controller {
	private View view = new View();
	private GameRules GR;
	private int Turn = 1;

	public void setup() throws FileNotFoundException, JAXBException{			//the function for setup when starting the application
		int selection = view.SetupMenu();
		if (selection == 1){
			int players = view.startupText();
			int computers = view.startupText2();
			GR = new GameRules(5,players,computers);
			for (int i = 0; i< GR.players.size(); i++){
				if (GR.getPlayer(i).isAComputer() == false){
					String name = view.getPlayerName();
					GR.getPlayer(i).setName(name);
				}
			}
			run();
		}
		else if (selection == 2){						// when user selects "load game"
			GR = Wrapper.loadGame(new File("Game.xml"));
			shortList();
			run();
		}
		else if (selection == 0){			// if user wants to quit. Might be unnecessary though.

		}
		else{				// if user enters anything but 1,2 or 0.
			setup();
		}
	}
	

	public void run() throws JAXBException{		// the main flow of the application.
		int player = 0;
		while (GR.isGameOver(Turn) == false){
			view.playersTurn(GR.getPlayerName(player));
			int rolls = 0;
			GR.unlockAll(); // unlocks all the dice for next player
			while (rolls < 3){
				int choice =0;
				if(GR.getPlayer(player).isAComputer()==true){
					choice = 1;
				}
				else{
				choice = view.rollOptions(rolls);
				}
				if (choice == 1){
					view.printDiceResults(GR.roll());
					rolls++;
				}
				else if (choice == 2){
					int selection = 0;
					while (selection < 5){
						selection = view.saveDie();
						if (selection > 4)
							break;
						GR.saveDie(selection);
					}
				}
				else if (choice == 3){
					int selection = 0;
					while (selection < 5){
						selection = view.unlockDie();
						if (selection > 4)
							break;
						GR.unlockDie(selection);
					}
				}
				else if ( choice == 0){
					int selection = view.optionsMenu();
					if (selection == 1){
						
					}
					if (selection == 2){
						Wrapper.saveGame(GR);
					}
					if (selection == 0){
						detailedList();
					}
					if (selection > 2){
						
					}
					
				}
			}
			int[] dice = getPossibleOptions(player);
			int score = 0;
			if (GR.players.get(player).isAComputer()){
				if ( Turn < 8)		// avoid trying to score the bonus field.
				score = Turn-1;
				else {
				score = Turn;
				}
			}
			else{
			score = view.afterRollText();
			}
			if (score == 99){
				int avail[] = GR.getPlayer(player).getScoreboard().scores.clone();
				view.printAvailable(avail);
				int choice = view.zeroOption();
				while (choice > 16){
					choice = view.zeroOption();
				}
				GR.scoreZero(GR.getPlayer(player), choice);
				
			}
			else if (score <16){
				GR.score(score, dice, player);
			}
			else {
				score = view.afterRollText();
				
			}
			if (player == GR.players.size()-1){
				player = 0;
				Turn++;
			}
			else{
				player++;
				
			}
		}
		Winner();
		detailedList();
		Wrapper.saveGame(GR);
	}
	
	
	public int[] getPossibleOptions(int p){		// a method for calculating all the possible scorable options.
		int ones= 0;
		int twos = 0;
		int threes = 0;
		int fours = 0;
		int fives = 0;
		int sixes = 0;

		for ( int i = 0; i<GR.dice.size(); i++){		
			if (GR.dice.get(i).getSum() == 1){
				ones++;
			}
			else if (GR.dice.get(i).getSum() == 2){
				twos++;
			}
			else if (GR.dice.get(i).getSum() == 3){
				threes++;
			}
			else if (GR.dice.get(i).getSum() == 4){
				fours++;
			}
			else if (GR.dice.get(i).getSum() == 5){
				fives++;
			}
			else if (GR.dice.get(i).getSum() == 6){
				sixes++;
			}
		}
		int dice[] = new int[6];
		dice[0] = ones;
		dice[1] = twos;
		dice[2] = threes;
		dice[3] = fours;
		dice[4] = fives;
		dice[5] = sixes;
		if (ones > 0 && GR.getPlayer(p).getScoreboard().scores[0] == -1){
			System.out.println(view.scoreable(0));
		}
		if (twos > 0 && GR.getPlayer(p).getScoreboard().scores[1] == -1){
			System.out.println(view.scoreable(1));
		}
		if (threes > 0 && GR.getPlayer(p).getScoreboard().scores[2] == -1){
			System.out.println(view.scoreable(2));
		}
		if (fours > 0 && GR.getPlayer(p).getScoreboard().scores[3] == -1){
			System.out.println(view.scoreable(3));
		}
		if (fives > 0 && GR.getPlayer(p).getScoreboard().scores[4] == -1){
			System.out.println(view.scoreable(4));
		}
		if (sixes > 0 && GR.getPlayer(p).getScoreboard().scores[5] == -1){
			System.out.println(view.scoreable(5));
		}
		if (ones > 1 || twos > 1 || threes >1 || fours > 1|| fives > 1 || sixes > 1){	// pair
			if (GR.getPlayer(p).getScoreboard().scores[8] == -1){
				System.out.println(view.scoreable(8));
			}
		}
		if (ones == 2 || twos == 2 || threes == 2 || fours == 2 || fives == 2  || sixes == 2){	// Two pairs
			int pairs = 0;
			for (int i = 0; i < dice.length; i++){
				if (dice[i] >1 ){
					pairs++;
				}
			}
			if (pairs == 2){
				if (GR.getPlayer(p).getScoreboard().scores[9] == -1){
					System.out.println(view.scoreable(9));
				}
			}
			
		}
		if (ones > 2 || twos > 2 || threes >2 || fours > 2|| fives > 2 || sixes > 2){	// three of a kind
			if (GR.getPlayer(p).getScoreboard().scores[10] == -1){
				System.out.println(view.scoreable(10));
			}
		}
		if (ones > 3 || twos > 3 || threes >3 || fours > 3|| fives > 3 || sixes > 3){	// Four of a kind
			if (GR.getPlayer(p).getScoreboard().scores[11] == -1){
				System.out.println(view.scoreable(11));
			}
		}
		if (ones == 2 || twos == 2 || threes == 2 || fours == 2 || fives == 2  || sixes == 2){	// Full House
			if (ones == 2 ){
				for (int i = 0; i<dice.length;i++){
					if(dice[i] == 3){
						if (GR.getPlayer(p).getScoreboard().scores[12] == -1){
							System.out.println(view.scoreable(12));
						}
					}
				}
			}
			if (twos == 2 ){
				for (int i = 0; i<dice.length;i++){
					if(dice[i] == 3){
						if (GR.getPlayer(p).getScoreboard().scores[12] == -1){
							System.out.println(view.scoreable(12));
						}
					}
				}
			}
			if (threes == 2 ){
				for (int i = 0; i<dice.length;i++){
					if(dice[i] == 3){
						if (GR.getPlayer(p).getScoreboard().scores[12] == -1){
							System.out.println(view.scoreable(12));
						}
					}
				}
			}
			if (fours == 2 ){
				for (int i = 0; i<dice.length;i++){
					if(dice[i] == 3){
						if (GR.getPlayer(p).getScoreboard().scores[12] == -1){
							System.out.println(view.scoreable(12));
						}
					}
				}
			}
			if (fives == 2 ){				
				for (int i = 0; i<dice.length;i++){
					if(dice[i] == 3){
						if (GR.getPlayer(p).getScoreboard().scores[12] == -1){
							System.out.println(view.scoreable(12));
						}
					}
				}
			}
			if (sixes == 2 ){
				
				for (int i = 0; i<dice.length;i++){
					if(dice[i] == 3){
						if (GR.getPlayer(p).getScoreboard().scores[12] == -1){
							System.out.println(view.scoreable(12));
						}
					}
				}

			}
		
		}
		if (ones == 1 && twos == 1 && threes == 1 && fours == 1 && fives == 1){	// small straight
			if (GR.getPlayer(p).getScoreboard().scores[13] == -1){
				System.out.println(view.scoreable(13));
			}
		}
		if (twos == 1 && threes == 1 && fours == 1 && fives == 1 && sixes == 1){	// Large straight
			if (GR.getPlayer(p).getScoreboard().scores[14] == -1){
				System.out.println(view.scoreable(14));
			}
		}

		if (GR.getPlayer(p).getScoreboard().scores[15] == -1){	// Chance
			System.out.println(view.scoreable(15));

		}
		if (ones > 4 || twos > 4 || threes >4 || fours > 4|| fives > 4 || sixes > 4){	// Yahtzee
			if (GR.getPlayer(p).getScoreboard().scores[16] == -1){
				System.out.println(view.scoreable(16));
			}
		}
		return dice;

	}
	
	public void shortList(){			// Prints the short list
		for(int i = 0; i< GR.players.size(); i++){
			String name = GR.getPlayerName(i);
			int Score = GR.getPlayer(i).getScoreboard().getCurrentScore();
			view.printShortList(name, Score);
		}
		
	}
	public void detailedList(){			// prints a detailed list and scoreboard
		for(int i = 0; i<GR.players.size(); i++){
			String name = GR.getPlayerName(i);
			int Score = GR.getPlayer(i).getScoreboard().getCurrentScore();
			int[] arr = GR.players.get(i).getScoreboard().scores.clone();
			view.printDetailedList(name, Score, arr);
		}
		
	}
	public void Winner(){		//prints who won,
		Player winner = GR.whoIsWinner();
		String name = winner.getName();
		int score = winner.getScoreboard().getCurrentScore();
		view.printWinner(name, score);
	}
}
