package com.example.FeedHunter;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class RssFeedListActivity extends ListActivity {
    private String url = "";
    private Intent currentIntent;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.rssfeedlist);

        currentIntent = getIntent();
        Bundle extras = currentIntent.getExtras();
        if (extras != null) {
            url = extras.getString("URL");
        }

        // Get feed
        int count = loadArticles();

        // Set results
        currentIntent.putExtra("ArticlesCount", count);
        setResult(Activity.RESULT_OK, currentIntent);
    }

    private int loadArticles() {
        if (url == null || url == "")
            return 0;

        IArticleProvider provider = new FakeArticleProvider();
        ArrayList<ArticleInfo> articles = provider.getArticles();
        Bind(articles);
        return articles.size();
    }

    private void Bind(ArrayList<ArticleInfo> articles) {
        ArrayAdapter<ArticleInfo> adapter = new ArticleAdapter(this, articles);
        setListAdapter(adapter);
    }
}
