package com.example.intership.CuntryInformationRoomDatabase;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.intership.CountryInformation;
import com.example.intership.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder>
{
    ArrayList<Country> countryArrayList;
    Context context;

    public CountryAdapter(ArrayList<Country> countryArrayList, Context context) {
        this.countryArrayList = countryArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        @SuppressLint("InflateParams")
        View view= LayoutInflater.from(context).inflate(R.layout.single_country_info,null,false);
        return new CountryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position)
    {
        String imageUrl=countryArrayList.get(position).getFlag();
        Glide.with(context).load(imageUrl).placeholder(R.drawable.ic_round_flag_24).into(holder.circleImageView);
        holder.textView.setText(countryArrayList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return countryArrayList.size();
    }

     class CountryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        CircleImageView circleImageView;
        TextView textView;
        public CountryViewHolder(@NonNull  View itemView) {
            super(itemView);
            circleImageView=itemView.findViewById(R.id.recyclerview_image);
            textView=itemView.findViewById(R.id.recyclerviewName);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v)
        {
            Intent intent=new Intent(context, CountryInformation.class);
            Country country=countryArrayList.get(getAdapterPosition());
            intent.putExtra("country",country);
            context.startActivity(intent);
        }
    }
}
