package com.example.saad.votingproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText email,password;
    private Button btnlogin;
    private TextView tvsignup;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email=findViewById(R.id.email);
        password=findViewById(R.id.pass);
        btnlogin=findViewById(R.id.login);
        tvsignup=findViewById(R.id.notsignup);
        btnlogin.setOnClickListener(this);
        tvsignup.setOnClickListener(this);

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user != null) {
                    firebaseAuth.getInstance().signOut();

                } else {
                    // User is signed out
                    Log.d("a", "onAuthStateChanged:signed_out");
                }

            }
        };
    }

    private void signIn(String email, String password)
    {
        Log.d("a", "signIn:" + email);
        mAuth=FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>()
        {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {


                // If sign in fails, display a message to the user.
                if (task.isSuccessful()) {
                    Intent intent=new Intent(LoginActivity.this,VoteActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(LoginActivity.this, "your id is not registered",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    public void onClick(View view) {
        if(view==btnlogin){

            String Email1=email.getText().toString().trim();
            String Password1=password.getText().toString().trim();
            signIn(Email1,Password1);
        }
        if(view==tvsignup){
            Intent i=new Intent(LoginActivity.this,SignUpActivity.class);
            startActivity(i);
        }
    }
}
