package com.example.etechk;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {


    EditText uFirstName,uLastName,uEmail,uPassword,uConfirmPassword;

    TextInputLayout userFirstNameWrapper,userLastNameWrapper,userEmailWrapper,
                    userPasswordWrapper, userConfirmPasswordWrapper;

    Button btnRegister;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

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

                if(mAuth.getCurrentUser()!=null){
                    //User is logged in and can redirect to another activity

                }else {

                    final String strFirstName = uFirstName.getText().toString().trim();
                    final String strLastName = uLastName.getText().toString().trim();
                    final String strEmail = uEmail.getText().toString().trim();
                    String strPassword = uPassword.getText().toString().trim();
                    String strConfirmPassword = uConfirmPassword.getText().toString().trim();

                    if (strFirstName.isEmpty()) {
                        userFirstNameWrapper.setError("Enter First Name");
                        userFirstNameWrapper.requestFocus();
                        return;
                    }
                    if (strLastName.isEmpty()) {
                        userLastNameWrapper.setError("Enter Last Name");
                        userLastNameWrapper.requestFocus();
                        return;
                    }
                    if (strEmail.isEmpty()) {
                        userEmailWrapper.setError("Enter Email");
                        userEmailWrapper.requestFocus();
                        return;
                    }
                    if (strPassword.isEmpty()) {
                        userPasswordWrapper.setError("Enter Password");
                        userPasswordWrapper.requestFocus();
                        return;
                    }
                    if (strConfirmPassword.isEmpty()) {
                        userConfirmPasswordWrapper.setError("Confirm Password");
                        userConfirmPasswordWrapper.requestFocus();
                        return;
                    }
                    if (!strPassword.equals(strConfirmPassword)) {
                        userConfirmPasswordWrapper.setError("Password did not match");
                        userConfirmPasswordWrapper.requestFocus();
                        return;
                    }

                    mAuth.createUserWithEmailAndPassword(strEmail,strPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){
                                //We need to add additional info to firebase
                                User user = new User(strFirstName,strLastName,strEmail);
                                FirebaseDatabase.getInstance().getReference("Users")
                                        .child(FirebaseAuth.getInstance().getCurrentUser()
                                                .getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()){
                                            Toast.makeText(RegisterActivity.this,"User Created Successfully",Toast.LENGTH_LONG).show();
                                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                            startActivity(intent);

                                        }else{
                                            Toast.makeText(RegisterActivity.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();

                                        }

                                    }
                                });

                            }else{
                                Toast.makeText(RegisterActivity.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();

                            }


                        }
                    });

                }


            }
        });



    }
}
