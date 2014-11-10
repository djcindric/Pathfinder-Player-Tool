package com.example.pathfinderplayertool;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.TextView;

public class SkillsFragment extends Fragment implements OnCheckedChangeListener {
	public String charName = "";
	public TextView tv = null;
	
	public CheckBox acrobaticsCheck;
	public CheckBox appraiseCheck;
	public CheckBox bluffCheck;
	public CheckBox climbCheck;
	public CheckBox craftCheck;
	public CheckBox diplomacyCheck;
	public CheckBox disableCheck;
	public CheckBox flyCheck;
	public CheckBox healCheck;
	public CheckBox intimidateCheck;
	public CheckBox stealthCheck;
	public CheckBox swimCheck;
	public CheckBox umdCheck;
	
	public TextView acrobaticsText;
	public TextView appraiseText;
	public TextView bluffText;
	public TextView climbText;
	public TextView craftText;
	public TextView diplomacyText;
	public TextView disableText;
	public TextView flyText;
	public TextView healText;
	public TextView intimidateText;
	public TextView stealthText;
	public TextView swimText;
	public TextView umdText;
	
	public TextView acrobaticsPointsText;
	public TextView appraisePointsText;
	public TextView bluffPointsText;
	public TextView climbPointsText;
	public TextView craftPointsText;
	public TextView diplomacyPointsText;
	public TextView disablePointsText;
	public TextView flyPointsText;
	public TextView healPointsText;
	public TextView intimidatePointsText;
	public TextView stealthPointsText;
	public TextView swimPointsText;
	public TextView umdPointsText;
	
	public TextView acrobaticsRankText;
	public TextView appraiseRankText;
	public TextView bluffRankText;
	public TextView climbRankText;
	public TextView craftRankText;
	public TextView diplomacyRankText;
	public TextView disableRankText;
	public TextView flyRankText;
	public TextView healRankText;
	public TextView intimidateRankText;
	public TextView stealthRankText;
	public TextView swimRankText;
	public TextView umdRankText;
	
	public SkillsFragment (String s){
		charName = s;
	}
	
    @Override
    public View onCreateView(LayoutInflater inflater,
        ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.skills_wip, container, false);
		
        acrobaticsCheck = (CheckBox) rootView.findViewById(R.id.acrobaticsCheckBox);
		appraiseCheck = (CheckBox) rootView.findViewById(R.id.appraiseCheckBox); 
		bluffCheck = (CheckBox) rootView.findViewById(R.id.bluffCheckBox);
		climbCheck = (CheckBox) rootView.findViewById(R.id.climbCheckBox);
		craftCheck = (CheckBox) rootView.findViewById(R.id.craftCheckBox);
		diplomacyCheck = (CheckBox) rootView.findViewById(R.id.diplomacyCheckBox);
		disableCheck = (CheckBox) rootView.findViewById(R.id.disableCheckBox);
		flyCheck = (CheckBox) rootView.findViewById(R.id.flyCheckBox);
		healCheck = (CheckBox) rootView.findViewById(R.id.healCheckBox);
		intimidateCheck = (CheckBox) rootView.findViewById(R.id.intimidateCheckBox);
		stealthCheck = (CheckBox) rootView.findViewById(R.id.stealthCheckBox);
		swimCheck = (CheckBox) rootView.findViewById(R.id.swimCheckBox);
		umdCheck = (CheckBox) rootView.findViewById(R.id.umdCheckBox);
		
		acrobaticsText = (TextView) rootView.findViewById(R.id.acrobaticsModValue);
		appraiseText = (TextView) rootView.findViewById(R.id.appraiseModValue);
		bluffText = (TextView) rootView.findViewById(R.id.bluffModValue);
		climbText = (TextView) rootView.findViewById(R.id.climbModValue);
		craftText = (TextView) rootView.findViewById(R.id.craftModValue);
		diplomacyText = (TextView) rootView.findViewById(R.id.diplomacyModValue);
		disableText = (TextView) rootView.findViewById(R.id.disableModValue);
		flyText = (TextView) rootView.findViewById(R.id.flyModValue);
		healText = (TextView) rootView.findViewById(R.id.healModValue);
		intimidateText = (TextView) rootView.findViewById(R.id.intimidateModValue);
		stealthText = (TextView) rootView.findViewById(R.id.stealthModValue);
	    swimText = (TextView) rootView.findViewById(R.id.swimModValue);
		umdText = (TextView) rootView.findViewById(R.id.umdModValue);
		
		acrobaticsPointsText = (TextView) rootView.findViewById(R.id.acrobaticsPoints);
		appraisePointsText = (TextView) rootView.findViewById(R.id.appraisePoints);
		bluffPointsText = (TextView) rootView.findViewById(R.id.bluffPoints);
		climbPointsText = (TextView) rootView.findViewById(R.id.climbPoints);
		craftPointsText = (TextView) rootView.findViewById(R.id.craftPoints);
		diplomacyPointsText = (TextView) rootView.findViewById(R.id.diplomacyPoints);
		disablePointsText = (TextView) rootView.findViewById(R.id.disablePoints);
		flyPointsText = (TextView) rootView.findViewById(R.id.flyPoints);
		healPointsText = (TextView) rootView.findViewById(R.id.healPoints);
		intimidatePointsText = (TextView) rootView.findViewById(R.id.intimidatePoints);
		stealthPointsText = (TextView) rootView.findViewById(R.id.stealthPoints);
		swimPointsText = (TextView) rootView.findViewById(R.id.swimPoints);
		umdPointsText = (TextView) rootView.findViewById(R.id.umdPoints);
		
		acrobaticsRankText = (TextView) rootView.findViewById(R.id.acrobaticsRank);
		appraiseRankText = (TextView) rootView.findViewById(R.id.appraiseRank);
		bluffRankText = (TextView) rootView.findViewById(R.id.bluffRank);
		climbRankText = (TextView) rootView.findViewById(R.id.climbRank);
		craftRankText = (TextView) rootView.findViewById(R.id.craftRank);
		diplomacyRankText = (TextView) rootView.findViewById(R.id.diplomacyRank);
		disableRankText = (TextView) rootView.findViewById(R.id.disableRank);
		flyRankText = (TextView) rootView.findViewById(R.id.flyRank);
		healRankText = (TextView) rootView.findViewById(R.id.healRank);
		intimidateRankText = (TextView) rootView.findViewById(R.id.intimidateRank);
		stealthRankText = (TextView) rootView.findViewById(R.id.stealthRank);
		swimRankText = (TextView) rootView.findViewById(R.id.swimRank);
		umdRankText = (TextView) rootView.findViewById(R.id.umdRank);
		
		acrobaticsCheck.setChecked(MainTabbedActivity.thisCharacter.getSkillsMap().get("Acrobatics").getClassSkill());
        appraiseCheck.setChecked(MainTabbedActivity.thisCharacter.getSkillsMap().get("Appraise").getClassSkill());
		bluffCheck.setChecked(MainTabbedActivity.thisCharacter.getSkillsMap().get("Bluff").getClassSkill());
		climbCheck.setChecked(MainTabbedActivity.thisCharacter.getSkillsMap().get("Climb").getClassSkill());
		craftCheck.setChecked(MainTabbedActivity.thisCharacter.getSkillsMap().get("Craft").getClassSkill());
		diplomacyCheck.setChecked(MainTabbedActivity.thisCharacter.getSkillsMap().get("Diplomacy").getClassSkill());
		disableCheck.setChecked(MainTabbedActivity.thisCharacter.getSkillsMap().get("Disable").getClassSkill());
		flyCheck.setChecked(MainTabbedActivity.thisCharacter.getSkillsMap().get("Fly").getClassSkill());
		healCheck.setChecked(MainTabbedActivity.thisCharacter.getSkillsMap().get("Heal").getClassSkill());
		intimidateCheck.setChecked(MainTabbedActivity.thisCharacter.getSkillsMap().get("Intimidate").getClassSkill());
		stealthCheck.setChecked(MainTabbedActivity.thisCharacter.getSkillsMap().get("Stealth").getClassSkill());
		swimCheck.setChecked(MainTabbedActivity.thisCharacter.getSkillsMap().get("Swim").getClassSkill());
		umdCheck.setChecked(MainTabbedActivity.thisCharacter.getSkillsMap().get("Use Magic Device").getClassSkill());

		acrobaticsCheck.setOnCheckedChangeListener(this);
		appraiseCheck.setOnCheckedChangeListener(this);
		bluffCheck.setOnCheckedChangeListener(this);
		climbCheck.setOnCheckedChangeListener(this);
		craftCheck.setOnCheckedChangeListener(this);
		diplomacyCheck.setOnCheckedChangeListener(this);
		disableCheck.setOnCheckedChangeListener(this);
		flyCheck.setOnCheckedChangeListener(this);
		healCheck.setOnCheckedChangeListener(this);
		intimidateCheck.setOnCheckedChangeListener(this);
		stealthCheck.setOnCheckedChangeListener(this);
		swimCheck.setOnCheckedChangeListener(this);
		umdCheck.setOnCheckedChangeListener(this);
		
		acrobaticsPointsText.setOnClickListener(new Points_Clicker());
		appraisePointsText.setOnClickListener(new Points_Clicker());
		bluffPointsText.setOnClickListener(new Points_Clicker());
		climbPointsText.setOnClickListener(new Points_Clicker());
		craftPointsText.setOnClickListener(new Points_Clicker());
		diplomacyPointsText.setOnClickListener(new Points_Clicker());
		disablePointsText.setOnClickListener(new Points_Clicker());
		flyPointsText.setOnClickListener(new Points_Clicker());
		healPointsText.setOnClickListener(new Points_Clicker());
		intimidatePointsText.setOnClickListener(new Points_Clicker());
		stealthPointsText.setOnClickListener(new Points_Clicker());
		swimPointsText.setOnClickListener(new Points_Clicker());
		umdPointsText.setOnClickListener(new Points_Clicker());
		
		acrobaticsRankText.setOnClickListener(new Rank_Clicker());
		appraiseRankText.setOnClickListener(new Rank_Clicker());
		bluffRankText.setOnClickListener(new Rank_Clicker());
		climbRankText.setOnClickListener(new Rank_Clicker());
		craftRankText.setOnClickListener(new Rank_Clicker());
		diplomacyRankText.setOnClickListener(new Rank_Clicker());
		disableRankText.setOnClickListener(new Rank_Clicker());
		flyRankText.setOnClickListener(new Rank_Clicker());
		healRankText.setOnClickListener(new Rank_Clicker());
		intimidateRankText.setOnClickListener(new Rank_Clicker());
		stealthRankText.setOnClickListener(new Rank_Clicker());
		swimRankText.setOnClickListener(new Rank_Clicker());
		umdRankText.setOnClickListener(new Rank_Clicker());
		
        setValues();
        
        return rootView;
    }
    
    public void setValues(){
		int strMod = ((MainTabbedActivity.thisCharacter.getAbilities().getStrength() - 10 ) / 2);
		int dexMod = ((MainTabbedActivity.thisCharacter.getAbilities().getDexterity() - 10 ) / 2);
    	int conMod = ((MainTabbedActivity.thisCharacter.getAbilities().getConstitution() - 10 ) / 2);
		int intMod = ((MainTabbedActivity.thisCharacter.getAbilities().getIntelligence() - 10 ) / 2);
		int wisMod = ((MainTabbedActivity.thisCharacter.getAbilities().getWisdom() - 10 ) / 2);
		int chaMod = ((MainTabbedActivity.thisCharacter.getAbilities().getCharisma() - 10 ) / 2);
		
        acrobaticsText.setText("" + dexMod); 	MainTabbedActivity.thisCharacter.getSkillsMap().get("Acrobatics").setModifierBonus(dexMod);
		appraiseText.setText("" + intMod);		MainTabbedActivity.thisCharacter.getSkillsMap().get("Appraise").setModifierBonus(intMod);
		bluffText.setText("" + chaMod);			MainTabbedActivity.thisCharacter.getSkillsMap().get("Bluff").setModifierBonus(chaMod);
		climbText.setText("" + strMod);			MainTabbedActivity.thisCharacter.getSkillsMap().get("Climb").setModifierBonus(strMod);
		craftText.setText("" + intMod);			MainTabbedActivity.thisCharacter.getSkillsMap().get("Craft").setModifierBonus(intMod);
		diplomacyText.setText("" + chaMod);		MainTabbedActivity.thisCharacter.getSkillsMap().get("Diplomacy").setModifierBonus(chaMod);
		disableText.setText("" + dexMod);		MainTabbedActivity.thisCharacter.getSkillsMap().get("Disable").setModifierBonus(dexMod);
        flyText.setText("" + dexMod);			MainTabbedActivity.thisCharacter.getSkillsMap().get("Fly").setModifierBonus(dexMod);
		healText.setText("" + wisMod);			MainTabbedActivity.thisCharacter.getSkillsMap().get("Heal").setModifierBonus(wisMod);
		intimidateText.setText("" + chaMod);	MainTabbedActivity.thisCharacter.getSkillsMap().get("Intimidate").setModifierBonus(chaMod);
		stealthText.setText("" + dexMod);		MainTabbedActivity.thisCharacter.getSkillsMap().get("Stealth").setModifierBonus(dexMod);
		swimText.setText("" + strMod);			MainTabbedActivity.thisCharacter.getSkillsMap().get("Swim").setModifierBonus(strMod);
		umdText.setText("" + chaMod);			MainTabbedActivity.thisCharacter.getSkillsMap().get("Use Magic Device").setModifierBonus(chaMod);
		
		MainTabbedActivity.thisCharacter.getSkillsMap().get("Acrobatics").calculateSkillRank();
		MainTabbedActivity.thisCharacter.getSkillsMap().get("Appraise").calculateSkillRank();
		MainTabbedActivity.thisCharacter.getSkillsMap().get("Bluff").calculateSkillRank();
		MainTabbedActivity.thisCharacter.getSkillsMap().get("Climb").calculateSkillRank();
		MainTabbedActivity.thisCharacter.getSkillsMap().get("Craft").calculateSkillRank();
		MainTabbedActivity.thisCharacter.getSkillsMap().get("Diplomacy").calculateSkillRank();
		MainTabbedActivity.thisCharacter.getSkillsMap().get("Disable").calculateSkillRank();
        MainTabbedActivity.thisCharacter.getSkillsMap().get("Fly").calculateSkillRank();
		MainTabbedActivity.thisCharacter.getSkillsMap().get("Heal").calculateSkillRank();
		MainTabbedActivity.thisCharacter.getSkillsMap().get("Intimidate").calculateSkillRank();
		MainTabbedActivity.thisCharacter.getSkillsMap().get("Stealth").calculateSkillRank();
		MainTabbedActivity.thisCharacter.getSkillsMap().get("Swim").calculateSkillRank();
		MainTabbedActivity.thisCharacter.getSkillsMap().get("Use Magic Device").calculateSkillRank();
		
		acrobaticsPointsText.setText("" + MainTabbedActivity.thisCharacter.getSkillsMap().get("Acrobatics").getSkillPoints());
		appraisePointsText.setText("" + MainTabbedActivity.thisCharacter.getSkillsMap().get("Appraise").getSkillPoints());
		bluffPointsText.setText("" + MainTabbedActivity.thisCharacter.getSkillsMap().get("Bluff").getSkillPoints());
		climbPointsText.setText("" + MainTabbedActivity.thisCharacter.getSkillsMap().get("Climb").getSkillPoints());
		craftPointsText.setText("" + MainTabbedActivity.thisCharacter.getSkillsMap().get("Craft").getSkillPoints());
		diplomacyPointsText.setText("" + MainTabbedActivity.thisCharacter.getSkillsMap().get("Diplomacy").getSkillPoints());
		disablePointsText.setText("" + MainTabbedActivity.thisCharacter.getSkillsMap().get("Disable").getSkillPoints());
		flyPointsText.setText("" + MainTabbedActivity.thisCharacter.getSkillsMap().get("Fly").getSkillPoints());
		healPointsText.setText("" + MainTabbedActivity.thisCharacter.getSkillsMap().get("Heal").getSkillPoints());
		intimidatePointsText.setText("" + MainTabbedActivity.thisCharacter.getSkillsMap().get("Intimidate").getSkillPoints());
		stealthPointsText.setText("" + MainTabbedActivity.thisCharacter.getSkillsMap().get("Stealth").getSkillPoints());
		swimPointsText.setText("" + MainTabbedActivity.thisCharacter.getSkillsMap().get("Swim").getSkillPoints());
		umdPointsText.setText("" + MainTabbedActivity.thisCharacter.getSkillsMap().get("Use Magic Device").getSkillPoints());
		
		acrobaticsRankText.setText("" + MainTabbedActivity.thisCharacter.getSkillsMap().get("Acrobatics").getSkillRank());
		appraiseRankText.setText("" + MainTabbedActivity.thisCharacter.getSkillsMap().get("Appraise").getSkillRank());
		bluffRankText.setText("" + MainTabbedActivity.thisCharacter.getSkillsMap().get("Bluff").getSkillRank());
		climbRankText.setText("" + MainTabbedActivity.thisCharacter.getSkillsMap().get("Climb").getSkillRank());
		craftRankText.setText("" + MainTabbedActivity.thisCharacter.getSkillsMap().get("Craft").getSkillRank());
		diplomacyRankText.setText("" + MainTabbedActivity.thisCharacter.getSkillsMap().get("Diplomacy").getSkillRank());
		disableRankText.setText("" + MainTabbedActivity.thisCharacter.getSkillsMap().get("Disable").getSkillRank());
		flyRankText.setText("" + MainTabbedActivity.thisCharacter.getSkillsMap().get("Fly").getSkillRank());
		healRankText.setText("" + MainTabbedActivity.thisCharacter.getSkillsMap().get("Heal").getSkillRank());
		intimidateRankText.setText("" + MainTabbedActivity.thisCharacter.getSkillsMap().get("Intimidate").getSkillRank());
		stealthRankText.setText("" + MainTabbedActivity.thisCharacter.getSkillsMap().get("Stealth").getSkillRank());
		swimRankText.setText("" + MainTabbedActivity.thisCharacter.getSkillsMap().get("Swim").getSkillRank());
		umdRankText.setText("" + MainTabbedActivity.thisCharacter.getSkillsMap().get("Use Magic Device").getSkillRank());
		
    }
	
	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		switch(buttonView.getId()){
		
			case R.id.acrobaticsCheckBox:
				if(isChecked){
					MainTabbedActivity.thisCharacter.getSkillsMap().get("Acrobatics").setClassSkill(true);
					MainTabbedActivity.thisCharacter.getSkillsMap().get("Acrobatics").addClassBonus();
				}
				else{
					MainTabbedActivity.thisCharacter.getSkillsMap().get("Acrobatics").setClassSkill(false);
					MainTabbedActivity.thisCharacter.getSkillsMap().get("Acrobatics").removeClassBonus();
				}
			break;
			
			case R.id.appraiseCheckBox:
				if(isChecked){
					MainTabbedActivity.thisCharacter.getSkillsMap().get("Appraise").setClassSkill(true);
					MainTabbedActivity.thisCharacter.getSkillsMap().get("Appraise").addClassBonus();
				}
				else{
					MainTabbedActivity.thisCharacter.getSkillsMap().get("Appraise").setClassSkill(false);
					MainTabbedActivity.thisCharacter.getSkillsMap().get("Appraise").removeClassBonus();
				}
			break;
			
			case R.id.bluffCheckBox:
				if(isChecked){
					MainTabbedActivity.thisCharacter.getSkillsMap().get("Bluff").setClassSkill(true);
					MainTabbedActivity.thisCharacter.getSkillsMap().get("Bluff").addClassBonus();
				}
				else{
					MainTabbedActivity.thisCharacter.getSkillsMap().get("Bluff").setClassSkill(false);
					MainTabbedActivity.thisCharacter.getSkillsMap().get("Bluff").removeClassBonus();
				}
			break;
			
			case R.id.climbCheckBox:
				if(isChecked){
					MainTabbedActivity.thisCharacter.getSkillsMap().get("Climb").setClassSkill(true);
					MainTabbedActivity.thisCharacter.getSkillsMap().get("Climb").addClassBonus();
				}
				else{
					MainTabbedActivity.thisCharacter.getSkillsMap().get("Climb").setClassSkill(false);
					MainTabbedActivity.thisCharacter.getSkillsMap().get("Climb").removeClassBonus();
				}
			break;
			
			case R.id.craftCheckBox:
				if(isChecked){
					MainTabbedActivity.thisCharacter.getSkillsMap().get("Craft").setClassSkill(true);
					MainTabbedActivity.thisCharacter.getSkillsMap().get("Craft").addClassBonus();
				}
				else{
					MainTabbedActivity.thisCharacter.getSkillsMap().get("Craft").setClassSkill(false);
					MainTabbedActivity.thisCharacter.getSkillsMap().get("Craft").removeClassBonus();
				}
			break;
			
			case R.id.diplomacyCheckBox:
				if(isChecked){
					MainTabbedActivity.thisCharacter.getSkillsMap().get("Diplomacy").setClassSkill(true);
					MainTabbedActivity.thisCharacter.getSkillsMap().get("Diplomacy").addClassBonus();
				}
				else{
					MainTabbedActivity.thisCharacter.getSkillsMap().get("Diplomacy").setClassSkill(false);
					MainTabbedActivity.thisCharacter.getSkillsMap().get("Diplomacy").removeClassBonus();
				}
			break;
			
			case R.id.disableCheckBox:
				if(isChecked){
					MainTabbedActivity.thisCharacter.getSkillsMap().get("Disable").setClassSkill(true);
					MainTabbedActivity.thisCharacter.getSkillsMap().get("Disable").addClassBonus();
				}
				else{
					MainTabbedActivity.thisCharacter.getSkillsMap().get("Disable").setClassSkill(false);
					MainTabbedActivity.thisCharacter.getSkillsMap().get("Disable").removeClassBonus();
				}
			break;
			
			case R.id.flyCheckBox:
				if(isChecked){
					MainTabbedActivity.thisCharacter.getSkillsMap().get("Fly").setClassSkill(true);
					MainTabbedActivity.thisCharacter.getSkillsMap().get("Fly").addClassBonus();
				}
				else{
					MainTabbedActivity.thisCharacter.getSkillsMap().get("Fly").setClassSkill(false);
					MainTabbedActivity.thisCharacter.getSkillsMap().get("Fly").removeClassBonus();
				}
			break;
			
			case R.id.healCheckBox:
				if(isChecked){
					MainTabbedActivity.thisCharacter.getSkillsMap().get("Heal").setClassSkill(true);
					MainTabbedActivity.thisCharacter.getSkillsMap().get("Heal").addClassBonus();
				}
				else{
					MainTabbedActivity.thisCharacter.getSkillsMap().get("Heal").setClassSkill(false);
					MainTabbedActivity.thisCharacter.getSkillsMap().get("Heal").removeClassBonus();
				}
			break;
			
			case R.id.intimidateCheckBox:
				if(isChecked){
					MainTabbedActivity.thisCharacter.getSkillsMap().get("Intimidate").setClassSkill(true);
					MainTabbedActivity.thisCharacter.getSkillsMap().get("Intimidate").addClassBonus();
				}
				else{
					MainTabbedActivity.thisCharacter.getSkillsMap().get("Intimidate").setClassSkill(false);
					MainTabbedActivity.thisCharacter.getSkillsMap().get("Intimidate").removeClassBonus();
				}
			break;
			
			case R.id.stealthCheckBox:
				if(isChecked){
					MainTabbedActivity.thisCharacter.getSkillsMap().get("Stealth").setClassSkill(true);
					MainTabbedActivity.thisCharacter.getSkillsMap().get("Stealth").addClassBonus();
				}
				else{
					MainTabbedActivity.thisCharacter.getSkillsMap().get("Stealth").setClassSkill(false);
					MainTabbedActivity.thisCharacter.getSkillsMap().get("Stealth").removeClassBonus();
				}
			break;
			
			case R.id.swimCheckBox:
				if(isChecked){
					MainTabbedActivity.thisCharacter.getSkillsMap().get("Swim").setClassSkill(true);
					MainTabbedActivity.thisCharacter.getSkillsMap().get("Swim").addClassBonus();
				}
				else{
					MainTabbedActivity.thisCharacter.getSkillsMap().get("Swim").setClassSkill(false);
					MainTabbedActivity.thisCharacter.getSkillsMap().get("Swim").removeClassBonus();
				}
			break;
			
			case R.id.umdCheckBox:
				if(isChecked){
					MainTabbedActivity.thisCharacter.getSkillsMap().get("Use Magic Device").setClassSkill(true);
					MainTabbedActivity.thisCharacter.getSkillsMap().get("Use Magic Device").addClassBonus();
				}
				else{
					MainTabbedActivity.thisCharacter.getSkillsMap().get("Use Magic Device").setClassSkill(false);
					MainTabbedActivity.thisCharacter.getSkillsMap().get("Use Magic Device").removeClassBonus();
				}
			break;

		}
		setValues();
	}
	
	class Points_Clicker implements Button.OnClickListener	{

		@Override
		public void onClick(final View v) {
			final EditText input = new EditText(v.getContext());
	    	input.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
	    	new AlertDialog.Builder(v.getContext())
	        .setTitle("Edit Skill Points")
	        .setMessage("Enter the points you have in the skill")
	        .setView(input)
	        .setPositiveButton("Save", new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface dialog, int whichButton) {
	                Editable value = input.getText(); 
	                TextView f = (TextView) v;
	                f.setText(value);
	                
	                switch(v.getId()){
	        		
		    			case R.id.acrobaticsPoints:
		    				MainTabbedActivity.thisCharacter.getSkillsMap().get("Acrobatics").setSkillPoints(Integer.parseInt(value.toString()));
		    			break;
		    			
		    			case R.id.appraisePoints:
		    				MainTabbedActivity.thisCharacter.getSkillsMap().get("Appraise").setSkillPoints(Integer.parseInt(value.toString()));
		    			break;
		    			
		    			case R.id.bluffPoints:
		    				MainTabbedActivity.thisCharacter.getSkillsMap().get("Bluff").setSkillPoints(Integer.parseInt(value.toString()));
		    			break;
		    			
		    			case R.id.climbPoints:
		    				MainTabbedActivity.thisCharacter.getSkillsMap().get("Climb").setSkillPoints(Integer.parseInt(value.toString()));
		    			break;
		    			
		    			case R.id.craftPoints:
		    				MainTabbedActivity.thisCharacter.getSkillsMap().get("Craft").setSkillPoints(Integer.parseInt(value.toString()));
		    			break;
		    			
		    			case R.id.diplomacyPoints:
		    				MainTabbedActivity.thisCharacter.getSkillsMap().get("Diplomacy").setSkillPoints(Integer.parseInt(value.toString()));
		    			break;
		    			
		    			case R.id.disablePoints:
		    				MainTabbedActivity.thisCharacter.getSkillsMap().get("Disable").setSkillPoints(Integer.parseInt(value.toString()));
		    			break;
		    			
		    			case R.id.flyPoints:
		    				MainTabbedActivity.thisCharacter.getSkillsMap().get("Fly").setSkillPoints(Integer.parseInt(value.toString()));
		    			break;
		    			
		    			case R.id.healPoints:
		    				MainTabbedActivity.thisCharacter.getSkillsMap().get("Heal").setSkillPoints(Integer.parseInt(value.toString()));
		    			break;
		    			
		    			case R.id.intimidatePoints:
		    				MainTabbedActivity.thisCharacter.getSkillsMap().get("Intimidate").setSkillPoints(Integer.parseInt(value.toString()));
		    			break;
		    			
		    			case R.id.stealthPoints:
		    				MainTabbedActivity.thisCharacter.getSkillsMap().get("Stealth").setSkillPoints(Integer.parseInt(value.toString()));
		    			break;
		    			
		    			case R.id.swimPoints:
		    				MainTabbedActivity.thisCharacter.getSkillsMap().get("Swim").setSkillPoints(Integer.parseInt(value.toString()));
		    			break;
		    			
		    			case R.id.umdPoints:
		    				MainTabbedActivity.thisCharacter.getSkillsMap().get("Use Magic Device").setSkillPoints(Integer.parseInt(value.toString()));
		    			break;

	                }
	                setValues();
	            }
	        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface dialog, int whichButton) {
	            }
	        }).show();
		}
	}
}

class Rank_Clicker implements Button.OnClickListener	{

	@Override
	public void onClick(View v) {
		final Dialog popup = new Dialog(v.getContext());
		popup.setTitle("Skills");
		popup.setCancelable(true);
		popup.setContentView(R.layout.dialog_clicked_skills);
		TextView tv = (TextView) popup.findViewById(R.id.skillValue);
		
		switch(v.getId()){
		
			case R.id.acrobaticsCheckBox:
			break;
			
			case R.id.appraiseCheckBox:
			break;
			
			case R.id.bluffCheckBox:
			break;
			
			case R.id.climbCheckBox:
			break;
			
			case R.id.craftCheckBox:
			break;
			
			case R.id.diplomacyCheckBox:
			break;
			
			case R.id.disableCheckBox:
			break;
			
			case R.id.flyCheckBox:
			break;
			
			case R.id.healCheckBox:
			break;
			
			case R.id.intimidateCheckBox:
			break;
			
			case R.id.stealthCheckBox:
			break;
			
			case R.id.swimCheckBox:
			break;
			
			case R.id.umdCheckBox:
			break;

		}
		
		popup.show();
	}
}


