package com.demotxt.myapp.myapplication.activities;

//import android.support.design.widget.CollapsingToolbarLayout;
//import android.support.v7.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.bumptech.glide.request.RequestOptions;
import com.demotxt.myapp.myapplication.R ;

public class EmployeeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Recieve data
        String name  = getIntent().getExtras().getString("employee_name");
        int id = getIntent().getExtras().getInt("id") ;

        // ini views
        TextView tv_studio = findViewById(R.id.id);
        tv_studio.setText(id);

        //collapsingToolbarLayout.setTitle(name);

        RequestOptions requestOptions = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);
    }
}
