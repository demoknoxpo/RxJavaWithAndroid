package com.example.knoxpo.rxjavatest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.WeakHashMap;

/**
 * Created by knoxpo on 23/8/17.
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyViewHolder> {

    private LayoutInflater mInflater;
    private ArrayList<Weather.City> mItems;

    public ItemAdapter(Context context, ArrayList<Weather.City> items){
        mInflater = LayoutInflater.from(context);
        mItems = items;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = mInflater.inflate(R.layout.item_list,parent,false);
        return new MyViewHolder(v);
    }

    public void addItem(ArrayList<Weather.City> datas){
        mItems.addAll(datas);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.bindMyHolder(mItems.get(position));
    }

    @Override
    public int getItemCount() {
        if(mItems != null) {
            return mItems.size();
        }else{
            return  0;
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView mTitleTV;

        public MyViewHolder(View itemView) {
            super(itemView);
             mTitleTV = (TextView)itemView.findViewById(R.id.tv_title);
            // mSubTitleTV = (TextView)itemView.findViewById(R.id.tv_sub_title);
        }

        public void bindMyHolder(Weather.City str){
            mTitleTV.setText(str.toString());
        }
    }
}
