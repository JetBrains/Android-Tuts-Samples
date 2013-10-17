package com.example.FeedHunter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class FeedHunterActivity extends Activity {

    private Button buttonConnect;
    private View.OnClickListener connectTapListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        initializeWindow();
        initializeApp();
    }

    // Private members
    private void initializeWindow() {
        // Just hides the app's status bar: where notifications, clock and battery status are displayed.
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Attach some UI to the activity
        setContentView(R.layout.main);
    }

    private void initializeApp() {
        buttonConnect = (Button) findViewById(R.id.buttonConnect);
        connectTapListener = new View.OnClickListener() {
            public void onClick(View v) {
                goGetFeed();
            }
        };
        buttonConnect.setOnClickListener(connectTapListener);
    }

    private void goGetFeed() {
        // Jump to another activity to display a RSS list
        Intent i = new Intent(this, RssFeedListActivity.class);

        // Add params to the intent
        i.putExtra("URL", "http://www.e-tennis.me/news/fullrss?tournamentId=1&language=ENG");

        //startActivity(i);
        startActivityForResult(i, 10);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent)
    {
        if (requestCode != 10)	      // Use 10 or whatever request code you set
            return;

        // Proceed otherwise
        Bundle extras = intent.getExtras();
        if (extras != null) {
            int returned = extras.getInt("Count");
            Toast.makeText(this, "Got: " + returned, Toast.LENGTH_LONG).show();
        }
    }
}
