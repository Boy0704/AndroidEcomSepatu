package com.jualkoding.ecomsepatu.ui.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.jualkoding.ecomsepatu.R;
import com.jualkoding.ecomsepatu.model.home.Sport;

import java.util.List;

public class SportAdapter extends RecyclerView.Adapter<SportAdapter.ViewHolder> {

    private List<Sport> rvData;
    private ItemAdapterCallback itemAdapterCallback;
    private Context context;

    public SportAdapter(List<Sport> listData, ItemAdapterCallback mItemAdapterCallback){
        rvData = listData;
        itemAdapterCallback = mItemAdapterCallback;
    }

    @NonNull
    @Override
    public SportAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_diskon, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SportAdapter.ViewHolder holder, int position) {
        holder.tvDiskon.setText(rvData.get(position).getDisc());
//        holder.tvDiskon.setVisibility(View.INVISIBLE);
        Glide.with(context).load(rvData.get(position).getPoster()).into(holder.ivPoster);
        holder.ivPoster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemAdapterCallback.onClickSport(v, rvData.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return rvData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView ivPoster;
        public TextView tvDiskon;

        public ViewHolder(View v) {
            super(v);
            tvDiskon = v.findViewById(R.id.tv_diskon);
            ivPoster = v.findViewById(R.id.iv_poster);
        }

    }

    public interface ItemAdapterCallback{
        void onClickSport(View view, Sport sport);
    }


}
