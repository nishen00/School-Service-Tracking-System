package com.example.vehicle;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

public class vehicleAction extends AppCompatActivity {

    private ListView listview;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;
    DatabaseReference detabaseRef;
    List<Students>studentsList;
    public String  userid ;
    public static final String SEND_TEXT = "send";
    public static final String SEND_Name = "name";
    public static final String SEND_ADDRESS = "address";
    public static final String SEND_SCHOOL = "school";
    private String vehiid3;
    public String u;

    studentInfoAdapter find ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_action);
        listview = (ListView)findViewById(R.id.list_view);
        super.onStart();
        vehiid3 = FirebaseAuth.getInstance().getCurrentUser().getUid();


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

               final Students s = studentsList.get(position);

                u = s.getUserid();


              if(s.getInVehicle().equals("YES") || s.getInVehicle().equals(""))
              {

                  AlertDialog.Builder builder = new AlertDialog.Builder(vehicleAction.this);
                  builder.setTitle("QR Code Results..");
                  builder.setMessage("You Change your passenger vehicle in status");
                  builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                      @Override
                      public void onClick(DialogInterface dialog, int which) {

                          FirebaseDatabase database2 = FirebaseDatabase.getInstance();

                          Task<Void> myRef2 = database2.getInstance().getReference("users").child("vehiclePConnection").child(vehiid3).child(u).child("inVehicle").setValue("NO");
                          Task<Void> myRef4 = database2.getInstance().getReference("users").child("Customers").child(u).child("inVehicle").setValue("NO");

                          studentsList.remove(s);
                          studentsList.add(s);


                          studentInfoAdapter a = new studentInfoAdapter(vehicleAction.this,studentsList);
                          listview.setAdapter(a);
                          a.notifyDataSetChanged();




                      }
                  });

                  AlertDialog alert = builder.create();
                  alert.show();


              }

              else if(s.getInVehicle().equals("NO"))
              {

                  AlertDialog.Builder builder = new AlertDialog.Builder(vehicleAction.this);
                  builder.setTitle("passenger Status ");
                  builder.setMessage("You Change your passenger vehicle in status");
                  builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                      @Override
                      public void onClick(DialogInterface dialog, int which) {


                          FirebaseDatabase database = FirebaseDatabase.getInstance();

                          Task<Void> myRef2 = database.getInstance().getReference("users").child("vehiclePConnection").child(vehiid3).child(u).child("inVehicle").setValue("YES");
                          Task<Void> myRef5 = database.getInstance().getReference("users").child("Customers").child(u).child("inVehicle").setValue("YES");
                          studentsList.remove(s);

                          studentInfoAdapter a = new studentInfoAdapter(vehicleAction.this,studentsList);
                          listview.setAdapter(a);

                      }
                  });

                  AlertDialog alert = builder.create();
                  alert.show();







              }



                //Intent intent = new Intent(vehicleAction.this,pop.class);

                //intent.putExtra(SEND_TEXT, s.getUserid() );
                //intent.putExtra(SEND_Name, s.getName() );
                //intent.putExtra(SEND_ADDRESS, s.getAddress() );
                //intent.putExtra(SEND_SCHOOL, s.getSchool() );


               // startActivity(intent);



            }
        });



        userid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        detabaseRef = FirebaseDatabase.getInstance().getReference("users").child("vehiclePConnection").child(userid);

        studentsList = new ArrayList<>();

        display();

    }

    private void display() {

        detabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                studentsList.clear();
                for (DataSnapshot studentnapshot : dataSnapshot.getChildren()) {

                    Students students = studentnapshot.getValue(Students.class);
                    studentsList.add(students);


                }
                studentInfoAdapter studentInfoAdapter = new studentInfoAdapter(vehicleAction.this, studentsList);
                listview.setAdapter(studentInfoAdapter);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




    }



}
