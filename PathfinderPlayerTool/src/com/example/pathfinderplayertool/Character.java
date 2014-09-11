package com.example.pathfinderplayertool;

import java.io.Serializable;
import java.util.ArrayList;

public class Character implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8141948373632578807L;
	public String Name;
	public int ID;
	public ArrayList<Weapon> Weapons;
	public ArrayList<Armor> Armor;
	
	public Character(){
		
	}
	
	public Character(String name, int id){
		Name = name;
		ID = id;
	}
	
	public void addWeapon(Weapon w){
		Weapons.add(w);
	}
	
	public void addArmor(Armor a){
		Armor.add(a);
	}
	
	public void removeWeapon(Weapon w){
		Weapons.remove(w);
	}
	
	public void removeArmor(Armor a){
		Armor.remove(a);
	}
	
	public ArrayList<Weapon> getWeapons(){
		return Weapons;
	}
	
	public ArrayList<Armor> getArmor(){
		return Armor;
	}
}
