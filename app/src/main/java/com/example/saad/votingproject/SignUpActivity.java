package com.example.saad.votingproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText email,password;
    private Button btnsignup;
    private TextView tvlogin;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        email=findViewById(R.id.email);
        password=findViewById(R.id.pass);
        btnsignup=findViewById(R.id.signup);
        tvlogin=findViewById(R.id.haveaccount);
        btnsignup.setOnClickListener(this);
        tvlogin.setOnClickListener(this);
    }
    private void registerUser(){
        String Email=email.getText().toString().trim();
        String Password=password.getText().toString().trim();
        if(TextUtils.isEmpty(Email)){
            Toast.makeText(this,"plaese enter the email address",Toast.LENGTH_LONG);
            return;
        }
        if(TextUtils.isEmpty(Password)){

            Toast.makeText(this,"plaese enter the password",Toast.LENGTH_LONG);
            return;
        }
        mAuth=FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Intent intent=new Intent(SignUpActivity.this,LoginActivity.class);
                    startActivity(intent);
                    mAuth.signOut();
                }
                else
                {
                    Toast.makeText(SignUpActivity.this,"Plz enter correct format of email and password",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void onClick(View view) {

            if (view == btnsignup) {

                registerUser();
        }
        if(view==tvlogin){
            Intent i=new Intent(SignUpActivity.this,LoginActivity.class);
            startActivity(i);
        }
    }
}
