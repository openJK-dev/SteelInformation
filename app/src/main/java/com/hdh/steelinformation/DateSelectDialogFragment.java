package com.hdh.steelinformation;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hdh.steelinformation.adapter.TimeSelectAdapter;

import java.util.Calendar;

public class DateSelectDialogFragment extends DialogFragment {
    private TextView tv_year;
    private TextView tv_month;
    private LinearLayout ll_week;
    private RecyclerView recycle;
    private String type;
    private TimeSelectAdapter timeSelectAdapter = null;
    private SelectItemDate selectItemDate;
    private String year, month, day;

    public void setSelectItemDate(SelectItemDate selectItemDate) {
        this.selectItemDate = selectItemDate;
    }

    public interface SelectItemDate {
        void setSelectDate(String date);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR) + "";
        month = calendar.get(Calendar.MONTH) + 1 < 10 ? "0" + calendar.get(Calendar.MONTH) + 1 : calendar.get(Calendar.MONTH) + 1 + "";
        day = calendar.get(Calendar.DAY_OF_MONTH) < 10 ? "0" + calendar.get(Calendar.DAY_OF_MONTH) : calendar.get(Calendar.DAY_OF_MONTH) + "";
        type = getTag();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_date_select, null, false);
        tv_year = (TextView) view.findViewById(R.id.tv_year);
        tv_month = (TextView) view.findViewById(R.id.tv_month);
        ll_week = (LinearLayout) view.findViewById(R.id.ll_week);
        recycle = (RecyclerView) view.findViewById(R.id.recycle);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        tv_month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type = "month";
                tv_year.setVisibility(View.VISIBLE);
                ll_week.setVisibility(View.GONE);
                tv_month.setText(month + "月" + day + "日");
                recycle.setLayoutManager(layoutManager);
                timeSelectAdapter = new TimeSelectAdapter(getActivity(), 0);
                timeSelectAdapter.setData(createArry(12, 12));
                tv_year.setText(year + "");
                recycle.setAdapter(timeSelectAdapter);
                timeSelectAdapter.setSelectItemDate(new TimeSelectAdapter.SelectItemDate() {
                    @Override
                    public void setSelectDate(String date) {
                        month = date;
                        type = "day";
                        tv_year.setVisibility(View.VISIBLE);
                        ll_week.setVisibility(View.VISIBLE);
                        tv_month.setText(month + "月" + day + "日");
                        tv_year.setText(year);
                        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 7);
                        recycle.setLayoutManager(gridLayoutManager);
                        timeSelectAdapter = new TimeSelectAdapter(getActivity(), 1);
                        timeSelectAdapter.setData(createArray(1, getDaysByYearMonth(year, month), getWeekdayByYearMonth(year, month) - 1));
                        recycle.setAdapter(timeSelectAdapter);
                        timeSelectAdapter.setSelectItemDate(new TimeSelectAdapter.SelectItemDate() {
                            @Override
                            public void setSelectDate(String date) {
                                if (selectItemDate != null) {
                                    if ("day".equals(type)) {
                                        selectItemDate.setSelectDate(year + "-" + month + "-" + date);
                                        dismiss();
                                    }
                                }
                            }
                        });
                    }
                });
            }
        });

            tv_year.setVisibility(View.VISIBLE);
            ll_week.setVisibility(View.VISIBLE);
            tv_month.setText(month + "月" + day + "日");
            tv_year.setText(year);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 7);
            recycle.setLayoutManager(gridLayoutManager);
            timeSelectAdapter = new TimeSelectAdapter(getActivity(), 1);
            timeSelectAdapter.setData(createArray(1, getDaysByYearMonth(year, month), getWeekdayByYearMonth(year, month) - 1));

        recycle.setAdapter(timeSelectAdapter);
        timeSelectAdapter.setSelectItemDate(new TimeSelectAdapter.SelectItemDate() {
            @Override
            public void setSelectDate(String date) {
                if (selectItemDate != null) {
                    if ("day".equals(type)) {
                        selectItemDate.setSelectDate(year + "-" + month + "-" + date);
                        dismiss();
                    }
                }
            }
        });
    }


    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            DisplayMetrics dm = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
            dialog.getWindow().setLayout((int) (dm.widthPixels * 0.8), (int) (dm.heightPixels * 0.6));
        }
    }

    /**
     * 根据年月获取月天数
     *
     * @param year
     * @param month
     * @return
     */
    public int getDaysByYearMonth(String year, String month) {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, Integer.parseInt(year));
        a.set(Calendar.MONTH, Integer.parseInt(month) - 1);
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }

    /**
     * 获得每个月第一天的星期
     *
     * @param year
     * @param month
     * @return
     */
    public int getWeekdayByYearMonth(String year, String month) {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, Integer.parseInt(year));
        a.set(Calendar.MONTH, Integer.parseInt(month) - 1);
        a.set(Calendar.DAY_OF_MONTH, 1);
        return a.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 构建数据
     *
     * @param index     从什么数开始
     * @param length    实际显示长度
     * @param perLength 前面空的数
     * @return
     */
    public String[] createArray(int index, int length, int perLength) {
        String[] strArr = new String[length + perLength];
        for (int i = 0; i < length + perLength; i++) {
            if (i < perLength) {
                strArr[i] = "0";
                continue;
            }
            int value = index + i - perLength;
            if (value < 10) {
                strArr[i] = "0" + value;
            } else {
                strArr[i] = "" + value;
            }
        }
        return strArr;
    }

    public String[] createArry(int index, int length) {
        String[] strArr = new String[length];
        for (int i = 0; i < length; i++) {
            int value = index - i;
            if (value < 10) {
                strArr[i] = "0" + value;
            } else {
                strArr[i] = "" + value;
            }
        }
        return strArr;
    }
}
