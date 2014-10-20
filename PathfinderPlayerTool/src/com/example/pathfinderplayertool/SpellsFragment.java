package com.example.pathfinderplayertool;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

public class SpellsFragment extends Fragment {
	public String charName = "";
	public Character thisCharacter = null;
	public TextView tv = null;
	
	public ExpandableListAdapter listAdapter;
	public ExpandableListView expListView;
	public List<String> listDataHeader;
	public HashMap<String, List<String>> listDataChild;
	
	public SpellsFragment (String s){
		charName = s;
	}
    @Override
    public View onCreateView(LayoutInflater inflater,
        ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.spells, container, false);
        
        try
        {
        	File file = new File(MainActivity.fileDir, "/chars/" + charName + ".ser");
	        FileInputStream fileIn = new FileInputStream(file);
	        ObjectInputStream in = new ObjectInputStream(fileIn);
	        thisCharacter = (Character) in.readObject();
	        in.close();
	        fileIn.close();
        }catch(IOException i){i.printStackTrace();}
        catch(ClassNotFoundException c){c.printStackTrace();}
        
        return rootView;
    }
    
    public void onViewCreated(View v, Bundle b){
    	super.onViewCreated(v,b);
    	expListView = (ExpandableListView) this.getActivity().findViewById(R.id.spellsExpandableList);
        prepareListData();
        listAdapter = new ExpandableListAdapter(this.getActivity().getBaseContext(), listDataHeader, listDataChild);
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
    	
    	
    	List<String> test1 = new ArrayList<String>();
    	List<String> test2 = new ArrayList<String>();
    	List<String> test3 = new ArrayList<String>();
    	List<String> test4 = new ArrayList<String>();
    	List<String> test5 = new ArrayList<String>();
    	List<String> test6 = new ArrayList<String>();
    	List<String> test7 = new ArrayList<String>();
    	List<String> test8 = new ArrayList<String>();
    	List<String> test9 = new ArrayList<String>();
    	List<String> test10 = new ArrayList<String>();
        
    	test1.add("Light");
    	test1.add("Guidance");
    	test2.add("Bless");
    	test3.add("Aid");
    	test4.add("Contagion");
    	test5.add("Dismissal");
    	test6.add("Atonement");
    	test7.add("Banishment");
    	test8.add("Resurrection");
    	test9.add("Fire Storm");
    	test10.add("Heal, Mass");
    	
    	listDataChild.put(listDataHeader.get(0), test1);
    	listDataChild.put(listDataHeader.get(1), test2);
    	listDataChild.put(listDataHeader.get(2), test3);
    	listDataChild.put(listDataHeader.get(3), test4);
    	listDataChild.put(listDataHeader.get(4), test5);
    	listDataChild.put(listDataHeader.get(5), test6);
    	listDataChild.put(listDataHeader.get(6), test7);
    	listDataChild.put(listDataHeader.get(7), test8);
    	listDataChild.put(listDataHeader.get(8), test9);
    	listDataChild.put(listDataHeader.get(9), test10);
    	
    }
}
