package com.boda.voi.taita.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.boda.voi.taita.taita.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

public class ServiceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public TextView riderName,bikeDescription,riderlocation,riderPhone;
    public ImageView imageView;
    private ItemClickListener listener;

    public ServiceViewHolder(@NonNull View itemView)
    {
        super(itemView);

        imageView= itemView.findViewById(R.id.bike_image);
        bikeDescription= itemView.findViewById(R.id.bike_description);
        riderName= itemView.findViewById(R.id.rider_name);
        riderlocation= itemView.findViewById(R.id.rider_place);
        riderPhone= itemView.findViewById(R.id.rider_phone);
        Picasso.get().load(String.valueOf(imageView)).networkPolicy(NetworkPolicy.OFFLINE).into(imageView, new Callback() {
            public void onSuccess()
            {

            }

            @Override
            public void onError(Exception e)
            {

            }
        });
    }


    @Override
    public void onClick(View view)
    {
        ItemClickListener.onClick(view,getAdapterPosition(),false);

    }
    public  void setItemClickListener(ItemClickListener listener)
    {
        this.listener=listener;
    }


}

