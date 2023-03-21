package com.demotxt.myapp.myapplication.adapters;

import android.content.Context;
import android.content.Intent;
//import android.support.v7.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.request.RequestOptions;
import com.demotxt.myapp.myapplication.activities.EmployeeActivity;
import com.demotxt.myapp.myapplication.model.Employee;
import com.demotxt.myapp.myapplication.R ;

import java.util.List;

/**
 * Created by JS on 3/20/2023.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context mContext ;
    private List<Employee> mData ;

    public RecyclerViewAdapter(Context mContext, List<Employee> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view ;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.employee_row_item,parent,false) ;
        final MyViewHolder viewHolder = new MyViewHolder(view) ;
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        //holder.idView.setText(mData.get(position).getId());
        holder.idView.setText("ID: " + mData.get(position).getId());
        holder.listIdView.setText(mData.get(position).getListId());
        holder.nameView.setText(mData.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nameView, idView, listIdView;
        //TextView idView ;
        LinearLayout view_container;

        public MyViewHolder(View itemView) {
            super(itemView);

            view_container = itemView.findViewById(R.id.container);
            idView = itemView.findViewById(R.id.id);
            listIdView = itemView.findViewById(R.id.listId);
            nameView = itemView.findViewById(R.id.employee_name);
        }
    }
}
