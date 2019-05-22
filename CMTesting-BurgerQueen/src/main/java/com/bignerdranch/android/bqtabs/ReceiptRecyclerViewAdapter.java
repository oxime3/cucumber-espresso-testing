package com.bignerdranch.android.bqtabs;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class ReceiptRecyclerViewAdapter extends RecyclerView.Adapter<ReceiptRecyclerViewAdapter.ReceiptViewHolder> {
    private Context mContext;
    private List<MenuItem> mData;



    public ReceiptRecyclerViewAdapter(Context context, List<MenuItem> mData) {
        this.mContext = context;
        this.mData = mData;

    }

    @NonNull
    @Override
    public ReceiptViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.receipt_item,parent,false);
        final ReceiptRecyclerViewAdapter.ReceiptViewHolder vHolder = new ReceiptRecyclerViewAdapter.ReceiptViewHolder(v);

        return vHolder;
    }

    @Override
    public int getItemCount() {
        if (mData != null){
            int r = mData.size();
            String x = String.valueOf(r);
            Log.i("itemsize",x);
            return r;}
        return 0;
    }

    @Override
    public void onBindViewHolder(@NonNull ReceiptRecyclerViewAdapter.ReceiptViewHolder holder, int position) {
        holder.tv_name.setText(mData.get(position).getName());
        holder.tv_cost.setText(mData.get(position).getPrice());
    }

    public static class ReceiptViewHolder extends RecyclerView.ViewHolder{
        private LinearLayout item_receipt;
        private TextView tv_name;
        private TextView tv_cost;


        public ReceiptViewHolder(@NonNull View itemView) {
            super(itemView);

            item_receipt = itemView.findViewById(R.id.receipt_layout);
            tv_name = itemView.findViewById(R.id.itemName);
            tv_cost = itemView.findViewById(R.id.itemCost);
        }
    }
}
