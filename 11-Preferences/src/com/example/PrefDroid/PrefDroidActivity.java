package com.example.PrefDroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.Button;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_LONG;

public class PrefDroidActivity extends Activity {
    private Button buttonPref;
    private View.OnClickListener jumpTapListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        initializeWindow();
        initializeApp();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle item selection
        switch (item.getItemId())
        {
            case R.id.mainMenuItemSettings:
                showPreferences();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    // Private members
    private void initializeWindow() {
        setContentView(R.layout.main);
    }

    private void initializeApp() {
        buttonPref = (Button) findViewById(R.id.buttonPref);
        jumpTapListener = new View.OnClickListener() {
            public void onClick(View v) {
                MatchSettings settings = MatchSettings.loadFromPreferences(getApplicationContext());
                Toast.makeText(getApplicationContext(), settings.toString(), LENGTH_LONG).show();
            }
        };
        buttonPref.setOnClickListener(jumpTapListener);
    }

    private void showPreferences() {
        Intent i = new Intent(this, MatchSettingsActivity.class);
        startActivity(i);
    }

}
