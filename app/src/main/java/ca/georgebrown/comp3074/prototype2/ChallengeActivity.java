package ca.georgebrown.comp3074.prototype2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ChallengeActivity extends AppCompatActivity {

    protected Button btnShare;
    ImageButton home;
    ImageButton profile;
    ImageButton goals;
    ImageButton challenge;
    ImageButton settings;
    CheckBox habit1;
    CheckBox habit2;
    CheckBox habit3;
    EditText msg;
    String toShare = "";
    List<CheckBox> habits = new ArrayList<CheckBox>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge);

        home= (ImageButton) findViewById(R.id.imageButtonHome);
        profile=(ImageButton) findViewById(R.id.imageButtonProfile);
        goals =(ImageButton) findViewById(R.id.imageButtonGoals);
        challenge=(ImageButton) findViewById(R.id.imageButtonChallenge);
        settings=(ImageButton) findViewById(R.id.imageButtonSettings);
        btnShare = findViewById(R.id.btnShare);
        habit1 = findViewById(R.id.cbHabit1);
        habit2 = findViewById(R.id.cbHabit2);
        habit3 = findViewById(R.id.cbHabit3);
        habits.add(habit1);
        habits.add(habit2);
        habits.add(habit3);
        msg = findViewById(R.id.etMessage);



        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(msg.getText().toString().trim().isEmpty()){
                    msg.setText("Try to make more habits too!");
                }
                else {
                    toShare = msg.getText().toString() + "\n";
                }

                for (CheckBox habit : habits){
                    if(habit.isChecked()) {
                        toShare = toShare + habit.getText().toString()+"\n";
                    }

                }

                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, toShare.trim());
                sendIntent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProfilePage.class);
                startActivity(intent);
            }
        });
        goals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), GoalsActivity.class);
                startActivity(intent);
            }
        });
        challenge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Settings.class);
                startActivity(intent);
            }
        });
    }

}
