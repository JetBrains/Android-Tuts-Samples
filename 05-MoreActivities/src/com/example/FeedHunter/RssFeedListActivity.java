package com.example.FeedHunter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

public class RssFeedListActivity extends Activity {
    private String url;
    private TextView textViewUrl;
    private Intent currentIntent;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.rssfeedlist);

        currentIntent = getIntent();
        textViewUrl = (TextView) findViewById(R.id.rssUrl);

        // Accessing parameters received from the intent
        Bundle extras = currentIntent.getExtras();
        if (extras != null) {
            url = extras.getString("URL");
            textViewUrl.setText(url);
        }

        // Returning values
        currentIntent.putExtra("Count", 111);
        setResult(Activity.RESULT_OK, currentIntent);

        // use finish() to explicitly terminate an activity
    }
}
