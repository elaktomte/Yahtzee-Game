package model;
import java.util.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "GameRules")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"dice", "players"})

public class GameRules {
	@XmlElement public ArrayList<Dice> dice = new ArrayList<Dice>();
	@XmlElement public ArrayList<Player> players = new ArrayList<Player>();
	


	public GameRules(int Dice, int Players, int C_players){			//Basic constructor for starting new games
		for (int i = 0; i < Dice; i++){
			dice.add(new Dice());
		}
		for (int i = 0; i<Players; i++){
			String name = "";
			players.add(new Player(name));
		}
		for (int i =0; i<C_players; i++){
			Player Comp = new Player("Computer"+i);
			Comp.setIsComputer();
			players.add(Comp);
		}
	}
	public GameRules(){ // empty constructor to enable XML handling. You cannot marshal and unmarshal without an empty constructor.
		
	}

	public Dice[] roll(){			//Rolls the dice and returns a dicearray.
		for (int i = 0; i<dice.size(); i++){
			dice.get(i).roll();
		}
		Dice[] dicearr = new Dice[5];
		for (int i = 0; i<5;i++){
			dicearr[i] = dice.get(i);
		}
		return dicearr;
	}
	public Player getPlayer(int i ){
		return players.get(i);
	}
	public void saveDie(int i){
		dice.get(i).save();
	}
	public void unlockDie(int i){
		dice.get(i).unlock();
	}
	public void unlockAll(){
		for (int i = 0; i<dice.size(); i++){
			dice.get(i).unlock();
		}
	}
	public String getPlayerName(int i){
		return players.get(i).getName();
	}

	public boolean isGameOver(int Turn){		// checks to see if the game is over, as there cannot be more than 15 turns in total.

		return Turn >15;
	}
	public void scoreZero(Player p, int i){		// Scoring 0 on an object in the scoreboard.
		p.getScoreboard().scores[i] = 0;
	}
	public void score(int i, int[] arr, int player){		//Calculates the scores on the certain scoreable object.
		/* arr[0] = ones
		 * arr[1] = twos
		 * arr[2] = threes
		 * arr[3] = fours
		 * arr[4] = fives
		 * arr[5] = sixes
		 */
		int sum = 0;
		if (i == 0){
			sum = arr[i];
			players.get(player).getScoreboard().scores[0] = sum;
		}
		if (i == 1){
			sum = arr[i]*2;
			players.get(player).getScoreboard().scores[1] = sum;
		}
		if (i == 2){
			sum = arr[i]*3;
			players.get(player).getScoreboard().scores[2] = sum;
		}
		if (i == 3){
			sum = arr[i]*4;
			players.get(player).getScoreboard().scores[3] = sum;
		}
		if (i == 4){
			sum = arr[i]*5;
			players.get(player).getScoreboard().scores[4] = sum;
		}
		if (i == 5){
			sum = arr[i]*6;
			players.get(player).getScoreboard().scores[5] = sum;
		}
		if (i == 8){
			if (arr[5] == 2){
				sum = 12;
			}
			else if ( arr[4] == 2){
				sum = 10;
			}
			else if ( arr[3] == 2){
				sum = 8;
			}
			else if ( arr[2] == 2){
				sum = 6;
			}
			else if ( arr[1] == 2){
				sum = 4;
			}
			else if ( arr[0] == 2){
				sum = 2;
			}
			players.get(player).getScoreboard().scores[8] = sum;
		}
		if (i == 9){
			if (arr[5] >= 2){
				sum = sum + 12;
			}
			if ( arr[4] >= 2){
				sum = sum + 10;
			}
			if ( arr[3] >= 2){
				sum = sum + 8;
			}
			if ( arr[2] >= 2){
				sum = sum + 6;
			}
			if ( arr[1] >= 2){
				sum = sum + 4;
			}
			if ( arr[0] >= 2){
				sum = sum + 2;
			}
			players.get(player).getScoreboard().scores[9] = sum;
		}
		if (i == 10){
			for(int j = 0; j < arr.length; j++){
				if (arr[j] == 3){
					sum = j+1;
				}
			}
			sum = sum * 3;
			players.get(player).getScoreboard().scores[10] = sum;
		}
		if (i == 11){
			for(int j = 0; j < arr.length; j++){
				if (arr[j] == 4){
					sum = j+1;
				}
			}
			sum = sum * 4;
			players.get(player).getScoreboard().scores[11] = sum;

		}
		if (i == 12){
			for (int j = 0; j < arr.length; j++){
				if (arr[j] == 2){
					sum = 2 *(j+1);
				}
			}
			for (int k = 0; k < arr.length; k++){
				if (arr[k]> 2){
					sum = sum + ((k+1)*3);
				}
			}
			players.get(player).getScoreboard().scores[12] = sum;	
		}
		if (i == 13){
			players.get(player).getScoreboard().scores[13] = 15;
		}
		if (i == 14){
			players.get(player).getScoreboard().scores[14] = 20;

		}
		if (i == 15){
			sum = arr[0]+2*arr[1]+3*arr[2]+4*arr[3]+5*arr[4]+6*arr[5];
			players.get(player).getScoreboard().scores[15] = sum;

		}
		if (i == 16){
			players.get(player).getScoreboard().scores[16] = 50;

		}

	}
	public Player whoIsWinner(){			//Calculates the winner for the game
		int Sum = 0;
		Player winner = players.get(0);
		for (int i = 0; i < players.size(); i++){
			if (players.get(i).getScoreboard().getCurrentScore() > Sum){
				Sum = players.get(i).getScoreboard().getCurrentScore();
				winner = players.get(i);
			}
		}
		return winner;
	}

}

