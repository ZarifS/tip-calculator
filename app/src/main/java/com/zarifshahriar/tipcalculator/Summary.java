package com.zarifshahriar.tipcalculator;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class Summary extends AppCompatActivity {

    private final Context context = this;

    private TextView billAmount;
    private TextView finalBill;
    private TextView totalTip;
    private TextView tipPerPerson;
    private TextView peoplePay;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent intent = new Intent(context, MainActivity.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_help:
                    return true;
                case R.id.navigation_settings:
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        String bill = getIntent().getStringExtra("billAmount");
        String tip = getIntent().getStringExtra("tipAmount");
        String people = getIntent().getStringExtra("peopleAmount");
        Double billAmount = Double.parseDouble(bill);
        Double tipAmount = Double.parseDouble(tip);
        int peopleAmount = Integer.parseInt(people);
        calcAmounts(billAmount,tipAmount,peopleAmount);
    }

    private void calcAmounts(Double bill, Double tip, int people) {
        Double tipAmount = bill * tip/100;
        Double finalAmount = tipAmount + bill;

        billAmount = (TextView) findViewById(R.id.bill);
        finalBill = (TextView) findViewById(R.id.total_bill);
        totalTip = (TextView) findViewById(R.id.tip);

        System.out.println((Double.toString(bill)));
        System.out.println((Double.toString(tipAmount)));
        System.out.println((Double.toString(finalAmount)));

        billAmount.setText((Double.toString(bill)));
        totalTip.setText(Double.toString(tipAmount));
        finalBill.setText(Double.toString(finalAmount));

        View personLayout = (View) findViewById(R.id.person_summary);
        if(people > 1) {
            Double perPerson = finalAmount/people;
            Double tipDivided = tipAmount/people;
            peoplePay = (TextView) findViewById(R.id.bill_per_person);
            tipPerPerson = (TextView) findViewById(R.id.tip_per_person);
            peoplePay.setText(Double.toString(perPerson));
            tipPerPerson.setText(Double.toString(tipDivided));
            personLayout.setVisibility(View.VISIBLE);
        }
        else{
            personLayout.setVisibility(View.GONE);
        }
    }

}
