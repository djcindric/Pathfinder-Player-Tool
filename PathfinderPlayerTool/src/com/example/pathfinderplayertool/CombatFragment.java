package com.example.pathfinderplayertool;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class CombatFragment extends Fragment {
	public String charName = "";
	public TextView tv = null;
	
	public CombatFragment (String s){
		charName = s;
	}
	
    @Override
    public View onCreateView(LayoutInflater inflater,
        ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.combat, container, false);
        
        tv = (TextView) rootView.findViewById(R.id.bab_val);
        tv.setText("" + MainTabbedActivity.thisCharacter.getBaseAttack());
        
        tv = (TextView) rootView.findViewById(R.id.init_val);
        tv.setText("" + MainTabbedActivity.thisCharacter.getInitiative());
        
        tv = (TextView) rootView.findViewById(R.id.cmb_val);
        tv.setText("" + ( (MainTabbedActivity.thisCharacter.getAbilities().getStrength() -10)/2));
        
        tv = (TextView) rootView.findViewById(R.id.melee_val);
        tv.setText("" + ( (MainTabbedActivity.thisCharacter.getAbilities().getStrength() -10)/2));
        
        tv = (TextView) rootView.findViewById(R.id.ranged_val);
        tv.setText("" + ( (MainTabbedActivity.thisCharacter.getAbilities().getDexterity() -10)/2));
        
        tv = (TextView) rootView.findViewById(R.id.main_weapon_name);
        tv.setText("" + MainTabbedActivity.thisCharacter.getMainHand().getName());
        
        tv = (TextView) rootView.findViewById(R.id.off_weapon_name);
        tv.setText("" + MainTabbedActivity.thisCharacter.getOffHand().getName());
        
        tv = (TextView) rootView.findViewById(R.id.main_weapon_damage_value);
        tv.setText("" + MainTabbedActivity.thisCharacter.getMainHand().getDamage());
        
        tv = (TextView) rootView.findViewById(R.id.off_weapon_damage_value);
        tv.setText("" + MainTabbedActivity.thisCharacter.getOffHand().getDamage());
        
        return rootView;
    }
}
