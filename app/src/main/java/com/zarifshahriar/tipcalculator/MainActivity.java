package com.zarifshahriar.tipcalculator;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final Context context = this;
    private EditText bill;
    private EditText tip;
    private EditText people;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    selectedFragment = Welcome.newInstance();
                    break;
                case R.id.navigation_help:
                    selectedFragment = Suggestion.newInstance();
                    break;
                case R.id.navigation_settings:
                    break;
            }
            android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.content, selectedFragment);
            transaction.commit();
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.content, Welcome.newInstance());
        transaction.commit();
    }

    public void getTip(View view){
        bill = (EditText) findViewById(R.id.bill_amount);
        tip = (EditText) findViewById(R.id.tip_amount);
        people = (EditText) findViewById(R.id.people_amount);
        if(validateInput()){
            Toast.makeText(this, "Please enter all fields.", Toast.LENGTH_SHORT).show();
        }
        else {
            String tipAmount = tip.getText().toString();
            String peopleAmount = people.getText().toString();
            String billAmount = bill.getText().toString();
            Bundle bundle = new Bundle();
            bundle.putString("billAmount", billAmount);
            bundle.putString("tipAmount", tipAmount);
            bundle.putString("peopleAmount", peopleAmount);
            Log.i("In calculate tip", "Sending to summary page");
            Fragment fragobj = new Summary();
            fragobj.setArguments(bundle);
            android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.content, fragobj);
            transaction.commit();
        }

    }

    public boolean validateInput(){
        return ((bill.getText().toString().equals("")) || (tip.getText().toString().equals("")) || (people.getText().toString().equals("")));
    }

}
