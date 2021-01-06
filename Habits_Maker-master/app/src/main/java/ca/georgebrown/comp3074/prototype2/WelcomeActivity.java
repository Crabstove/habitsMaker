package ca.georgebrown.comp3074.prototype2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class WelcomeActivity extends AppCompatActivity {

    private ViewPager screenPager;
    WelcomeViewAdapter welcomeViewAdapter;
    TabLayout tabIndicator;
    Button btnDiscover;
    int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        /* //make the activity on full screen (not working)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        */

        //check if app has been opened before
        if (restorePreData()){
            Intent mainActivity = new Intent(getApplicationContext(), Login.class);
            startActivity(mainActivity);
            finish();
        }

        // hide action bar
        getSupportActionBar().hide();

        //ini views
        btnDiscover = findViewById(R.id.btnDiscover);
        tabIndicator = findViewById(R.id.tabIndicator);

        // fill list screen
        final List<ScreenItem> mList = new ArrayList<>();
        mList.add(new ScreenItem("Welcome to Habit Maker!", "Set up new habits everyday with Habit Maker for a new happy life!",R.drawable.img1));
        mList.add(new ScreenItem("Did you know?", "The secret to make a new habit is to make it really simple and do it daily for at least 22 consecutive days",R.drawable.img2));



        screenPager = findViewById(R.id.viewPagerScreen);
        welcomeViewAdapter = new WelcomeViewAdapter(this, mList);
        screenPager.setAdapter(welcomeViewAdapter);

        // setup  tablayout with viewpager
        tabIndicator.setupWithViewPager(screenPager);

        // discover button
        btnDiscover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open main activity
                Intent mainActivity = new Intent(getApplicationContext(), Login.class);
                startActivity(mainActivity);

                // Do not open welcome page for a second time;
                savePrefData();
                finish();
            }
        });

    }

    private boolean restorePreData() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs", MODE_PRIVATE);
        Boolean isWelcomeAvtivityOpenedBefore = pref.getBoolean("isWelcomeOpened", false);
        return isWelcomeAvtivityOpenedBefore;
    }

    private void savePrefData() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("isIntroOpened", true);
        editor.commit();
    }
}
