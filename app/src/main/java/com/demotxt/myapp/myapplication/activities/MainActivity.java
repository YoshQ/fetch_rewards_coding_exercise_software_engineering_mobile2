package com.demotxt.myapp.myapplication.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.demotxt.myapp.myapplication.R;
import com.demotxt.myapp.myapplication.adapters.RecyclerViewAdapter;
import com.demotxt.myapp.myapplication.model.Employee;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final String JSON_URL = "https://fetch-hiring.s3.amazonaws.com/hiring.json";
    private JsonArrayRequest request ;
    private RequestQueue requestQueue ;
    private List<Employee> lstEmployee;
    private RecyclerView recyclerView ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lstEmployee = new ArrayList<>() ;
        recyclerView = findViewById(R.id.recyclerviewid);
        jsonrequest();
    }

    private void jsonrequest() {
        request = new JsonArrayRequest(JSON_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject  = null ;

                for (int i = 0 ; i < response.length(); i++ ) {
                    try {
                        jsonObject = response.getJSONObject(i) ;
                        Employee employee = new Employee() ;
                        employee.setName(jsonObject.getString("name"));
                        employee.setId(jsonObject.getString("id"));
                        //employee.setId(jsonObject.getInt("id"));
                        lstEmployee.add(employee);

                        Collections.sort(lstEmployee, new Comparator<Employee>() {
                            @Override
                            public int compare(Employee lhs, Employee rhs) {
                                int nameCompare = lhs.getName().compareTo(rhs.getName());
                                if(nameCompare != 0) {
                                    return nameCompare;
                                }
                                //return lhs.getName().compareTo(rhs.getName());
                                //return lhs.getId().compareTo(rhs.getId());
                                //return Integer.compare(lhs.getId(), rhs.getId());
                                return Integer.compare(Integer.parseInt(lhs.getId()), Integer.parseInt(rhs.getId()));
                            }
                        });

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                setuprecyclerview(lstEmployee);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(request) ;
    }

    private void setuprecyclerview(List<Employee> lstEmployee) {
        RecyclerViewAdapter myadapter = new RecyclerViewAdapter(this, lstEmployee) ;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myadapter);
    }
}
