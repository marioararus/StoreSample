package com.mr.app.android.storesample.ui.main;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mr.app.android.storesample.R;
import com.mr.app.android.storesample.data.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Marioara Rus on 8/25/2018.
 */
public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductViewHolder> {

    private List<Product> products;

    public void setProducts(List<Product> products) {
        this.products = products;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProductViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Picasso.with(holder.itemView.getContext()).load(products.get(position).getUrl())
                .into(holder.ivProduct);
    }

    @Override
    public int getItemCount() {
        return products != null ? products.size() : 0;
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView ivProduct;

        public ProductViewHolder(View itemView) {
            super(itemView);
            ivProduct = itemView.findViewById(R.id.iv_product);
        }
    }
}
