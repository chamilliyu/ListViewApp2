package com.example.listviewapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.listviewapp.ClassView;
import com.example.listviewapp.MainActivity;
import com.example.listviewapp.R;

import java.util.ArrayList;

public class ListViewAdapter extends ArrayAdapter<ClassView> {

    private Context context;
    private ArrayList<ClassView> arrayList;

    public ListViewAdapter(@NonNull Context context, ArrayList<ClassView> arrayList) {
        super(context, 0, arrayList);
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View currentItemView = convertView;
        if (currentItemView == null) {
            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_row, parent, false);
        }

        ClassView currentNumberPosition = getItem(position);

        ImageView remove = currentItemView.findViewById(R.id.remove);

        TextView textView1 = currentItemView.findViewById(R.id.name);
        assert currentNumberPosition != null;
        textView1.setText(currentNumberPosition.getClassName());

        TextView textView2 = currentItemView.findViewById(R.id.name1);
        textView2.setText(currentNumberPosition.getToDo());

        TextView textView3 = currentItemView.findViewById(R.id.name2);
        textView3.setText(currentNumberPosition.getTime());

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) context).removeItem(position);
                Toast.makeText(context, "Item removed at position: " + position, Toast.LENGTH_SHORT).show();
            }
        });
        return currentItemView;
    }
}
