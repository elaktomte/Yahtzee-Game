package model;
import java.util.*;

public class Dice {
	public int sum;
	public boolean saved = false;
	
	public int roll(){		// rolls the die
		if (saved == false){
			Random rand = new Random();
			sum = rand.nextInt(6)+1;
		}
		return sum;	
	}
	public boolean isSaved(){
		return saved;
	}
	public void save(){
		saved = true;
	}
	public void unlock(){
		saved = false;
	}
	public int getSum(){
		return sum;
	}
	
}