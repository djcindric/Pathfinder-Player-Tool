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
import android.widget.TextView;

public class SkillsFragment extends Fragment {
	public String charName = "";
	public Character thisCharacter = null;
	public TextView tv = null;
	
	public SkillsFragment (String s){
		charName = s;
	}
	
    @Override
    public View onCreateView(LayoutInflater inflater,
        ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.skills_list, container, false);
        
        try
        {
        	File file = new File(MainActivity.fileDir, "/chars/" + charName + ".ser");
	        FileInputStream fileIn = new FileInputStream(file);
	        ObjectInputStream in = new ObjectInputStream(fileIn);
	        thisCharacter = (Character) in.readObject();
	        in.close();
	        fileIn.close();
        }catch(IOException i){i.printStackTrace();}
        catch(ClassNotFoundException c){c.printStackTrace();}
        
        tv = (TextView) rootView.findViewById(R.id.acrobaticsValue);
        tv.setText("" + (( thisCharacter.getSkills().getDexterity() - 10 ) / 2) );
        
        tv = (TextView) rootView.findViewById(R.id.appraiseValue);
        tv.setText("" + (( thisCharacter.getSkills().getIntelligence() - 10 ) / 2) );
        
        tv = (TextView) rootView.findViewById(R.id.bluffValue);
        tv.setText("" + (( thisCharacter.getSkills().getCharisma() - 10 ) / 2) );
        
        tv = (TextView) rootView.findViewById(R.id.climbValue);
        tv.setText("" + (( thisCharacter.getSkills().getStrength() - 10 ) / 2) );
        
        tv = (TextView) rootView.findViewById(R.id.craftValue);
        tv.setText("" + (( thisCharacter.getSkills().getIntelligence() - 10 ) / 2) );
        
        tv = (TextView) rootView.findViewById(R.id.diplomacyValue);
        tv.setText("" + (( thisCharacter.getSkills().getCharisma() - 10 ) / 2) );
        
        tv = (TextView) rootView.findViewById(R.id.disableValue);
        tv.setText("" + (( thisCharacter.getSkills().getDexterity() - 10 ) / 2) );
        
        tv = (TextView) rootView.findViewById(R.id.flyValue);
        tv.setText("" + (( thisCharacter.getSkills().getDexterity() - 10 ) / 2) );
        
        tv = (TextView) rootView.findViewById(R.id.healValue);
        tv.setText("" + (( thisCharacter.getSkills().getWisdom() - 10 ) / 2) );
        
        tv = (TextView) rootView.findViewById(R.id.intimidateValue);
        tv.setText("" + (( thisCharacter.getSkills().getCharisma() - 10 ) / 2) );
        
        tv = (TextView) rootView.findViewById(R.id.stealthValue);
        tv.setText("" + (( thisCharacter.getSkills().getDexterity() - 10 ) / 2) );
        
        tv = (TextView) rootView.findViewById(R.id.swimValue);
        tv.setText("" + (( thisCharacter.getSkills().getStrength() - 10 ) / 2) );
        
        tv = (TextView) rootView.findViewById(R.id.umdValue);
        tv.setText("" + (( thisCharacter.getSkills().getCharisma() - 10 ) / 2) );
        
        return rootView;
    }
}
