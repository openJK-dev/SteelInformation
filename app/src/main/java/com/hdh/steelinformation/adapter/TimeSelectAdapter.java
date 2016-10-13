package com.hdh.steelinformation.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hdh.steelinformation.R;


/**
 * Created by huangdianhua on 2016/9/23 09:44.
 */
public class TimeSelectAdapter extends RecyclerView.Adapter<TimeSelectAdapter.TimeViewHolder> {
    private String[] array;
    private Context context;
    private int type;//0-年月，1-表示天
    private SelectItemDate selectItemDate;

    public void setSelectItemDate(SelectItemDate selectItemDate) {
        this.selectItemDate = selectItemDate;
    }

    public interface SelectItemDate {
        void setSelectDate(String date);
    }

    public TimeSelectAdapter(Context context, int type) {
        this.context = context;
        this.type = type;
    }

    public void setData(String[] arrays) {
        array = arrays;

    }

    @Override
    public TimeSelectAdapter.TimeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TimeSelectAdapter.TimeViewHolder viewHolder = null;
        if (viewType == R.layout.item_date) {
            viewHolder = new TimeViewHolder(LayoutInflater.from(context).inflate(R.layout.item_date, parent, false));
        } else if (viewType == R.layout.item_date_day) {
            viewHolder = new TimeViewHolder(LayoutInflater.from(context).inflate(R.layout.item_date_day, parent, false));
        }
        return viewHolder;
    }

    @Override
    public int getItemViewType(int position) {
        if (type == 0) {
            return R.layout.item_date;
        } else if (type == 1) {
            return R.layout.item_date_day;
        } else {
            return R.layout.item_date;
        }
    }

    @Override
    public void onBindViewHolder(TimeSelectAdapter.TimeViewHolder holder, final int position) {
        if ("0".equals(array[position])) {
            holder.tv_time.setVisibility(View.GONE);
        } else {
            holder.tv_time.setVisibility(View.VISIBLE);
            holder.tv_time.setText(array[position]);
            holder.tv_time.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (selectItemDate != null) {
                        selectItemDate.setSelectDate(array[position]);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (array == null || array.length == 0) {
            return 0;
        }
        return array.length;
    }

    class TimeViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_time;

        public TimeViewHolder(View itemView) {
            super(itemView);
            tv_time = (TextView) itemView.findViewById(R.id.tv_time);
        }
    }
}
