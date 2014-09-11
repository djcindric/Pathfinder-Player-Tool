package com.example.pathfinderplayertool;

import java.util.ArrayList;

public class Weapon {
	public String WeaponName;
	public ArrayList<String> Attributes;
	
	public Weapon(String name, ArrayList<String> attributes){
		WeaponName = name;
		Attributes = attributes;
	}
	
	public String returnName(){
		return WeaponName;
	}
	
	public ArrayList<String> returnAttributes(){
		return Attributes;
	}
}
