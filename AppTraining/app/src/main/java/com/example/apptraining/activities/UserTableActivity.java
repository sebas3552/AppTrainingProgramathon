package com.example.apptraining.activities;

import android.os.Bundle;

import com.example.apptraining.data.APIClient;
import com.example.apptraining.data.User;
import com.example.apptraining.data.UserDataProvider;
import com.example.apptraining.data.UserTableAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;

import com.example.apptraining.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserTableActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private UserTableAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_table);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setupRecyclerView();
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void setupRecyclerView(){
        final UserTableActivity activity = this;
        recyclerView = findViewById(R.id.user_table_recycler_view);
        APIClient client = UserDataProvider.getAPIClient();
        Call<List<User>> getUsers = client.getAllUsers();
        getUsers.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                adapter = new UserTableAdapter(activity, response.body());
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(activity));
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.d("UserTableActivity", "Failure on getUsers");
            }
        });


    }

}
