package com.hdh.steelinformation;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hdh.steelinformation.adapter.DetailsAdapter;
import com.hdh.steelinformation.data.ExtractService;
import com.hdh.steelinformation.data.Rule;

import java.util.List;

public class DetailsActivity extends AppCompatActivity {
    private String url;
    private RecyclerView rc_details;
    private ProgressBar progressBar;
    private TextView tv_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        rc_details = (RecyclerView) findViewById(R.id.rc_details);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        tv_title = (TextView) findViewById(R.id.tv_title);
        url = getIntent().getStringExtra("url");
        tv_title.setText(getIntent().getStringExtra("title"));
    }

    @Override
    protected void onStart() {
        super.onStart();
        new MyAsycnTask().execute();
    }

    class MyAsycnTask extends AsyncTask<Void, Integer, List<List<String>>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(List<List<String>> extracts) {
            super.onPostExecute(extracts);
            progressBar.setVisibility(View.GONE);
            if (extracts == null || extracts.size() <= 0)
                return;
            GridLayoutManager g = new GridLayoutManager(getApplicationContext(), extracts.get(0).size());
            rc_details.setLayoutManager(g);
            DetailsAdapter detailsAdapter = new DetailsAdapter(getApplicationContext(), extracts);
            rc_details.addItemDecoration(new AdvanceDecoration(getApplicationContext(), OrientationHelper.VERTICAL, extracts.get(0).size()));
            rc_details.setAdapter(detailsAdapter);
        }

        @Override
        protected List<List<String>> doInBackground(Void... params) {
            Rule rule = new Rule(url, null, null, "content-text", Rule.ID, Rule.GET);
            return ExtractService.extractString(rule);
        }
    }
}
