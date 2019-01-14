package com.example.saad.votingproject;

import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class VoteActivity extends AppCompatActivity implements View.OnClickListener {
    private Button Votens,Voteik,Votes,VotesI,Result;
    private FirebaseAuth mAuth;
    private long countN,countI;
    TextView a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);
        Votens=findViewById(R.id.votens);
        Voteik=findViewById(R.id.voteik);
        Votes=findViewById(R.id.votes);
        Result=findViewById(R.id.result);
        a=findViewById(R.id.a);
        VotesI=findViewById(R.id.votesI);
        Votens.setOnClickListener(this);
        Voteik.setOnClickListener(this);
        Votes.setOnClickListener(this);

    }

    @Override
    public void onClick(final View view) {
        if(view==Votens){
            String uidN = FirebaseAuth.getInstance().getCurrentUser().getEmail();
            FirebaseDatabase.getInstance().getReference().child("Nawaz").child("Nvoters").push().setValue(uidN);
            Votens.setVisibility(view.GONE);
            Voteik.setVisibility(view.GONE);
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference().child("Nawaz");

            myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot snap: dataSnapshot.getChildren()) {
                        countN=snap.getChildrenCount();
                        String N=Long.toString(countN);
                        Votes.setText(N);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            DatabaseReference myRef1= database.getReference().child("Imran");

            myRef1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot snap: dataSnapshot.getChildren()) {
                        countI=snap.getChildrenCount();
                        String I=Long.toString(countI);
                        VotesI.setText(I);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            new CountDownTimer(15000, 1000) {

                public void onTick(long millisUntilFinished) {
                    a.setText("Calculating Result: " + millisUntilFinished / 1000);
                }

                public void onFinish() {

                    Votes.setVisibility(view.VISIBLE);
                    VotesI.setVisibility(view.VISIBLE);
                    Result.setVisibility(view.VISIBLE);
                    a.setVisibility(view.GONE);
                    String n=Votes.getText().toString().trim();
                    String i=VotesI.getText().toString().trim();
                    final int im=Integer.parseInt(i);
                    final int nw=Integer.parseInt(n);
                    if(im>nw){
                        VotesI.setTextColor(Color.parseColor("#00ff00"));
                        Toast.makeText(VoteActivity.this,"imran",Toast.LENGTH_SHORT).show();
                        Result.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent i=new Intent(VoteActivity.this,Imran.class);
                                Bundle b=new Bundle();
                                b.putString("imran",String.valueOf(im));
                                i.putExtras(b);
                                startActivity(i);
                            }
                        });

                    }
                    else if(nw>im){
                        Votes.setTextColor(Color.parseColor("#00ff00"));
                        Toast.makeText(VoteActivity.this,"nawaz",Toast.LENGTH_SHORT).show();
                        Result.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent i=new Intent(VoteActivity.this,Nawaz.class);
                                Bundle b=new Bundle();
                                b.putString("nawaz",String.valueOf(nw));
                                i.putExtras(b);
                                startActivity(i);
                            }
                        });

                    }
                    else{
                        Toast.makeText(VoteActivity.this,"draw",Toast.LENGTH_SHORT).show();

                    }
                }
            }.start();

        }
        if(view==Voteik){
            String uidI = FirebaseAuth.getInstance().getCurrentUser().getEmail();
            FirebaseDatabase.getInstance().getReference().child("Imran").child("Ivoters").push().setValue(uidI);
            Votens.setVisibility(view.GONE);
            Voteik.setVisibility(view.GONE);
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference().child("Imran");

            myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot snap: dataSnapshot.getChildren()) {
                        long countI=snap.getChildrenCount();
                        String I=Long.toString(countI);
                        VotesI.setText(I);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            DatabaseReference myRef1 = database.getReference().child("Nawaz");

            myRef1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot snap: dataSnapshot.getChildren()) {
                        countN=snap.getChildrenCount();
                        String N=Long.toString(countN);
                        Votes.setText(N);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            new CountDownTimer(15000, 1000) {

                public void onTick(long millisUntilFinished) {
                    a.setText("Calculaing Result: " + millisUntilFinished / 1000);
                }

                public void onFinish() {

                    Votes.setVisibility(view.VISIBLE);
                    VotesI.setVisibility(view.VISIBLE);
                    Result.setVisibility(view.VISIBLE);
                    a.setVisibility(view.GONE);
                    String n=Votes.getText().toString().trim();
                    String i=VotesI.getText().toString().trim();
                    final int im=Integer.parseInt(i);
                    final int nw=Integer.parseInt(n);
                    if(im>nw){
                        VotesI.setTextColor(Color.parseColor("#00ff00"));
                        Toast.makeText(VoteActivity.this,"imran",Toast.LENGTH_SHORT).show();
                        Result.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent i=new Intent(VoteActivity.this,Imran.class);
                                Bundle b=new Bundle();
                                b.putString("imran",String.valueOf(im));
                                i.putExtras(b);
                                startActivity(i);
                            }
                        });

                    }
                    else if(nw>im){
                        Votes.setTextColor(Color.parseColor("#00ff00"));
                        Toast.makeText(VoteActivity.this,"nawaz",Toast.LENGTH_SHORT).show();
                        Result.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent i=new Intent(VoteActivity.this,Nawaz.class);
                                Bundle b=new Bundle();
                                b.putString("nawaz",String.valueOf(nw));
                                i.putExtras(b);
                                startActivity(i);
                            }
                        });

                    }
                    else{
                        Toast.makeText(VoteActivity.this,"draw",Toast.LENGTH_SHORT).show();

                    }
                }
            }.start();
        }
    }
}
