package ca.georgebrown.comp3074.prototype2;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    private Button btnLogin;
    private Button signUp;
    private EditText txtEmail;
    private EditText txtPass;
    DatabaseHandler dbHandler;
    Switch disAll;
    Switch remind;
    Switch tips;
    Switch recom;
    Switch challeng;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dbHandler = new DatabaseHandler(this, DatabaseHandler.DATABASE_NAME, null, DatabaseHandler.DATABASE_VERSION);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        txtEmail = (EditText) findViewById(R.id.editTextEmail);
        txtPass = (EditText) findViewById(R.id.editTextPassword);
        disAll = (Switch) findViewById(R.id.s_dis);
        remind = (Switch) findViewById(R.id.s_rem);
        tips = (Switch) findViewById(R.id.s_tip);
        //recom = (Switch) findViewById(R.id.s_rec);
        //challeng = (Switch) findViewById(R.id.s_chal);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = txtEmail.getText().toString().trim();
                String pwd = txtPass.getText().toString().trim();
                Boolean res = dbHandler.checkUser(email, pwd);

                if (res) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(v.getContext(),"Wrong username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });

        signUp = (Button) findViewById(R.id.btnSingUp);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignUp.class);
                startActivity(intent);
            }
        });
    }
}
