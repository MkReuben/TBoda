package com.boda.voi.taita.Admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.boda.voi.taita.taita.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.boda.voi.taita.Model.AdminRiderInfo;

public class RiderInfoActivity extends AppCompatActivity {


    private RecyclerView userDataList;
    private DatabaseReference userRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rider_info);

        userRef= FirebaseDatabase.getInstance().getReference().child("Riders");

        userDataList=findViewById(R.id.riders_data_list);
        userDataList.setLayoutManager(new LinearLayoutManager(this));


    }

    @Override
    protected void onStart()

    {
        super.onStart();

        FirebaseRecyclerOptions<AdminRiderInfo> options=
                new FirebaseRecyclerOptions.Builder<AdminRiderInfo>()
                        .setQuery(userRef,AdminRiderInfo.class)
                        .build();

        FirebaseRecyclerAdapter<AdminRiderInfo,UserViewHolder> adapter=
                new FirebaseRecyclerAdapter<AdminRiderInfo, UserViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull UserViewHolder userViewHolder, int i, @NonNull AdminRiderInfo adminUsersInfo)
                    {
                        userViewHolder.userName.setText("Name:"+adminUsersInfo.getRiderName());
                        userViewHolder.userLocation.setText("Location:"+adminUsersInfo.getRiderLocation());
                        userViewHolder.userPhone.setText("Phone:"+adminUsersInfo.getRiderPhone());
                    }

                    @NonNull
                    @Override
                    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rider_data_layout, parent, false);
                        return new UserViewHolder(view);
                    }
                };

        userDataList.setAdapter(adapter);
        adapter.startListening();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder
    {

        TextView userName;
        TextView userPhone;
        TextView userLocation;

        UserViewHolder(@NonNull View itemView)

        {
            super(itemView);

            userName=itemView.findViewById(R.id.rider_data_user_name);
            userPhone=itemView.findViewById(R.id.rider_data_phone_number);
            userLocation=itemView.findViewById(R.id.rider_location_data);


        }
    }
}