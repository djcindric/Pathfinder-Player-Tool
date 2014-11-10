package com.example.pathfinderplayertool;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class InventoryFragment extends Fragment {
	public String charName = "";
	public TextView tv = null;
	final ArrayList<String> weaponList = new ArrayList<String>();
	final ArrayList<String> weaponListName = new ArrayList<String>();
	public ArrayAdapter<String> whichHandArrayAdapter;
	public ArrayAdapter<String> adapter = null;
	
	public InventoryFragment (String s){
		charName = s;
	}
	
    @Override
    public View onCreateView(LayoutInflater inflater,
        ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.inventory, container, false);
        
        whichHandArrayAdapter = new ArrayAdapter<String>(rootView.getContext(), android.R.layout.simple_list_item_1);
        
        whichHandArrayAdapter.add("Main Hand");
        whichHandArrayAdapter.add("Off Hand");
        
        Button mButton = (Button) rootView.findViewById(R.id.addWeapon);
        mButton.setOnClickListener(new OnClickListener() {
        	public void onClick(View v){
        		AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
            	LinearLayout layout = new LinearLayout(getActivity());
            	layout.setOrientation(LinearLayout.VERTICAL);

            	final EditText nameBox = new EditText(getActivity());
            	nameBox.setHint("Name");
            	layout.addView(nameBox);
            	
            	final EditText damageBox = new EditText(getActivity());
            	damageBox.setHint("Number of Dice");
            	damageBox.setInputType(InputType.TYPE_CLASS_NUMBER);
            	layout.addView(damageBox);

            	final EditText sideBox = new EditText(getActivity());
            	sideBox.setHint("Dice Sides");
            	sideBox.setInputType(InputType.TYPE_CLASS_NUMBER);
            	layout.addView(sideBox);

            	dialog.setView(layout);
            	new AlertDialog.Builder(getActivity()).setTitle("Add Weapon").setMessage("Enter the name and damage bonus (Ex. 1, 6 for 1d6) of the the weapon")
        		.setPositiveButton("Add", new DialogInterface.OnClickListener() {
        			public void onClick(DialogInterface dialog, int which) { 
        				String tempString = nameBox.getText().toString();
        				String tempNum = damageBox.getText().toString();
        				String tempSide = sideBox.getText().toString();
        				
        				if (!tempString.equals("") && !tempNum.equals("") && !tempSide.equals("")){
        					Weapon tempWeapon = new Weapon(tempString, (tempNum + "d" + tempSide));
        					MainTabbedActivity.thisCharacter.getWeapons().add(tempWeapon);
        					weaponListName.add(tempWeapon.getName());
        		        	weaponList.add(tempWeapon.getName() + "\n" + tempWeapon.getDamage());
        		        	adapter.notifyDataSetChanged();
        				}
        			}
        			})
        		.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
        			public void onClick(DialogInterface dialog, int which) { 
        				
        			}
        			}).setView(layout).show();   
        	}
        });
        
        tv = (TextView) rootView.findViewById(R.id.mainhand_name);
        tv.setText(MainTabbedActivity.thisCharacter.getMainHand().getName());
        
        tv = (TextView) rootView.findViewById(R.id.offhand_name);
        tv.setText(MainTabbedActivity.thisCharacter.getOffHand().getName());
        
        ArrayList<Weapon> tempWeapons = MainTabbedActivity.thisCharacter.getWeapons();
        for(Weapon w: tempWeapons){
        	weaponListName.add(w.getName());
        	weaponList.add(w.getName() + "\n" + w.getDamage());
        }
        
        //Populate the weapon list view
  		final ListView listview = (ListView) rootView.findViewById(R.id.weapon_list);
  		adapter = new ArrayAdapter<String>(rootView.getContext(), R.drawable.custom_textview_weaponslist, weaponList);
  	    listview.setAdapter(adapter);
  	    
  	    //Set the listview to load the character that is clicked on
  	    listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
  			@Override
  		    public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
  				displayCharList(rootView, position);
  		    }
  	    });
  	    
  	    listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
  			@Override
  	    	public boolean onItemLongClick(final AdapterView<?> parent, final View view, final int position, long arg3){
  				new AlertDialog.Builder(parent.getContext()).setTitle("Delete entry").setMessage("Are you sure you want to delete this entry?")
    			.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
    				public void onClick(DialogInterface dialog, int which) { 
    					weaponList.remove(position);
    	  				weaponListName.remove(position);
    			    	adapter.notifyDataSetChanged();
    			    	MainTabbedActivity.thisCharacter.getWeapons().remove(position);
    				}
    				})
    			.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
    				public void onClick(DialogInterface dialog, int which) { 
    				}
    				}).setIcon(android.R.drawable.ic_dialog_alert).show();   
  				
  	    		return true; 
  	    	}
  		});
      	    
        
        return rootView;
    }
    
    public void displayCharList(final View v, final int thisOne){
    	AlertDialog.Builder builderSingle = new AlertDialog.Builder(v.getContext());
        builderSingle.setTitle("Select Hand");
        builderSingle.setNegativeButton("Cancel",
            new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
        builderSingle.setAdapter(whichHandArrayAdapter,
	        new DialogInterface.OnClickListener() {
	
	            @Override
	            public void onClick(DialogInterface dialog, int which) {
	            	String whichHand = whichHandArrayAdapter.getItem(which);
	                equipWeapon(whichHand, thisOne, v);
	                
	            }
	        });
        builderSingle.show();
    }
    
    public void equipWeapon(String which, int position, View v){
    	if(which.equals("Main Hand")){
    		Weapon tempWep = MainTabbedActivity.thisCharacter.getWeapons().get(position);
    		MainTabbedActivity.thisCharacter.setMainHand(tempWep);
    		
    		tv = (TextView) v.findViewById(R.id.mainhand_name);
            tv.setText(MainTabbedActivity.thisCharacter.getMainHand().getName());
    	}
    	else{
    		Weapon tempWep = MainTabbedActivity.thisCharacter.getWeapons().get(position);
    		MainTabbedActivity.thisCharacter.setOffHand(tempWep);
    		
    		tv = (TextView) v.findViewById(R.id.offhand_name);
            tv.setText(MainTabbedActivity.thisCharacter.getOffHand().getName());
    	}
    }
    
}
