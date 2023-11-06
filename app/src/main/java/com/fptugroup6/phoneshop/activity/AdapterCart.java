package com.fptugroup6.phoneshop.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fptugroup6.phoneshop.R;
import com.fptugroup6.phoneshop.model.Phone;

import java.util.ArrayList;

public class AdapterCart extends RecyclerView.Adapter<AdapterCart.ViewHolder> {
    private ArrayList<Phone> PhoneList;
    private Context context;

    public AdapterCart(ArrayList<Phone> phoneList, Context context) {
        PhoneList = phoneList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(PhoneList.get(position).getModelName());
        holder.price.setText(String.valueOf(PhoneList.get(position).getPrice()));
        holder.des.setText(PhoneList.get(position).getDescription());
        holder.name.setText(PhoneList.get(position).getModelName());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private ImageView button_delete;
        private TextView increment;
        private TextView decrement;
        private TextView name;
        private TextView price;
        private TextView des;
        private EditText amount;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.product_cart);
            name = itemView.findViewById(R.id.name_cart);
            price = itemView.findViewById(R.id.price_cart);
            des = itemView.findViewById(R.id.desciption_product_cart);
            amount = itemView.findViewById(R.id.name_cart);
            increment = itemView.findViewById(R.id.increment);
            decrement = itemView.findViewById(R.id.decrement);
        }
    }
}
