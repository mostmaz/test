package com.example.mobilecaseshop.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobilecaseshop.R;
import com.example.mobilecaseshop.models.CartManager;
import com.example.mobilecaseshop.models.Product;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<Product> products;
    private OnCartUpdateListener cartUpdateListener;

    public interface OnCartUpdateListener {
        void onCartUpdated();
    }

    public ProductAdapter(List<Product> products, OnCartUpdateListener listener) {
        this.products = products;
        this.cartUpdateListener = listener;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = products.get(position);
        holder.bind(product);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView imgProduct;
        TextView txtProductName;
        TextView txtProductPrice;
        Button btnAddToCart;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProduct = itemView.findViewById(R.id.imgProduct);
            txtProductName = itemView.findViewById(R.id.txtProductName);
            txtProductPrice = itemView.findViewById(R.id.txtProductPrice);
            btnAddToCart = itemView.findViewById(R.id.btnAddToCart);
        }

        public void bind(Product product) {
            txtProductName.setText(product.getName());
            
            // Format price with thousand separators
            NumberFormat formatter = NumberFormat.getInstance(Locale.US);
            String formattedPrice = formatter.format(product.getPrice()) + " IQD";
            txtProductPrice.setText(formattedPrice);

            // Set placeholder image (you can load actual images here)
            imgProduct.setImageResource(android.R.drawable.ic_menu_gallery);

            btnAddToCart.setOnClickListener(v -> {
                CartManager.getInstance().addToCart(product);
                Toast.makeText(v.getContext(), 
                    product.getName() + " added to cart", 
                    Toast.LENGTH_SHORT).show();
                
                if (cartUpdateListener != null) {
                    cartUpdateListener.onCartUpdated();
                }
            });
        }
    }
}
