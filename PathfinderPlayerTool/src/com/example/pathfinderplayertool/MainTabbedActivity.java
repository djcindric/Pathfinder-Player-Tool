package com.example.pathfinderplayertool;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainTabbedActivity extends FragmentActivity {
	
	private static int RESULT_LOAD_IMAGE = 144;
	public int REQUEST_ENABLE_BT = 1337;
	
    MainTabbedPagerAdapter mMainTabbedPagerAdapter;
    ViewPager mViewPager;
    public static String message = "";
    public static Character thisCharacter = null;
    public BluetoothAdapter mBluetoothAdapter;
	final Handler mHandler = new Handler();
	public ArrayAdapter<String> bluetoothArrayAdapter;
	public UUID MY_UUID;
	public File theDirectory;
	public static File theFullFile;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tabbed);
        
        MY_UUID = UUID.fromString("543c05d0-5a54-11e4-8ed6-0800200c9a66");
        theDirectory = this.getFilesDir();
        //Receive the string (Character Name) Passed from the calling activity
		Intent intent = getIntent();
		message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
		
		mMainTabbedPagerAdapter = new MainTabbedPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mMainTabbedPagerAdapter);
        
        PagerTabStrip strip = (PagerTabStrip) findViewById(R.id.pager_tab_strip);
        strip.setTabIndicatorColor(0xB30000);
        strip.setDrawFullUnderline(true);
        
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        //Retrieve the character object by the name/ID
        try
        {
        	theFullFile = new File(this.getFilesDir(), "/chars/" + message + ".ser");
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
		if(id == R.id.transfer_character){
			transferCharacter();
		}
		if (id == R.id.load_profile) {
			mViewPager.setCurrentItem(MainTabbedPagerAdapter.PROFILE, true);
		}
		if(id == R.id.load_combat){
			mViewPager.setCurrentItem(MainTabbedPagerAdapter.COMBAT, true);
		}
		if (id == R.id.load_skills) {
			mViewPager.setCurrentItem(MainTabbedPagerAdapter.SKILLS, true);
		}
		if (id == R.id.load_inventory) {
			mViewPager.setCurrentItem(MainTabbedPagerAdapter.INVENTORY, true);
		}
		if (id == R.id.load_spells) {
			mViewPager.setCurrentItem(MainTabbedPagerAdapter.SPELLBOOK, true);
		}
		if (id == R.id.load_notes) {
			mViewPager.setCurrentItem(MainTabbedPagerAdapter.NOTES, true);
		}
		return super.onOptionsItemSelected(item);
	}
    
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
    	 mViewPager.setAdapter(mMainTabbedPagerAdapter);
    }
    
    public void viewStrength(View v){
    	final EditText input = new EditText(this);
    	input.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
    	new AlertDialog.Builder(this)
        .setTitle("Edit Strength")
        .setMessage("Enter new strength value")
        .setView(input)
        .setPositiveButton("Save", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Editable value = input.getText(); 
                if(!value.toString().equals("")){
                	int val1 = Integer.parseInt(value.toString());
                    thisCharacter.getAbilities().setStrength(val1);
                    TextView tv = (TextView) findViewById(R.id.str_value_text);
                    tv.setText("" + val1);
                }
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            }
        }).show();
    }
    
    public void viewDexterity(View v){
    	final EditText input = new EditText(this);
    	input.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
    	new AlertDialog.Builder(this)
        .setTitle("Edit Dexterity")
        .setMessage("Enter new dexterity value")
        .setView(input)
        .setPositiveButton("Save", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Editable value = input.getText(); 
                if(!value.toString().equals("")){
                	int val1 = Integer.parseInt(value.toString());
	                thisCharacter.getAbilities().setDexterity(val1);
	                TextView tv = (TextView) findViewById(R.id.dex_value_text);
	                tv.setText("" + val1);
                }
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            }
        }).show();
    }

    public void viewConstitution(View v){
    	final EditText input = new EditText(this);
    	input.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
    	new AlertDialog.Builder(this)
        .setTitle("Edit Constitution")
        .setMessage("Enter new constitution value")
        .setView(input)
        .setPositiveButton("Save", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Editable value = input.getText(); 
                if(!value.toString().equals("")){
	                int val1 = Integer.parseInt(value.toString());
	                thisCharacter.getAbilities().setConstitution(val1);
	                TextView tv = (TextView) findViewById(R.id.con_value_text);
	                tv.setText("" + val1);
                }
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            }
        }).show();
    }
    
    public void viewIntelligence(View v){
    	final EditText input = new EditText(this);
    	input.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
    	new AlertDialog.Builder(this)
        .setTitle("Edit Intelligence")
        .setMessage("Enter new intelligence value")
        .setView(input)
        .setPositiveButton("Save", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Editable value = input.getText(); 
                if(!value.toString().equals("")){
	                int val1 = Integer.parseInt(value.toString());
	                thisCharacter.getAbilities().setIntelligence(val1);
	                TextView tv = (TextView) findViewById(R.id.int_value_text);
	                tv.setText("" + val1);
                }
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            }
        }).show();
    }
    
    public void viewWisdom(View v){
    	final EditText input = new EditText(this);
    	input.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
    	new AlertDialog.Builder(this)
        .setTitle("Edit Wisdom")
        .setMessage("Enter new wisdom value")
        .setView(input)
        .setPositiveButton("Save", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Editable value = input.getText(); 
                if(!value.toString().equals("")){
	                int val1 = Integer.parseInt(value.toString());
	                thisCharacter.getAbilities().setWisdom(val1);
	                TextView tv = (TextView) findViewById(R.id.wis_value_text);
	                tv.setText("" + val1);
                }
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            }
        }).show();
    }
    
    public void viewCharisma(View v){
    	final EditText input = new EditText(this);
    	input.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
    	new AlertDialog.Builder(this)
        .setTitle("Edit Charisma")
        .setMessage("Enter new charisma value")
        .setView(input)
        .setPositiveButton("Save", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Editable value = input.getText(); 
                if(!value.toString().equals("")){
	                int val1 = Integer.parseInt(value.toString());
	                thisCharacter.getAbilities().setCharisma(val1);
	                TextView tv = (TextView) findViewById(R.id.cha_value_text);
	                tv.setText("" + val1);
                }
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            }
        }).show();
    }
    
    public void viewBAB(View v){
    	final EditText input = new EditText(this);
    	input.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
    	new AlertDialog.Builder(this)
        .setTitle("Edit Base Attack Bonus")
        .setMessage("Enter new base attack value")
        .setView(input)
        .setPositiveButton("Save", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Editable value = input.getText(); 
                if(!value.toString().equals("")){
                	int val1 = Integer.parseInt(value.toString());
	                thisCharacter.setBaseAttack(val1);
	                TextView tv = (TextView) findViewById(R.id.bab_val);
	                tv.setText("" + val1);
                }
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            }
        }).show();
    }
    
    public void viewInit(View v){
    	final EditText input = new EditText(this);
    	input.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
    	new AlertDialog.Builder(this)
        .setTitle("Edit Initiative")
        .setMessage("Enter new initiative value")
        .setView(input)
        .setPositiveButton("Save", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Editable value = input.getText(); 
                if(!value.toString().equals("")){
                	int val1 = Integer.parseInt(value.toString());
	                thisCharacter.setInitiative(val1);
	                TextView tv = (TextView) findViewById(R.id.init_val);
	                tv.setText("" + val1);
                }
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            }
        }).show();
    }
    
    public void viewLevel(View v){
    	final EditText input = new EditText(this);
    	input.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
    	new AlertDialog.Builder(this)
        .setTitle("Edit Level")
        .setMessage("Enter new level")
        .setView(input)
        .setPositiveButton("Save", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Editable value = input.getText(); 
                if(!value.toString().equals("")){
	                int val1 = Integer.parseInt(value.toString());
	                thisCharacter.setLevel(val1);
	                TextView tv = (TextView) findViewById(R.id.level_text);
	                tv.setText("Level: " + val1);
                }
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            }
        }).show();
    }
    
    public void viewExperience(View v){
    	final EditText input = new EditText(this);
    	input.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
    	new AlertDialog.Builder(this)
        .setTitle("Edit Experience")
        .setMessage("Enter current experience")
        .setView(input)
        .setPositiveButton("Save", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Editable value = input.getText(); 
                if(!value.toString().equals("")){
	                int val1 = Integer.parseInt(value.toString());
	                thisCharacter.setExperience(val1);
	                TextView tv = (TextView) findViewById(R.id.experience_value_text);
	                tv.setText("" + val1);
                }
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            }
        }).show();
    }
    
    public void viewNext(View v){
    	final EditText input = new EditText(this);
    	input.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
    	new AlertDialog.Builder(this)
        .setTitle("Edit Level")
        .setMessage("Enter experience to next level")
        .setView(input)
        .setPositiveButton("Save", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Editable value = input.getText(); 
                if(!value.toString().equals("")){
	                int val1 = Integer.parseInt(value.toString());
	                thisCharacter.setNext(val1);
	                TextView tv = (TextView) findViewById(R.id.next_value_text);
	                tv.setText("" + val1);
                }
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            }
        }).show();
    }
    
    public void viewRace(View v){
    	final EditText input = new EditText(this);
    	new AlertDialog.Builder(this)
        .setTitle("Edit Race")
        .setMessage("Enter Race")
        .setView(input)
        .setPositiveButton("Save", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Editable value = input.getText(); 
                if(!value.toString().equals("")){
	                thisCharacter.setRace(value.toString());
	                TextView tv = (TextView) findViewById(R.id.race_text);
	                tv.setText("Race: " + value.toString());
                }
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            }
        }).show();
    }
    
    public void viewClass(View v){
    	final EditText input = new EditText(this);
    	new AlertDialog.Builder(this)
        .setTitle("Edit Class")
        .setMessage("Enter Class")
        .setView(input)
        .setPositiveButton("Save", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Editable value = input.getText(); 
                if(!value.toString().equals("")){
	                thisCharacter.setCharClass(value.toString());
	                TextView tv = (TextView) findViewById(R.id.class_text);
	                tv.setText("Class: " + value.toString());
                }
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            }
        }).show();
    }
    
    public void viewAlignment(View v){
    	final EditText input = new EditText(this);
    	new AlertDialog.Builder(this)
        .setTitle("Edit Alignment")
        .setMessage("Enter Alignment")
        .setView(input)
        .setPositiveButton("Save", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Editable value = input.getText(); 
                if(!value.toString().equals("")){
	                thisCharacter.setAlignment(value.toString());
	                TextView tv = (TextView) findViewById(R.id.alignment_text);
	                tv.setText("Alignment: " + value.toString());
                }
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            }
        }).show();
    }
    
    public void viewAC(View v){
    	final EditText input = new EditText(this);
    	input.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
    	new AlertDialog.Builder(this)
        .setTitle("Edit Armor Class")
        .setMessage("Enter new armor class")
        .setView(input)
        .setPositiveButton("Save", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Editable value = input.getText(); 
                if(!value.toString().equals("")){
	                int val1 = Integer.parseInt(value.toString());
	                thisCharacter.setArmorClass(val1);
	                TextView tv = (TextView) findViewById(R.id.ac_text);
	                tv.setText("Armor: " + val1);
                }
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            }
        }).show();
    }
    
    public void viewHP(View v){
    	final EditText input = new EditText(this);
    	input.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
    	new AlertDialog.Builder(this)
        .setTitle("Edit Health Points")
        .setMessage("Enter new health points value")
        .setView(input)
        .setPositiveButton("Save", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Editable value = input.getText(); 
                if(!value.toString().equals("")){
	                int val1 = Integer.parseInt(value.toString());
	                thisCharacter.setBaseHealth(val1);
	                TextView tv = (TextView) findViewById(R.id.hp_text);
	                tv.setText("Health: " + val1);
                }
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            }
        }).show();
    }
    
    
    public void pictureClicked(View v){
    	Intent i = new Intent(
    	Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
    	startActivityForResult(i, RESULT_LOAD_IMAGE);
    }
    
    public void changePicture(String pathName){
    	ImageView iv = (ImageView) findViewById(R.id.picture_view);
    	Bitmap myBitmap = BitmapFactory.decodeFile(pathName);
    	iv.setImageBitmap(myBitmap);
    	thisCharacter.setPicturePath(pathName);
    }
    
    public void rollStrength(View v){
    	Random r = new Random();
    	int ran = r.nextInt(21 - 1) + 1;
    	Toast t = Toast.makeText(this, thisCharacter.getAbilities().getStrength() + " + " + ran, Toast.LENGTH_SHORT);
    	t.show();
    }
    
    public void rollDexterity(View v){
    	Random r = new Random();
    	int ran = r.nextInt(21 - 1) + 1;
    	Toast t = Toast.makeText(this, thisCharacter.getAbilities().getDexterity() + " + " + ran, Toast.LENGTH_SHORT);
    	t.show();
    	
    }
    
    public void rollConstitution(View v){
    	Random r = new Random();
    	int ran = r.nextInt(21 - 1) + 1;
    	Toast t = Toast.makeText(this, thisCharacter.getAbilities().getConstitution() + " + " + ran, Toast.LENGTH_SHORT);
    	t.show();
    	
    }
    
    public void rollIntelligence(View v){
    	Random r = new Random();
    	int ran = r.nextInt(21 - 1) + 1;
    	Toast t = Toast.makeText(this, thisCharacter.getAbilities().getIntelligence() + " + " + ran, Toast.LENGTH_SHORT);
    	t.show();
    	
    }
    
    public void rollWisdom(View v){
    	Random r = new Random();
    	int ran = r.nextInt(21 - 1) + 1;
    	Toast t = Toast.makeText(this, thisCharacter.getAbilities().getWisdom() + " + " + ran, Toast.LENGTH_SHORT);
    	t.show();
    	
    }
    
    public void rollCharisma(View v){
    	Random r = new Random();
    	int ran = r.nextInt(21 - 1) + 1;
    	Toast t = Toast.makeText(this, thisCharacter.getAbilities().getCharisma() + " + " + ran, Toast.LENGTH_SHORT);
    	t.show();
    	
    }
    
    public void rollInit(View v){
    	Random r = new Random();
    	int ran = r.nextInt(21 - 1) + 1;
    	Toast t = Toast.makeText(this, thisCharacter.getInitiative() + " + " + ran, Toast.LENGTH_SHORT);
    	t.show();
    	
    }
    
    public void rollCmb(View v){
    	Random r = new Random();
    	int ran = r.nextInt(21 - 1) + 1;
    	TextView tv = (TextView) findViewById(R.id.cmb_val);
    	Toast t = Toast.makeText(this,  tv.getText() + " + " + ran, Toast.LENGTH_SHORT);
    	t.show();
    	
    }
    
    public void rollMelee(View v){
    	Random r = new Random();
    	int ran = r.nextInt(21 - 1) + 1;
    	TextView tv = (TextView) findViewById(R.id.melee_val);
    	Toast t = Toast.makeText(this,  tv.getText() + " + " + ran, Toast.LENGTH_SHORT);
    	t.show();
    	
    }
    
    public void rollRanged(View v){
    	Random r = new Random();
    	int ran = r.nextInt(21 - 1) + 1;
    	TextView tv = (TextView) findViewById(R.id.ranged_val);
    	Toast t = Toast.makeText(this,  tv.getText() + " + " + ran, Toast.LENGTH_SHORT);
    	t.show();
    	
    }
    
    public void rollMain(View v){
    	Random r = new Random();
    	int ran = r.nextInt(21 - 1) + 1;
    	TextView tv = (TextView) findViewById(R.id.main_weapon_damage_value);
    	Toast t = Toast.makeText(this,  tv.getText() + " + " + ran, Toast.LENGTH_SHORT);
    	t.show();
    	
    }
    
    public void rollOff(View v){
    	Random r = new Random();
    	int ran = r.nextInt(21 - 1) + 1;
    	TextView tv = (TextView) findViewById(R.id.off_weapon_damage_value);
    	Toast t = Toast.makeText(this,  tv.getText() + " + " + ran, Toast.LENGTH_SHORT);
    	t.show();
    	
    }
    
    public void incrementDaily(View v){
    	TextView tv = (TextView) findViewById(R.id.dailyValue);
    	int currVal = Integer.parseInt(tv.getText().toString());
    	tv.setText("" + (currVal+1));
    	thisCharacter.setDailySpellLimit(currVal+1);
    }
    
    public void decrementDaily(View v){
    	TextView tv = (TextView) findViewById(R.id.dailyValue);
    	int currVal = Integer.parseInt(tv.getText().toString());
    	tv.setText("" + (currVal-1));
    	thisCharacter.setDailySpellLimit(currVal-1);
    }
    
    public void resetDaily(View v){
    	TextView tv = (TextView) findViewById(R.id.dailyValue);
    	tv.setText("0");
    	thisCharacter.setDailySpellLimit(0);
    }
  
    //Pop up window. Allows selection of which device to transfer to
    public void displayCharList(){
    	AlertDialog.Builder builderSingle = new AlertDialog.Builder(this);
        builderSingle.setTitle("Select Device");
        builderSingle.setNegativeButton("Cancel",
            new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
        builderSingle.setAdapter(bluetoothArrayAdapter,
	        new DialogInterface.OnClickListener() {
	
	            @Override
	            public void onClick(DialogInterface dialog, int which) {
	                String strName = bluetoothArrayAdapter.getItem(which);
	                sendToDevice(strName.substring(strName.lastIndexOf('\n')+1, strName.length()));
	            }
	        });
        builderSingle.show();
    }
    
    //Called from the transfer button. Checks preconditions of using BlueTooth
    //Calls beginCharacterTransfer.  Turns on bluetooth if it is off
    public void transferCharacter(){
    	mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    	if (mBluetoothAdapter == null) {
    	    // Device does not support Bluetooth. Must be pretty old. Sucks for them!
    	}
    	
    	if (!mBluetoothAdapter.isEnabled()) {
    	    Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
			startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT );
    	}
    	else{
    		beginCharacterTransfer();
    	}
    }
    
    public void beginCharacterTransfer(){
		
    	//Instantiate array adapter for bluetooth list
	    bluetoothArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
	    
	    //Find all devices
	    Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
    	if (pairedDevices.size() > 0) {
    	    for (BluetoothDevice device : pairedDevices) {
    	        // Add the name and address to an array adapter to show in a ListView
    	    	bluetoothArrayAdapter.add(device.getName() + "\n" + device.getAddress());
    	    }
    	}
    	displayCharList();
	    
    }
    
    protected void onActivityResult (int requestCode, int resultCode, Intent data){
    	
    		if(requestCode == REQUEST_ENABLE_BT){
    			if(resultCode == RESULT_OK){
    				beginCharacterTransfer();
    			}
    			else{	//They didn't enable BlueTooth. THEY HAVE NO IDEA WHAT THEY'RE MISSING OUT ON
        			Toast t = Toast.makeText(this, "YOU FOOL!", Toast.LENGTH_SHORT);
        	    	t.show();
        		}
    		}
    		
    		
    		if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
    	         Uri selectedImage = data.getData();
    	         String[] filePathColumn = { MediaStore.Images.Media.DATA };
    	 
    	         Cursor cursor = getContentResolver().query(selectedImage,
    	                 filePathColumn, null, null, null);
    	         cursor.moveToFirst();
    	 
    	         int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
    	         String picturePath = cursor.getString(columnIndex);
    	         cursor.close();
    	         
    	         changePicture(picturePath);
    	                      
    	     }
    	
    }
    
    public void sendToDevice(final String address){
    	final Runnable finishedTransfer = new Runnable() {
	        public void run() {
	        	transferComplete();
	        }
	    };
	    
	    Thread t = new Thread() {
            public void run() {
            	BluetoothDevice mmDevice = mBluetoothAdapter.getRemoteDevice(address);
            	BluetoothSocket mmSocket;
                BluetoothSocket tmp = null;
                try {
                    // MY_UUID is the app's UUID string, also used by the server code
                    tmp = mmDevice.createRfcommSocketToServiceRecord(MY_UUID);
                } catch (IOException e) { }
                mmSocket = tmp;
                try {
                    // Connect the device through the socket. This will block
                    // until it succeeds or throws an exception
                    mmSocket.connect();
                } catch (IOException connectException) {
                    // Unable to connect; close the socket and get out
                    try {
                        mmSocket.close();
                    } catch (IOException closeException) { }
                    return;
                }
         
                // Do work to manage the connection (in a separate thread)
                manageConnectedSocket(mmSocket);
                mHandler.post(finishedTransfer);
            }
        };
        t.start();
    }
    
    public void transferComplete(){
    	Toast t = Toast.makeText(this, "Character transfer completed", Toast.LENGTH_SHORT);
    	t.show();
    }

    //Send file to other device
	public void manageConnectedSocket(final BluetoothSocket mmSocket) {
		final InputStream mmInStream;
	    final OutputStream mmOutStream;
        InputStream tmpIn = null;
        OutputStream tmpOut = null;
        
	   	 try {
	   		tmpIn = mmSocket.getInputStream();
            tmpOut = mmSocket.getOutputStream();
	     } catch (IOException e) {e.printStackTrace();}
	   	 
	   	mmInStream = tmpIn;
        mmOutStream = tmpOut;
		
		Thread t = new Thread() {
            public void run() {
            	File file = new File(theDirectory, "/chars/" + message + ".ser");
    	        
    	        try {
    		        try {
    		        	FileInputStream fileIn = new FileInputStream(file);
        		        ObjectInputStream in = new ObjectInputStream(fileIn);
						Character tempChar = (Character) in.readObject();
						ObjectOutputStream oos = new ObjectOutputStream(mmOutStream);
						oos.writeObject(tempChar);
						in.close();
	    		        fileIn.close();
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
            }
        };
        t.start();
	}
}