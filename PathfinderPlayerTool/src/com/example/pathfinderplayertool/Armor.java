package com.example.pathfinderplayertool;

import java.util.ArrayList;

public class Armor {
	public String ArmorName;
	public ArrayList<String> Attributes;
	
	public Armor(String name, ArrayList<String> attributes){
		ArmorName = name;
		Attributes = attributes;
	}
	
	public String returnName(){
		return ArmorName;
	}
	
	public ArrayList<String> returnAttributes(){
		return Attributes;
	}
}
