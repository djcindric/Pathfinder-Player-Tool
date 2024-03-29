package com.example.pathfinderplayertool;

import java.io.Serializable;

public class Abilities implements Serializable{
	private int strength=8;
	private int dexterity=8;
	private int constitution=8;
	private int intelligence=8;
	private int wisdom=8;
	private int charisma=8;
	
	public Abilities(){
		
	}
	
	public Abilities(int str, int dex, int con, int intel, int wis, int cha){
		setStrength(str);
		setDexterity(dex);
		setConstitution(con);
		setIntelligence(intel);
		setWisdom(wis);
		setCharisma(cha);
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public int getDexterity() {
		return dexterity;
	}

	public void setDexterity(int dexterity) {
		this.dexterity = dexterity;
	}

	public int getConstitution() {
		return constitution;
	}

	public void setConstitution(int constitution) {
		this.constitution = constitution;
	}

	public int getIntelligence() {
		return intelligence;
	}

	public void setIntelligence(int intelligence) {
		this.intelligence = intelligence;
	}

	public int getWisdom() {
		return wisdom;
	}

	public void setWisdom(int wisdom) {
		this.wisdom = wisdom;
	}

	public int getCharisma() {
		return charisma;
	}

	public void setCharisma(int charisma) {
		this.charisma = charisma;
	}

}
