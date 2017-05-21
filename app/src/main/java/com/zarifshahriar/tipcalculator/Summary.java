package com.zarifshahriar.tipcalculator;

import android.app.Fragment;
import android.os.Bundle;
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

        System.out.println((Double.toString(bill)));
        System.out.println((Double.toString(tipAmount)));
        System.out.println((Double.toString(finalAmount)));

        billAmount.setText((Double.toString(bill)));
        totalTip.setText(Double.toString(tipAmount));
        finalBill.setText(Double.toString(finalAmount));

        if(people > 1) {
            Double perPerson = finalAmount/people;
            Double tipDivided = tipAmount/people;
            peoplePay.setText(Double.toString(perPerson));
            tipPerPerson.setText(Double.toString(tipDivided));
            personLayout.setVisibility(View.VISIBLE);
        }
        else{
            personLayout.setVisibility(View.GONE);
        }
    }

}
