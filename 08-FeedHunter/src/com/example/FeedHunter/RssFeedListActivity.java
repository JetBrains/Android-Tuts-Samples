package com.example.FeedHunter;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ListView;
import android.widget.Toast;

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

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id)
    {
        ArticleInfo listItem = (ArticleInfo) getListAdapter().getItem(position);
        Toast.makeText(this, "You selected: " + listItem.Title, Toast.LENGTH_LONG).show();
    }
}
