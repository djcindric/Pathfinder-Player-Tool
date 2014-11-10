package com.example.pathfinderplayertool;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class InventoryFragment extends Fragment {
	public String charName = "";
	public TextView tv = null;
	
	public InventoryFragment (String s){
		charName = s;
	}
	
    @Override
    public View onCreateView(LayoutInflater inflater,
        ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.inventory, container, false);
        
        return rootView;
    }
    
}
