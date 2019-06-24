package com.example.slimify;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Exercises extends AppCompatActivity {

    private Toast toast;

    private String weight;
    private String time;
    private String cals;
    boolean countDownDone = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises);

        Intent receivedIntent = getIntent();
        time = receivedIntent.getStringExtra("time");
        cals = receivedIntent.getStringExtra("cals");
        weight = receivedIntent.getStringExtra("weight");

        final TextView mTextField = findViewById(R.id.TimerTextView);
        final Button nextSkipBtn = findViewById(R.id.button2);
        final TextView exerciseName = findViewById(R.id.textView3);



        new CountDownTimer(5000, 1000) {

            public void onTick(long millisUntilFinished) {
                mTextField.setText("seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                mTextField.setText("done!");
                nextSkipBtn.setText("Next");
                countDownDone = true;
            }
        }.start();

        nextSkipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(countDownDone) {
                    Intent exerciseIntent = new Intent(getApplication(), Exercises.class);
                    exerciseIntent.putExtra("time", time);
                    exerciseIntent.putExtra("cals", cals);
                    exerciseIntent.putExtra("weight", weight);
                    startActivity(exerciseIntent);
                }
            }
        });

        int met = findMet(Integer.valueOf(weight), Integer.valueOf(cals));
        //toastMessage(Integer.toString(met));
        String exercise = getExercise(met);
        exerciseName.setText(exercise);
    }

    String level1[] = {
            "sitting"
    };

    String level2[] = {
            "walking"
    };

    String level3[] = {
            "walking"
    };

    String level4[] = {
            "Stair Master"
    };

    String level5[] = {
            "Stair Master"
    };

    String level6[] = {
            "Cycling"
    };

    String level7[] = {
            "cycling"
    };

    String level8[] = {
            "Row Machine"
    };

    String level9[] = {
            "Running"
    };

    String  exerciseList[][] = {
            level1,
            level2,
            level3,
            level4,
            level5,
            level6,
            level7,
            level8,
            level9,
    };

    private int findMet(int weight, float desiredCals)
    {
        float Rate = desiredCals/10;
        float met = (float) (Rate/(0.0175*(weight/2.2)));

        return (int) met;
    }

    private String getExercise(int met){
        int metVal = met-1;
        if(metVal < 0)
            metVal = 0;

        Random rand = new Random();

        String level[] = exerciseList[metVal];
        int randomVal = rand.nextInt(level.length);
        String exercise = level[randomVal];

        return exercise;
    }

    private void toastMessage(String message){
        if (toast != null) {
            toast.cancel();
        }
        toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }
}
