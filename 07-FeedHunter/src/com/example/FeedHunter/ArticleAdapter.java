package com.example.FeedHunter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ArticleAdapter extends ArrayAdapter<ArticleInfo>
{
    private final Activity context;
    private final ArrayList<ArticleInfo> articles;

    public ArticleAdapter(Activity context, ArrayList<ArticleInfo> articles)
    {
        super(context, R.layout.listitem, articles);
        this.context = context;
        this.articles = articles;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder holder;
        View listItemView;

        listItemView = convertView;
        if (convertView == null)
        {
            LayoutInflater inflater = context.getLayoutInflater();
            listItemView = inflater.inflate(R.layout.listitem, null, true);
            holder = new ViewHolder();
            holder.Title = (TextView) listItemView.findViewById(R.id.title);
            holder.Summary = (TextView) listItemView.findViewById(R.id.summary);
            listItemView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        // Set the text to display
        ArticleInfo info = articles.get(position);
        holder.Title.setText(info.Title);
        holder.Summary.setText(info.Abstract);
        return listItemView;
    }

    static class ViewHolder
    {
        public TextView Title;
        public TextView Summary;
        public TextView Link;
    }
}
