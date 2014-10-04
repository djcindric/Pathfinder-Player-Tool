package com.example.pathfinderplayertool;

import java.io.Serializable;
import java.util.ArrayList;

public class Character implements Serializable{

	private static final long serialVersionUID = -7400878238089045378L;
	private int ID;
	private int Level;
	private int Experience;
	private int Next;
	private int Age;
	private int Height;
	private int Weight;
	private int BaseHealth;
	private String Name;
	private String CharClass;
	private String Race;
	private String Description;
	private String Alignment;
	private String Deity;
	private String Homeland;
	private String Size;
	private String Gender;
	private String Player;
	private String Hair;
	private String Eyes;
	private ArrayList<Weapon> Weapons;
	private ArrayList<Armor> Armors;
	private ArrayList<Spell> Spells;
	private ArrayList<Feat> Feats;
	private Ability Abilities;
	private Skill Skills = new Skill();
	
	public Character(){
		
	}
	
	public Character(String name, int id){
		setName(name);
		setID(id);
	}
	
	public void addWeapon(Weapon w){
		Weapons.add(w);
	}
	
	public void removeWeapon(Weapon w){
		Weapons.remove(w);
	}
	
	public void addArmor(Armor a){
		Armors.add(a);
	}
	
	public void removeArmor(Armor a){
		Armors.remove(a);
	}
	
	public void addSpell(Spell s){
		Spells.add(s);
	}
	
	public void removeSpell(Spell s){
		Spells.remove(s);
	}
	
	public void addFeat(Feat f){
		Feats.add(f);
	}
	
	public void removeFeat(Feat f){
		Feats.remove(f);
	}
	
	public ArrayList<Weapon> getWeapons(){
		return Weapons;
	}
	
	public ArrayList<Armor> getArmor(){
		return Armors;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getCharClass() {
		return CharClass;
	}

	public void setCharClass(String charClass) {
		CharClass = charClass;
	}

	public String getRace() {
		return Race;
	}

	public void setRace(String race) {
		Race = race;
	}

	public String getAlignment() {
		return Alignment;
	}

	public void setAlignment(String alignment) {
		Alignment = alignment;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getHomeland() {
		return Homeland;
	}

	public void setHomeland(String homeland) {
		Homeland = homeland;
	}

	public String getDeity() {
		return Deity;
	}

	public void setDeity(String deity) {
		Deity = deity;
	}

	public String getPlayer() {
		return Player;
	}

	public void setPlayer(String player) {
		Player = player;
	}

	public String getGender() {
		return Gender;
	}

	public void setGender(String gender) {
		Gender = gender;
	}

	public String getSize() {
		return Size;
	}

	public void setSize(String size) {
		Size = size;
	}

	public String getHair() {
		return Hair;
	}

	public void setHair(String hair) {
		Hair = hair;
	}

	public String getEyes() {
		return Eyes;
	}

	public void setEyes(String eyes) {
		Eyes = eyes;
	}

	public Ability getAbilities() {
		return Abilities;
	}

	public void setAbilities(Ability abilities) {
		Abilities = abilities;
	}

	public Skill getSkills() {
		return Skills;
	}

	public void setSkills(Skill skills) {
		Skills = skills;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getLevel() {
		return Level;
	}

	public void setLevel(int level) {
		Level = level;
	}

	public int getNext() {
		return Next;
	}

	public void setNext(int next) {
		Next = next;
	}

	public int getHeight() {
		return Height;
	}

	public void setHeight(int height) {
		Height = height;
	}

	public int getWeight() {
		return Weight;
	}

	public void setWeight(int weight) {
		Weight = weight;
	}

	public int getAge() {
		return Age;
	}

	public void setAge(int age) {
		Age = age;
	}

	public int getExperience() {
		return Experience;
	}

	public void setExperience(int experience) {
		Experience = experience;
	}

	public ArrayList<Spell> getSpells() {
		return Spells;
	}

	public void setSpells(ArrayList<Spell> spells) {
		Spells = spells;
	}

	public ArrayList<Feat> getFeats() {
		return Feats;
	}

	public void setFeats(ArrayList<Feat> feats) {
		Feats = feats;
	}

	public int getBaseHealth() {
		return BaseHealth;
	}

	public void setBaseHealth(int baseHealth) {
		BaseHealth = baseHealth;
	}
}
