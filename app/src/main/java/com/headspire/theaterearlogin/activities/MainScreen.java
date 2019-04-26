package com.headspire.theaterearlogin.activities;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.headspire.theaterearlogin.R;
import com.headspire.theaterearlogin.RetrofitInstance;
import com.headspire.theaterearlogin.User;
import com.headspire.theaterearlogin.adapter.ProductAdapter;
//import com.headspire.theaterearlogin.model.Model;
import com.headspire.theaterearlogin.model.ResponseModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainScreen extends AppCompatActivity {

    RecyclerView.LayoutManager layoutManager;
    ProductAdapter productAdapter;
    ArrayList<Model> arrayList;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        createArrayList();
            buildRecylcerView();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.sign_out:
                finish();
                break;
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    private void createArrayList() {
        //http://192.168.4.58/NewProject/getdata.php
        arrayList=new ArrayList<>();
        User user= RetrofitInstance.getInstance().create(User.class);
        Call<List<Model>> call=user.getData();
        call.enqueue(new Callback<List<Model>>() {
            @Override
            public void onResponse(Call<List<Model>> call, Response<List<Model>> response) {

                List<Model> list = response.body();
                for(Model data:list)
                {
                    arrayList.add(data);
                }
            }

            @Override
            public void onFailure(Call<List<Model>> call, Throwable t) {
                Log.e("tagg","failed");
            }
        });
    }
    private void buildRecylcerView()
    {
        Log.e("tagg","size:-"+arrayList.size());
            recyclerView=findViewById(R.id.myrecyclerview);
            layoutManager=new LinearLayoutManager(this);
            ((LinearLayoutManager) layoutManager).setOrientation(LinearLayoutManager.VERTICAL);
            productAdapter=new ProductAdapter(arrayList,this);
            recyclerView.setAdapter(productAdapter);
            recyclerView.setLayoutManager(layoutManager);
    }
}
