package com.hdh.steelinformation.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hdh.steelinformation.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangdianhua on 2016/10/11 13:13.
 */
public class DetailsAdapter extends RecyclerView.Adapter<DetailsAdapter.DetailsViewHolder> {
    private Context context;
    private List<String> detailsList = new ArrayList<>();

    private void createData(List<List<String>> details) {
        detailsList.clear();
        for (List<String> values : details) {
            for (String value : values) {
                detailsList.add(value);
            }
        }
    }

    public void setAdapterData(List<List<String>> list) {
        if (list != null) {
            createData(list);
            notifyDataSetChanged();
        }
    }

    public DetailsAdapter(Context context, List<List<String>> addressInfos) {
        this.context = context;
        if (addressInfos != null) {
            createData(addressInfos);
        }
    }

    @Override
    public DetailsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DetailsViewHolder(LayoutInflater.from(context).inflate(R.layout.item_details, parent, false));
    }

    @Override
    public void onBindViewHolder(DetailsViewHolder holder, final int position) {
        holder.tv_address.setText(detailsList.get(position));
    }

    @Override
    public int getItemCount() {
        return detailsList.size();
    }

    class DetailsViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_address;

        public DetailsViewHolder(View itemView) {
            super(itemView);
            tv_address = (TextView) itemView.findViewById(R.id.tv_address);
        }
    }
}
