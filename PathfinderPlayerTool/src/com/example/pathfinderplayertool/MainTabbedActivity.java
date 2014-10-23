package com.example.pathfinderplayertool;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
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
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainTabbedActivity extends FragmentActivity {
	
    MainTabbedPagerAdapter mMainTabbedPagerAdapter;
    ViewPager mViewPager;
    public static String message = "";
    Character thisCharacter = null;
    public BluetoothAdapter mBluetoothAdapter;
	final Handler mHandler = new Handler();
	public int REQUEST_ENABLE_BT = 1337;
	public ArrayAdapter<String> bluetoothArrayAdapter;
	public UUID MY_UUID;
	public File theDirectory;

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
		if (id == R.id.load_feats) {
			mViewPager.setCurrentItem(MainTabbedPagerAdapter.FEATS, true);
		}
		if (id == R.id.load_spells) {
			mViewPager.setCurrentItem(MainTabbedPagerAdapter.SPELLBOOK, true);
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
    	if(requestCode == 1337){ //BlueTooth enable request{
    		
    		if(resultCode == RESULT_OK){
    			beginCharacterTransfer();
    		}
    		
    		else{	//They didn't enable BlueTooth. THEY HAVE NO IDEA WHAT THEY'RE MISSING OUT ON
    			Toast t = Toast.makeText(this, "YOU FOOL!", Toast.LENGTH_SHORT);
    	    	t.show();
    		}
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
						// TODO Auto-generated catch block
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