package com.boda.voi.taita.Users;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.boda.voi.taita.taita.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.boda.voi.taita.Model.Services;
import com.boda.voi.taita.ViewHolder.ServiceViewHolder;
import com.squareup.picasso.Picasso;

public class SearchActivity extends AppCompatActivity {



        private Button searchBtn;
        private EditText inputText;
        private RecyclerView searchlist;
        private String SearchInput;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_search);

            inputText=findViewById(R.id.search_location_name);
            searchBtn=findViewById(R.id.search_btn);
            searchlist=findViewById(R.id.search_list);
            searchlist.setLayoutManager(new LinearLayoutManager(SearchActivity.this));

            searchBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)


                {
                    SearchInput=inputText.getText().toString().toUpperCase();
                    onStart();

                }
            });



        }

        @Override
        protected void onStart() {
            super.onStart();

            DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Riders");

            FirebaseRecyclerOptions<Services> options =
                    new FirebaseRecyclerOptions.Builder<Services>()
                            .setQuery(reference.orderByChild("riderlocation").startAt(SearchInput), Services.class)

                            .build();

            FirebaseRecyclerAdapter<Services, ServiceViewHolder> adapter =
                    new FirebaseRecyclerAdapter<Services, ServiceViewHolder>(options) {
                        @Override
                        protected void onBindViewHolder(@NonNull ServiceViewHolder serviceViewHolder, int i, @NonNull final Services services)
                        {
                            serviceViewHolder.riderName.setText(services.getRiderName());
                            serviceViewHolder.bikeDescription.setText(services.getBikeDescription());
                            serviceViewHolder.riderPhone.setText(services.getRiderPhone());
                            serviceViewHolder.riderlocation.setText(services.getRiderLocation());
                            Picasso.get().load(services.getImage()).into(serviceViewHolder.imageView);

                            serviceViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view)
                                {
                                    Intent intent =new Intent(SearchActivity.this, ServiceDetailsActivity.class);
                                    intent.putExtra("rid",services.getRid());
                                    startActivity(intent);
                                }
                            });

                        }

                        @NonNull
                        @Override
                        public ServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
                        {
                            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.service_item_layout, parent, false);
                            ServiceViewHolder holder= new ServiceViewHolder(view);
                            return holder;
                        }
                    };

            searchlist.setAdapter(adapter);
            adapter.startListening();



        }
    }

