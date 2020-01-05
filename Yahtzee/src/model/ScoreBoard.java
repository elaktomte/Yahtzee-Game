package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

//an Array of the possible options to score.
	/*
	[0] Ones
	[1] Two's
	[2] Three's
	[3] Fours
	[4] Fives
	[5] Sixes
	[6] Upper score
	[7] Bonus
	[8] Pair
	[9] Two pair
	[10] Three of a kind
	[11] Four of a kind
	[12] Full house
	[13] Small Straight
	[14] Large straight
	[15] Chance
	[16] Yahtzee
	[17] Lower score
	[18] Total
	
	*/
@XmlRootElement(name = "ScoreBoard")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"scores"})

public class ScoreBoard {
	@XmlElement public int[] scores = new int[19];
	
	public ScoreBoard(){		//having the start values as -1 since I cannot do booleans with null value.
		for (int i = 0; i< scores.length; i++){
			scores[i] = -1;			
		}
	}
	

	public void setUpperScore(){			// sets and calculates the upper score.
		int sum = 0;
		for ( int i = 0; i < 6; i++){
			if (scores[i] != -1)
			sum = sum+scores[i];
		}
		scores[6] = sum;
	}
	public void setLowerScore(){			//sets and calculates the lower score
		int sum = 0;
		for ( int i = 7; i < 17; i++){
			if (scores[i] != -1)
			sum = sum+scores[i];
		}
		scores[17] = sum;
	}
	public void setTotalScore(){		//calculates the total score
		scores[18] = scores[6]+scores[17];
	}
	public int getCurrentScore(){		//calculates the scores and returns them
		int score = 0;
		setUpperScore();
		scoreBonus();
		setLowerScore();
		setTotalScore();
		score = scores[18];
		return score;
	}
	public void scoreBonus(){			//Checks if you are able to get the bonus.
		if (scores[6] >= 63){
			scores[7] = 50;
		}
	}
	
}