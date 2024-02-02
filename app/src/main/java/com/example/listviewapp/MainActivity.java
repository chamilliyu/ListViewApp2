package com.example.listviewapp;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.listviewapp.ClassView;
import com.example.listviewapp.ListViewAdapter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView lv;
    private static ArrayList<ClassView> items;
    private static ListViewAdapter adapter;
    private static Context context;
    private ImageView enter;
    private EditText input;
    private EditText input1;
    private EditText input2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_main);

        lv = findViewById(R.id.listview);
        input = findViewById(R.id.input);
        input1 = findViewById(R.id.input1);
        input2 = findViewById(R.id.input2);
        enter = findViewById(R.id.add);
        context = this; // Use non-static context

        items = new ArrayList<>();
        items.add(new ClassView("2340", "Get ListView Functioning", "11 PM"));
        adapter = new ListViewAdapter(this, items);
        lv.setAdapter(adapter);

        loadContent();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                String clickedItem = lv.getItemAtPosition(i).toString();
                Toast.makeText(MainActivity.this, clickedItem, Toast.LENGTH_SHORT).show();
            }
        });

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = input.getText().toString();
                String text1 = input1.getText().toString();
                String text2 = input2.getText().toString();

                if (text.isEmpty() || text1.isEmpty() || text2.isEmpty()) {
                    makeToast("Enter TO-DO List Item");
                } else {
                    ClassView newItem = new ClassView(text, text1, text2);
                    addItem(newItem);
                    input.setText("");
                    input1.setText("");
                    input2.setText("");
                    makeToast("Added: " + text + " " + text1 + " " + text2);
                }
            }
        });
    }

    public void loadContent() {
        try {
            File path = getApplicationContext().getFilesDir();
            File readFrom = new File(path, "list.txt");
            byte[] content = new byte[(int) readFrom.length()];
            FileInputStream stream = new FileInputStream(readFrom);
            stream.read(content);
            stream.close();
            String s = new String(content);
            s = s.substring(1, s.length() - 1);
            String[] split = s.split(", ");
            for (String item : split) {
                String[] viewData = item.split(";");
                if (viewData.length == 3) {
                    items.add(new ClassView(viewData[0], viewData[1], viewData[2]));
                }
            }
            adapter.notifyDataSetChanged();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            File path = getApplicationContext().getFilesDir();
            FileOutputStream writer = new FileOutputStream(new File(path, "list.txt"));
            writer.write(items.toString().getBytes());
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void removeItem(int position) {
        makeToast("Removed: " + items.get(position));
        items.remove(position);
        adapter.notifyDataSetChanged();
    }

    public void addItem(ClassView item) {
        items.add(item);
        adapter.notifyDataSetChanged();
    }

    // s = message
    private static void makeToast(String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }
}
