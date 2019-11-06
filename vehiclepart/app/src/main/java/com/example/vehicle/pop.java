package com.example.vehicle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class pop extends Activity {
    private String vehiid3;
    private String UID;
    ToggleButton t;
    boolean value =false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        DatabaseReference getb2 = FirebaseDatabase.getInstance().getReference();
        getb2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                checkData(dataSnapshot);


            }



            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });





        super.onCreate(savedInstanceState);
        TextView name,address ;

        setContentView(R.layout.popup);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        name = (TextView) findViewById(R.id.userID);
        address = (TextView) findViewById(R.id.address);
        t = (ToggleButton) findViewById(R.id.insideStatus);



        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.6),(int)(height*.5));

        Intent intent = getIntent();
        UID = intent.getStringExtra(vehicleAction.SEND_TEXT);
        final String name1 = intent.getStringExtra(vehicleAction.SEND_Name);
        final String address1 = intent.getStringExtra(vehicleAction.SEND_ADDRESS);
        final String school1 = intent.getStringExtra(vehicleAction.SEND_SCHOOL);

        name.setText(name1);
        address.setText(address1);

        t.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {

                        FirebaseDatabase database = FirebaseDatabase.getInstance();

                        Task<Void> myRef2 = database.getInstance().getReference("users").child("vehiclePConnection").child(vehiid3).child(UID).child("inVehicle").setValue("YES");


                }
                else
                {

                        FirebaseDatabase database2 = FirebaseDatabase.getInstance();

                        Task<Void> myRef2 = database2.getInstance().getReference("users").child("vehiclePConnection").child(vehiid3).child(UID).child("inVehicle").setValue("NO");




                }
            }
        });




    }

    @Override
    protected void onStart() {
        super.onStart();
        vehiid3 = FirebaseAuth.getInstance().getCurrentUser().getUid();




    }


    private void checkData(DataSnapshot dataSnapshot) {

        presentStatus present = new presentStatus();

        present.setPresentStatus(dataSnapshot.child("users").child("vehiclePConnection").child(vehiid3).child(UID).getValue(presentStatus.class).getPresentStatus());

        String ps = present.getPresentStatus();


        if(ps.equals("YES")){
            t.setChecked(false);


        }
        else if(ps.equals("NO")){
            t.setChecked(true);

        }
    }
}
