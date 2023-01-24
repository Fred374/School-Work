package com.example.assign15;

import androidx.appcompat.app.AppCompatActivity;

import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class SecondActivity extends AppCompatActivity {

    String[][] arr = new String[40][3];
    ArrayList<String> arr2 = new ArrayList<>();
    ArrayList<String> arr3 = new ArrayList<>();
    ArrayAdapter<String> adapter;

    int j = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Uri allTitles = Uri.parse(
                "content://com.example.provider.Books/books");
        Cursor c;
        CursorLoader cursorLoader = new CursorLoader(
                this,
                allTitles, null, null, null,
                "title desc");
        c = cursorLoader.loadInBackground();
        if (c.moveToFirst()) {
            int i = 0;
            do{
                arr[i][0] = c.getString(c.getColumnIndex(BooksProvider._ID));
                arr[i][1] = c.getString(c.getColumnIndex(BooksProvider.TITLE));
                arr[i][2] = c.getString(c.getColumnIndex(BooksProvider.ISBN));
                arr2.add(arr[i][1]);
                i++;
            } while (c.moveToNext());
        }
        ListView listView = (ListView)findViewById(R.id.listview);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setTextFilterEnabled(true);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arr2);
        listView.setAdapter(adapter);

        int count = arr2.size();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent doing = new Intent("com.example.assign15.ThirdActivity");
                if (j%2 == 0) {
                    final int finalCount = count-position;
                    doing.putExtra("Integer", finalCount);
                } else {
                    position++;
                    doing.putExtra("Integer", position);
                }
                startActivity(doing);
            }
        });
    }

    public void onClickSort(View v) {
        if (j == 0) {
            int i = arr2.size()-1;
            while (i > -1) {
                arr3.add(arr2.get(i));
                i--;
            }
        }
        ListView listView = (ListView)findViewById(R.id.listview);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setTextFilterEnabled(true);
        if (j % 2 == 0) {
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arr3);
        } else {
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arr2);
        }
        j++;
        listView.setAdapter(adapter);
    }

    public void onClickSearch(View v) {
        EditText search = (EditText)findViewById(R.id.searchbar);
        String search1 = search.getText().toString();
        for(int i = 0; i < arr2.size(); i++) {
            if(arr[i][1].contains(search1)) {
                Intent doing = new Intent("com.example.assign15.ThirdActivity");
                doing.putExtra("Integer", Integer.parseInt(arr[i][0]));
                startActivity(doing);
                break;
            } else if (arr[i][2].contains(search1)) {
                Intent doing = new Intent("com.example.assign15.ThirdActivity");
                doing.putExtra("Integer", Integer.parseInt(arr[i][0]));
                startActivity(doing);
                break;
            }
        }
    }

    public void onClickBack(View v) {
        finish();
    }
}