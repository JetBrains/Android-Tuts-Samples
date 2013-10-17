package com.example.FeedHunter;

import java.util.ArrayList;

public class FakeArticleProvider implements IArticleProvider {
    public String getName() {
        return "Sample data";
    }

    public ArrayList<ArticleInfo> getArticles() {
        ArrayList<ArticleInfo> articles = new ArrayList<ArticleInfo>();
        ArticleInfo a1 =  new ArticleInfo();
        a1.Title = "First article";
        a1.Abstract = "Abstract of first article";
        articles.add(a1);

        ArticleInfo a2 =  new ArticleInfo();
        a2.Title = "Second article";
        a2.Abstract = "Abstract of second article";
        articles.add(a2);
        return articles;
    }
}
