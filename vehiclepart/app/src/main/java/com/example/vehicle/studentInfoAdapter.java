package com.example.vehicle;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class studentInfoAdapter extends ArrayAdapter<Students> {
    private Activity context;
    Context c ;
    private List<Students> studentList;
    String name;

    public studentInfoAdapter(Activity context, List<Students>studentList ){

        super(context,R.layout.list_view,studentList);
        this.context=context;
        this.studentList=studentList;

    }


    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View listview = inflater.inflate(R.layout.list_view,null,true);
        TextView SName = (TextView)listview.findViewById(R.id.CName);
        TextView invehicle = (TextView)listview.findViewById(R.id.invehicle);

        TextView present = (TextView)listview.findViewById(R.id.CPresent);
        Students student = studentList.get(position);

        String pre = present.getText().toString();

        if(pre.equals("NO"))
        {
            LinearLayout l = (LinearLayout)listview.findViewById(R.id.prelay);
            l.setBackgroundColor(Color.RED);
        }

        SName.setText(student.getName());

        present.setText(student.getPresentStatus());

        invehicle.setText(student.getInVehicle());

        final String name = SName.getText().toString();








        return listview;




    }




}
