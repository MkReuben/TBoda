package com.boda.voi.taita.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.boda.voi.taita.taita.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class AdminMaintainActivity extends AppCompatActivity {

    private Button applyChangebtn,Deletebtn;
    private EditText name,phone,description,location;
    private ImageView imageView;
    private String serviceID = "";
    private DatabaseReference serviceRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_maintain);

        serviceID = getIntent().getStringExtra("rid");
        serviceRef= FirebaseDatabase.getInstance().getReference().child("Riders").child(serviceID);



        applyChangebtn=findViewById(R.id.rider_edit_btn);
        Deletebtn=findViewById(R.id.rider_delete_btn);
        name=findViewById(R.id.rider_name_maintain);
        phone=findViewById(R.id.rider_maintain_phone);
        description=findViewById(R.id.rider_maintain_description);
        location=findViewById(R.id.rider_maintain_location);
        imageView=findViewById(R.id.bike_image_maintain);

        displaySpecificServiceInfo();

        Deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                deleteThisService();

            }
        });

        applyChangebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                applyChanges();

            }
        });
    }

    private void deleteThisService()
    {
        serviceRef.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task)
            {
                if (task.isSuccessful())
                {
                    Toast.makeText(AdminMaintainActivity.this, "The Service is deleted successfully", Toast.LENGTH_SHORT).show();
                    Intent intent =new Intent(AdminMaintainActivity.this, AdminHomeActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private void applyChanges()
    {

        String sName=name.getText().toString();
        String sPhone=phone.getText().toString();
        String sDescription=description.getText().toString();
        String sLocation=location.getText().toString();

        if (sName.equals(""))
        {
            Toast.makeText(this, "Write down Rider Name", Toast.LENGTH_SHORT).show();
        }
        else  if (sPhone.equals(""))
        {
            Toast.makeText(this, "Write down Rider Phone Number", Toast.LENGTH_SHORT).show();
        }
        else  if (sDescription.equals(""))
        {
            Toast.makeText(this, "Write down Riders' Description", Toast.LENGTH_SHORT).show();
        }
        else  if (sLocation.equals(""))
        {
            Toast.makeText(this, "Write down Riders' Location", Toast.LENGTH_SHORT).show();
        }
        else
        {

            HashMap<String,Object> serviceMap= new HashMap<>();

            serviceMap.put("rid",serviceID);
            serviceMap.put("riderDescription",sDescription);
            serviceMap.put("riderlocation",sLocation);
            serviceMap.put("riderPhone",sPhone);
            serviceMap.put("riderName",sName);

            serviceRef.updateChildren(serviceMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task)
                {
                    if (task.isSuccessful())
                    {
                        Toast.makeText(AdminMaintainActivity.this, "Changes applied Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(AdminMaintainActivity.this,AdminHomeActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            });

        }


    }

    private void displaySpecificServiceInfo()
    {
        serviceRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if (dataSnapshot.exists())
                {
                    String sName =dataSnapshot.child("riderName").getValue().toString();
                    String sPhone =dataSnapshot.child("riderPhone").getValue().toString();
                    String sDescription =dataSnapshot.child("bikeDescription").getValue().toString();
                    String sLocation =dataSnapshot.child("riderlocation").getValue().toString();
                    String sImage =dataSnapshot.child("image").getValue().toString();

                    name.setText(sName);

                    phone.setText(sPhone);
                    description.setText(sDescription);
                    location.setText(sLocation);
                    Picasso.get().load(sImage).into(imageView);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {


            }
        });
    }
}
