package com.zarifshahriar.tipcalculator;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

public class Suggestion extends Fragment {

    private RatingBar rating;
    private TextView tip;

    public static Suggestion newInstance() {
        Suggestion fragment = new Suggestion();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.suggestion_frag, container, false);
        rating = (RatingBar) rootView.findViewById(R.id.ratingBar);
        tip = (TextView) rootView.findViewById(R.id.tip);
        rating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            String text;
            Double tipPercent = 10.0;
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                tipPercent = 10.0 + (v*2);
                text = Double.toString(tipPercent) + "%";
                tip.setText(text);
            }
        });
        return rootView;
    }

}
