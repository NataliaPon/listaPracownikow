package com.poniatowska.n.listapracownikow;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


     List<Person> dataList;
     private RecyclerView recyclerView;
     private ListAdapter adapter;
     private Context context;

    GetApi mApi;
    Call<List<Person>> call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context =this;

        mApi = RetrofitClient.getClient().create(GetApi.class);
        call = mApi.getData();

        dataList = new ArrayList<Person>();

        call.enqueue(new Callback<List<Person>>() {
            @Override
            public void onResponse(Call<List<Person>> call, Response<List<Person>> response) {
                dataList = response.body();
                generateList(dataList);

            }

            @Override
            public void onFailure(Call<List<Person>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something went wrong...Error message: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void generateList(final List<Person> dataList)
    {
        recyclerView = findViewById(R.id.recyclerView);
        adapter = new ListAdapter(dataList,context);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = recyclerView.getChildAdapterPosition(v);
                Toast.makeText(MainActivity.this, dataList.get(position).getEmployeeName(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
