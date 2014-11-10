package com.example.pathfinderplayertool;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileFragment extends Fragment {
	public String charName = "";
	public TextView tv = null;
	
	public ProfileFragment (String s){
		charName = s;
	}
	
    @Override
    public View onCreateView(LayoutInflater inflater,
        ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.character_profile_wip, container, false);
        
        tv = (TextView) rootView.findViewById(R.id.str_value_text);
        tv.setText("" + MainTabbedActivity.thisCharacter.getAbilities().getStrength());

        tv = (TextView) rootView.findViewById(R.id.dex_value_text);
        tv.setText("" + MainTabbedActivity.thisCharacter.getAbilities().getDexterity());
        
        tv = (TextView) rootView.findViewById(R.id.con_value_text);
        tv.setText("" + MainTabbedActivity.thisCharacter.getAbilities().getConstitution());
        
        tv = (TextView) rootView.findViewById(R.id.int_value_text);
        tv.setText("" + MainTabbedActivity.thisCharacter.getAbilities().getIntelligence());
        
        tv = (TextView) rootView.findViewById(R.id.wis_value_text);
        tv.setText("" + MainTabbedActivity.thisCharacter.getAbilities().getWisdom());
        
        tv = (TextView) rootView.findViewById(R.id.cha_value_text);
        tv.setText("" + MainTabbedActivity.thisCharacter.getAbilities().getCharisma());
        
        tv = (TextView) rootView.findViewById(R.id.experience_value_text);
        tv.setText("" + MainTabbedActivity.thisCharacter.getExperience());
        
        tv = (TextView) rootView.findViewById(R.id.next_value_text);
        tv.setText("" + MainTabbedActivity.thisCharacter.getNext());
        
        tv = (TextView) rootView.findViewById(R.id.hp_text);
        tv.setText("Health: " + MainTabbedActivity.thisCharacter.getBaseHealth());
        
        tv = (TextView) rootView.findViewById(R.id.ac_text);
        tv.setText("Armor: " + MainTabbedActivity.thisCharacter.getArmorClass());
        
        tv = (TextView) rootView.findViewById(R.id.level_text);
        tv.setText("Level: " + MainTabbedActivity.thisCharacter.getLevel());
        
        tv = (TextView) rootView.findViewById(R.id.race_text);
        tv.setText("Race: " + MainTabbedActivity.thisCharacter.getRace());
        
        tv = (TextView) rootView.findViewById(R.id.class_text);
        tv.setText("Class: " + MainTabbedActivity.thisCharacter.getCharClass());
        
        tv = (TextView) rootView.findViewById(R.id.alignment_text);
        tv.setText("Alignment: " + MainTabbedActivity.thisCharacter.getAlignment());
        
        if(!MainTabbedActivity.thisCharacter.getPicturePath().equals("")){
        	ImageView iv = (ImageView) rootView.findViewById(R.id.picture_view);
        	Bitmap myBitmap = BitmapFactory.decodeFile(MainTabbedActivity.thisCharacter.getPicturePath());
        	iv.setImageBitmap(myBitmap);
        }
        
        return rootView;
    }
    
}
