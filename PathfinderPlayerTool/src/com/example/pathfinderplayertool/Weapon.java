package com.example.pathfinderplayertool;

import java.io.Serializable;
import java.util.ArrayList;

public class Weapon implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8318069707491895802L;
	private String WeaponName = "Unnamed Weapon";
	private ArrayList<String> Attributes = new ArrayList<String>();
	private String damage = "1d12";
	
	public Weapon(){
		
	}
	
	public Weapon(String name, ArrayList<String> attributes, String damage){
		WeaponName = name;
		Attributes = attributes;
		this.damage = damage;
	}
	
	public void setName(String weaponName){
		this.WeaponName = weaponName;
	}
	
	public String getName(){
		return WeaponName;
	}
	
	public ArrayList<String> getAttributes(){
		return Attributes;
	}
	
	public String getDamage() {
		return damage;
	}
	
	public void setDamage(String damage) {
		this.damage = damage;
	}
}
