package com.example.slimify;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Finished extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finished);

        Intent receivedIntent = getIntent();
        String calsBurned = receivedIntent.getStringExtra("calsBurned");
        String timeSpent = receivedIntent.getStringExtra("time");

        final TextView mCalsBurned = findViewById(R.id.calsBurned);
        final TextView mTimeSpent = findViewById(R.id.timeSpent);
        final Button continueBtn = findViewById(R.id.cont);

        mCalsBurned.setText(calsBurned + " cals burned");
        mTimeSpent.setText(timeSpent + " min spent");

        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startMain = new Intent(getApplication(), MainActivity.class);
                startActivity(startMain);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent startMain = new Intent(getApplication(), MainActivity.class);
        startActivity(startMain);
    }
}
