package com.example.vehicle;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class studentAdapter extends BaseAdapter {
    Context c;
    ArrayList<Students> students;

    public studentAdapter(Context c, ArrayList<Students> students) {
        this.c = c;
        this.students = students;
    }

    @Override
    public int getCount() {
        return students.size();
    }

    @Override
    public Object getItem(int position) {
        return students.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView!=null)
        {
            convertView= LayoutInflater.from(c).inflate(R.layout.model,parent,true);
        }


        TextView name = (TextView) convertView.findViewById(R.id.CName);
        TextView present = (TextView) convertView.findViewById(R.id.CPresent);

        final Students s = (Students) this.getItem(position);

        name.setText(s.getName());
        present.setText(s.getPresentStatus());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(c,s.getName(),Toast.LENGTH_SHORT).show();
            }
        });


        return convertView;
    }
}
