package com.hdh.steelinformation.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hdh.steelinformation.DetailsActivity;
import com.hdh.steelinformation.R;
import com.hdh.steelinformation.entitys.LinkTypeData;
import com.hdh.steelinformation.utils.CommonUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangdianhua on 2016-10-09 21:55.
 */
public class AddressAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<LinkTypeData> addressInfos;

    public void setAdapterData(List<LinkTypeData> list) {
        if (addressInfos != null) {
            addressInfos.clear();
            addressInfos.addAll(list);
        } else {
            addressInfos = list;
        }
        notifyDataSetChanged();
    }

    public AddressAdapter(Context context, List<LinkTypeData> addressInfos) {
        this.context = context;
        if (addressInfos != null) {
            this.addressInfos = addressInfos;
        } else {
            this.addressInfos = new ArrayList<>();
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (addressInfos.size() <= 0 && position == 0) {
            return R.layout.item_nodata;
        } else {
            return R.layout.item_address;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == R.layout.item_nodata) {
            return new NoDataViewHolder(LayoutInflater.from(context).inflate(R.layout.item_nodata, parent, false));
        }
        if (viewType == R.layout.item_address) {
            return new AddressViewHolder(LayoutInflater.from(context).inflate(R.layout.item_address, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof NoDataViewHolder) {

        }
        if (holder instanceof AddressViewHolder) {
            AddressViewHolder addressViewHolder = (AddressViewHolder) holder;
            addressViewHolder.tv_address.setText(addressInfos.get(position).getLinkText());
            addressViewHolder.tv_address.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String url = CommonUtil.getRootURL() + addressInfos.get(position).getLinkHref();
                    Intent intent = new Intent(context, DetailsActivity.class);
                    intent.putExtra("url", url);
                    intent.putExtra("title", addressInfos.get(position).getLinkText());
                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (addressInfos.size() <= 0) {
            return 1;
        }
        return addressInfos.size();
    }

    class AddressViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_address;

        public AddressViewHolder(View itemView) {
            super(itemView);
            tv_address = (TextView) itemView.findViewById(R.id.tv_address);
        }
    }

    class NoDataViewHolder extends RecyclerView.ViewHolder {

        public NoDataViewHolder(View itemView) {
            super(itemView);
        }
    }
}
