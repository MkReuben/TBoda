package com.boda.voi.taita.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.boda.voi.taita.Notification;
import com.boda.voi.taita.Users.HomeActivity;
import com.boda.voi.taita.taita.R;


public class AdminHomeActivity extends AppCompatActivity {

    private Button maintain,addRider,riderInfo,logout,notify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        maintain=findViewById(R.id.maintain_rider_btn);
        addRider=findViewById(R.id.add_Rider_btn);
        riderInfo=findViewById(R.id.rider_info_btn);
        notify=findViewById(R.id.notifications);
        logout=findViewById(R.id.logout_btn);

        notify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent =new Intent(AdminHomeActivity.this, Notification.class);
                startActivity(intent);

            }
        });

        maintain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent=new Intent(AdminHomeActivity.this, HomeActivity.class);
                intent.putExtra("Admins","Admins");
                startActivity(intent);
            }
        });
        addRider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent =new Intent(AdminHomeActivity.this, AddRiderActivity.class);
                startActivity(intent);
            }
        });

        riderInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(AdminHomeActivity.this, RiderInfoActivity.class);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent go = new Intent(AdminHomeActivity.this, HomeActivity.class);
                go.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(go);
                finish();
            }
        });

    }
}


