package com.zarifshahriar.tipcalculator;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * Created by Zarif on 2017-05-21.
 */

public class Settings extends Fragment{

    private Spinner mSpinner;

    public static Settings newInstance() {
        Settings fragment = new Settings();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.settings_frag, container, false);
        String defaultValue = "";
        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);

        String tip = sharedPref.getString("DefaultTip", defaultValue);
        EditText tipEditor = (EditText) rootView.findViewById(R.id.defaultTip);
        tipEditor.setText(tip);

        mSpinner = (Spinner) rootView.findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.curr_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(R.layout.dropdown_list);
        mSpinner.setAdapter(adapter);

        String curr = sharedPref.getString("Currency", "Dollar");
        if(curr.equals("Dollar")){
            mSpinner.setSelection(0);
        }
        else if (curr.equals("Pound")){
            mSpinner.setSelection(2);
        }
        else mSpinner.setSelection(1);

        return rootView;
    }
}
