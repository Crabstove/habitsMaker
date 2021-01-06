package ca.georgebrown.comp3074.prototype2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

public class AddingActivity extends AppCompatActivity {
    public DatabaseHandler handler;
    CheckBox checkBoxBuild;
    CheckBox checkBoxQuit;
    String type = "";

    ListView recommentations; // Alan

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding);
        final EditText editText = findViewById(R.id.editText);
        handler = new DatabaseHandler(this, DatabaseHandler.DATABASE_NAME, null, DatabaseHandler.DATABASE_VERSION);
        Button btnSave = findViewById(R.id.btnSave);
        checkBoxBuild = findViewById(R.id.cb_build);
        checkBoxQuit = findViewById(R.id.cb_quit);
        recommentations = findViewById(R.id.listViewRecommendations); //Alan

        //RECOMENDATION LIST
        final ArrayList<String> recommentationsList = new ArrayList<>();
        recommentationsList.add("Take a glass of water first thing in the morning");
        recommentationsList.add("Do 5 push-ups");
        recommentationsList.add("Meditate (do 5 minutes breathing exercise)");
        recommentationsList.add("Plan your next day");
        recommentationsList.add("One cigarette less than usual");
        recommentationsList.add("Read a book for 5 minutes");
        recommentationsList.add("One apple a day keeps the doctor away");
        recommentationsList.add("Help with the dishes");
        recommentationsList.add("Take a short walk today");
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_activated_1,recommentationsList);
        recommentations.setAdapter(arrayAdapter);
        recommentations.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            //recommendations from listView to editText - Alan
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                editText.setText(recommentationsList.get(position));
            }
        });


        checkBoxBuild.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    checkBoxQuit.setEnabled(false);
                    type = checkBoxBuild.getText().toString();
                } else {
                    checkBoxQuit.setEnabled(true);
                }
            }
        });
        checkBoxQuit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    checkBoxBuild.setEnabled(false);
                    type = checkBoxQuit.getText().toString();
                } else {
                    checkBoxBuild.setEnabled(true);
                }
            }
        });


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editText.getText().toString();

                if (TextUtils.isEmpty(name) || !checkBoxBuild.isChecked() && !checkBoxQuit.isChecked()) {
                    Toast.makeText(v.getContext(), "Empty inputs", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        int id = (int)handler.insert_habit(name, type, 1);
                        handler.insert_Settings(id, 1,1,1,1,1);

                        Intent intent = new Intent();
                        if (checkBoxBuild.isChecked()) {
                            intent.putExtra("checkbox", checkBoxBuild.getText().toString());
                        } else if (checkBoxQuit.isChecked()) {
                            intent.putExtra("checkbox", checkBoxQuit.getText().toString());
                        }
                        ((Activity) v.getContext()).setResult(Activity.RESULT_OK, intent);
                        finish();
                    } catch (Exception e) {
                        Toast.makeText(v.getContext(), "Name Exists, Please enter new Name!!!", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });


    }


}
