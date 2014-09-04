package com.example.pathfinderplayertool;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MainTabbedPagerAdapter extends FragmentStatePagerAdapter {
	int NUMTABS = 8; //The number of tabs in the main view
	
	public MainTabbedPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int i) {
		switch(i){
			case 0:
				Fragment frag = new ProfileFragment(MainTabbedActivity.message);
				return frag;
			default:
				Fragment fragment = new DemoObjectFragment();
				Bundle args = new Bundle();
				args.putInt(DemoObjectFragment.ARG_OBJECT, i + 1);
				fragment.setArguments(args);
				return fragment;
		}
	}

	@Override
	public int getCount() {
		return NUMTABS;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		switch(position){
			case 0:
				return "Profile";
			case 1:
				return "Abilities";
			case 2:
				return "Equipment";
			case 3:
				return "Skills";
			case 4:
				return "Inventory";
			case 5:
				return "Feats";
			case 6:
				return "Spells";
			case 7:
				return "Notes";
			default:
				return "Empty Page";
		}
	}
}

class DemoObjectFragment extends Fragment {
    public static final String ARG_OBJECT = "object";

    @Override
    public View onCreateView(LayoutInflater inflater,
        ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.text_swipe, container, false);
        return rootView;
    }
}