package com.example.assign15;

import androidx.appcompat.app.AppCompatActivity;

import android.content.CursorLoader;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        String name, isbn;
        Intent i = getIntent();
        int count = (int)i.getIntExtra("Integer",0);

        Uri allTitles = Uri.parse(
                "content://com.example.provider.Books/books");
        Cursor c;
        CursorLoader cursorLoader = new CursorLoader(
                this,
                allTitles, null, null, null,
                "title desc");
        c = cursorLoader.loadInBackground();
        if (c.moveToFirst()) {
            do{
                if(Integer.parseInt(c.getString(c.getColumnIndex(BooksProvider._ID))) == count) {
                    break;
                }
            } while (c.moveToNext());
        }
        name = c.getString(c.getColumnIndex(BooksProvider.TITLE));
        isbn = c.getString(c.getColumnIndex(BooksProvider.ISBN));

        TextView lbl = (TextView) findViewById(R.id.textView);
        lbl.setText(count+"\n\n"+name+"\n\n"+isbn);

        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }
}