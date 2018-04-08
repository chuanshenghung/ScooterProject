package com.example.gavinhung.scooterproject2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    Button SignOut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SignOut = (Button) findViewById(R.id.SignOut);

        SignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent LoginActivity = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(LoginActivity);
                MainActivity.this.finish();
            }
        });

    }
}
