package com.example.etechk;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {


    EditText uFirstName,uLastName,uEmail,uPassword,uConfirmPassword;

    TextInputLayout userFirstNameWrapper,userLastNameWrapper,userEmailWrapper,
                    userPasswordWrapper, userConfirmPasswordWrapper;

    Button btnRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        uFirstName = findViewById(R.id.userFirstName);
        uLastName = findViewById(R.id.userLastName);
        uEmail = findViewById(R.id.userEmail);
        uPassword = findViewById(R.id.userPassword);
        uConfirmPassword = findViewById(R.id.userConfirmPassword);

        userFirstNameWrapper = findViewById(R.id.userFirstNameWrapper);
        userLastNameWrapper = findViewById(R.id.userLastNameWrapper);
        userEmailWrapper = findViewById(R.id.userEmailWrapper);
        userPasswordWrapper = findViewById(R.id.userPasswordWrapper);
        userConfirmPasswordWrapper = findViewById(R.id.userConfirmPasswordWrapper);

        btnRegister = findViewById(R.id.btnRegister);


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String strFirstName = uFirstName.getText().toString().trim();
                String strLastName = uLastName.getText().toString().trim();
                String strEmail = uEmail.getText().toString().trim();
                String strPassword = uPassword.getText().toString().trim();
                String strConfirmPassword = uConfirmPassword.getText().toString().trim();

                if(strFirstName.isEmpty()){
                    userFirstNameWrapper.setError("Enter First Name");
                    userFirstNameWrapper.requestFocus();
                    return;
                }
                if(strLastName.isEmpty()){
                    userLastNameWrapper.setError("Enter Last Name");
                    userLastNameWrapper.requestFocus();
                    return;
                }
                if(strEmail.isEmpty()){
                    userEmailWrapper.setError("Enter Email");
                    userEmailWrapper.requestFocus();
                    return;
                }
                if(strPassword.isEmpty()){
                    userPasswordWrapper.setError("Enter Password");
                    userPasswordWrapper.requestFocus();
                    return;
                }
                if(strConfirmPassword.isEmpty()){
                    userConfirmPasswordWrapper.setError("Confirm Password");
                    userConfirmPasswordWrapper.requestFocus();
                    return;
                }
                if(!strPassword.equals(strConfirmPassword)){
                    userConfirmPasswordWrapper.setError("Password did not match");
                    userConfirmPasswordWrapper.requestFocus();
                    return;
                }


            }
        });



    }
}
