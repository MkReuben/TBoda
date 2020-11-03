package com.boda.voi.taita.Admin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.boda.voi.taita.taita.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class AddRiderActivity extends AppCompatActivity {


    private String bikeDescription,riderLocation,riderName,saveCurrrentDate,saveCurrentTime,riderPhone;
    private ImageView InputBikeImage;
    private Button AddNewRiderButton;
    private EditText InputRiderName,InputBikeDescription,InputRiderLocation,InputriderPhone;
    private static final  int Gallerypick=1;
    private Uri imageUri;
    private Uri ImageUri;
    private String checker="";
    private String serviceRandomKey,downloadImageuRL;
    private StorageReference ServiceImageRef;
    private DatabaseReference ServiceRef,riderRef;
    private ProgressDialog LoadingBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_rider);

//        CategoryName = getIntent().getExtras().get("Category").toString();
        ServiceImageRef= FirebaseStorage.getInstance().getReference().child("Bike images");
        ServiceRef= FirebaseDatabase.getInstance().getReference().child("Riders");
        AddNewRiderButton=findViewById(R.id.add_rider_btn);
        InputRiderName=findViewById(R.id.rider_name);
        InputBikeDescription=findViewById(R.id.bike_description);
        InputRiderLocation=findViewById(R.id.rider_location);
        InputBikeImage=findViewById(R.id.bike_image);
        InputriderPhone=findViewById(R.id.rider_phone);
        LoadingBar = new ProgressDialog(this);

        InputBikeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
//            checker = "clicked";
//            CropImage.activity(imageUri)
                //  .setAspectRatio(7,7)
                //.start(AdminAddNewServicesActivity.this);
                OpenGallery();
            }
        });


        AddNewRiderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                ValidateData();
            }
        });

    }

    private void ValidateData()
    {

        bikeDescription=InputBikeDescription.getText().toString();
        riderLocation=InputRiderLocation.getText().toString();
        riderName=InputRiderName.getText().toString();
        riderPhone=InputriderPhone.getText().toString();

        if (ImageUri== null)
        {
            Toast.makeText(this, "Bike Image is required", Toast.LENGTH_SHORT).show();
        }
        else if (riderName.isEmpty()) {
            Toast.makeText(this, "Rider Name is required", Toast.LENGTH_SHORT).show();
        }
        else if (riderPhone.isEmpty()) {
            Toast.makeText(this, "Rider Phone is required", Toast.LENGTH_SHORT).show();
        }
        else if (bikeDescription.isEmpty())
        {
            Toast.makeText(this, "Bike Description is required", Toast.LENGTH_SHORT).show();
        }else if (riderLocation.isEmpty())
        {
            Toast.makeText(this, "Your Rider Location is required", Toast.LENGTH_SHORT).show();
        }



        else
        {
            StoreInformation();
        }
    }

    private void StoreInformation()
    {

        LoadingBar.setTitle("Adding New Rider");
        LoadingBar.setMessage("Please wait while we are adding the new service");
        LoadingBar.setCanceledOnTouchOutside(false);
        LoadingBar.show();
        Calendar calendar=Calendar.getInstance();


        DateFormat currentDate= DateFormat.getDateInstance();
        saveCurrrentDate=currentDate.format(calendar.getTime());


        SimpleDateFormat currentTime= (SimpleDateFormat) DateFormat.getTimeInstance();
        saveCurrentTime=currentTime.format(calendar.getTime());

        serviceRandomKey=saveCurrentTime+saveCurrrentDate;

        final StorageReference filepath= ServiceImageRef.child(ImageUri.getLastPathSegment()+serviceRandomKey + "jpg");

        final UploadTask uploadTask=filepath.putFile(ImageUri);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                String message =e.toString();
                Toast.makeText(AddRiderActivity.this, "Error:"+message, Toast.LENGTH_SHORT).show();
                LoadingBar.dismiss();

            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot)
            {
                Toast.makeText(AddRiderActivity.this, "Bike Image uploaded successfully", Toast.LENGTH_SHORT).show();
                Task<Uri> uriTask=uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if (!task.isSuccessful())
                        {
                            throw task.getException();
                        }
                        downloadImageuRL=filepath.getDownloadUrl().toString();
                        return  filepath.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if (task.isSuccessful())
                        {

                            downloadImageuRL=task.getResult().toString();
                            Toast.makeText(AddRiderActivity.this, "Getting bike image URL successful", Toast.LENGTH_SHORT).show();


                            SavedServiceInfoToDatabase();
                        }
                    }
                });
            }
        });


    }

    private void SavedServiceInfoToDatabase()
    {
        HashMap<String,Object> serviceMap= new HashMap<>();
        serviceMap.put("rid",serviceRandomKey);
        serviceMap.put("date",saveCurrrentDate);
        serviceMap.put("time",saveCurrentTime);
        serviceMap.put("bikeDescription",bikeDescription);
        serviceMap.put("image",downloadImageuRL);
        serviceMap.put("riderlocation",riderLocation);
        serviceMap.put("riderName",riderName);
        serviceMap.put("riderPhone",riderPhone);




        ServiceRef.child(serviceRandomKey).updateChildren(serviceMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful())
                        {
                            Intent intent=new Intent(AddRiderActivity.this, AdminHomeActivity.class);
                            startActivity(intent);
                            LoadingBar.dismiss();
                            Toast.makeText(AddRiderActivity.this, "Rider is added successfully..", Toast.LENGTH_SHORT).show();
                        }else
                        {
                            LoadingBar.dismiss();

                            String message=task.getException().toString();
                            Toast.makeText(AddRiderActivity.this, "Error"+message, Toast.LENGTH_SHORT).show();


                        }

                    }
                });
    }


    private void OpenGallery()
    {

        Intent galleryIntent =new Intent();
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent,Gallerypick);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==Gallerypick && resultCode== RESULT_OK && data!=null)
        {
            ImageUri=data.getData();
            InputBikeImage.setImageURI(ImageUri);
        }
    }
}


