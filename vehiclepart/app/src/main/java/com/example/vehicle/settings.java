package com.example.vehicle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class settings extends AppCompatActivity {

    DatabaseReference db;
    FirebaseHelper helper;
    studentAdapter adapter;
    GridView gv;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;
    public String  userid ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        userid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        gv =(GridView) findViewById(R.id.show);

        db = FirebaseDatabase.getInstance().getReference("users").child("vehiclePConnection").child(userid);

        helper = new FirebaseHelper(db);

        adapter = new studentAdapter(settings.this,helper.retrieve());
        gv.setAdapter(adapter);

        Toast.makeText(getApplicationContext(),userid , Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();





    }
}
