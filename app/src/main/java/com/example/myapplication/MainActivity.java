package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static EditText editTextNom;
    public static EditText editTextPrenom;
    public static TextView textViewNom;
    public static Switch switchSexe;
    public static TextView textViewSexe;
    public static TextView textViewPrenom;
    public static ImageView imageViewSignature;
    public static CheckBox checkBoxSignature;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        setContentView(R.layout.activity_main);
        if(savedInstanceState != null && getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {

            editTextNom = (EditText)findViewById(R.id.editTextNom);
            editTextPrenom = (EditText)findViewById(R.id.editTextPrenom);

            textViewPrenom = (TextView)findViewById(R.id.textViewPrenom);
            textViewNom = (TextView)findViewById(R.id.textViewwNom);

            editTextPrenom.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                    textViewPrenom.setText(s.toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            editTextNom.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                    textViewNom.setText(s.toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            textViewSexe = (TextView)findViewById(R.id.textViewSexeP);
            switchSexe = (Switch)findViewById(R.id.switchSexe);
            switchSexe.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked) {
                        Log.i("DIM","checkeeeee");
                        textViewSexe.setText("F");
                    }
                    else {
                        Log.i("DIM","Non checkeee");
                        textViewSexe.setText("H");
                    }
                }
            });

            checkBoxSignature = (CheckBox)findViewById(R.id.checkBoxSignature);
            imageViewSignature = (ImageView)findViewById(R.id.imageViewSignature);
            checkBoxSignature.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked) {
                        imageViewSignature.setVisibility(View.VISIBLE);
                    }
                    else {
                        imageViewSignature.setVisibility(View.INVISIBLE);
                    }
                }
            });


        }


    }

    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);


        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setContentView(R.layout.activity_main);
        }
        else if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            setContentView(R.layout.activity_main);
        }
    }


}
