package com.example.FeedHunter;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

public class eTennisArticleProvider implements IArticleProvider {
    public String getName() {
        return "e-tennis.ME";
    }

    public ArrayList<ArticleInfo> getArticles() {
        if (!HttpHelpers.isInternetAvailable())
        {
            ArrayList<ArticleInfo> articles = new ArrayList<ArticleInfo>();
            ArticleInfo info = new ArticleInfo();
            info.Title = "No articles found.";
            info.Abstract = "Check your Internet connection.";
            articles.add(info);
            return articles;
        }

        String url = "http://www.e-tennis.me/news/fullrss?tournamentId=1&language=ENG";
        String rss = HttpHelpers.DownloadRss(url);
        ArrayList<ArticleInfo> articles = ParseToArticles(rss);
        return articles;
    }

    private static ArrayList<ArticleInfo> ParseToArticles(String rss)
    {
        ArrayList<ArticleInfo> articles = new ArrayList<ArticleInfo>();
        Document doc = DomFromString(rss);
        NodeList nodes = doc.getElementsByTagName("entry");
        int size = Math.min(nodes.getLength(), 20);

        // For each ENTRY node
        for (int i=0; i<size; i++) {
            Node rssEntry = nodes.item(i);
            NodeList entryNodes = rssEntry.getChildNodes();
            ArticleInfo a = new ArticleInfo();
            Node title = entryNodes.item(1);
            a.Title = title.getTextContent();
            Node summary = entryNodes.item(2);
            a.Abstract = summary.getTextContent();
            articles.add(a);
        }

        return articles;
    }

    private static Document DomFromString(String xml)
    {
        Document doc = null;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(xml));
            doc = db.parse(is);
        } catch (ParserConfigurationException e) {
            System.out.println("XML parse error: " + e.getMessage());
            return null;
        } catch (SAXException e) {
            System.out.println("Wrong XML file structure: " + e.getMessage());
            return null;
        } catch (IOException e) {
            System.out.println("I/O exception: " + e.getMessage());
            return null;
        }

        return doc;
    }
}
