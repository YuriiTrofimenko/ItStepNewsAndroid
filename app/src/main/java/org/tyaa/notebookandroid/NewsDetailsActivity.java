package org.tyaa.notebookandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class NewsDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        String detailsString = getIntent().getStringExtra("details");

        TextView detailsTextView = findViewById(R.id.detailsTextView);
        detailsTextView.setText(detailsString);
    }
}
