package com.example.FeedHunter;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.Date;

public class StorageHelper {
    private static final String StorageArticlesArea = "Articles-Area";

    public static void saveArticles(Context context, ArrayList<ArticleInfo> articles)
    {
        SharedPreferences storage = context.getSharedPreferences(StorageArticlesArea, Context.MODE_PRIVATE);
        SharedPreferences.Editor e = storage.edit();
        int i=0;
        e.putInt("articlesCount", articles.size());
        e.putLong("lastUpdate", new Date().getTime());
        for(ArticleInfo item : articles)
        {
            String key = "article" + i;
            String value = String.format("%s|%s|%s", item.Title, item.Abstract, item.Link);
            e.putString(key, value);
            i++;
        }
        e.commit();
    }

    public static ArticlesContainer readArticles(Context context)
    {
        SharedPreferences storage = context.getSharedPreferences(StorageArticlesArea, Context.MODE_PRIVATE);
        ArticlesContainer container = new ArticlesContainer();
        long lastUpdate = storage.getLong("lastUpdate", 0);
        int articlesCount = storage.getInt("articlesCount", 0);
        Date current = new Date();
        current.setTime(lastUpdate);
        container.LastUpdate = current;

        for(int i=0; i<articlesCount; i++)
        {
            String key = "article" + i;
            String value = storage.getString(key, "");
            String[] tokens = value.split("\\|");       // Escape to avoid it is assumed to be a reg-ex
            ArticleInfo article = new ArticleInfo();
            article.Title = tokens[0];
            article.Abstract = tokens[1];
            article.Link = tokens[2];
            container.Articles.add(article);
        }
        return container;
    }
}
