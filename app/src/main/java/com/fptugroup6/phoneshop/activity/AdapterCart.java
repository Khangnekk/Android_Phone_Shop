package com.fptugroup6.phoneshop.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fptugroup6.phoneshop.R;
import com.fptugroup6.phoneshop.api.ApiClient;
import com.fptugroup6.phoneshop.api.ApiService;
import com.fptugroup6.phoneshop.model.Phone;
import com.fptugroup6.phoneshop.model.Product_CartDetail;
import com.google.android.gms.tasks.Task;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterCart extends RecyclerView.Adapter<AdapterCart.ViewHolder> {
    private ArrayList<Product_CartDetail> PhoneList = new ArrayList<>();
    private Context context;
    private ApiService apiService = ApiClient.getClient().create(ApiService.class);

    public AdapterCart(ArrayList<Product_CartDetail> phoneList, Context context) {
        this.PhoneList = phoneList;
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
        Picasso.get().load(PhoneList.get(position).getImageUrl()).fit().into(holder.imageView);
        holder.name.setText(PhoneList.get(position).getModelName());
        holder.price.setText(String.valueOf(PhoneList.get(position).getPrice()* PhoneList.get(position).getQuantity()));
        holder.des.setText(PhoneList.get(position).getDescription());
        holder.amount.setText(String.valueOf(PhoneList.get(position).getQuantity()));
        Product_CartDetail phone = PhoneList.get(position);
        int index = position;
        holder.decrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int amount = Integer.parseInt(holder.amount.getText().toString())-1;
                holder.price.setText(String.valueOf(amount* phone.getPrice()));
                holder.amount.setText(String.valueOf(amount));
            }
        });

        holder.increment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                int amount = Integer.parseInt(holder.amount.getText().toString())+1;
                holder.price.setText(String.valueOf(amount* phone.getPrice()));
                holder.amount.setText(String.valueOf(amount));
            }
        });

        holder.button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhoneList.remove(index);
                notifyDataSetChanged();
                Call<Task> call = apiService.Delete_cart(phone.getOrderDetailId());
                call.enqueue(new Callback<Task>() {
                    @Override
                    public void onResponse(Call<Task> call, Response<Task> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(context,"Delete Successfully", Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(context,"Delete Successfully", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Task> call, Throwable t) {
                        Toast.makeText(context,"Disconnect with api service", Toast.LENGTH_LONG).show();

                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return PhoneList.size();
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
            amount = itemView.findViewById(R.id.amout_cart);
            increment = itemView.findViewById(R.id.increment);
            decrement = itemView.findViewById(R.id.decrement);
            button_delete = itemView.findViewById(R.id.delete_cart);
        }
    }
}
