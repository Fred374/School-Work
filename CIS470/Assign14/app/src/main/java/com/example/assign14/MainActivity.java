package com.example.assign14;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBAdapter db = new DBAdapter(this);

        Button btn0 = (Button) findViewById(R.id.but1);
        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText txt_name = (EditText) findViewById(R.id.txtName);
                EditText txt_email = (EditText) findViewById(R.id.txtEmail);
                db.open();
                db.insertContact(txt_name.getText().toString(), txt_email.getText().toString());
                db.close();
                Toast.makeText(getApplicationContext(), "Contact Inserted", Toast.LENGTH_LONG).show();
            }
        });

        Button btn1 = (Button) findViewById(R.id.but2);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.open();
                Cursor c = db.getAllContacts();
                if (c.moveToFirst()) {
                    do {
                        DisplayContact(c);
                    } while (c.moveToNext());
                }
                db.close();
            }
        });

        Button btn2 = (Button) findViewById(R.id.but3);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText txt_rowId = (EditText) findViewById(R.id.txtRowId);
                long rowId = (long) Integer.parseInt(txt_rowId.getText().toString());
                Cursor c;
                db.open();
                c = db.getContact(rowId);
                DisplayContact(c);
                db.close();
            }
        });

        Button btn3 = (Button) findViewById(R.id.but4);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText txt_name = (EditText) findViewById(R.id.txtName);
                EditText txt_email = (EditText) findViewById(R.id.txtEmail);
                EditText txt_rowId = (EditText) findViewById(R.id.txtRowId);
                long rowId = (long) Integer.parseInt(txt_rowId.getText().toString());
                db.open();
                db.updateContact(rowId, txt_name.getText().toString(), txt_email.getText().toString());
                db.close();
                Toast.makeText(getApplicationContext(), "Entry Updated", Toast.LENGTH_LONG).show();
            }
        });

        Button btn4 = (Button) findViewById(R.id.but5);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText txt_rowId = (EditText) findViewById(R.id.txtRowId);
                long rowId = (long) Integer.parseInt(txt_rowId.getText().toString());
                db.open();
                db.deleteContact(rowId);
                db.close();
                Toast.makeText(getApplicationContext(), "Entry Deleted", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void DisplayContact(Cursor c) {
        Toast.makeText(this,
                "id: " + c.getString(0) + "\n" +
                "Name: " + c.getString(1) + "\n" +
                "Email: " + c.getString(2) + "\n",
                Toast.LENGTH_LONG).show();
    }
}