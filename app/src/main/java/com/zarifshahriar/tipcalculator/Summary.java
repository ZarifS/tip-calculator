package com.zarifshahriar.tipcalculator;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Summary extends Fragment {

    private TextView billAmount;
    private TextView finalBill;
    private TextView totalTip;
    private TextView tipPerPerson;
    private TextView peoplePay;
    private View personLayout;

    public static Summary newInstance() {
        Summary fragment = new Summary();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.summary_frag, container, false);
        billAmount = (TextView) rootView.findViewById(R.id.bill);
        finalBill = (TextView) rootView.findViewById(R.id.total_bill);
        totalTip = (TextView) rootView.findViewById(R.id.tip);
        personLayout = rootView.findViewById(R.id.person_summary);
        peoplePay = (TextView) rootView.findViewById(R.id.bill_per_person);
        tipPerPerson = (TextView) rootView.findViewById(R.id.tip_per_person);

        String bill = getArguments().getString("billAmount");
        String tip = getArguments().getString("tipAmount");
        String people = getArguments().getString("peopleAmount");
        System.out.println(tip+bill+people);
        Double billAmount = Double.parseDouble(bill);
        Double tipAmount = Double.parseDouble(tip);
        int peopleAmount = Integer.parseInt(people);
        calcAmounts(billAmount,tipAmount,peopleAmount);
        return rootView;
    }

    private void calcAmounts(Double bill, Double tip, int people) {
        Double tipAmount = bill * tip/100;
        Double finalAmount = tipAmount + bill;
        String curr ="$";
        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        String currency = sharedPref.getString("Currency", "");
        if(currency.equals("Euro")){
            curr="\u20ac";
        }
        else if (currency.equals("Pound")){
            curr= "\u00a3";
        }
        String b = curr+String.format("%.2f", bill);
        String t = curr+String.format("%.2f", tipAmount);
        String fB = curr+String.format("%.2f", finalAmount);
        billAmount.setText(b);
        totalTip.setText(t);
        finalBill.setText(fB);

        if(people > 1) {
            Double perPerson = finalAmount/people;
            Log.i("PerPerson", ""+perPerson);
            Double tipDivided = tipAmount/people;
            String pP = curr+String.format("%.2f", perPerson);
            String tD = curr+String.format("%.2f", tipDivided);
            peoplePay.setText(pP);
            tipPerPerson.setText(tD);
            personLayout.setVisibility(View.VISIBLE);
        }
        else{
            personLayout.setVisibility(View.GONE);
        }
    }

}
