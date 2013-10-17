package com.example.FeedHunter;

import java.util.ArrayList;
import java.util.Date;

public class ArticlesContainer {

    public ArticlesContainer() {
        LastUpdate = new Date();
        Articles = new ArrayList<ArticleInfo>();
    }

    public Date LastUpdate;
    public ArrayList<ArticleInfo> Articles;
}
