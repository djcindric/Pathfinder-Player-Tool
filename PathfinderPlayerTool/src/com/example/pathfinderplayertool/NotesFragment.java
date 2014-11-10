package com.example.pathfinderplayertool;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class NotesFragment extends Fragment {
	public String charName = "";
	public TextView tv = null;
	
	public NotesFragment (String s){
		charName = s;
	}
	
    @Override
    public View onCreateView(LayoutInflater inflater,
        ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.notes, container, false);
        
        tv = (TextView) rootView.findViewById(R.id.notes_edit_text);
        tv.setText(MainTabbedActivity.thisCharacter.getNotes());
        
        Button mButton = (Button) rootView.findViewById(R.id.save_notes_button);
        mButton.setOnClickListener(new OnClickListener() {
        	public void onClick(View v){
        		EditText et = (EditText) rootView.findViewById(R.id.notes_edit_text);
        		MainTabbedActivity.thisCharacter.setNotes(et.getText().toString());
        		Toast t = Toast.makeText(rootView.getContext(), "Notes Saved", Toast.LENGTH_SHORT);
        		t.show();
        	}});
        
        return rootView;
    }
    
}
