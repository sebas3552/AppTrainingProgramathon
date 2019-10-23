package com.example.apptraining.activities;

import android.content.Intent;
import android.os.Bundle;

import com.example.apptraining.R;
import com.example.apptraining.data.User;
import com.example.apptraining.data.UserDataProvider;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.EditText;

public class UserEdit extends AppCompatActivity {
    private User user;
    private EditText firstName;
    private EditText lastName;
    private EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_edit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        String userId = intent.getStringExtra("INTENT_EXTRA_USER_ID");
        //user = UserDataProvider.getSingletonInstance().getUserWithID(userId);
        //loadUserData();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveUserData();
                goBackAfterSave();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    private void loadUserData(){
        firstName = findViewById(R.id.user_edit_first_name);
        lastName = findViewById(R.id.user_edit_last_name);
        email = findViewById(R.id.user_edit_email);
        firstName.setText(user.getFirstName());
        lastName.setText(user.getLastName());
        email.setText(user.getEmail());
    }

    private void saveUserData(){
        user.setFirstName(firstName.getText().toString());
        user.setLastName(lastName.getText().toString());
        user.setEmail(email.getText().toString());
    }

    private void goBackAfterSave(){
        Intent goBack = new Intent();
        goBack.putExtra("INTENT_EXTRA_GOING_BACK", "didEdit");
        setResult(RESULT_OK, goBack);
        finish();
    }
}
