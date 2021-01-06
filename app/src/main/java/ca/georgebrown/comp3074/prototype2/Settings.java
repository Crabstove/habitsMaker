package ca.georgebrown.comp3074.prototype2;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Settings extends AppCompatActivity {

    TextView tv_btn;
    Switch disAll;
    Switch remind;
    Switch tips;
    Switch recom;
    Switch challeng;
    DatabaseHandler dbHandler;
    ImageButton home;
    ImageButton profile;
    ImageButton goals;
    ImageButton challenge;
    ImageButton settings;
    ImageView logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        dbHandler = new DatabaseHandler(this, DatabaseHandler.DATABASE_NAME, null, DatabaseHandler.DATABASE_VERSION);
        tv_btn = findViewById(R.id.tv_btn);
        disAll = (Switch) findViewById(R.id.s_dis);
        remind = (Switch) findViewById(R.id.s_rem);
        tips = (Switch) findViewById(R.id.s_tip);
        //recom = (Switch) findViewById(R.id.s_rec);
        //challeng = (Switch) findViewById(R.id.s_chal);
        home = (ImageButton) findViewById(R.id.imageButtonHome);
        profile =(ImageButton) findViewById(R.id.imageButtonProfile);
        goals =(ImageButton) findViewById(R.id.imageButtonGoals);
        challenge = (ImageButton) findViewById(R.id.imageButtonChallenge);
        settings = (ImageButton) findViewById(R.id.imageButtonSettings);
        logout = findViewById(R.id.imageButtonLogout);

        disAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    //long val = dbHandler.insert_Settings(0,0,0,0,0);
                    long val = 1;
                    if (val > 0) {
                        remind.setChecked(false);
                        tips.setChecked(false);
                        //recom.setChecked(false);
                        //challeng.setChecked(false);
                        Toast.makeText(getBaseContext(), "Your settings are all OFF", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(getBaseContext(), "Changing settings error", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    //long val = dbHandler.insert_Settings(1,1,1,1,1);
                    long val = 1;
                    if (val > 0) {
                        remind.setChecked(true);
                        tips.setChecked(true);
                        //recom.setChecked(true);
                        //challeng.setChecked(true);
                        Toast.makeText(getBaseContext(), "Your settings are all ON", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(getBaseContext(), "Changing settings error", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        tv_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAbout();
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

        logout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
            }
        });

    }

    public void openAbout(){
        Intent intent = new Intent(this, About.class);
        startActivity(intent);
    }

}
