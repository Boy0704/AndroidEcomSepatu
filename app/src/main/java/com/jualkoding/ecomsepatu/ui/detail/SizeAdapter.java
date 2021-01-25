package com.jualkoding.ecomsepatu.ui.detail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jualkoding.ecomsepatu.R;
import com.jualkoding.ecomsepatu.model.SizeModel;

import java.util.List;

public class SizeAdapter extends RecyclerView.Adapter<SizeAdapter.ViewHolder> {

    private List<SizeModel> rvData;
    private ItemAdapterCallback itemAdapterCallback;

    public SizeAdapter(List<SizeModel> listData, ItemAdapterCallback mItemAdapterCallback){
        rvData = listData;
        itemAdapterCallback = mItemAdapterCallback;
    }

    @NonNull
    @Override
    public SizeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_size, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SizeAdapter.ViewHolder holder, int position) {
        holder.tvSize.setText(rvData.get(position).getLabels());

        holder.tvSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemAdapterCallback.onClick(v);
            }
        });
    }

    @Override
    public int getItemCount() {
        return rvData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvSize;

        public ViewHolder(View v) {
            super(v);
            tvSize = v.findViewById(R.id.tv_size);
        }

    }

    public interface ItemAdapterCallback{
        void onClick(View view);
    }
}
