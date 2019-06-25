package com.example.slimify;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import java.util.Random;

public class UserPage extends AppCompatActivity {

    private Toast toast;

    DatabaseHelper mDatabaseHelper;

    private int itemId;
    private String name;
    private String weight;

    private int timeVal = 30;
    private int calVal = 30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_page);

        mDatabaseHelper = new DatabaseHelper(this);

        Intent receivedIntent = getIntent();
        itemId = receivedIntent.getIntExtra("id", -1);
        name = receivedIntent.getStringExtra("name");
        weight = receivedIntent.getStringExtra("weight");

        setTitle(name);

        Button btnStart = findViewById(R.id.start);

        SeekBar time = findViewById(R.id.time);
        time.incrementProgressBy(10);
        SeekBar cals = findViewById(R.id.cals);
        cals.incrementProgressBy(10);

        time.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progress = progress / 10;
                progress = progress * 10;

                timeVal = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                toastMessage(Integer.toString(timeVal));
            }
        });

        cals.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progress = progress / 10;
                progress = progress * 10;

                calVal = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                toastMessage(Integer.toString(calVal));
            }
        });

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent exerciseIntent = new Intent(getApplication(), Exercises.class);
                exerciseIntent.putExtra("time", Integer.toString(timeVal));
                exerciseIntent.putExtra("calsBurned", "0");
                exerciseIntent.putExtra("cals", Integer.toString(calVal));
                exerciseIntent.putExtra("weight", weight);
                startActivity(exerciseIntent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_edit) {
            Intent editScreenIntent = new Intent(this, EditUser.class);
            editScreenIntent.putExtra("id", itemId);
            editScreenIntent.putExtra("name", name);
            editScreenIntent.putExtra("weight", weight);
            startActivity(editScreenIntent);

            return true;
        }
        else if (id == R.id.action_delete) {
            mDatabaseHelper.deleteName(itemId, name, weight);
            toastMessage("removed from database");

            Intent mainIntent = new Intent(getApplication(), MainActivity.class);
            startActivity(mainIntent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void toastMessage(String message){
        if (toast != null) {
            toast.cancel();
        }
        toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }
}
