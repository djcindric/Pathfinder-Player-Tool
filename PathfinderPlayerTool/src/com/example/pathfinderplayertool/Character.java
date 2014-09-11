package com.example.pathfinderplayertool;

import java.io.Serializable;
import java.util.ArrayList;

public class Character implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8141948373632578807L;
	public int ID;
	public int Level;
	public int Experience;
	public int Next;
	public int Age;
	public int Height;
	public int Weight;
	public String Name;
	public String Class;
	public String Race;
	public String Description;
	public String Alignment;
	public String Deity;
	public String Homeland;
	public String Size;
	public String Gender;
	public String Player;
	public String Hair;
	public String Eyes;
	public ArrayList<Weapon> Weapons;
	public ArrayList<Armor> Armors;
	public ArrayList<Spell> Spells;
	public ArrayList<Feat> Feats;
	public Ability Abilities;
	public Skill Skills;
	
	public Character(){
		
	}
	
	public Character(String name, int id){
		Name = name;
		ID = id;
		Level=9;
		Experience=1337;
		Next=2000;
		Class="Orc";
	}
	
	public void addWeapon(Weapon w){
		Weapons.add(w);
	}
	
	public void addArmor(Armor a){
		Armors.add(a);
	}
	
	public void removeWeapon(Weapon w){
		Weapons.remove(w);
	}
	
	public void removeArmor(Armor a){
		Armors.remove(a);
	}
	
	public ArrayList<Weapon> getWeapons(){
		return Weapons;
	}
	
	public ArrayList<Armor> getArmor(){
		return Armors;
	}
}
