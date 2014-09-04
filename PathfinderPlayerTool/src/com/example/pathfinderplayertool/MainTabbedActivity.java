package com.example.pathfinderplayertool;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
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
}