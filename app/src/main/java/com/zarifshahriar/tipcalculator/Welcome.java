package com.zarifshahriar.tipcalculator;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Zarif on 2017-05-21.
 */

public class Welcome extends Fragment {

    public static Welcome newInstance() {
        Welcome fragment = new Welcome();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String defaultValue = "";
        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        String tip = sharedPref.getString("TipSuggest", defaultValue);
        if(tip.equals("")){ //No user suggestion
            tip = sharedPref.getString("DefaultTip", defaultValue);
        }
        else {
            SharedPreferences.Editor edit = sharedPref.edit();
            edit.remove("TipSuggest"); // Remove suggestion after using it
            edit.apply();
        }
        String curr ="$";
        String currency = sharedPref.getString("Currency", "");
        if(currency.contains("Euro")){
            curr="\u20ac";
        }
        else if (currency.contains("Pound")){
            curr= "\u00a3";
        }
        String text = "BILL AMOUNT " + "("+curr+")";
        View rootView = inflater.inflate(R.layout.welcome_frag, container, false);
        TextView billAmountView = (TextView) rootView.findViewById(R.id.bill_header);
        billAmountView.setText(text);
        EditText tipEditor = (EditText) rootView.findViewById(R.id.tip_amount);
        tipEditor.setText(tip);
        return rootView;
    }
}
