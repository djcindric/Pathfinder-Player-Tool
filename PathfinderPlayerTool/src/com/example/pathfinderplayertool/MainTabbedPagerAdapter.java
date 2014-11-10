package com.example.pathfinderplayertool;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class MainTabbedPagerAdapter extends FragmentStatePagerAdapter {
	int NUMTABS = 6; //The number of tabs in the main view
	
	public static int PROFILE 	= 0; 	
	public static int COMBAT 	= 1;
	public static int SKILLS 	= 2;
	public static int SPELLBOOK = 3;	//Static ints for referencing pages. 
	public static int INVENTORY = 4;	//Must be updated if page order changes
	public static int NOTES 	= 5;//
	
	public MainTabbedPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int i) {
		Fragment frag=null;
		
		switch(i){
			case 0:
				frag = new ProfileFragment(MainTabbedActivity.message);
				return frag;
			case 1:
				frag = new CombatFragment(MainTabbedActivity.message);
				return frag;
			case 2:
				frag = new SkillsFragment(MainTabbedActivity.message);
				return frag;
			case 3:
				frag = new SpellsFragment(MainTabbedActivity.message);
				return frag;
			case 4:
				frag = new InventoryFragment(MainTabbedActivity.message);
				return frag;
			default:
				frag = new NotesFragment(MainTabbedActivity.message);
				return frag;
		}
	}

	@Override
	public int getCount() {
		return NUMTABS;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		switch(position){
			case 0:
				return "Profile";
			case 1:
				return "Combat";
			case 2:
				return "Skills";
			case 3:
				return "Spells";
			case 4:
				return "Inventory";
			case 5:
				return "Notes";//
			default:
				return "Empty Page";
		}
	}
}