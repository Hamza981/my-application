package com.example.saad.votingproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Nawaz extends AppCompatActivity {

    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nawaz);
        tv=findViewById(R.id.res);
        Bundle b=getIntent().getExtras();
        String a=b.getString("nawaz");
        tv.setText(a);
    }
}
