package com.example.listviewapp;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    static ListView lv;
    static ArrayList<String> items;
    static ListViewAdapter adapter;

    EditText input;
    ImageView enter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_main);

        lv = findViewById(R.id.listview);
        input = findViewById(R.id.input);
        enter = findViewById(R.id.add);

        items = new ArrayList<>();
        items.add("Work on Homework 1");
        items.add("Work on Homework 2");
        items.add("Work on Homework 3");
        items.add("Work on Homework 4");
        items.add("Work on Homework 5");

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                String name = items.get(i);
                makeToast(name);
            }
        });

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int i, long l) {
                makeToast("Removed: " + items.get(i));
                removeItem(i);
                return false;
            }
        });
        // connect the code with the andriod app
        adapter = new ListViewAdapter(getApplicationContext(), items);
        lv.setAdapter(adapter);

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = input.getText().toString();
                if (text == null || text.length() == 0) {
                    makeToast("Enter TO-DO List Item");
                } else {
                    addItem(text);
                    input.setText("");
                    makeToast("Added: " + text);
                }
            }
        });
    }

    public static void removeItem(int r) {
        items.remove(r);
//        adapter.notifyDataSetChanged();
        lv.setAdapter(adapter);
    }

    public static void addItem(String item) {
        items.add(item);
        lv.setAdapter(adapter);
    }

    // code that will be used a lot
    Toast t;
    private void makeToast(String s) {
        if (t != null) t.cancel();
        t = Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT);
        t.show();
    }
}
