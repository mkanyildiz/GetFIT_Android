package info.androidhive.slidingmenu;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.packone.login.R;

public class ErnaehrungstagebuchFragment extends Fragment {
	
	public ErnaehrungstagebuchFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_ernaehrungstagebuch, container, false);
         
        return rootView;
    }
}
