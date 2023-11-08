package com.fptugroup6.phoneshop.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.fptugroup6.phoneshop.R;
import com.fptugroup6.phoneshop.model.Phone;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ListProductAdapter extends RecyclerView.Adapter<ListProductViewHolder> {

    Context context;
    ArrayList<Phone> items;

    private SelectListener listener;
    public ListProductAdapter(Context context, ArrayList<Phone> items, SelectListener listener) {
        this.context = context;
        this.items = items;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ListProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ListProductViewHolder(LayoutInflater.from(context).inflate(R.layout.item_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ListProductViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.nameView.setText(items.get(position).getModelName());

        String price = String.valueOf(items.get(position).getPrice());
        String formattedNumber = addThousandSeparators(price);

        holder.priceView.setText(formattedNumber+" VNĐ");
        ImageLoader imageLoader = new ImageLoader();
        String imageURL = items.get(position).getImageUrl();
//        Log.e("ImageTest", imageURL.substring(0, imageURL.length()-2));
//        Picasso.get().load("https://scontent.fhan14-4.fna.fbcdn.net/v/t39.30808-6/399468337_713451227482267_399211847584177906_n.jpg?stp=dst-jpg_p843x403&_nc_cat=1&ccb=1-7&_nc_sid=5f2048&_nc_ohc=8h7PIkuMRQ8AX98rIR8&_nc_ht=scontent.fhan14-4.fna&oh=00_AfAv6uUsdEQ2NLqlZPpjLcyGy02ExgISjWCA3RJh6Tg1GA&oe=654FF142").fit().into(holder.imageView);
        imageLoader.loadImage(imageURL, holder.imageView);
        Phone phone = items.get(position);
//        holder.imageView.setImageResource(R.drawable.addtocart);
        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                listener.OnItemClicked(phone);
            }
        });

    }

    public static String addThousandSeparators(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        int length = input.length();
        StringBuilder result = new StringBuilder();
        int count = 0;

        for (int i = length - 1; i >= 0; i--) {
            result.insert(0, input.charAt(i));
            count++;

            if (count % 3 == 0 && i > 0) {
                result.insert(0, ",");
            }
        }

        return result.toString();
    }

    public class ImageLoader {
        public void loadImage(String imageUrl, ImageView imageView) {
            Picasso.get()
                    .load(imageUrl)
                    .into(imageView);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
