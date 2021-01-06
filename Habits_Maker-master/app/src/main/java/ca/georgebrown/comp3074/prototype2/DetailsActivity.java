package ca.georgebrown.comp3074.prototype2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

public class DetailsActivity extends AppCompatActivity {
    public DatabaseHandler handler;

    // Doing: let the checkBox be checked only once a day
    //to let checkBoxDone be use once a day - Alan
    Date lastDateDone;
    Date yesterday = new Date(System.currentTimeMillis()-24*60*60*1000);
    String strDate = DateFormat.getDateInstance().format(yesterday); //format: "Dec 4, 2019"

    // TODO: when creating a habit save lastDateDone in the database
    // TODO: when checkBox is checked, update lastDateDone= toDay and saved in the database


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        handler = new DatabaseHandler(this, DatabaseHandler.DATABASE_NAME, null, DatabaseHandler.DATABASE_VERSION);
        final TextView habitName = findViewById(R.id.habit_name);
        final TextView dayNum = findViewById(R.id.day_num);
        Button btnDelete = findViewById(R.id.btnDelete);
        Button btnUpdate = findViewById(R.id.btnUpdate);
        final CheckBox checkBoxDone = findViewById(R.id.cb_done);
        final ProgressBar progressBar = findViewById(R.id.progressBar);
        // Alan's
        final TextView message = findViewById(R.id.checkedMessage);

        dayNum.setText("DAY " + getIntent().getStringExtra("day_count"));
        habitName.setText(getIntent().getStringExtra("name"));
        // Alan's
        handler.getAllHabits();

        //message.setText("Gettin lastDayDone from db: "+ getIntent().getStringExtra("lastDate"));
        message.setText("Check 'Done' and click 'UPDATE' to record your progress");
        try {
            String test = getIntent().getStringExtra("lastDate");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = dateFormat.parse(test);
            message.setText("Gettin lastDayDone from db: "+ getIntent().getStringExtra("lastDate"));
        }
        catch (ParseException e) {

        }


        progressBar.setMax(22);
        progressBar.setProgress(Integer.parseInt(getIntent().getStringExtra("day_count")));

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = habitName.getText().toString();
                handler.delete_habit(name);
                Intent i = new Intent();
                ((Activity) v.getContext()).setResult(Activity.RESULT_OK, i);
                finish();
            }
        });

        //TODO add current date (if checkBox=true in current day locked checkBox until next day

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = getIntent().getStringExtra("name");
                int count = Integer.parseInt(getIntent().getStringExtra("day_count"));
                if (checkBoxDone.isChecked()) {

                    /**
                    if(lastDateDone == yesterday){
                        lastDateDone = toDay;
                        message.setText("Well done");
                    }else{
                        message.setText("Already done!");
                    }
                    */

                    if (count < 22) {
                        count = count + 1;
                    }

                    progressBar.setProgress(count);
                }
                handler.update_habit(name, count);
                Intent i = new Intent();
                ((Activity) v.getContext()).setResult(Activity.RESULT_OK, i);
                finish();
            }
        });
    }


}
