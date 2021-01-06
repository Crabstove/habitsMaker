package ca.georgebrown.comp3074.prototype2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ContactListActivity extends AppCompatActivity {

    protected Button invite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        invite = findViewById(R.id.btnInvite);
        invite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(v.getContext(),"Invitation sent!",Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }
}
