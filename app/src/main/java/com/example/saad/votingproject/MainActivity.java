package com.example.saad.votingproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btnLogin,btnSignup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLogin=findViewById(R.id.login);
        btnSignup=findViewById(R.id.signup);
        btnLogin.setOnClickListener(this);
        btnSignup.setOnClickListener(this);
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            String uid = FirebaseAuth.getInstance().getCurrentUser().getEmail();
            Toast.makeText(this,uid,Toast.LENGTH_SHORT).show();
            FirebaseAuth.getInstance().signOut();
        }

    }

    @Override
    public void onClick(View view) {
        if(view==btnLogin){
            Intent i=new Intent(MainActivity.this,LoginActivity.class);
            startActivity(i);
        }
        if(view==btnSignup){
            Intent i=new Intent(MainActivity.this,SignUpActivity.class);
            startActivity(i);
        }

    }
}
