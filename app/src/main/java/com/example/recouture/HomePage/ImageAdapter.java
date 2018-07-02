package com.example.recouture.HomePage;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recouture.R;
import com.example.recouture.ShirtGallery.ShirtActivity;


import org.w3c.dom.Text;

import static android.support.v4.content.ContextCompat.startActivity;


public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {


    private Context context;

    public ImageAdapter(Context context) {
        this.context = context;
    }

    private int[] resourceId = new int[] {R.drawable.shirt,R.drawable.sleeveless,R.drawable.outerwear,
            R.drawable.sweater,R.drawable.pants,R.drawable.shorts,R.drawable.skirt,R.drawable.dresses,
            R.drawable.shoes,R.drawable.bags,R.drawable.accessories,R.drawable.swimwear
    };

    private String[] names = new String[]{
            "Shirts","Sleevless","Outerwear","Sweater","Pants","Shorts","Skirts","Dresses","Shoes","Bags","Accessories","Swimwear"
    };



    @NonNull
    @Override
    public ImageAdapter.ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.single_item_homepage,parent,false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageAdapter.ImageViewHolder holder, int position) {
        String currName = names[position];
        int currResource = resourceId[position];
        holder.categoryName.setText(currName);
        holder.categoryImage.setImageResource(currResource);

    }

    @Override
    public int getItemCount() {
        return names.length;
    }

    class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView categoryName;
        ImageView categoryImage;


        public ImageViewHolder(View itemView) {
            super(itemView);

            categoryName = itemView.findViewById(R.id.textViewCategory);
            categoryImage = itemView.findViewById(R.id.imageViewCategory);
            LinearLayout linearLayout = itemView.findViewById(R.id.linearLayout);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            String category = categoryName.getText().toString();
            switch (category) {
                case "Shirts":
                    Toast.makeText(context,"you clicked on " + category,Toast.LENGTH_SHORT).show();
                    context.startActivity(new Intent(context, ShirtActivity.class));

            }
        }

    }


}
