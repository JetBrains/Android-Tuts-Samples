package com.example.FeedHunter;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Window;

public class RssFeedListActivity extends ListActivity {

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.rssfeedlist);

        RssDownloadOperation op = new RssDownloadOperation(this);
        op.execute("");
    }
}
