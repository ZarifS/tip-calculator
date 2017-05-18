package com.zarifshahriar.tipcalculator;

import android.content.Intent;
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

    private EditText bill;
    private EditText tip;
    private EditText people;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
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
        setContentView(R.layout.activity_main);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    public void calculateTip(View view){
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
            Intent intent = new Intent(this, Summary.class);
            intent.putExtra("billAmount", billAmount);
            intent.putExtra("tipAmount", tipAmount);
            intent.putExtra("peopleAmount", peopleAmount);
            Log.i("In calculate tip", "Sending to summary page");
            startActivity(intent);
        }

    }

    public boolean validateInput(){
        return ((bill.getText().toString().equals("")) || (tip.getText().toString().equals("")) || (people.getText().toString().equals("")));
    }

}
