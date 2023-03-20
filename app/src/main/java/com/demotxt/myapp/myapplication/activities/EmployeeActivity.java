package com.demotxt.myapp.myapplication.activities;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.bumptech.glide.request.RequestOptions;
import com.demotxt.myapp.myapplication.R ;

public class EmployeeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);

        // hide the default actionbar
        getSupportActionBar().hide();

        // Recieve data
        String name  = getIntent().getExtras().getString("employee_name");
        int id = getIntent().getExtras().getInt("id") ;
        //String id = getIntent().getExtras().getString("id");
        //String id = toString(getIntent().getExtras().getInt("id"));
        //String id = getIntent().getExtras().getInt("id").toString();
        //id = id.toString();
        //String idString = id.toString();

        // ini views
        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsingtoolbar_id);
        collapsingToolbarLayout.setTitleEnabled(true);

        TextView tv_name = findViewById(R.id.aa_employee_name);
        TextView tv_studio = findViewById(R.id.aa_id);

        // setting values to each view
        tv_name.setText(name);
        tv_studio.setText(id);

        collapsingToolbarLayout.setTitle(name);

        RequestOptions requestOptions = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);
    }
}
