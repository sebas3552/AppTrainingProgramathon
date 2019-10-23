package com.example.apptraining.activities;

import android.content.Intent;
import android.os.Bundle;

import com.example.apptraining.R;
import com.example.apptraining.data.APIClient;
import com.example.apptraining.data.User;
import com.example.apptraining.data.UserDataProvider;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewUser extends AppCompatActivity {
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Log.d("ViewUser","ScrollingActvity was just created");

        Intent intent = getIntent();
        String userId = intent.getStringExtra("RECYCLER_VIEW_USER_ID");
        APIClient client = UserDataProvider.getAPIClient();
        Call<User> getUser = client.getUserWithId(userId);
        getUser.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                user = response.body();
                loadUserData();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("ViewUser","View User Activity getUser failed!");
            }
        });

        final ViewUser activity = this;

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToEditUser = new Intent(activity, UserEdit.class);
                goToEditUser.putExtra("INTENT_EXTRA_USER_ID", user.getId());
                startActivityForResult(goToEditUser, 510);
            }
        });
    }

    private void loadUserData(){
        TextView firstName = findViewById(R.id.user_view_first_name);
        TextView lastName = findViewById(R.id.user_view_last_name);
        TextView email = findViewById(R.id.user_view_email);
        firstName.setText(user.getFirstName());
        lastName.setText(user.getLastName());
        email.setText(user.getEmail());
    }

    @Override
    public void onActivityReenter(int resultCode, Intent data) {
        super.onActivityReenter(resultCode, data);
        if(resultCode == RESULT_OK){
            loadUserData();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
