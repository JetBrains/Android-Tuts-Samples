package com.example.FeedHunter;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpHelpers
{
    private static Context _context;

    public static void initialize(Context context)
    {
        _context = context;
    }

    public static Boolean isInternetAvailable()
    {
        ConnectivityManager cm = (ConnectivityManager) _context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm == null)
            return false;

        NetworkInfo ni = cm.getActiveNetworkInfo();
        if (ni != null && ni.isAvailable() && ni.isConnected())
            return true;
        return false;
    }

    public static String DownloadRss(String url)
    {
        String text = "";
        try {
            DefaultHttpClient client = new DefaultHttpClient();
            HttpResponse response = client.execute(new HttpGet(url));
            HttpEntity entity = response.getEntity();
            text = EntityUtils.toString(entity);
        }
        catch(IOException ioException)
        {
        }
        return text;
    }
}
