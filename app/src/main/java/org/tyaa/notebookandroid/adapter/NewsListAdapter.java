package org.tyaa.notebookandroid.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.tyaa.notebookandroid.MainActivity;
import org.tyaa.notebookandroid.NewsDetailsActivity;
import org.tyaa.notebookandroid.R;
import org.tyaa.notebookandroid.model.News;

import java.util.List;

/**
 * Created by student on 13.01.2018.
 */

public class NewsListAdapter extends ArrayAdapter<News> {

    private List<News> mNews;
    private Context mContext;
    private int mResource;

    //private LayoutInflater mInflater;

    public NewsListAdapter(@NonNull Context context, int resource, List<News> _news) {

        super(context, resource, _news);

        mNews = _news;
        mContext = context;
        mResource = resource;

        //mInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view;// = convertView;
        //if(view == null) {
            LayoutInflater inflater = ((MainActivity) mContext).getLayoutInflater();
            view = inflater.inflate(mResource, parent, false);
        //}

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Toast.makeText(mContext, mNews.get(position).getCustomer(), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(mContext, NewsDetailsActivity.class);
                intent.putExtra("details", mNews.get(position).getContent());
                ((MainActivity) mContext).startActivityForResult(intent, MainActivity.DETAILS_REQUEST);
            }
        });

        TextView titleTextView = view.findViewById(R.id.titleTextView);
        TextView contententTextView = view.findViewById(R.id.contententTextView);

        titleTextView.setText("Title: " + mNews.get(position).getTitle());
        contententTextView.setText(
                "Task: " +
                (mNews.get(position).getContent().length() > 25
                ? mNews.get(position).getContent().substring(0, 25) + " ..."
                : mNews.get(position).getContent())
        );

        return view;
    }


}
