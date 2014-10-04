package com.example.pathfinderplayertool;

import java.io.Serializable;
import java.util.ArrayList;

public class Armor implements Serializable{
	private String ArmorName;
	private ArrayList<String> Attributes;
	
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
