package com.example.pathfinderplayertool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SpellsFragment extends Fragment {
	public String charName = "";
	public TextView tv = null;
	
	public ExpandableListAdapter listAdapter;
	public ExpandableListView expListView;
	public List<String> listDataHeader;
	public HashMap<String, List<String>> listDataChild;
	
	DisplayMetrics displayMetrics;
	int width;
	
	public SpellsFragment (String s){
		charName = s;
	}
    @Override
    public View onCreateView(LayoutInflater inflater,
        ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.spells, container, false);
        
        tv = (TextView) rootView.findViewById(R.id.dailyValue);
        tv.setText("" + MainTabbedActivity.thisCharacter.getDailySpellLimit());
        
        Button mButton = (Button) rootView.findViewById(R.id.addSpell);
        mButton.setOnClickListener(new OnClickListener() {
        	public void onClick(View v){
        		AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
            	LinearLayout layout = new LinearLayout(getActivity());
            	layout.setOrientation(LinearLayout.VERTICAL);

            	final EditText nameBox = new EditText(getActivity());
            	nameBox.setHint("Name");
            	layout.addView(nameBox);

            	final EditText levelBox = new EditText(getActivity());
            	levelBox.setHint("Level");
            	levelBox.setInputType(InputType.TYPE_CLASS_NUMBER);
            	layout.addView(levelBox);

            	dialog.setView(layout);
            	new AlertDialog.Builder(getActivity()).setTitle("Add Spell").setMessage("Enter the name and level of the spell")
        		.setPositiveButton("Add", new DialogInterface.OnClickListener() {
        			public void onClick(DialogInterface dialog, int which) { 
        				String tempString = levelBox.getText().toString();
        				if (!tempString.equals("")){
        					Spell newSpell = new Spell(nameBox.getText().toString(), Integer.parseInt(tempString));
            		    	MainTabbedActivity.thisCharacter.addSpell(newSpell);
            		    	listDataChild.get(listDataHeader.get(newSpell.getLevel())).add(newSpell.getName());
                    		listAdapter.notifyDataSetChanged();
        				}
        			}
        			})
        		.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
        			public void onClick(DialogInterface dialog, int which) { 
        				
        			}
        			}).setView(layout).show();    
            	
        	}
        });
        
        return rootView;
    }
    
    @SuppressLint("NewApi")
	public void onViewCreated(View v, Bundle b){
    	super.onViewCreated(v,b);
    	expListView = (ExpandableListView) this.getActivity().findViewById(R.id.spellsExpandableList);
    	expListView.setOnChildClickListener(new OnChildClickListener() {
    		@Override
    		public boolean onChildClick(ExpandableListView parent, View v, final int groupPosition, final int childPosition, long id) {
    			new AlertDialog.Builder(parent.getContext()).setTitle("Remove spell").setMessage("Are you sure you want to remove this spell?")
    			.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
    				public void onClick(DialogInterface dialog, int which) { 
    					String spellName = listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition);
    	    			ArrayList<Spell> spells = MainTabbedActivity.thisCharacter.getSpells();
    	    			Spell toDelete = null;
    	    			for(Spell s : spells){
    	    				if(s.getName().equals(spellName)){
    	    					toDelete = s;
    	    				}
    	    			
    	    			}
    	    			MainTabbedActivity.thisCharacter.removeSpell(toDelete);
    	    			
    					listDataChild.get(listDataHeader.get(groupPosition)).remove(childPosition);
    	        		listAdapter.notifyDataSetChanged();
    				}
    				})
    			.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
    				public void onClick(DialogInterface dialog, int which) { 
    				}
    				}).setIcon(android.R.drawable.ic_dialog_alert).show();         
    			
    			return false;
    		}
    	});
    	
        prepareListData();
        listAdapter = new ExpandableListAdapter(this.getActivity().getBaseContext(), listDataHeader, listDataChild);
    	expListView.setAdapter(listAdapter);
    	
    	displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) v.getContext().getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);

        width = displayMetrics.widthPixels;

        if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {
               expListView.setIndicatorBounds(width - GetDipsFromPixel(35), width - GetDipsFromPixel(10));
        } else {
        	expListView.setIndicatorBoundsRelative(width - GetDipsFromPixel(35), width - GetDipsFromPixel(10));
        }
        expListView.setAdapter(listAdapter);
    	
    }
    
    public void prepareListData(){
    	listDataHeader = new ArrayList<String>();
    	listDataChild = new HashMap<String, List<String>>();
    	
    	listDataHeader.add("0");
    	listDataHeader.add("1");
    	listDataHeader.add("2");
    	listDataHeader.add("3");
    	listDataHeader.add("4");
    	listDataHeader.add("5");
    	listDataHeader.add("6");
    	listDataHeader.add("7");
    	listDataHeader.add("8");
    	listDataHeader.add("9");
    	
    	
    	List<String> spells0 = new ArrayList<String>();
    	List<String> spells1 = new ArrayList<String>();
    	List<String> spells2 = new ArrayList<String>();
    	List<String> spells3 = new ArrayList<String>();
    	List<String> spells4 = new ArrayList<String>();
    	List<String> spells5 = new ArrayList<String>();
    	List<String> spells6 = new ArrayList<String>();
    	List<String> spells7 = new ArrayList<String>();
    	List<String> spells8 = new ArrayList<String>();
    	List<String> spells9 = new ArrayList<String>();
    	
    	ArrayList<Spell> spells = MainTabbedActivity.thisCharacter.getSpells();
    	
    	for(Spell s : spells){
    		int spellLevel = s.getLevel();
    		switch(spellLevel){
    			case 0:
    				spells0.add(s.getName());
    				break;
    				
    			case 1:
    				spells1.add(s.getName());
    				break;
    				
    			case 2:
    				spells2.add(s.getName());
    				break;
    				
    			case 3:
    				spells3.add(s.getName());
    				break;
    				
    			case 4:
    				spells4.add(s.getName());
    				break;
    				
    			case 5:
    				spells5.add(s.getName());
    				break;
    				
    			case 6:
    				spells6.add(s.getName());
    				break;
    				
    			case 7:
    				spells7.add(s.getName());
    				break;
    				
    			case 8:
    				spells8.add(s.getName());
    				break;
    				
    			case 9:
    				spells9.add(s.getName());
    				break;
    		}
    	}
    	
    	listDataChild.put(listDataHeader.get(0), spells0);
    	listDataChild.put(listDataHeader.get(1), spells1);
    	listDataChild.put(listDataHeader.get(2), spells2);
    	listDataChild.put(listDataHeader.get(3), spells3);
    	listDataChild.put(listDataHeader.get(4), spells4);
    	listDataChild.put(listDataHeader.get(5), spells5);
    	listDataChild.put(listDataHeader.get(6), spells6);
    	listDataChild.put(listDataHeader.get(7), spells7);
    	listDataChild.put(listDataHeader.get(8), spells8);
    	listDataChild.put(listDataHeader.get(9), spells9);
    }
    

    private int GetDipsFromPixel(int pixels) {
	    final float scale = getResources().getDisplayMetrics().density;
	    return (int) (pixels * scale + 0.5f);
    }
}