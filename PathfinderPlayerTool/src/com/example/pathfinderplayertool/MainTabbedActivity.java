package com.example.pathfinderplayertool;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Random;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.view.Menu;
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
      
        //Change the action bar title
		ActionBar ab = getActionBar();
		ab.setTitle(thisCharacter.getName());
		ab.setSubtitle("Pathfinder Player Tool");
    }
    
    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_tabbed, menu);
		return true;
	}
    
    @Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.save_character) {
			saveCharacter();
		}
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
    
//    public void clickedName(final View v){
//    	
//    }
//    
//    public void clickedLevel(final View v){
//    	
//    }
//    
//    public void clickedExperience(View v){
//    	final EditText input = new EditText(this);
//    	new AlertDialog.Builder(this)
//        .setTitle("Add Experience")
//        .setMessage("Enter amount of experience to add")
//        .setView(input)
//        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int whichButton) {
//                Editable value = input.getText(); 
//                thisCharacter.setExperience(thisCharacter.getExperience() + Integer.parseInt(value.toString()));
//                TextView tv = (TextView) findViewById(R.id.charExperienceValue);
//                tv.setText("" + thisCharacter.getExperience());
//            }
//        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int whichButton) {
//            }
//        }).show();
//    }
//    
//    public void clickedNext(View v){
//    	Toast t = Toast.makeText(this, "Edit Next", Toast.LENGTH_SHORT);
//    	t.show();
//    }
//    
//    public void clickedLevelUp(View v){
//    	//Increment players level and display the new change
//    	thisCharacter.setLevel(thisCharacter.getLevel()+1);
//        TextView tv = (TextView) findViewById(R.id.charLevelValue);
//        tv.setText("" + thisCharacter.getLevel()); 
//        
//        Toast t = Toast.makeText(this, "Level Up", Toast.LENGTH_SHORT);
//    	t.show();
//    }
    
    public void saveCharacter(){
    	 try
         {
    		File f = new File(this.getFilesDir(), "/chars/" + message + ".ser");
            FileOutputStream fileOut = new FileOutputStream(f);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(thisCharacter);
            out.close();
            fileOut.close();
            
            Toast t = Toast.makeText(this, "Character Saved", Toast.LENGTH_SHORT);
        	t.show();
         }catch(IOException i)
         {
             i.printStackTrace();
         }
    }
    
    public void viewStrength(View v){
    	final EditText input = new EditText(this);
    	new AlertDialog.Builder(this)
        .setTitle("Edit Strength")
        .setMessage("Enter new strength value")
        .setView(input)
        .setPositiveButton("Save", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Editable value = input.getText(); 
                int val1 = Integer.parseInt(value.toString());
                thisCharacter.getSkills().setStrength(val1);
                TextView tv = (TextView) findViewById(R.id.str_value_text);
                tv.setText("" + val1);
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            }
        }).show();
    }
    
    public void viewDexterity(View v){
    	final EditText input = new EditText(this);
    	new AlertDialog.Builder(this)
        .setTitle("Edit Dexterity")
        .setMessage("Enter new dexterity value")
        .setView(input)
        .setPositiveButton("Save", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Editable value = input.getText(); 
                int val1 = Integer.parseInt(value.toString());
                thisCharacter.getSkills().setDexterity(val1);
                TextView tv = (TextView) findViewById(R.id.dex_value_text);
                tv.setText("" + val1);
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            }
        }).show();
    }

    public void viewConstitution(View v){
    	final EditText input = new EditText(this);
    	new AlertDialog.Builder(this)
        .setTitle("Edit Constitution")
        .setMessage("Enter new constitution value")
        .setView(input)
        .setPositiveButton("Save", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Editable value = input.getText(); 
                int val1 = Integer.parseInt(value.toString());
                thisCharacter.getSkills().setConstitution(val1);
                TextView tv = (TextView) findViewById(R.id.con_value_text);
                tv.setText("" + val1);
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            }
        }).show();
    }
    
    public void viewIntelligence(View v){
    	final EditText input = new EditText(this);
    	new AlertDialog.Builder(this)
        .setTitle("Edit Intelligence")
        .setMessage("Enter new intelligence value")
        .setView(input)
        .setPositiveButton("Save", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Editable value = input.getText(); 
                int val1 = Integer.parseInt(value.toString());
                thisCharacter.getSkills().setIntelligence(val1);
                TextView tv = (TextView) findViewById(R.id.int_value_text);
                tv.setText("" + val1);
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            }
        }).show();
    }
    
    public void viewWisdom(View v){
    	final EditText input = new EditText(this);
    	new AlertDialog.Builder(this)
        .setTitle("Edit Wisdom")
        .setMessage("Enter new wisdom value")
        .setView(input)
        .setPositiveButton("Save", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Editable value = input.getText(); 
                int val1 = Integer.parseInt(value.toString());
                thisCharacter.getSkills().setWisdom(val1);
                TextView tv = (TextView) findViewById(R.id.wis_value_text);
                tv.setText("" + val1);
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            }
        }).show();
    }
    
    public void viewCharisma(View v){
    	final EditText input = new EditText(this);
    	new AlertDialog.Builder(this)
        .setTitle("Edit Charisma")
        .setMessage("Enter new charisma value")
        .setView(input)
        .setPositiveButton("Save", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Editable value = input.getText(); 
                int val1 = Integer.parseInt(value.toString());
                thisCharacter.getSkills().setCharisma(val1);
                TextView tv = (TextView) findViewById(R.id.cha_value_text);
                tv.setText("" + val1);
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            }
        }).show();
    }
    
    public void changePhoto(View v){
    	Toast t = Toast.makeText(this, "Change your photo", Toast.LENGTH_SHORT);
    	t.show();
    }
    
    public void rollStrength(View v){
    	Random r = new Random();
    	int ran = r.nextInt(21 - 1) + 1;
    	Toast t = Toast.makeText(this, thisCharacter.getSkills().getStrength() + " + " + ran, Toast.LENGTH_SHORT);
    	t.show();
    }
    
    public void rollDexterity(View v){
    	Random r = new Random();
    	int ran = r.nextInt(21 - 1) + 1;
    	Toast t = Toast.makeText(this, thisCharacter.getSkills().getDexterity() + " + " + ran, Toast.LENGTH_SHORT);
    	t.show();
    	
    }
    
    public void rollConstitution(View v){
    	Random r = new Random();
    	int ran = r.nextInt(21 - 1) + 1;
    	Toast t = Toast.makeText(this, thisCharacter.getSkills().getConstitution() + " + " + ran, Toast.LENGTH_SHORT);
    	t.show();
    	
    }
    
    public void rollIntelligence(View v){
    	Random r = new Random();
    	int ran = r.nextInt(21 - 1) + 1;
    	Toast t = Toast.makeText(this, thisCharacter.getSkills().getIntelligence() + " + " + ran, Toast.LENGTH_SHORT);
    	t.show();
    	
    }
    
    public void rollWisdom(View v){
    	Random r = new Random();
    	int ran = r.nextInt(21 - 1) + 1;
    	Toast t = Toast.makeText(this, thisCharacter.getSkills().getWisdom() + " + " + ran, Toast.LENGTH_SHORT);
    	t.show();
    	
    }
    
    public void rollCharisma(View v){
    	Random r = new Random();
    	int ran = r.nextInt(21 - 1) + 1;
    	Toast t = Toast.makeText(this, thisCharacter.getSkills().getCharisma() + " + " + ran, Toast.LENGTH_SHORT);
    	t.show();
    	
    }
    
}