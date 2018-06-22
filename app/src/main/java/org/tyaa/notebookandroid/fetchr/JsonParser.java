package org.tyaa.notebookandroid.fetchr;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.tyaa.notebookandroid.model.News;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yurii on 18.01.18.
 */

public class JsonParser {

    public List<News> parseNews(JSONObject _jsonObject) throws JSONException {

        List<News> newsList = new ArrayList<>();
        JSONArray jsonArray = _jsonObject.getJSONArray("data");
        for (int i = 0; i < jsonArray.length(); i++){

            JSONObject newsJSONObject =
                    jsonArray.getJSONObject(i);

            News news = new News();
            news.setId(newsJSONObject.getLong("id"));
            news.setTitle(newsJSONObject.getString("title"));
            news.setContent(newsJSONObject.getString("content"));
            newsList.add(news);
        }

        return newsList;
    }
}
