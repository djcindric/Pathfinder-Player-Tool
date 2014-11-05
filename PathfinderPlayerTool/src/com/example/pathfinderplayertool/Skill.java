package com.example.pathfinderplayertool;

import java.io.Serializable;

public class Skill implements Serializable{

	private static final long serialVersionUID = -8354385479663275242L;
	private boolean isClassSkill = false;
	private String name;
	private String modifier;
	private int modifierBonus = 0;
	private int skillPoints= 0;
	
	public Skill(){
		
	}
	
	public Skill(String name, String modifier){
		this.name = name;
		this.modifier = modifier;
	}

	public boolean isClassSkill() {
		return isClassSkill;
	}

	public void setClassSkill(boolean isClassSkill) {
		this.isClassSkill = isClassSkill;
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

	public int getSkillPoints() {
		return skillPoints;
	}

	public void setSkillPoints(int skillPoints) {
		this.skillPoints = skillPoints;
	}
	
}
