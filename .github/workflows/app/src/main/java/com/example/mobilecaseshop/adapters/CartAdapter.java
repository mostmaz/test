package com.example.mobilecaseshop.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobilecaseshop.R;
import com.example.mobilecaseshop.models.CartItem;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private List<CartItem> cartItems;

    public CartAdapter(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CartItem cartItem = cartItems.get(position);
        holder.bind(cartItem);
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    class CartViewHolder extends RecyclerView.ViewHolder {
        ImageView imgCartProduct;
        TextView txtCartProductName;
        TextView txtCartProductPrice;
        TextView txtCartQuantity;
        TextView txtCartTotal;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            imgCartProduct = itemView.findViewById(R.id.imgCartProduct);
            txtCartProductName = itemView.findViewById(R.id.txtCartProductName);
            txtCartProductPrice = itemView.findViewById(R.id.txtCartProductPrice);
            txtCartQuantity = itemView.findViewById(R.id.txtCartQuantity);
            txtCartTotal = itemView.findViewById(R.id.txtCartTotal);
        }

        public void bind(CartItem cartItem) {
            NumberFormat formatter = NumberFormat.getInstance(Locale.US);
            
            txtCartProductName.setText(cartItem.getProduct().getName());
            txtCartProductPrice.setText(formatter.format(cartItem.getProduct().getPrice()) + " IQD");
            txtCartQuantity.setText("Qty: " + cartItem.getQuantity());
            txtCartTotal.setText(formatter.format(cartItem.getTotalPrice()) + " IQD");

            // Set placeholder image
            imgCartProduct.setImageResource(android.R.drawable.ic_menu_gallery);
        }
    }
}
