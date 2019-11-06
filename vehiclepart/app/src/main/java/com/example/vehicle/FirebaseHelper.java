package com.example.vehicle;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FirebaseHelper {
    DatabaseReference db;
    ArrayList<Students> students = new ArrayList<>();


    public FirebaseHelper(DatabaseReference db){

        this.db=db;
    }

    private void fetchData(DataSnapshot dataSnapshot){

        //students.clear();

        for(DataSnapshot ds : dataSnapshot.getChildren())
        {
            Students name = ds.getValue(Students.class);


            students.add(name);


        }

    }

    public ArrayList<Students> retrieve()
    {
       db.addChildEventListener(new ChildEventListener() {
           @Override
           public void onChildAdded(DataSnapshot dataSnapshot, String s) {
               fetchData(dataSnapshot);
           }

           @Override
           public void onChildChanged(DataSnapshot dataSnapshot, String s) {
               fetchData(dataSnapshot);
           }

           @Override
           public void onChildRemoved(DataSnapshot dataSnapshot) {

           }

           @Override
           public void onChildMoved(DataSnapshot dataSnapshot, String s) {

           }

           @Override
           public void onCancelled(DatabaseError databaseError) {

           }
       });
        return students;
    }





}
