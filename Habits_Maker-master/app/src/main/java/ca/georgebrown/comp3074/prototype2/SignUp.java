package ca.georgebrown.comp3074.prototype2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignUp extends AppCompatActivity {

    EditText txtName;
    EditText txtEmail;
    EditText txtPass;
    EditText txtConfPass;
    Button btnCreateAccount;
    DatabaseHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        dbHandler = new DatabaseHandler(this, DatabaseHandler.DATABASE_NAME, null, DatabaseHandler.DATABASE_VERSION);
        btnCreateAccount = (Button) findViewById(R.id.btnCreateAccount);
        txtName = (EditText) findViewById(R.id.editTextName);
        txtEmail = (EditText) findViewById(R.id.editTextEmail);
        txtPass = (EditText) findViewById(R.id.editTextPassword);
        txtConfPass = (EditText) findViewById(R.id.editTextConfirmPassword);

        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = txtName.getText().toString().trim();
                String email = txtEmail.getText().toString().trim();
                String pass = txtPass.getText().toString().trim();
                String cnfPass = txtConfPass.getText().toString().trim();

                if (pass.equals(cnfPass)) {
                    long val = dbHandler.addUser(name, email, pass);
                    if (val > 0) {
                        int id = (int)val;
                        long insSettings = dbHandler.insert_Settings(id,0,1,1,1,1);
                        Toast.makeText(v.getContext(), "You have registered", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), Login.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(v.getContext(), "Registration error", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(v.getContext(), "Password is not matching", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
