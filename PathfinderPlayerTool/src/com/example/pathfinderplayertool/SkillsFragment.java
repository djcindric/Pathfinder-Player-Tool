package com.example.pathfinderplayertool;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.TextView;

public class SkillsFragment extends Fragment {
	public String charName = "";
	public TextView tv = null;
	public CheckBox cb = null;
	
	public SkillsFragment (String s){
		charName = s;
	}
	
    @Override
    public View onCreateView(LayoutInflater inflater,
        ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.skills_list, container, false);
        
        setValues(rootView);
        
        return rootView;
    }
    
    public void setValues(View rootView){
    	tv = (TextView) rootView.findViewById(R.id.acrobaticsValue);
        tv.setText("" + (( MainTabbedActivity.thisCharacter.getAbilities().getDexterity() - 10 ) / 2) );
        
        tv = (TextView) rootView.findViewById(R.id.appraiseValue);
        tv.setText("" + (( MainTabbedActivity.thisCharacter.getAbilities().getIntelligence() - 10 ) / 2) );
        
        tv = (TextView) rootView.findViewById(R.id.bluffValue);
        tv.setText("" + (( MainTabbedActivity.thisCharacter.getAbilities().getCharisma() - 10 ) / 2) );
        
        tv = (TextView) rootView.findViewById(R.id.climbValue);
        tv.setText("" + (( MainTabbedActivity.thisCharacter.getAbilities().getStrength() - 10 ) / 2) );
        
        tv = (TextView) rootView.findViewById(R.id.craftValue);
        tv.setText("" + (( MainTabbedActivity.thisCharacter.getAbilities().getIntelligence() - 10 ) / 2) );
        
        tv = (TextView) rootView.findViewById(R.id.diplomacyValue);
        tv.setText("" + (( MainTabbedActivity.thisCharacter.getAbilities().getCharisma() - 10 ) / 2) );
        
        tv = (TextView) rootView.findViewById(R.id.disableValue);
        tv.setText("" + (( MainTabbedActivity.thisCharacter.getAbilities().getDexterity() - 10 ) / 2) );
        
        tv = (TextView) rootView.findViewById(R.id.flyValue);
        tv.setText("" + (( MainTabbedActivity.thisCharacter.getAbilities().getDexterity() - 10 ) / 2) );
        
        tv = (TextView) rootView.findViewById(R.id.healValue);
        tv.setText("" + (( MainTabbedActivity.thisCharacter.getAbilities().getWisdom() - 10 ) / 2) );
        
        tv = (TextView) rootView.findViewById(R.id.intimidateValue);
        tv.setText("" + (( MainTabbedActivity.thisCharacter.getAbilities().getCharisma() - 10 ) / 2) );
        
        tv = (TextView) rootView.findViewById(R.id.stealthValue);
        tv.setText("" + (( MainTabbedActivity.thisCharacter.getAbilities().getDexterity() - 10 ) / 2) );
        
        tv = (TextView) rootView.findViewById(R.id.swimValue);
        tv.setText("" + (( MainTabbedActivity.thisCharacter.getAbilities().getStrength() - 10 ) / 2) );
        
        tv = (TextView) rootView.findViewById(R.id.umdValue);
        tv.setText("" + (( MainTabbedActivity.thisCharacter.getAbilities().getCharisma() - 10 ) / 2) );
        
        cb = (CheckBox) rootView.findViewById(R.id.acrobaticsCheckBox);
        cb.setOnClickListener(new OnClickListener() {public void onClick(View v){if(cb.isChecked()){MainTabbedActivity.thisCharacter.getSkills().get(0).setClassSkill(true); } else{MainTabbedActivity.thisCharacter.getSkills().get(0).setClassSkill(false);}}});
        cb.setChecked(MainTabbedActivity.thisCharacter.getSkills().get(0).isClassSkill());
        
        cb = (CheckBox) rootView.findViewById(R.id.appraiseCheckBox);
        cb.setOnClickListener(new OnClickListener() {public void onClick(View v){if(cb.isChecked()){MainTabbedActivity.thisCharacter.getSkills().get(1).setClassSkill(true); } else{MainTabbedActivity.thisCharacter.getSkills().get(1).setClassSkill(false);}}});
        cb.setChecked(MainTabbedActivity.thisCharacter.getSkills().get(1).isClassSkill());
        
        cb = (CheckBox) rootView.findViewById(R.id.bluffCheckBox);
        cb.setOnClickListener(new OnClickListener() {public void onClick(View v){if(cb.isChecked()){MainTabbedActivity.thisCharacter.getSkills().get(2).setClassSkill(true); } else{MainTabbedActivity.thisCharacter.getSkills().get(2).setClassSkill(false);}}});
        cb.setChecked(MainTabbedActivity.thisCharacter.getSkills().get(2).isClassSkill());
    }
}
