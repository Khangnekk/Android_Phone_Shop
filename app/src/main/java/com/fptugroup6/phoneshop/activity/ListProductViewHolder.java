package com.fptugroup6.phoneshop.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fptugroup6.phoneshop.R;

public class ListProductViewHolder extends RecyclerView.ViewHolder {

    ImageView imageView;
    TextView nameView;

    TextView priceView;

    public RelativeLayout container;
    public ListProductViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.productImage);
        nameView = itemView.findViewById(R.id.nameProductInList);
        priceView = itemView.findViewById(R.id.priceOfEachProduct);
        container = itemView.findViewById(R.id.main_container_ListProduct);
    }
}
