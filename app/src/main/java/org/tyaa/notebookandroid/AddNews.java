package org.tyaa.notebookandroid;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import java.net.URLEncoder;

public class AddNews extends AppCompatActivity {

    //private final String mBaseUrl = "http://10.20.60.10:8080/NoteBookServer-war/Api";
    private final String mBaseUrl = "http://it-step-news-1.appspot.com/news";
    //http://it-step-news-1.appspot.com/news?action=fetch-all-news
    private Context mContext;

    @BindView(R.id.contentEditText)
    EditText mContentText;

    @BindView(R.id.titleEditText)
    EditText mTitleEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_add_news);
        ButterKnife.bind((Activity) mContext);
    }

    @OnClick(R.id.addNewsImageButton)
    public void addOrder(View view) {

        String urlString =
                mBaseUrl
                + "?action=create"
                + "&title="
                + prepareString(mTitleEditText.getText().toString())
                + "&content="
                + prepareString(mContentText.getText().toString());

        RequestQueue queue = Volley.newRequestQueue(this);
        //Log.d("my", urlString);
        JsonObjectRequest jsonObjectRequest =
                new JsonObjectRequest(
                        urlString
                        , new JSONObject()
                        , new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        //Log.d("my s", "ok");
                        Toast.makeText(mContext, "Новость была добавлена", Toast.LENGTH_LONG);
                        mTitleEditText.setText("");
                        mContentText.setText("");
                    }
                }
                        , new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        //Log.d("my e", "error");
                        Toast.makeText(mContext, "Error", Toast.LENGTH_LONG);
                        mTitleEditText.setText("");
                        mContentText.setText("");
                    }
                }
                );
        /*StringRequest stringRequest =
                new StringRequest(
                        StringRequest.Method.GET
                        , urlString
                        , new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.d("my s", response.toString());
                                Toast
                                    .makeText(mContext, "New order was added", Toast.LENGTH_LONG)
                                    .show();
                                mCustomerEditText.setText("");
                            }
                        }
                        , new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.d("my e", error.toString());
                                Toast
                                    .makeText(mContext, "Error", Toast.LENGTH_LONG)
                                    .show();
                                mTaskEditText.setText("");
                            }
                        }
                );*/
        queue.add(jsonObjectRequest);
    }

    private String prepareString(String _string){
        try {
            return URLEncoder.encode(_string, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }
}
