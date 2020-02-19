package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Icon;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static EditText editTextNom;
    public static EditText editTextPrenom;
    public static TextView textViewNom;
    public static Switch switchSexe;
    public static TextView textViewSexe;
    public static TextView textViewPrenom;
    public static ImageView imageViewSignature;
    public static CheckBox checkBoxSignature;
    public static Button buttonToast;
    public static Button buttonNotification;
    private NotificationManager nm;
    private int count;
    private static String CHANNEL_ID = "id_257";
    private static String CHANNEL_NAME = "channel_257";
    private static String CHANNEL_DESCRIPTION = "description_257";
    private static int NOTIFICATION_ID = 1111;

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
            createNotificationChannel();


            buttonToast = (Button)findViewById(R.id.buttonToast);
            buttonNotification = (Button)findViewById(R.id.buttonNotification);
            buttonToast.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this,"Les 12 Travaux d'AstEric !!",Toast.LENGTH_LONG).show();
                }
            });
            buttonNotification.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Intent intent = new Intent(this,NotificationActivity.class);
//                    PendingIntent pIntent = PendingIntent.getActivity(this,0,intent,0);
                    Intent intent = new Intent(MainActivity.this,NotificationActivity.class);
                    PendingIntent pIntent = PendingIntent.getActivity(MainActivity.this, 0, intent, 0);
                    Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

                    Notification.Action action1 = new Notification.Action.Builder(
                            Icon.createWithResource(MainActivity.this, R.mipmap.ic_launcher_round),
                            "Un",
                            pIntent).build();


                    Notification n  = new Notification.Builder(MainActivity.this, CHANNEL_ID)
                            .setContentTitle("Message - démo INF257 (API 26+)")
                            .setContentText("C'est un exemple de notification avec channel")
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setContentIntent(pIntent)
                            .setNumber(count++)
                            .setAutoCancel(true)
                            .setOngoing(false)       // Impossible de supprimer la notification sauf par programmation
                            .setFullScreenIntent(pIntent, true)
                            .setStyle(new Notification.BigTextStyle().bigText("IMPORTANT"))
                            .setTicker("Hey! tu as recu un message!")
                            .addAction(action1)
                            .build();
                    try{
                        nm.notify(NOTIFICATION_ID, n);
                    }
                    catch(Exception e){
                        Log.i("DICJ", e.getMessage(), e);
                    }



                }
            });

        }


    }

    private void createNotificationChannel() {

        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, importance);
            channel.setDescription(CHANNEL_DESCRIPTION);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            nm = getSystemService(NotificationManager.class);
            //nm = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
            nm.createNotificationChannel(channel);
        }
        else
            Log.i("DIM", "VERSION ANDROID < API 26, Voir exemple Notification_25_moins");
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
            this.requestWindowFeature(Window.FEATURE_NO_TITLE);
            this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            this.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
            setContentView(R.layout.activity_main);
        }
        else if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            this.requestWindowFeature(Window.FEATURE_NO_TITLE);
            this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            this.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
            setContentView(R.layout.activity_main);
        }
    }


}
