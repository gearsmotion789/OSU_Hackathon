package com.example.slimify;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddUser extends AppCompatActivity {

    DatabaseHelper mDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        setTitle("Add User");

        mDatabaseHelper = new DatabaseHelper(this);
        final EditText name = findViewById(R.id.name);
        final EditText weight = findViewById(R.id.weight);

        Button save = findViewById(R.id.finish);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newName = name.getText().toString();
                String newWeight = weight.getText().toString();

                if (newName.length() != 0 || newWeight.length() != 0) {
                    AddData(newName, newWeight);
                    finish();
                } else {
                    toastMessage("You must put something in the text field!");
                }
            }
        });
    }

    public void AddData(String name, String weight) {
        boolean insertData = mDatabaseHelper.addData(name, weight);

        if (insertData) {
            toastMessage("Data Successfully Inserted!");
        } else {
            toastMessage("Something went wrong");
        }
    }

    private void toastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
