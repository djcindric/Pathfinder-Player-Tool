package com.example.pathfinderplayertool;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import android.annotation.SuppressLint;
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

@SuppressWarnings("rawtypes")
public class MainActivity extends Activity  implements NewCharacterDialogFragment.NoticeDialogListener{
	final ArrayList<String> list = new ArrayList<String>();
	public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
	public ArrayAdapter<String> adapter = null;
	public static int currentapiVersion = android.os.Build.VERSION.SDK_INT;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		File saveFile = new File(this.getFilesDir(), "charNames.txt");
    	BufferedReader in  =null;
    	String name="";
    	try {
    		in = new BufferedReader(new FileReader(saveFile));
    		while((name = in.readLine()) != null){
    			list.add(name);
    		}
    	} catch (IOException e) {
			e.printStackTrace();
		}
		
		final ListView listview = (ListView) findViewById(R.id.character_list);
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
	    listview.setAdapter(adapter);
	    listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
		    public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
		        final String item = (String) parent.getItemAtPosition(position);
		        Intent intent = new Intent(parent.getContext(), MainTabbedActivity.class);
		        intent.putExtra(EXTRA_MESSAGE, item);
		        startActivity(intent);
		    }
	    });
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
	public void onPause(){
		saveCharacters();
		super.onDestroy();
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
			NewCharacterDialogFragment dialog = new NewCharacterDialogFragment();
			dialog.show(getFragmentManager(), null);
		}
		return super.onOptionsItemSelected(item);
	}
	
	//Create a new character. Add it to the list
	public void newCharacter(String s){
        list.add(s);
        adapter.notifyDataSetChanged();
	}
	
	public void onDialogPositiveClick(DialogFragment d) {
		EditText et = (EditText)d.getDialog().findViewById(R.id.char_name);
		String s = et.getText().toString();
		newCharacter(s);
    }

    @Override
    public void onDialogNegativeClick(DialogFragment d) {
    }
    
    public boolean saveCharacters(){
    	File saveFile = new File(this.getFilesDir(), "charNames.txt");
    	BufferedWriter os=null;
    	saveFile.delete();
    	try {
    		os = new BufferedWriter(new FileWriter(saveFile, true));
    		saveFile.createNewFile(); //Create the file if it doesn't exist
    		for(String s: list){
        		os.append(s);
        		os.newLine();
        	}
    		os.close();
    	} catch (IOException e) {
			e.printStackTrace();
		}
    	return true;
    }
}
