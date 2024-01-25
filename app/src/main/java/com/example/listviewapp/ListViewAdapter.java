package com.example.listviewapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

class ListViewAdapter extends ArrayAdapter<String> {
    ArrayList<String> list;
    Context c;

    public ListViewAdapter(Context c, ArrayList<String> items) {
        super(c, R.layout.list_row, items);
        this.c = c;
        list = items;
    }

    @NonNull
    @Override
    public View getView(int pos, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) c.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_row, null);

            TextView num = convertView.findViewById(R.id.number);
            num.setText(pos + 1 + ".");

            TextView name = convertView.findViewById(R.id.number);
            name.setText(list.get(pos));

            ImageView duplicate = convertView.findViewById(R.id.copy);
            ImageView remove = convertView.findViewById(R.id.remove);
            duplicate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MainActivity.addItem(list.get(pos));
                }
            });
            remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MainActivity.removeItem(pos);
                }
            });
        }
        return super.getView(pos, convertView, parent);
    }

}
