package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Player")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"Name", "sb","isComputer"})

public class Player{
	@XmlElement private String Name;
	@XmlElement private ScoreBoard sb;
	@XmlElement private boolean isComputer = false;
	
	
	public Player(String n){
		Name = n;
		sb = new ScoreBoard();
	}
	public Player(){
		
	}
	public String getName(){
		return Name;
	}
	public ScoreBoard getScoreboard(){
		return sb;
	}
	public void setName(String name){
		this.Name = name;
	}
	public void setIsComputer(){
		isComputer = true;
	}
	public boolean isAComputer(){
		return isComputer;
	}

}
