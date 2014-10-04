package com.example.pathfinderplayertool;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity  implements NewCharacterDialogFragment.NoticeDialogListener{
	final ArrayList<String> list = new ArrayList<String>();
	public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
	public ArrayAdapter<String> adapter = null;
	public static int currentapiVersion = android.os.Build.VERSION.SDK_INT;
	public static File fileDir;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//Change the action bar title
		ActionBar ab = getActionBar();
		ab.setTitle("Characters");
		ab.setSubtitle("Pathfinder Player Tool");
		
		//Open the character directory. Create it if it doesnt already exist
		fileDir = this.getFilesDir();
		File saveFilesDir = new File(this.getFilesDir(), "/chars/");
		saveFilesDir.mkdir();

		//Get all files in the chars directory
    	File file[] = saveFilesDir.listFiles();
    	for (int i=0; i < file.length; i++)
    	{
    	    list.add(file[i].getName().substring(0,file[i].getName().length()-4));
    	}
		
    	//Populate the listview (Declared in activity_main.xml) with names from the .txt file
		final ListView listview = (ListView) findViewById(R.id.character_list);
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
	    listview.setAdapter(adapter);
	    
	    //Set the listview to load the character that is clicked on
	    listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
		    public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
		        final String item = (String) parent.getItemAtPosition(position);
		        Intent intent = new Intent(parent.getContext(), MainTabbedActivity.class);
		        intent.putExtra(EXTRA_MESSAGE, item);
		        startActivity(intent);
		    }
	    });
	    
	    //Set the listview to prompt to remove a character on a long click (hold)
	    listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
	    	@SuppressLint("NewApi")
			@Override
	    	public boolean onItemLongClick(final AdapterView<?> parent, final View view, int position, long arg3){
	    		final String item = (String) parent.getItemAtPosition(position);
    			
	    		new AlertDialog.Builder(parent.getContext()).setTitle("Delete entry").setMessage("Are you sure you want to delete this entry?")
	    			.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
	    				public void onClick(DialogInterface dialog, int which) { 
					    	list.remove(item);
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
			promptTutorial();
		}
		if(id == R.id.rate_application){
			rateApplication();
		}
		return super.onOptionsItemSelected(item);
	}
	
	//Create a new character. Add it to the list
	public void newCharacter(String s){
//        list.add(s);
//        adapter.notifyDataSetChanged();
        createCharacter(s);
	}
	
	//TODO Add link to rate application in store
	public void rateApplication(){
		
	}
	
	public void onDialogPositiveClick(DialogFragment d) {
		EditText et = (EditText)d.getDialog().findViewById(R.id.char_name);
		String s = et.getText().toString();
		newCharacter(s);
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
    public boolean createCharacter(String s){
    	int ID = CharacterID.generateID(this.getFilesDir());
    	Character newChar = new Character(s, ID);
    	Toast t = Toast.makeText(this, ID+s, Toast.LENGTH_SHORT);
    	t.show();
    	 try
         {
    		File f = new File(this.getFilesDir(), "/chars/" + s + "-" + ID + ".ser");
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
    	 
    	list.add(s + "-" + ID);
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
}
