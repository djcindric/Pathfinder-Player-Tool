package com.example.pathfinderplayertool;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ProfileFragment extends Fragment {
	public String charName = "";
	
	public ProfileFragment (String s){
		charName = s;
	}
	
    @Override
    public View onCreateView(LayoutInflater inflater,
        ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.character_profile, container, false);
        
        TextView tv = (TextView) rootView.findViewById(R.id.charName);
        tv.setText(charName);
        
        return rootView;
    }
    
}
