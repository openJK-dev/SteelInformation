package com.hdh.steelinformation;

import android.app.FragmentManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.hdh.steelinformation.adapter.AddressAdapter;
import com.hdh.steelinformation.customview.TitleView;
import com.hdh.steelinformation.data.ExtractService;
import com.hdh.steelinformation.data.Rule;
import com.hdh.steelinformation.entitys.LinkTypeData;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recycle;
    private ProgressBar progressBar;
    private AddressAdapter addressAdapter;
    private TitleView titleview;
    private Spinner spinnerziyuan, spinnerarea;
    private SwipeRefreshLayout swp;
    private TextView tv_stime, tv_etime;
    private int year, month, day;
    private String startTime, endTime;
    private String area = "江苏";
    private String ziyuan = "120";
    private List<String> areaList;
    private Button btn_search;
    private String[] ziyuans = {"120", "140", "141", "142"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recycle = (RecyclerView) findViewById(R.id.recycle);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        titleview = (TitleView) findViewById(R.id.titleview);
        spinnerarea = (Spinner) findViewById(R.id.spinnerarea);
        spinnerziyuan = (Spinner) findViewById(R.id.spinnerziyuan);
        tv_etime = (TextView) findViewById(R.id.tv_etime);
        tv_stime = (TextView) findViewById(R.id.tv_stime);
        btn_search = (Button) findViewById(R.id.btn_search);
        swp = (SwipeRefreshLayout) findViewById(R.id.swp);

        swp.setColorSchemeResources(R.color.colorPrimary, R.color.colorAccent, R.color.orange);
        areaList = Arrays.asList(getResources().getStringArray(R.array.area));
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        endTime = startTime = year + "-" + (1 + month < 10 ? "0" + 1 + month : 1 + month) + "-" + (day < 10 ? "0" + day : day);
        tv_stime.setText(startTime);
        tv_etime.setText(endTime);
        titleview.setTitle(true, "资讯快报");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recycle.setLayoutManager(linearLayoutManager);
        addressAdapter = new AddressAdapter(this, null);
        recycle.setAdapter(addressAdapter);
        Rule rule = new Rule("http://www.feigang.net/price.aspx",
                new String[]{"colid", "area", "keyword", "dateinput1", "dateinput2", "searchtype"}, new String[]{ziyuan, area, "", startTime, endTime, "0"},
                "result_list", Rule.CLASS, Rule.GET);
        new MyAsycnTask().execute(rule);

        swp.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Rule rule = new Rule("http://www.feigang.net/price.aspx",
                        new String[]{"colid", "area", "keyword", "dateinput1", "dateinput2", "searchtype"}, new String[]{ziyuan, area, "", startTime, endTime, "0"},
                        "result_list", Rule.CLASS, Rule.GET);
                new MyAsycnTask().execute(rule);
            }
        });
        tv_stime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                DateSelectDialogFragment dialogFragment = new DateSelectDialogFragment();
                dialogFragment.show(manager, "day");
                dialogFragment.setSelectItemDate(new DateSelectDialogFragment.SelectItemDate() {
                    @Override
                    public void setSelectDate(String date) {
                        tv_stime.setText(date);
                        startTime = date;
                    }
                });
            }
        });
        tv_etime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                DateSelectDialogFragment dialogFragment = new DateSelectDialogFragment();
                dialogFragment.show(manager, "day");
                dialogFragment.setSelectItemDate(new DateSelectDialogFragment.SelectItemDate() {
                    @Override
                    public void setSelectDate(String date) {
                        tv_etime.setText(date);
                        endTime = date;
                    }
                });
            }
        });
        spinnerarea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                area = areaList.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerziyuan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ziyuan = ziyuans[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Rule rule = new Rule("http://www.feigang.net/price.aspx",
                        new String[]{"colid", "area", "keyword", "dateinput1", "dateinput2", "searchtype"}, new String[]{ziyuan, area, "", startTime, endTime, "0"},
                        "result_list", Rule.CLASS, Rule.GET);
                new MyAsycnTask().execute(rule);
            }
        });
        //startService(new Intent(this, DownLoadService.class));
    }

    class MyAsycnTask extends AsyncTask<Rule, Integer, List<LinkTypeData>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(List<LinkTypeData> extracts) {
            super.onPostExecute(extracts);
            progressBar.setVisibility(View.GONE);
            addressAdapter.setAdapterData(extracts);
            swp.setRefreshing(false);
        }

        @Override
        protected List<LinkTypeData> doInBackground(Rule... params) {
            /*Rule rule = new Rule("http://www.feigang.net/price.aspx",
                    new String[]{"colid", "area", "keyword", "dateinput1", "dateinput2", "searchtype"}, new String[]{"120", "", "江苏", "2015-10-10", "2016-10-09", "0"},
                    "result_list", Rule.CLASS, Rule.GET);*/
            return ExtractService.extractURL(params[0]);
        }
    }
}
