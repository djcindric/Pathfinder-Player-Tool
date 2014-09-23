package com.example.pathfinderplayertool;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainTabbedActivity extends FragmentActivity {
	
    MainTabbedPagerAdapter mMainTabbedPagerAdapter;
    ViewPager mViewPager;
    public static String message = "";
    Character thisCharacter = null;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tabbed);
        
        //Receive the string (Character Name) Passed from the calling activity
		Intent intent = getIntent();
		message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
		//Change the action bar title
		ActionBar ab = getActionBar();
        ab.setTitle(message);
        ab.setSubtitle("Pathfinder Player Tool");
        
		mMainTabbedPagerAdapter = new MainTabbedPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mMainTabbedPagerAdapter);

        //Retrieve the character object by the name/ID
        try
        {
        	File file = new File(this.getFilesDir(), "/chars/" + message + ".ser");
	        FileInputStream fileIn = new FileInputStream(file);
	        ObjectInputStream in = new ObjectInputStream(fileIn);
	        thisCharacter = (Character) in.readObject();
	        in.close();
	        fileIn.close();
        }catch(IOException i){i.printStackTrace();

    	Toast ts = Toast.makeText(this, "IOException loading character", Toast.LENGTH_SHORT);
    	ts.show();}catch(ClassNotFoundException c){c.printStackTrace();}
        
        
    }
    
    @Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.load_profile) {
			mViewPager.setCurrentItem(MainTabbedPagerAdapter.PROFILE, true);
		}
		if (id == R.id.load_abilities) {
			mViewPager.setCurrentItem(MainTabbedPagerAdapter.ABILITY, true);
		}
		if (id == R.id.load_equipment) {
			mViewPager.setCurrentItem(MainTabbedPagerAdapter.EQUIPMENT, true);
		}
		if (id == R.id.load_skills) {
			mViewPager.setCurrentItem(MainTabbedPagerAdapter.SKILLS, true);
		}
		if (id == R.id.load_inventory) {
			mViewPager.setCurrentItem(MainTabbedPagerAdapter.INVENTORY, true);
		}
		if (id == R.id.load_feats) {
			mViewPager.setCurrentItem(MainTabbedPagerAdapter.FEATS, true);
		}
		if (id == R.id.load_spells) {
			mViewPager.setCurrentItem(MainTabbedPagerAdapter.SPELLS, true);
		}
		if (id == R.id.load_notes) {
			mViewPager.setCurrentItem(MainTabbedPagerAdapter.NOTES, true);
		}
		return super.onOptionsItemSelected(item);
	}
    
    public void clickedName(final View v){
    	
    }
    
    public void clickedLevel(final View v){
    	
    }
    
    public void clickedExperience(View v){
    	final EditText input = new EditText(this);
    	new AlertDialog.Builder(this)
        .setTitle("Add Experience")
        .setMessage("Enter amount of experience to add")
        .setView(input)
        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Editable value = input.getText(); 
                thisCharacter.setExperience(thisCharacter.getExperience() + Integer.parseInt(value.toString()));
                TextView tv = (TextView) findViewById(R.id.charExperienceValue);
                tv.setText(thisCharacter.getExperience());
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            }
        }).show();
    }
    
    public void clickedNext(View v){
    	Toast t = Toast.makeText(this, "Edit Next", Toast.LENGTH_SHORT);
    	t.show();
    }
    
    public void clickedLevelUp(View v){
    	//Increment players level and display the new change
    	thisCharacter.setLevel(thisCharacter.getLevel()+1);
        TextView tv = (TextView) findViewById(R.id.charLevelValue);
        tv.setText(thisCharacter.getLevel()); 
        
        Toast t = Toast.makeText(this, "Level Up", Toast.LENGTH_SHORT);
    	t.show();
    }
}