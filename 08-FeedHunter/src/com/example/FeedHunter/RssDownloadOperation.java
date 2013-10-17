package com.example.FeedHunter;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.Date;


public class RssDownloadOperation extends AsyncTask<String, Void, String> {

    private ListActivity parent;
    private  ArrayList<ArticleInfo> articles;
    private Intent currentIntent;

    public RssDownloadOperation(ListActivity activity)
    {
        parent = activity;
        currentIntent = activity.getIntent();
    }

    @Override
    protected String doInBackground(String... params) {
        articles = loadArticles();
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        Bind(articles);

        currentIntent.putExtra("ArticlesCount", articles.size());
        parent.setResult(Activity.RESULT_OK, currentIntent);

        Common.closeProgressBar();
    }

    @Override
    protected void onPreExecute() {
        Common.initializeProgressBar(parent);
    }


    private ArrayList<ArticleInfo> loadArticles() {
        // First try to read from storage
        ArticlesContainer container = StorageHelper.readArticles(parent);
        long now = new Date().getTime();
        long expired = container.LastUpdate.getTime() + 20000;   // cache for 20secs
        if (now <= expired) {
            return container.Articles;
        }

        // Data is stale: proceed with getting fresh data
        IArticleProvider provider = new eTennisArticleProvider();
        ArrayList<ArticleInfo> articles = provider.getArticles();

        // Cache for later
        StorageHelper.saveArticles(parent, articles);
        return articles;
    }

    private void Bind(ArrayList<ArticleInfo> articles) {
        ArrayAdapter<ArticleInfo> adapter = new ArticleAdapter(parent, articles);
        parent.setListAdapter(adapter);
    }
}
