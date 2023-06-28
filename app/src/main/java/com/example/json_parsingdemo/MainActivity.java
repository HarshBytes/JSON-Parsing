package com.example.json_parsingdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
ArrayList<DataModel> arrayList=new ArrayList<>();
RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String URL="https://dummyjson.com/products"; // GetApi
        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        AndroidNetworking.initialize(this);
        AndroidNetworking.get(URL)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Parsing Started
                        try {
                            JSONArray arrResult=response.getJSONArray("products");
                                for(int j=0;j<arrResult.length();j++) {
                                    JSONObject objResult = arrResult.getJSONObject(j);
                                    int id = objResult.getInt("id");
                                    String title = objResult.getString("title");
                                    String description = objResult.getString("description");
                                    int price = objResult.getInt("price");
                                    double discountPercentage = objResult.getInt("discountPercentage");
                                    double rating = objResult.getInt("rating");
                                    int stock = objResult.getInt("stock");
                                    String brand = objResult.getString("brand");
                                    String category = objResult.getString("category");
                                    String thumbnail = objResult.getString("thumbnail");

                                    arrayList.add(new DataModel(id, title, description, price, discountPercentage, rating, stock, brand, category, thumbnail));

                                }

                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                        Adapter_RecyclerView adapterRecyclerView=new Adapter_RecyclerView(MainActivity.this,arrayList);
                        recyclerView.setAdapter(adapterRecyclerView);
                    }

                    @Override
                    public void onError(ANError anError) {
                        anError.printStackTrace();
                        Log.e("ERROR",anError.toString());
                    }
                });

    }
}