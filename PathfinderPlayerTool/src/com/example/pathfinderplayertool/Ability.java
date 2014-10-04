package com.example.pathfinderplayertool;

import java.io.Serializable;

public class Ability implements Serializable{
	//Name of the ability
//	private String name;
	
	//Description of what the ability does
//	private String descr;
	
	//Type of ability that it is. Ex = Extraordinary. Sp = Spell-like. Su = Supernatural
//	private String type;
	
	/*
	 * Some abilities provide bonuses such as an increased attribute, damage reduction,
	 * or a spell. We will need a way to apply these bonuses to the character sheet if
	 * this ability is somehow added.
	 * 
	 * private Bonus bonuses;
	 */
	
	/*
	 * This Class could contain only the Ability scores (Str, Dex, Con, Int, Wis, Cha) represented as integers
	 * along with a get/set method.
	 * Pathfinder specifies other abilities as special abilities so we could implement a separate dynamic SpecialAbility
	 * class with the description, type, and bonuses
	 */
}
