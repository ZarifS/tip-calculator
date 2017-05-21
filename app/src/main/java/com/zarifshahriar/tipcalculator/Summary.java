package com.zarifshahriar.tipcalculator;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Summary extends Fragment {

    private View view;
    private TextView billAmount;
    private TextView finalBill;
    private TextView totalTip;
    private TextView tipPerPerson;
    private TextView peoplePay;

    public static Summary newInstance() {
        Summary fragment = new Summary();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String bill = getArguments().getString("billAmount");
        String tip = getArguments().getString("tipAmount");
        String people = getArguments().getString("peopleAmount");
        Double billAmount = Double.parseDouble(bill);
        Double tipAmount = Double.parseDouble(tip);
        int peopleAmount = Integer.parseInt(people);
        calcAmounts(billAmount,tipAmount,peopleAmount);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.summary_frag, container, false);
    }

    private void calcAmounts(Double bill, Double tip, int people) {
        Double tipAmount = bill * tip/100;
        Double finalAmount = tipAmount + bill;

        billAmount = (TextView) getView().findViewById(R.id.bill);
        finalBill = (TextView) getView().findViewById(R.id.total_bill);
        totalTip = (TextView) getView().findViewById(R.id.tip);

        System.out.println((Double.toString(bill)));
        System.out.println((Double.toString(tipAmount)));
        System.out.println((Double.toString(finalAmount)));

        billAmount.setText((Double.toString(bill)));
        totalTip.setText(Double.toString(tipAmount));
        finalBill.setText(Double.toString(finalAmount));

        View personLayout = getView().findViewById(R.id.person_summary);
        if(people > 1) {
            Double perPerson = finalAmount/people;
            Double tipDivided = tipAmount/people;
            peoplePay = (TextView) getView().findViewById(R.id.bill_per_person);
            tipPerPerson = (TextView) getView().findViewById(R.id.tip_per_person);
            peoplePay.setText(Double.toString(perPerson));
            tipPerPerson.setText(Double.toString(tipDivided));
            personLayout.setVisibility(View.VISIBLE);
        }
        else{
            personLayout.setVisibility(View.GONE);
        }
    }

}
