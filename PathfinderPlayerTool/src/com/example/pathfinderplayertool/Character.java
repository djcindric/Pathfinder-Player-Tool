package com.example.pathfinderplayertool;

import java.io.Serializable;
import java.util.ArrayList;

public class Character implements Serializable{

	private static final long serialVersionUID = -7400878238089045378L;
	private int ID;
	private int Level = 1;
	private int Experience;
	private int Next;
	private int Age;
	private int Height;
	private int Weight;
	private int BaseHealth;
	private int Initiative = 10;
	private int BaseAttack = 20;
	private int dailySpellLimit = 0;
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
	private ArrayList<Spell> Spells = new ArrayList<Spell>();
	private ArrayList<Skill> Skills = new ArrayList<Skill>();
	private ArrayList<Feat> Feats;
	private Weapon MainHand = new Weapon();
	private Weapon OffHand = new Weapon();
	private Abilities Abilities = new Abilities();
	
	
	public Character(){
		
	}
	
	public Character(String name, int id){
		setName(name);
		setID(id);
		populateSkills();
	}
	
	public void populateSkills(){
		Skill acrobatics = new Skill("Acrobatics", "Dex");
		Skill appraise = new Skill("Appraise", "Int");
		Skill bluff = new Skill("Bluff", "Cha");
		Skill climb = new Skill("Climb", "Str");
		Skill craft = new Skill("Craft", "Int");
		Skill diplomacy = new Skill("Diplomacy", "Cha");
		Skill disable = new Skill("Disable Device", "Dex");
		Skill fly = new Skill("Fly", "Dex");
		Skill heal = new Skill("Heal", "Wis");
		Skill intimidate = new Skill("Intimidate", "Cha");
		Skill stealth = new Skill("Stealth", "Dex");
		Skill swim = new Skill("Swim", "Str");
		Skill umd = new Skill("Use Magic Device", "Cha");
		
		Skills.add(acrobatics);
		Skills.add(appraise);
		Skills.add(bluff);
		Skills.add(climb);
		Skills.add(craft);
		Skills.add(diplomacy);
		Skills.add(disable);
		Skills.add(fly);
		Skills.add(heal);
		Skills.add(intimidate);
		Skills.add(stealth);
		Skills.add(swim);
		Skills.add(umd);
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

	public int getInitiative() {
		return Initiative;
	}

	public void setInitiative(int initiative) {
		Initiative = initiative;
	}

	public int getBaseAttack() {
		return BaseAttack;
	}

	public void setBaseAttack(int baseAttack) {
		BaseAttack = baseAttack;
	}

	public Weapon getMainHand() {
		return MainHand;
	}

	public void setMainHand(Weapon mainHand) {
		MainHand = mainHand;
	}

	public Weapon getOffHand() {
		return OffHand;
	}

	public void setOffHand(Weapon offHand) {
		OffHand = offHand;
	}

	public int getDailySpellLimit() {
		return dailySpellLimit;
	}

	public void setDailySpellLimit(int dailySpellLimit) {
		this.dailySpellLimit = dailySpellLimit;
	}

	public Abilities getAbilities() {
		return Abilities;
	}

	public void setAbilities(Abilities abilities) {
		Abilities = abilities;
	}

	public ArrayList<Skill> getSkills() {
		return Skills;
	}

	public void setSkills(ArrayList<Skill> skills) {
		Skills = skills;
	}
}
