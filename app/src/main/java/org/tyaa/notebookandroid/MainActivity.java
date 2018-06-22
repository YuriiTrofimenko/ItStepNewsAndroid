package org.tyaa.notebookandroid;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.view.View;

import org.tyaa.notebookandroid.adapter.NewsListAdapter;
import org.tyaa.notebookandroid.fetchr.JsonFetchr;
import org.tyaa.notebookandroid.interfaces.IFetchedDataHandler;
import org.tyaa.notebookandroid.model.News;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ListActivity implements IFetchedDataHandler {

    //private ListView mNotesListView;
    private List<News> mNews;
    private NewsListAdapter mAdapter;
    private FloatingActionButton mFloatingActionButton;
    private Context mContext;

    public static final int DETAILS_REQUEST = 0;
    public static final int ADD_NEWS_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        mContext = this;

        setContentView(R.layout.activity_main);

        mFloatingActionButton = findViewById(R.id.fab);
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, AddNews.class);
                startActivityForResult(intent, MainActivity.ADD_NEWS_REQUEST);
            }
        });

        //mNotesListView = findViewById(android.R.id.list);

        //mNews = generateOrders();
        mNews = new ArrayList<>();

        mAdapter =
                new NewsListAdapter(this, R.layout.news_list_item, mNews);

        //mNotesListView.setAdapter(adapter);
        setListAdapter(mAdapter);

        getAllNews();
    }

    /*private List<News> generateOrders(){

        List<News> news = new ArrayList<>();
        for (int i = 0; i < 10; i++){

            News news = new News();
            news.setCustomer("Customer " + i);
            news.setId(i);
            news.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum");
            news.add(news);
        }
        return news;
    }*/

    @Override
    public void onNewsFetched(List _orders) {

        mNews.clear();
        mNews.addAll(_orders);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MainActivity.ADD_NEWS_REQUEST){
            getAllNews();
        }
    }

    private void getAllNews(){

        new JsonFetchr(this)
                //.fetch("http://10.20.60.10:8080/NoteBookServer-war/Api?action=get_orders");
                .fetch("http://it-step-news-1.appspot.com/news?action=fetch-all-news");
        //.fetch("http://10.0.3.2:8080/NoteBookServer-war/Api?action=get_orders");
    }
}
