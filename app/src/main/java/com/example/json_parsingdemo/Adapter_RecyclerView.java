package com.example.json_parsingdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Adapter_RecyclerView extends RecyclerView.Adapter<Adapter_RecyclerView.viewholder> {
   Context context;
   ArrayList<DataModel> arrayList;
    Adapter_RecyclerView(Context context,ArrayList<DataModel> arrayList){
        this.context=context;
        this.arrayList=arrayList;
    }
    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.product_row,parent,false);
        viewholder viewholder=new viewholder(v);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        holder.titleTextView.setText(arrayList.get(position).title);
        holder.descriptionTextView.setText(arrayList.get(position).description);
        holder.priceTextView.setText("Price: $"+String.valueOf(arrayList.get(position).price));
        holder.discountTextView.setText(String.valueOf("-"+ (int) arrayList.get(position).discountPercentage+ "%"));

        holder.stockTextView.setText(String.valueOf(arrayList.get(position).stock+" in Stock " ));
        holder.brandTextView.setText("Brand: " +arrayList.get(position).brand);
        holder.categoryTextView.setText("Category: " +arrayList.get(position).category);


        Glide.with(holder.itemView.getContext())
                .load(arrayList.get(position).thumbnail)
                .placeholder(R.drawable.phone)
                .into(holder.thumbnailImageView);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        ImageView thumbnailImageView;
        TextView titleTextView;
        TextView descriptionTextView;
        TextView priceTextView;
        TextView discountTextView;
        TextView stockTextView;
        TextView brandTextView;
        TextView categoryTextView;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            thumbnailImageView = itemView.findViewById(R.id.thumbnailImageView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
            priceTextView = itemView.findViewById(R.id.priceTextView);
            discountTextView = itemView.findViewById(R.id.discountTextView);
            stockTextView = itemView.findViewById(R.id.stockTextView);
            brandTextView = itemView.findViewById(R.id.brandTextView);
            categoryTextView = itemView.findViewById(R.id.categoryTextView);
        }
    }
}
