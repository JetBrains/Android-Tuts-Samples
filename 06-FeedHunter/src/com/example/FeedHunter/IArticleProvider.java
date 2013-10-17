package com.example.FeedHunter;

import java.util.ArrayList;

public interface IArticleProvider {
    String getName();
    ArrayList<ArticleInfo> getArticles();
}
