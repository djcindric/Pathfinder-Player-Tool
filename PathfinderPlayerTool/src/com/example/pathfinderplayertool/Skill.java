package com.example.pathfinderplayertool;

import java.io.Serializable;

public class Skill implements Serializable{

	private static final long serialVersionUID = -8354385479663275242L;
	
	private boolean classSkill = false;	//Is it a class skill?
	private String name;			 	//The name of the skill
	private String modifier;			//The ability mod for the skill
	private int modifierBonus = 0; 		//Skill bonus based on ability
	private int skillPoints = 0; 		//Number of points put into the skill
	private int skillRank = 0;  		//Total rank, taking into account all modifiers (Ability, points, and class)
	private int classBonus = 0;			//Always either 0 or 3, depending on if box is checked
	
	public Skill(){
		
	}
	
	public Skill(String name, String modifier){
		this.name = name;
		this.modifier = modifier;
	}

	public boolean getClassSkill() {
		return classSkill;
	}

	public void setClassSkill(boolean classSkill) {
		this.classSkill = classSkill;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public int getModifierBonus() {
		return modifierBonus;
	}

	public void setModifierBonus(int modifierBonus) {
		this.modifierBonus = modifierBonus;
	}
	
	public void calculateSkillRank(){
		int calculatedRank = skillPoints + modifierBonus;
		if(classSkill){
			calculatedRank+=3;
		}
		skillRank = calculatedRank;
	}

	public int getSkillRank() {
		return skillRank;
	}

	public void setSkillRank(int skillRank) {
		this.skillRank = skillRank;
	}
	
	public int getSkillPoints() {
		return skillPoints;
	}

	public void setSkillPoints(int skillPoints) {
		this.skillPoints = skillPoints;
	}
	
	public void addClassBonus(){
		this.classBonus += 3;
	}
	
	public void removeClassBonus(){
		this.classBonus -= 3;
	}

	public int getClassBonus() {
		return classBonus;
	}

	public void setClassBonus(int classBonus) {
		this.classBonus = classBonus;
	}
}
