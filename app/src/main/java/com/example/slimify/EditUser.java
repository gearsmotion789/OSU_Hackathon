package com.example.slimify;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditUser extends AppCompatActivity {

    DatabaseHelper mDatabaseHelper;

    private int itemId;
    private String oldName;
    private String oldWeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        mDatabaseHelper = new DatabaseHelper(this);
        final EditText name = findViewById(R.id.editName);
        final EditText weight = findViewById(R.id.editWeight);
        Button btnSave = findViewById(R.id.save);

        Intent receivedIntent = getIntent();
        itemId = receivedIntent.getIntExtra("id", -1);
        oldName = receivedIntent.getStringExtra("name");
        oldWeight = receivedIntent.getStringExtra("weight");

        name.setText(oldName);
        weight.setText(oldWeight);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newName = name.getText().toString();
                String newWeight = weight.getText().toString();

                if(newName.length() != 0 || newWeight.length() != 0){
                    mDatabaseHelper.updateName(newName, newWeight, itemId, oldName, oldWeight);
                    mDatabaseHelper.updateWeight(newName, newWeight, itemId, newName, oldWeight);
                    toastMessage("updated in database");

                    Intent editScreenIntent = new Intent(getApplication(), UserPage.class);
                    editScreenIntent.putExtra("id", itemId);
                    editScreenIntent.putExtra("name", newName);
                    editScreenIntent.putExtra("weight", newWeight);
                    startActivity(editScreenIntent);

                }else{
                    toastMessage("You must put something in the text field!");
                }
            }
        });
    }

    private void toastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
