package com.boda.voi.taita.Users;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.boda.voi.taita.Model.Services;
import com.boda.voi.taita.taita.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class ServiceDetailsActivity extends AppCompatActivity {
    private ImageView bikeImage;
    private TextView riderName,riderPhone, bikeDescription,riderLocation;
    private String serviceID = "";
    private Button callBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_details);


        serviceID = getIntent().getStringExtra("rid");

        callBtn = findViewById(R.id.call_rider);
        bikeImage = findViewById(R.id.bike_image_details);
        riderLocation = findViewById(R.id.rider_location_details);
        bikeDescription = findViewById(R.id.bike_description_details);
        riderName = findViewById(R.id.rider_name_details);
        riderPhone = findViewById(R.id.rider_phone_details);


        getServiceDetails(serviceID);

//        Toast.makeText(ServiceDetailsActivity.this, "RIDERS-NAME " + riderName.getText(), Toast.LENGTH_LONG).show();





        callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + riderPhone.getText()));
                if (ContextCompat.checkSelfPermission(ServiceDetailsActivity.this,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(ServiceDetailsActivity.this, new String[]{Manifest.permission.CALL_PHONE},1);

                    Toast.makeText(ServiceDetailsActivity.this, "Please wait while we are calling"+riderName+"", Toast.LENGTH_LONG).show();
                }
                else
                {
                    startActivity(intent);
                }




            }
        });





    }


    private void getServiceDetails(String serviceID) {



        DatabaseReference serviceRef = FirebaseDatabase.getInstance().getReference().child("Riders");

        serviceRef.child(serviceID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Services services = dataSnapshot.getValue(Services.class);

                    riderName.setText(services.getRiderName());
                    riderPhone.setText(services.getRiderPhone());
                    bikeDescription.setText(services.getBikeDescription());
                    riderLocation.setText(services.getRiderLocation());
                    Picasso.get().load(services.getImage()).into(bikeImage);


                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


}






