package com.boda.voi.taita.Users;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.boda.voi.taita.Admin.AdminMaintainActivity;
import com.boda.voi.taita.Model.Services;
import com.boda.voi.taita.ViewHolder.ServiceViewHolder;
import com.boda.voi.taita.taita.R;
import com.boda.voi.taita.ui.home.HomeFragment;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private DatabaseReference RidersRef;
    private ImageView callHome;
    private TextView userNameTextView;
    private String type = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);




        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            type = getIntent().getExtras().get("Admins").toString();
        }

        // userNameTextView=(TextView)findViewById(R.id.user_profile_image);


        HomeFragment homeFragment = new HomeFragment();
//        AccountFragment accountFragment=new AccountFragment();
//        LogoutFragment logoutFragment =new LogoutFragment();
//        SearchFragment searchFragment =new SearchFragment();


        RidersRef = FirebaseDatabase.getInstance().getReference().child("Riders");
        RidersRef.keepSynced(true);

        recyclerView = findViewById(R.id.recycler_riders);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);

        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_con,
                new HomeFragment()).commit();


    }


    @Override
    protected void onStart() {
        super.onStart();


        FirebaseRecyclerOptions<Services> options =
                new FirebaseRecyclerOptions.Builder<Services>()
                        .setQuery(RidersRef, Services.class)
                        .build();

        FirebaseRecyclerAdapter<Services, ServiceViewHolder> adapter =
                new FirebaseRecyclerAdapter<Services, ServiceViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull ServiceViewHolder serviceViewHolder, int i, @NonNull final Services services) {
                        serviceViewHolder.riderName.setText(services.getRiderName());
                        serviceViewHolder.riderPhone.setText(services.getRiderPhone());
                        serviceViewHolder.bikeDescription.setText(services.getBikeDescription());
                        serviceViewHolder.riderlocation.setText(services.getRiderLocation());
                        Picasso.get().load(services.getImage()).into(serviceViewHolder.imageView);


                        serviceViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (type.equals("Admins")) {

                                    Intent intent = new Intent(HomeActivity.this, AdminMaintainActivity.class);
                                    intent.putExtra("rid", services.getRid());
                                    startActivity(intent);

                                } else {
                                    Intent intent = new Intent(HomeActivity.this, ServiceDetailsActivity.class);
                                    intent.putExtra("rid", services.getRid());
                                    startActivity(intent);
                                }

                            }
                        });
                    }

                    @NonNull
                    @Override
                    public ServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.service_item_layout, parent, false);
                        ServiceViewHolder holder = new ServiceViewHolder(view);
                        return holder;
                    }
                };
        recyclerView.setAdapter(adapter);
        adapter.startListening();
        super.onStart();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment = null;


                    switch (menuItem.getItemId()) {
                        case R.id.navigation_home:
                            selectedFragment = new HomeFragment();
                            break;
                        case R.id.navigation_admin:
                            if (!type.equals("Admins")) {
                                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                                startActivity(intent);
                                break;

                            }
                        case R.id.navigation_riders:

                            if (!type.equals("Admins")) {
                                Intent inten = new Intent(HomeActivity.this, RidersActivity.class);
                                startActivity(inten);
                                break;
                            }
                        case R.id.navigation_search:

                            if (!type.equals("Admin")) {
                                Intent cart = new Intent(HomeActivity.this, SearchActivity.class);
                                startActivity(cart);
                                break;
                            }
                    }


                    return true;
                }
            };
}
