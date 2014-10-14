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

public class CombatFragment extends Fragment {
	public String charName = "";
	public Character thisCharacter = null;
	public TextView tv = null;
	
	public CombatFragment (String s){
		charName = s;
	}
	
    @Override
    public View onCreateView(LayoutInflater inflater,
        ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.combat, container, false);
        
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
        
        return rootView;
    }
}
