package com.example.FeedHunter;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;

import java.util.ArrayList;


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
    protected void onPreExecute() {
        Common.initializeProgressBar(parent);
    }


    @Override
    protected void onPostExecute(String result) {
        Bind(articles);

        currentIntent.putExtra("ArticlesCount", articles.size());
        parent.setResult(Activity.RESULT_OK, currentIntent);

        Common.closeProgressBar();
    }



    // Private members
    private ArrayList<ArticleInfo> loadArticles() {
        IArticleProvider provider = new eTennisArticleProvider();
        return provider.getArticles();
    }

    private void Bind(ArrayList<ArticleInfo> articles) {
        ArrayAdapter<ArticleInfo> adapter = new ArticleAdapter(parent, articles);
        parent.setListAdapter(adapter);
    }
}
