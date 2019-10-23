package com.example.apptraining.data;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apptraining.R;
import com.example.apptraining.activities.ViewUser;

import java.util.List;

public class UserTableAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<User> users;

    public UserTableAdapter(Context context, List<User> users){
        this.context = context;
        this.users = users;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.recycler_view_user_item, parent, false);
        return new UserViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        User user = users.get(position);
        String userFullName = user.getFirstName() + " " + user.getLastName();
        UserViewHolder viewHolder = (UserViewHolder) holder;
        viewHolder.userFullName.setText(userFullName);
        viewHolder.position = position;
    }

    @Override
    public int getItemCount() {
        return this.users.size();
    }

    private void goToViewUserForUserAtPosition(Integer position){
        User user = users.get(position);
        Intent goToViewUser = new Intent(context, ViewUser.class);
        goToViewUser.putExtra("RECYCLER_VIEW_USER_ID", user.getId());
        context.startActivity(goToViewUser);
    }

    public class UserViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{
        UserTableAdapter adapter;
        private TextView userFullName;
        private Integer position;

        private UserViewHolder(View itemView, UserTableAdapter adapter){
            super(itemView);
            this.adapter = adapter;
            this.userFullName = itemView.findViewById(R.id.recycler_view_item_user_full_name);
            userFullName.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            adapter.goToViewUserForUserAtPosition(position);
        }
    }
}
