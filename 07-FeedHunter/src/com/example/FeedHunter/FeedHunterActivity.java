package com.example.FeedHunter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class FeedHunterActivity extends Activity {

    private int InternalRequestCode;
    private String downloadedLabel;
    private TextView textViewDownloaded;
    private Button buttonConnect;
    private View.OnClickListener connectTapListener;

    public FeedHunterActivity() {
        InternalRequestCode = 1234;
        downloadedLabel = "";
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        initializeWindow();
        initializeApp();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode != InternalRequestCode)
            return;

        Bundle extras = intent.getExtras();
        if (extras != null) {
            updateDownloadedLabel(extras.getInt("ArticlesCount"));
        }
    }

    // Private members
    private void initializeWindow() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.main);
    }

    private void initializeApp() {
        textViewDownloaded = (TextView) findViewById(R.id.labelArticlesCount);
        buttonConnect = (Button) findViewById(R.id.buttonConnect);

        connectTapListener = new View.OnClickListener() {
            public void onClick(View v) {
                goGetFeed();
            }
        };
        buttonConnect.setOnClickListener(connectTapListener);

        updateDownloadedLabel(-1);
        HttpHelpers.initialize(this);
    }

    private void updateDownloadedLabel(int count) {
        if (count >= 0) {
            downloadedLabel = String.format("%d article(s) found.", count);
        }

        textViewDownloaded.setText(downloadedLabel);
    }

    private void goGetFeed() {
        // Jump to another activity to display a RSS list
        Intent i = new Intent(this, RssFeedListActivity.class);
        startActivityForResult(i, InternalRequestCode);
    }
}
