package ca.georgebrown.comp3074.prototype2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

public class GoalsActivity extends AppCompatActivity {

    protected ProgressBar pb1,pb2,pb3;
    ImageButton home;
    ImageButton profile;
    ImageButton goals;
    ImageButton challenge;
    ImageButton settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goals);

        home= (ImageButton) findViewById(R.id.imageButtonHome);
        profile=(ImageButton) findViewById(R.id.imageButtonProfile);
        goals =(ImageButton) findViewById(R.id.imageButtonGoals);
        challenge=(ImageButton) findViewById(R.id.imageButtonChallenge);
        settings=(ImageButton) findViewById(R.id.imageButtonSettings);

        GoalFragment newGoalFragment = new GoalFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.container2,newGoalFragment).commit();

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

            }
        });

        challenge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ChallengeActivity.class);
                startActivity(intent);
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
