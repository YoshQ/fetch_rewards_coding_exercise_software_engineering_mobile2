package com.demotxt.myapp.myapplication.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
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
    //RequestOptions option;

    public RecyclerViewAdapter(Context mContext, List<Employee> mData) {
        this.mContext = mContext;
        this.mData = mData;

        // Request option for Glide
        //option = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view ;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.employee_row_item,parent,false) ;
        final MyViewHolder viewHolder = new MyViewHolder(view) ;
        viewHolder.view_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, EmployeeActivity.class);
                i.putExtra("name",mData.get(viewHolder.getAdapterPosition()).getName());
                i.putExtra("id",mData.get(viewHolder.getAdapterPosition()).getId());

                mContext.startActivity(i);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.nameView.setText(mData.get(position).getName());
        holder.idView.setText(mData.get(position).getId());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nameView ;
        TextView idView ;
        LinearLayout view_container;

        public MyViewHolder(View itemView) {
            super(itemView);

            view_container = itemView.findViewById(R.id.container);
            nameView = itemView.findViewById(R.id.employee_name);
            idView = itemView.findViewById(R.id.aa_id);
        }
    }

}
