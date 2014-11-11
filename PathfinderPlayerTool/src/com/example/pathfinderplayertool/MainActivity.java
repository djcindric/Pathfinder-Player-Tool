package com.example.pathfinderplayertool;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.UUID;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity  implements NewCharacterDialogFragment.NoticeDialogListener{
	public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
	public static int currentapiVersion = android.os.Build.VERSION.SDK_INT;
	public static File fileDir;
	final ArrayList<String> listWithoutID = new ArrayList<String>();
	final ArrayList<String> listWithID = new ArrayList<String>();
	public ArrayAdapter<String> adapter = null;
	public BluetoothAdapter mBluetoothAdapter;
	final Handler mHandler = new Handler();
	public int REQUEST_ENABLE_BT = 1337;
	public ArrayAdapter<String> bluetoothArrayAdapter;
	public boolean shouldListen = true;
	public UUID MY_UUID;
	public Character thisCharacter = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		MY_UUID = UUID.fromString("543c05d0-5a54-11e4-8ed6-0800200c9a66");
		
		//Check if BlueTooth is enabled. If it is, stand by to accept incoming transfers
		mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    	if (mBluetoothAdapter == null) {
    	    // Device does not support Bluetooth. Must be pretty old. Sucks for them!
    	}
    	else{
    		if (mBluetoothAdapter.isEnabled() && shouldListen) {
        	    beginListeningForTransfers();
        	}
    	}
    	
		//Change the action bar title
		ActionBar ab = getActionBar();
		ab.setTitle("Characters");
		ab.setSubtitle("Pathfinder Player Tool");
	
		//Open the character directory. Create it if it doesn't already exist
		fileDir = this.getFilesDir();
		File saveFilesDir = new File(this.getFilesDir(), "/chars/");
		saveFilesDir.mkdir();

		//Get all files in the chars directory
    	File file[] = saveFilesDir.listFiles();
    	for (int i=0; i < file.length; i++)
    	{
    		try
            {
    	        FileInputStream fileIn = new FileInputStream(file[i]);
    	        ObjectInputStream in = new ObjectInputStream(fileIn);
    	        thisCharacter = (Character) in.readObject();
    	        in.close();
    	        fileIn.close();
            }catch(IOException e){e.printStackTrace();}
            catch(ClassNotFoundException c){c.printStackTrace();}
	        
    		listWithID.add(thisCharacter.getName() + "-" + thisCharacter.getID());
    		listWithoutID.add(thisCharacter.getName() + " - Lvl: " + thisCharacter.getLevel());
	        
    	}
		
    	//Populate the listview (Declared in activity_main.xml) with names from the .txt file
		final ListView listview = (ListView) findViewById(R.id.character_list);
		adapter = new ArrayAdapter<String>(this, R.drawable.custom_textview, listWithoutID);
	    listview.setAdapter(adapter);
	    
	    //Set the listview to load the character that is clicked on
	    listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
		    public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
		        final String item = listWithID.get(position);
		        Intent intent = new Intent(parent.getContext(), MainTabbedActivity.class);
		        intent.putExtra(EXTRA_MESSAGE, item);
		        startActivity(intent);
		    }
	    });
	    
	    //Set the listview to prompt to remove a character on a long click (hold)
	    listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
	    	@SuppressLint("NewApi")
			@Override
	    	public boolean onItemLongClick(final AdapterView<?> parent, final View view, final int position, long arg3){
	    		final String item = listWithID.get(position);
    			
	    		new AlertDialog.Builder(parent.getContext()).setTitle("Delete entry").setMessage("Are you sure you want to delete this entry?")
	    			.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
	    				public void onClick(DialogInterface dialog, int which) { 
	    					listWithID.remove(position);
					    	listWithoutID.remove(position);
					    	adapter.notifyDataSetChanged();
					    	removeCharacter(item);
	    				}
	    				})
	    			.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
	    				public void onClick(DialogInterface dialog, int which) { 
	    				}
	    				}).setIcon(android.R.drawable.ic_dialog_alert).show();         
	    		return true; 
	    	}
		});
	}
	
	@Override 
	public void onDestroy(){
		super.onDestroy();
	}
	
	@Override
	public void onPause(){
		super.onPause();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		if (id == R.id.new_character) {
			skipTutorial();
		}
		if(id == R.id.rate_application){
			rateApplication();
		}
		return super.onOptionsItemSelected(item);
	}
	
	//TODO Add link to rate application in store
	public void rateApplication(){
		
	}
	
	public void onDialogPositiveClick(DialogFragment d) {
		EditText et = (EditText)d.getDialog().findViewById(R.id.char_name);
		String name = et.getText().toString();
		
		String temp = "";
		
		int level=1;
		int str = 8;
		int dex = 8;
		int con = 8;
		int intel = 8;
		int wis = 8;
		int cha = 8;
		
		et = (EditText)d.getDialog().findViewById(R.id.char_level);
		temp = et.getText().toString();
		if(!temp.equals("")){
			level = Integer.parseInt(temp);
		}
		
		et = (EditText)d.getDialog().findViewById(R.id.char_str);
		temp = et.getText().toString();
		if(!temp.equals("")){
			str = Integer.parseInt(temp);
		}
			
		
		et = (EditText)d.getDialog().findViewById(R.id.char_dex);
		temp = et.getText().toString();
		if(!temp.equals("")){
			dex = Integer.parseInt(temp);
		}
		
		et = (EditText)d.getDialog().findViewById(R.id.char_con);
		temp = et.getText().toString();
		if(!temp.equals("")){
			con = Integer.parseInt(temp);
		}
		
		et = (EditText)d.getDialog().findViewById(R.id.char_int);
		temp = et.getText().toString();
		if(!temp.equals("")){
			intel = Integer.parseInt(temp);
		}
		
		et = (EditText)d.getDialog().findViewById(R.id.char_wis);
		temp = et.getText().toString();
		if(!temp.equals("")){
			wis = Integer.parseInt(temp);
		}
		
		et = (EditText)d.getDialog().findViewById(R.id.char_cha);
		temp = et.getText().toString();
		if(!temp.equals("")){
			cha = Integer.parseInt(temp);
		}
		
		createCharacter(name, level, str, dex, con, intel, wis, cha);
    }

    @Override
    public void onDialogNegativeClick(DialogFragment d) {
    }
    
    //Delete a character file
    public boolean removeCharacter(String s){
    	File tempChar = new File(this.getFilesDir(), "/chars/" + s + ".ser");
    	tempChar.delete();
    	return true;
    }
    
    //With serialization
    public boolean createCharacter(String name, int level, int str, int dex, int con, int intel, int wis, int cha){
    	int ID = CharacterID.generateID(this.getFilesDir());
    	Character newChar = new Character(name, ID);
    	newChar.setLevel(level);
    	Abilities newAbilities = new Abilities(str, dex, con, intel, wis, cha);
    	newChar.setAbilities(newAbilities);
    	
    	 try
         {
    		File f = new File(this.getFilesDir(), "/chars/" + name + "-" + ID + ".ser");
            FileOutputStream fileOut = new FileOutputStream(f);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(newChar);
            out.close();
            fileOut.close();
         }catch(IOException i)
         {
        	 Toast ts = Toast.makeText(this, "IOException saving character", Toast.LENGTH_SHORT);
         	 ts.show();
             i.printStackTrace();
         }
    	 
    	listWithID.add(name + "-" + ID);
    	listWithoutID.add(name + " - Lvl: " + level);
        adapter.notifyDataSetChanged();
         
    	return true;
    }
    
    //Prompt to start tutorial
    public void promptTutorial(){
    	new AlertDialog.Builder(this).setTitle("Help").setMessage("Would you like help creating your character? \n(This message can be disabled in the settings)")
		.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) { 
		    	startTutorial();
			}
			})
		.setNegativeButton("No", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) { 
				skipTutorial();
			}
			}).setIcon(android.R.drawable.ic_dialog_alert).show();     
    }
    
    //Skip the tutorial. Prompt for character name
    public void skipTutorial(){
    	NewCharacterDialogFragment dialog = new NewCharacterDialogFragment();
		dialog.show(getFragmentManager(), null);
    }
    
    //Start the tutorial
    public void startTutorial(){
    	Toast t = Toast.makeText(this, "TODO:Start Tutorial", Toast.LENGTH_SHORT);
    	t.show();
    }
    
    public void beginListeningForTransfers(){
    	final Runnable finishedTransfer = new Runnable() {
	        public void run() {
	        	transferComplete();
	        }
	    };
	    
	    Thread t = new Thread() {
            public void run() {
            	final BluetoothServerSocket mmServerSocket;
                BluetoothServerSocket tmp = null;
                try {
                    tmp = mBluetoothAdapter.listenUsingRfcommWithServiceRecord("Pathfinder Player Tool", MY_UUID);
                } catch (IOException e) { }
                mmServerSocket = tmp;
                
                BluetoothSocket socket = null;
                // Keep listening until exception occurs or a socket is returned
                while (true) {
                    try {
                        socket = mmServerSocket.accept();
                    } catch (IOException e) {
                        break;
                    }
                    if (socket != null) {
                    	manageConnectedSocket(socket);
                    	mHandler.post(finishedTransfer);
                    }
                }
            }
        };
        t.start();
    }
    
    public void transferComplete(){
    	Toast t = Toast.makeText(this, "Character transfer completed", Toast.LENGTH_SHORT);
    	t.show();
    }
    
    //Receive file from other device
    public void manageConnectedSocket(final BluetoothSocket mmSocket) {
    	final InputStream mmInStream;
        InputStream tmpIn = null;
        
	   	 try {
	   		tmpIn = mmSocket.getInputStream();
	     } catch (IOException e) {e.printStackTrace();}
	   	 
	   	mmInStream = tmpIn;
		
		Thread t = new Thread() {
            public void run() {
            	
    	        
    	        try {
					try {
	    		        ObjectInputStream in = new ObjectInputStream(mmInStream);
						Character tempChar;
						tempChar = (Character) in.readObject();
						in.close();
						int ID = CharacterID.generateID(fileDir);
						tempChar.setID(ID);
						tempChar.setPicturePath("");
						File file = new File(fileDir, "/chars/" + tempChar.getName() + "-" + ID + ".ser");
			            FileOutputStream fileOut = new FileOutputStream(file);
			            ObjectOutputStream out = new ObjectOutputStream(fileOut);
			            out.writeObject(tempChar);
			            out.close();
			            fileOut.close();
			            
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
					
					Message msg = Message.obtain();
					msg.arg1 = 4004;
					tempHandler.sendMessage(msg);
					
				} catch (IOException e) {
					e.printStackTrace();
				}
            }
        };
        t.start();
	}
    
    private Handler tempHandler = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            super.handleMessage(msg);
            if(msg.arg1 == 4004){
            	transferComplete();	
            	recreate();
            }
        }

    };
}
