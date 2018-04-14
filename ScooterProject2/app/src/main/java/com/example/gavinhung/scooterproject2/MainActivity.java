package com.example.gavinhung.scooterproject2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.white.progressview.CircleProgressView;

public class MainActivity extends AppCompatActivity {

	Button SignOut,Test;
	BottomNavigationView Nav;
	CircleProgressView Circle1,Circle2,Circle3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

		Nav = (BottomNavigationView) findViewById(R.id.navigation);
		BottomNavigationViewHelper.disableShiftMode(Nav);





        SignOut = (Button) findViewById(R.id.Logout);

        SignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent LoginActivity = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(LoginActivity);
                MainActivity.this.finish();
            }
        });


        Test = (Button) findViewById(R.id.Test);

        Test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,PersonalActivity.class);
                startActivity(intent);
            }
        });


        Circle1 = (CircleProgressView) findViewById(R.id.circle1);
		Circle2 = (CircleProgressView) findViewById(R.id.circle2);
		Circle3 = (CircleProgressView) findViewById(R.id.circle3);

		//設定%數
		Circle1.setProgress(100);
		Circle2.setProgress(100);
		Circle3.setProgress(100);

		//設定跑到%數的時間
		Circle1.runProgressAnim(2500);
		Circle2.runProgressAnim(2500);
		Circle3.runProgressAnim(2500);



    }
}
