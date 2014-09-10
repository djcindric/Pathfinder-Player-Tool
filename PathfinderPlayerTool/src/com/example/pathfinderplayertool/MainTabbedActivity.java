package com.example.pathfinderplayertool;

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
    	Toast t = Toast.makeText(this, "Level up", Toast.LENGTH_SHORT);
    	t.show();
    }
}