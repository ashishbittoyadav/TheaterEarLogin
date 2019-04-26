package com.headspire.theaterearlogin.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.headspire.theaterearlogin.R;
import com.headspire.theaterearlogin.activities.Model;


import org.w3c.dom.Text;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductHolder> {

    ArrayList<Model> arrayList;
    Context context;
    public ProductAdapter(ArrayList<Model> arrayList, Context context)
    {
        this.context=context;
        this.arrayList=arrayList;
    }
    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.data_layout,viewGroup,false);
        ProductHolder productHolder=new ProductHolder(view);
        return productHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder productHolder, int i) {

        productHolder.price.setText(arrayList.get(i).getPrice());
        productHolder.description.setText(arrayList.get(i).getDescription());
        productHolder.product_id.setText(arrayList.get(i).getProduct_id().toString());
        productHolder.title.setText(arrayList.get(i).getTitle());
        Glide.with(context).load(arrayList.get(i).getImage()).into(productHolder.imageView);

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class ProductHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView product_id;
        TextView title;
        TextView description;
        TextView price;
        public ProductHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.product_image);
            product_id=itemView.findViewById(R.id.product_id);
            title=itemView.findViewById(R.id.product_title);
            description=itemView.findViewById(R.id.description);
            price=itemView.findViewById(R.id.product_price);
        }
    }
}
