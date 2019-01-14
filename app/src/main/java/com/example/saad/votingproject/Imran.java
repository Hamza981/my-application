package com.example.saad.votingproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Imran extends AppCompatActivity {
    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imran);
        tv=findViewById(R.id.res);
        Bundle b=getIntent().getExtras();
        String a=b.getString("imran");
        tv.setText(a);
    }
}
