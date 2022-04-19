package com.example.owais_midterm2;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity3 extends AppCompatActivity {
    DatabaseHelper db;
    EditText deleteT;
    int delnum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        db = new DatabaseHelper(this);

        Button go1 = (Button) findViewById(R.id.button5);
        go1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity3.this, MainActivity.class));
            }
        });

        Button go3 = (Button) findViewById(R.id.button6);
        go3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity3.this, MainActivity2.class));
            }
        });


        Button view = (Button)findViewById(R.id.viewbttn);
        Button delete = (Button)findViewById(R.id.deletebttn);
        deleteT = (EditText)findViewById(R.id.deleteET);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cur = db.getListContents();
                StringBuffer buffer = new StringBuffer();
                while(cur.moveToNext()) {
                    buffer.append("id: " + cur.getString(0)+ "\n");
                    buffer.append("Name: " + cur.getString(1)+ "\n");
                    buffer.append("Quantity: " + cur.getString(2)+ "\n\n");
                }
                AlertDialog.Builder builder = new
                        AlertDialog.Builder(MainActivity3.this);
                builder.setCancelable(true); // a dialog box that can be closed
                builder.setTitle("All Employees");
                builder.setMessage(buffer.toString());
                builder.show();
                Toast.makeText(MainActivity3.this, "Successful View",
                        Toast.LENGTH_LONG).show();
            }
        });


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textdel = deleteT.getText().toString();
                if(db.Delete(textdel)){
                    delnum++;
                    Toast.makeText(MainActivity3.this, delnum+"The Record is Deleted",
                            Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(MainActivity3.this, delnum+"The Record does not exist",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}