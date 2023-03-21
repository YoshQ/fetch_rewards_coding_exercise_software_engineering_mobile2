package com.demotxt.myapp.myapplication.activities;

//import android.support.v7.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
//import android.support.v7.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.demotxt.myapp.myapplication.R;
import com.demotxt.myapp.myapplication.adapters.RecyclerViewAdapter;
import com.demotxt.myapp.myapplication.model.Employee;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {

    private final String JSON_URL = "https://fetch-hiring.s3.amazonaws.com/hiring.json";
    private JsonArrayRequest request ;
    private RequestQueue requestQueue ;
    private List<Employee> lstEmployee;
    private List<Employee> lstEmployeeAll;
    private List<Employee> lstEmployee1;
    private List<Employee> lstEmployee2;
    private List<Employee> lstEmployee3;
    private List<Employee> lstEmployee4;
    private RecyclerView recyclerView ;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lstEmployee = new ArrayList<>() ;
        lstEmployeeAll = new ArrayList<>();
        lstEmployee1 = new ArrayList<>();
        lstEmployee2 = new ArrayList<>();
        lstEmployee3 = new ArrayList<>();
        lstEmployee4 = new ArrayList<>();
        //Map<String, List<Employee> empListGrouped =
                //lstEmployeeAll.stream().collect(Collectors.groupingBy(w -> w.getListId()));
        //Map<String, List<Employee>> empListGrouped =
                //lstEmployeeAll.stream().collect(Collectors.groupingBy(w -> w.getListId()));
        //Map<String, List<Employee>> passingFailing = students.stream()    .collect(Collectors.partitioningBy(s -> s.getGrade() >= PASS_THRESHOLD));
        recyclerView = findViewById(R.id.recyclerviewid);
        //recyclerView2 = findViewById(R.id.recyclerviewid2);
        jsonrequest();
    }

    private void jsonrequest() {
        request = new JsonArrayRequest(JSON_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.i("creation2", "just inside onresponse");
                JSONObject jsonObject  = null ;

                for (int i = 0 ; i < response.length(); i++ ) {
                    Log.i("creation2", "just inside first for loop");
                    try {
                        jsonObject = response.getJSONObject(i) ;
                        Employee employee = new Employee() ;
                        employee.setName(jsonObject.getString("name"));
                        employee.setId(jsonObject.getString("id"));
                        employee.setListId(jsonObject.getString("listId"));
                        lstEmployeeAll.add(employee);

                    } catch (JSONException e) {
                        Log.i("creation2", "inside jsonexception");
                        e.printStackTrace();
                    }
                }
                Collections.sort(lstEmployeeAll, new Comparator<Employee>() {
                    @Override
                    public int compare(Employee lhs, Employee rhs) {
                        //Log.i("creation2", "just inside compare");
                        //Log.i("creation2", "just inside compare, lstEmployeeAll.size(): " + lstEmployeeAll.size());
                        int listCompare = Integer.compare(Integer.parseInt(lhs.getListId()), Integer.parseInt(rhs.getListId()));
                        //return nameCompare;
                        if(listCompare != 0) {
                            return listCompare;
                        }
                        return lhs.getName().compareTo(rhs.getName());
                    }
                });

                //Log.i("creation2", "just before 2nd for loop");
                Log.i("creation2", "just before 2nd for loop, lstEmployeeAll.size(): " + lstEmployeeAll.size());
                for (int i = 0 ; i < lstEmployeeAll.size(); i++ ) {
                    Log.i("creation2", "just inside second for loop");
                    if (!lstEmployeeAll.get(i).getName().isEmpty() & lstEmployeeAll.get(i).getName() != "null") {
                        Log.i("creation2", "just inside if statement of second for loop");
                        if (Integer.parseInt(lstEmployeeAll.get(i).getListId()) == 1) {
                            Log.i("creation2", "in case 1");
                            lstEmployee1.add(lstEmployeeAll.get(i));
                        }
                        else if (Integer.parseInt(lstEmployeeAll.get(i).getListId()) == 2) {
                            lstEmployee2.add(lstEmployeeAll.get(i));
                        }
                        else if (Integer.parseInt(lstEmployeeAll.get(i).getListId()) == 3) {
                            lstEmployee3.add(lstEmployeeAll.get(i));
                        }
                        else if (Integer.parseInt(lstEmployeeAll.get(i).getListId()) == 4) {
                            lstEmployee4.add(lstEmployeeAll.get(i));
                        }
                    }
                }

                setuprecyclerview(lstEmployee1);
                //setuprecyclerview(lstEmployeeAll);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(request) ;
    }

    private void setuprecyclerview(List<Employee> lstEmployee1) {
    //private void setuprecyclerview(List<Employee> lstEmployeeAll) {
        //RecyclerViewAdapter myadapter = new RecyclerViewAdapter(this, lstEmployee) ;
        RecyclerViewAdapter myadapter = new RecyclerViewAdapter(this, lstEmployee1);
        //RecyclerViewAdapter myadapter = new RecyclerViewAdapter(this, lstEmployeeAll);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myadapter);
    }
}
