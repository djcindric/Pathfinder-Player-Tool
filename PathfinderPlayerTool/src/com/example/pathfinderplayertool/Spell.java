package com.example.pathfinderplayertool;

import java.io.Serializable;

public class Spell implements Serializable{

	private static final long serialVersionUID = -2933955882414027222L;
	private String name;
	private int level;
	
	public Spell(){
		
	}
	
	public Spell(String name, int level){
		this.setName(name);
		this.setLevel(level);
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
