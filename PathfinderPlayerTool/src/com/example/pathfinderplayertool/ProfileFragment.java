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

public class ProfileFragment extends Fragment {
	public String charName = "";
	public Character thisCharacter = null;
	public TextView tv = null;
	
	public ProfileFragment (String s){
		charName = s;
	}
	
    @Override
    public View onCreateView(LayoutInflater inflater,
        ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.character_profile, container, false);
        
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
        
        tv = (TextView) rootView.findViewById(R.id.str_value_text);
        tv.setText("" + thisCharacter.getAbilities().getStrength());

        tv = (TextView) rootView.findViewById(R.id.dex_value_text);
        tv.setText("" + thisCharacter.getAbilities().getDexterity());
        
        tv = (TextView) rootView.findViewById(R.id.con_value_text);
        tv.setText("" + thisCharacter.getAbilities().getConstitution());
        
        tv = (TextView) rootView.findViewById(R.id.int_value_text);
        tv.setText("" + thisCharacter.getAbilities().getIntelligence());
        
        tv = (TextView) rootView.findViewById(R.id.wis_value_text);
        tv.setText("" + thisCharacter.getAbilities().getWisdom());
        
        tv = (TextView) rootView.findViewById(R.id.cha_value_text);
        tv.setText("" + thisCharacter.getAbilities().getCharisma());
        
        tv = (TextView) rootView.findViewById(R.id.level_text);
        tv.setText("Level: " + thisCharacter.getLevel());
        
        return rootView;
    }
    
}
