package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    public static TextView textViewReponse;
    public static String RESPONSE_SECOND_ACTIVITY;
    public static EditText editTextResponseSA;
    public static Button buttonResponseSA;
    public Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        intent = getIntent();
        String randomNumber = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        textViewReponse = (TextView) findViewById(R.id.textViewReponse);
        textViewReponse.setText("Nombre aléatoire envoyé depuis MainActivity : " + randomNumber);


        buttonResponseSA = (Button)findViewById(R.id.buttonResponseSA);
        buttonResponseSA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextResponseSA = (EditText)findViewById(R.id.editTextResponseSA);

                intent.putExtra(RESPONSE_SECOND_ACTIVITY,editTextResponseSA.getText().toString());
                setResult(2,intent);
                finish();
            }
        });





    }
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            getWindow().getDecorView().setSystemUiVisibility(
//                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            );
        }
    }
}
