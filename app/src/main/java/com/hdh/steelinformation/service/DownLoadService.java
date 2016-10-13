package com.hdh.steelinformation.service;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.hdh.steelinformation.R;
import com.hdh.steelinformation.data.ExtractService;
import com.hdh.steelinformation.data.Rule;
import com.hdh.steelinformation.entitys.LinkTypeData;
import com.hdh.steelinformation.entitys.Shop;
import com.hdh.steelinformation.sql.SqlManager;
import com.hdh.steelinformation.utils.CommonUtil;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

/**
 * Created by huangdianhua on 2016/10/12 13:30.
 */
public class DownLoadService extends Service {
    private int year, month, day;
    private String startTime, endTime;
    private List<String> areaList;
    private String[] ziyuans = {"120", "140", "141", "142"};//铁，铜、铝、不锈钢
    private SqlManager sqlManager;

    public void init() {
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        endTime = startTime = year + "-" + (1 + month < 10 ? "0" + 1 + month : 1 + month) + "-" + (day < 10 ? "0" + day : day);
        areaList = Arrays.asList(getResources().getStringArray(R.array.area));
        sqlManager = new SqlManager(getApplication());
    }

    public void downData() {
        for (int i = 0; i < ziyuans.length; i++) {
            for (int j = 0; j < areaList.size(); j++) {
                Rule rule = new Rule("http://www.feigang.net/price.aspx",
                        new String[]{"colid", "area", "keyword", "dateinput1", "dateinput2", "searchtype"}, new String[]{ziyuans[i], areaList.get(j), "", startTime, endTime, "0"},
                        "result_list", Rule.CLASS, Rule.GET);
                rule.setUrlType(ziyuans[i]);
                rule.setAreaType(areaList.get(j));
                new MyAsycnTask().execute(rule);
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class Asycn extends AsyncTask<Void, Integer, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            downData();
            return null;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        init();
        new Asycn().execute();
    }

    /**
     * 下载连接
     */
    class MyAsycnTask extends AsyncTask<Rule, Integer, List<LinkTypeData>> {
        private String urlType;
        private String areaType;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected void onPostExecute(List<LinkTypeData> extracts) {
            super.onPostExecute(extracts);
            for (LinkTypeData linkTypeData : extracts) {
                Rule rule = new Rule(CommonUtil.getRootURL() + linkTypeData.getLinkHref(), null, null, "content-text", Rule.ID, Rule.GET);
                rule.setUrlType(urlType);
                rule.setAreaType(areaType);
                rule.setUrlName(linkTypeData.getLinkText());
                new DetailMyAsycnTask().execute(rule);
            }
        }

        @Override
        protected List<LinkTypeData> doInBackground(Rule... params) {
            urlType = params[0].getUrlType();
            areaType = params[0].getAreaType();
            return ExtractService.extractURL(params[0]);
        }
    }

    /**
     * 下载详细数据
     */
    class DetailMyAsycnTask extends AsyncTask<Rule, Integer, List<List<String>>> {
        private String urlType;
        private String areaType;
        private String urlName;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(List<List<String>> extracts) {
            super.onPostExecute(extracts);
            if (extracts == null || extracts.size() <= 0)
                return;
            for (int i = 0; i < extracts.size(); i++) {
                if (i == 0)
                    continue;
                List<String> extract = extracts.get(i);
                if (urlType.equals("120")) {//铁
                    if (i == extracts.size() - 1)
                        continue;
                    Shop shop = new Shop(urlType, areaType, startTime, urlName, extract.get(0), extract.get(1), extract.get(2), extract.get(3), extract.get(4), extract.get(5), extract.get(6));
                    Log.d("hdh", sqlManager.insertSteel(shop) + "=======11111111111111111111111111111111111");
                } else if (urlType.equals("140")) {//铜
                    Shop shop = new Shop(urlType, areaType, startTime, urlName, extract.get(0), extract.get(1), extract.get(4), extract.get(2), extract.get(3), extract.get(6));
                    Log.d("hdh", sqlManager.insertCopper(shop) + "=======22222222222222222222222222222222222222");
                } else if (urlType.equals("141")) {//铝
                    Shop shop = new Shop(urlType, areaType, startTime, urlName, extract.get(0), extract.get(1), extract.get(2), extract.get(3), extract.get(4), extract.get(5), extract.get(7));
                    Log.d("hdh", sqlManager.insertAluminum(shop) + "========33333333333333333333333333333333333333333");
                } else if (urlType.equals("142")) {//不锈钢
                    Shop shop = new Shop(urlType, areaType, startTime, urlName, extract.get(0), extract.get(1), extract.get(2), extract.get(3), extract.get(4), extract.get(5));
                    Log.d("hdh", sqlManager.insertCopper(shop) + "=======44444444444444444444444444444444444444444444444444444");
                }
            }
        }

        @Override
        protected List<List<String>> doInBackground(Rule... params) {
            urlType = params[0].getUrlType();
            areaType = params[0].getAreaType();
            urlName = params[0].getUrlName();
            return ExtractService.extractString(params[0]);
        }
    }
}
