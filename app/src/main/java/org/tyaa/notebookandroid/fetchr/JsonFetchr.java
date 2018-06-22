package org.tyaa.notebookandroid.fetchr;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.tyaa.notebookandroid.interfaces.IFetchedDataHandler;
import org.tyaa.notebookandroid.interfaces.IFetchr;
import org.tyaa.notebookandroid.model.News;

import java.util.List;

/**
 * Created by yurii on 18.01.18.
 */

public class JsonFetchr implements IFetchr {

    private IFetchedDataHandler mFetchedDataHandler;

    public JsonFetchr(IFetchedDataHandler _fetchedDataHandler){

        mFetchedDataHandler = _fetchedDataHandler;
    }

    @Override
    public void fetch(Object _args) {

        String urlString = _args.toString();

        RequestQueue queue = Volley.newRequestQueue((Context)mFetchedDataHandler);
        //Log.d("my", urlString);
        JsonObjectRequest jsonObjectRequest =
                new JsonObjectRequest(
                        urlString
                        , new JSONObject()
                        , new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {

                                Log.d("my", response.toString());
                                List<News> news = null;
                                try {
                                    news = new JsonParser().parseNews(response);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                mFetchedDataHandler.onNewsFetched(news);
                            }
                        }
                        , new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                                Log.d("my", error.toString());
                            }
                        }
                );
        queue.add(jsonObjectRequest);
    }
}
