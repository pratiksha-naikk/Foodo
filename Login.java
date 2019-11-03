package com.example.fooddonationdelivery;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText e1,e2;
    Button b1;
    DatabaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db = new DatabaseHelper(this);
        e1 = (EditText)findViewById(R.id.editText);
        e2 = (EditText)findViewById(R.id.editText2);
        b1 = (Button) findViewById(R.id.button5);


        e1.addTextChangedListener(loginTextWatcher);
        e2.addTextChangedListener(loginTextWatcher);

    }

    private TextWatcher loginTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String username = e1.getText().toString().trim();
            String password = e2.getText().toString().trim();

            b1.setEnabled(!username.isEmpty() && !password.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };



    public void volunteer1(View v5){

        String email = e1.getText().toString();
        String password = e2.getText().toString();
        Boolean Chkemailpass = db.emailpassword(email,password); //Returns a boolean value TRUE if email and password match
        if (Chkemailpass){
            Toast.makeText(getApplicationContext(),"Successfully Logged in",Toast.LENGTH_SHORT ).show();
            Intent i5 = new Intent(this,volunteer1.class);
            startActivity(i5);
        }
        else
            Toast.makeText(getApplicationContext(),"Invalid Credentials", Toast.LENGTH_SHORT).show();
      }

    public void register(View v10){
        //If the volunteer is new he/she can register by clicking on register button
        Intent i10 = new Intent(this,Register.class);
        startActivity(i10);
    }
}
