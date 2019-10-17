package com.example.schoolofrock;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity  extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    EditText voterEditText;

    Spinner songSpinner;

    String song;
    String voter;

    Intent intent;


    DBHandler dbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        voterEditText= (EditText) findViewById(R.id.voterEditText);
        songSpinner= (Spinner)findViewById(R.id.songSpinner);


        ArrayAdapter<CharSequence> songAdapter=ArrayAdapter.createFromResource(this, R.array.song, android.R.layout.simple_spinner_item);
        songAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        songSpinner.setAdapter(songAdapter);
        songSpinner.setOnItemSelectedListener(this);



        dbHandler= new DBHandler(this, null);

    }





    public void  addVoteLIke(MenuItem menuItem){


        voter= voterEditText.getText().toString();
        if((voter.trim().equals(""))||(song.trim().equals(""))){
            Toast.makeText(this, "Please select a song and enter your name!", Toast.LENGTH_LONG).show();
        }

        else{
            dbHandler.addStudent(song, voter, 1);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this);



            // set it icon, title, and text
            builder.setSmallIcon(R.mipmap.ic_launcher);
            builder.setContentTitle("Rock The Vote");
            builder.setContentText("You like "+song);

            // initialize an intent for the view list activity

//            intent = new Intent(this, ViewList.class);
            intent= new Intent(MainActivity.this, MainActivity.class);

            // initialize a pending intent
            PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);


            // set the content intent of the notification
            builder.setContentIntent(pendingIntent);

            // Initialize a Notification Manager
            NotificationManager notificationManager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

            // have the NotifcationManager send the Notification

            notificationManager.notify(2142, builder.build());
//            Toast.makeText(this, name+" added!", Toast.LENGTH_LONG).show();

        }



    }

    public void addVoteDislike(MenuItem menuItem){

        voter= voterEditText.getText().toString();
        if((voter.trim().equals(""))||(song.trim().equals(""))){
            Toast.makeText(this, "Please select a song and enter your name!", Toast.LENGTH_LONG).show();
        }

        else{
            dbHandler.addStudent(song, voter, 0);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this);



            // set it icon, title, and text
            builder.setSmallIcon(R.mipmap.ic_launcher);
            builder.setContentTitle("Rock The Vote");
            builder.setContentText("You dislike "+song);

            // initialize an intent for the view list activity

//            intent = new Intent(this, ViewList.class);
            intent= new Intent(MainActivity.this, MainActivity.class);

            // initialize a pending intent
            PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);


            // set the content intent of the notification
            builder.setContentIntent(pendingIntent);

            // Initialize a Notification Manager
            NotificationManager notificationManager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

            // have the NotifcationManager send the Notification

            notificationManager.notify(2142, builder.build());
//            Toast.makeText(this, name+" added!", Toast.LENGTH_LONG).show();

        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        switch (item.getItemId()){
//            case R.id.action_like:
//                addVoteLIke(item);
//                return true;
//            case R.id.action_dislike:
//                addVoteDislike(item);
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//
//        }
//
//    }


//

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {


            song= adapterView.getItemAtPosition(position).toString();


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
