package com.example.gavinhung.scooterproject2;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {

    //Element
    EditText SignUpActivityAddress,SignUpActivityPassword,SignUpActivity_password2;
    Button SignUpActivitySignUpButton;

    //Firebase
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        SignUpActivitySignUpButton = (Button)findViewById(R.id.SignUpButton);

        auth = FirebaseAuth.getInstance();


        SignUpActivitySignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String SignUpActivityAddressText = ((EditText)findViewById(R.id.SignUpActivity_email)).getText().toString();
                String SignUpActivityPasswordText = ((EditText)findViewById(R.id.SignUpActivity_password)).getText().toString();
                String SignUpActivityPassword2Text = ((EditText)findViewById(R.id.SignUpActivity_password2)).getText().toString();

                if(SignUpActivityAddressText.matches("")||SignUpActivityPasswordText.matches("")) {
                    Toast.makeText(SignUpActivity.this,"請輸入帳號密碼",Toast.LENGTH_LONG).show();
                }else if(SignUpActivityPasswordText.length()<6){
                    Toast.makeText(SignUpActivity.this,"密碼長度，須大於6位",Toast.LENGTH_LONG).show();
                }else{
                    if(!SignUpActivityPasswordText.equals(SignUpActivityPassword2Text)){
                        Toast.makeText(SignUpActivity.this,"兩次密碼必須相符",Toast.LENGTH_LONG).show();
                    }else{
                        auth.createUserWithEmailAndPassword(SignUpActivityAddressText,SignUpActivityPasswordText)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        String message = task.isComplete() ? "註冊成功" : "註冊失敗";
                                        new AlertDialog.Builder(SignUpActivity.this)
                                                .setMessage(message)
                                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        Intent MainActivity = new Intent(SignUpActivity.this, MainActivity.class);
                                                        startActivity(MainActivity);
                                                        SignUpActivity.this.finish();
                                                    }
                                                })
                                                .show();
                                    }
                                });
                    }

                }
            }
        });















    }
}
