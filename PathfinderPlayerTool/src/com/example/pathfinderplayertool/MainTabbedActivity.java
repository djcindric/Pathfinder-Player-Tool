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
		Toast t = Toast.makeText(this, message, Toast.LENGTH_SHORT);
		t.show();
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
    
    public void clickedName(final View v){
    	final EditText input = new EditText(this);
    	new AlertDialog.Builder(this)
        .setTitle("Change Name")
        .setMessage("Enter new name")
        .setView(input)
        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Editable value = input.getText(); 
                TextView tv = (TextView) findViewById(R.id.charName);
                tv.setText(value);
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            }
        }).show();
    }
    
    public void clickedLevel(final View v){
    	final EditText input = new EditText(this);
    	new AlertDialog.Builder(this)
        .setTitle("Change Level")
        .setMessage("Enter new level")
        .setView(input)
        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Editable value = input.getText(); 
                TextView tv = (TextView) findViewById(R.id.charLevelValue);
                tv.setText(value);
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            }
        }).show();
    }
    
    public void clickedExperience(View v){
    	Toast t = Toast.makeText(this, "Edit Experience", Toast.LENGTH_SHORT);
    	t.show();
    }
    
    public void clickedNext(View v){
    	Toast t = Toast.makeText(this, "Edit Next", Toast.LENGTH_SHORT);
    	t.show();
    }
    
    public void clickedLevelUp(View v){
    	Toast t = Toast.makeText(this, "Level Up", Toast.LENGTH_SHORT);
    	t.show();
    	
    	TextView tv;
        tv = (TextView) findViewById(R.id.charLevelValue);
        tv.setText("" + thisCharacter.Level);
        tv = (TextView) findViewById(R.id.charClassValue);
        tv.setText(thisCharacter.Class);
        tv = (TextView) findViewById(R.id.charExperienceValue);
        tv.setText("" + thisCharacter.Experience);
        tv = (TextView) findViewById(R.id.charNextValue);
        tv.setText("" + thisCharacter.Next);
        tv = (TextView) findViewById(R.id.charName);
        tv.setText("" + thisCharacter.Name);
    }
}