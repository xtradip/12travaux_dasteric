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
import android.view.Menu;
import android.view.MenuItem;
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

import java.io.Serializable;

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
//        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        setContentView(R.layout.activity_main);
        textViewPrenom = (TextView)findViewById(R.id.textViewPrenom);

        textViewNom =  (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) ? (TextView)findViewById(R.id.textViewwNom) : (TextView)findViewById(R.id.textViewNom);
        textViewSexe = (TextView)findViewById(R.id.textViewSexeP);
        imageViewSignature = (ImageView)findViewById(R.id.imageViewSignature);
        imageViewSignature.setVisibility(View.VISIBLE);
        switchSexe = new Switch(getApplicationContext());
        switchSexe.setChecked(false);
        checkBoxSignature = new CheckBox(getApplicationContext());
        checkBoxSignature.setChecked(true);
        if(savedInstanceState != null) {
            switchSexe.setChecked(savedInstanceState.getBoolean("swtichSexe"));
            checkBoxSignature.setChecked(savedInstanceState.getBoolean("checkSignature"));
            Log.i("DIM","[ONCREATE] [SAVED] checkBoxSignature --->" + savedInstanceState.getBoolean("checkSignature"));
            Log.i("DIM","[ONCREATE] [OBJECT] checkBoxSignature --->" + checkBoxSignature.isChecked());
        }
        if(savedInstanceState != null && getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {

            editTextNom = (EditText)findViewById(R.id.editTextNom);
            editTextPrenom = (EditText)findViewById(R.id.editTextPrenom);



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
            Log.i("DIM","[ONCREATE] [OBJECT] checkBoxSignature --->" + checkBoxSignature.isChecked());
            checkBoxSignature.setChecked(savedInstanceState.getBoolean("checkSignature"));
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

                    Intent intentActionNotification = new Intent(Intent.ACTION_VIEW, Uri.parse("https://i.ytimg.com/vi/2En63SCcSEI/maxresdefault.jpg"));
                    PendingIntent pendingIntentAction = PendingIntent.getActivity(MainActivity.this, 0, intentActionNotification, 0);

                    Notification.Action action1 = new Notification.Action.Builder(
                            Icon.createWithResource(MainActivity.this, R.mipmap.ic_launcher_round),
                            "Ouverture page web",
                            pendingIntentAction).build();
                    Log.i("DIM",pendingIntentAction.getIntentSender().toString());

                    Notification n  = new Notification.Builder(MainActivity.this, CHANNEL_ID)
                            .setContentTitle("Notification - Robert Byrka (API 26+)")
                            .setContentText("C'est un exemple de notification avec channel")
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setContentIntent(pIntent)
                            .setNumber(count++)
                            .setAutoCancel(true)
                            .setOngoing(false)       // Impossible de supprimer la notification sauf par programmation
                            .setFullScreenIntent(pIntent, true)
                            .setStyle(new Notification.BigTextStyle().bigText("IMPORTANT"))
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
//                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                             View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            );
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
//            this.requestWindowFeature(Window.FEATURE_NO_TITLE);
            this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            this.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
            setContentView(R.layout.activity_main);
        }

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.menu_toast:
                Toast.makeText(MainActivity.this, "Menu Toast",
                        Toast.LENGTH_SHORT).show();
                return true;

            case R.id.menu_log:
                Toast.makeText(MainActivity.this, "Log effectués",
                        Toast.LENGTH_SHORT).show();
                Log.i("DIM","Menu log !");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("DIM", "onDestroy");
    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.i("DIM", "onPause");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("DIM", "onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("DIM", "onResume");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i("DIM", "onRestoreInstanceState");
        if (savedInstanceState != null ) {

            String nom = savedInstanceState.getString("Nom");
            String prenom = savedInstanceState.getString("Prenom");
            boolean switchSex = savedInstanceState.getBoolean("switchSexe");
            boolean checkSignature = savedInstanceState.getBoolean("checkSignature");

            Log.i("DIM","NOM RESTORE ----> " + nom);
            if(nom != null)
                textViewNom.setText(savedInstanceState.getString("Nom"));
            if(prenom != null)
                textViewPrenom.setText(savedInstanceState.getString("Prenom"));


            if(switchSex)
                textViewSexe.setText("F");
            else
                textViewSexe.setText("H");
            if(checkSignature)
                imageViewSignature.setVisibility(View.VISIBLE);
            else
                imageViewSignature.setVisibility(View.INVISIBLE);
            switchSexe.setChecked(switchSex);
            Log.i("DIM","[ONRESTORE] checkBoxSignature --->" + checkSignature);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i("DIM", "onSaveInstanceState");

        outState.putString("Nom",textViewNom.getText().toString());
        outState.putString("Prenom",textViewPrenom.getText().toString());
        Log.i("DIM","NOM SAVE -> " + textViewNom.getText().toString());

        Log.i("DIM" , "SwitchSexe ----> " + switchSexe.isChecked());
        outState.putBoolean("switchSexe",switchSexe.isChecked());
        outState.putBoolean("checkSignature",checkBoxSignature.isChecked());

        Log.i("DIM","[ONSAVE] checkBoxSignature --->" + checkBoxSignature.isChecked());
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.i("DIM", "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("DIM", "onStop");
    }

}
