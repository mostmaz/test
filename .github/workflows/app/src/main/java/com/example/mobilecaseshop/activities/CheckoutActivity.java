package com.example.mobilecaseshop.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobilecaseshop.R;
import com.example.mobilecaseshop.adapters.CartAdapter;
import com.example.mobilecaseshop.models.CartManager;

import java.text.NumberFormat;
import java.util.Locale;

public class CheckoutActivity extends AppCompatActivity {

    private RecyclerView recyclerCartItems;
    private TextView txtSubtotal;
    private TextView txtDeliveryFee;
    private TextView txtTotal;
    private EditText editName;
    private EditText editPhone;
    private EditText editAddress;
    private Spinner spinnerGovernorate;
    private Button btnPlaceOrder;

    private CartAdapter cartAdapter;
    private static final double DELIVERY_FEE = 4000.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        initializeViews();
        setupGovernorateSpinner();
        setupCartRecyclerView();
        updatePrices();
        setupPlaceOrderButton();
    }

    private void initializeViews() {
        recyclerCartItems = findViewById(R.id.recyclerCartItems);
        txtSubtotal = findViewById(R.id.txtSubtotal);
        txtDeliveryFee = findViewById(R.id.txtDeliveryFee);
        txtTotal = findViewById(R.id.txtTotal);
        editName = findViewById(R.id.editName);
        editPhone = findViewById(R.id.editPhone);
        editAddress = findViewById(R.id.editAddress);
        spinnerGovernorate = findViewById(R.id.spinnerGovernorate);
        btnPlaceOrder = findViewById(R.id.btnPlaceOrder);
    }

    private void setupGovernorateSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.iraq_governorates,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGovernorate.setAdapter(adapter);
    }

    private void setupCartRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerCartItems.setLayoutManager(layoutManager);
        
        cartAdapter = new CartAdapter(CartManager.getInstance().getCartItems());
        recyclerCartItems.setAdapter(cartAdapter);
    }

    private void updatePrices() {
        NumberFormat formatter = NumberFormat.getInstance(Locale.US);
        
        double subtotal = CartManager.getInstance().getSubtotal();
        double total = CartManager.getInstance().getTotal();

        txtSubtotal.setText(formatter.format(subtotal) + " IQD");
        txtDeliveryFee.setText(formatter.format(DELIVERY_FEE) + " IQD");
        txtTotal.setText(formatter.format(total) + " IQD");
    }

    private void setupPlaceOrderButton() {
        btnPlaceOrder.setOnClickListener(v -> {
            if (validateForm()) {
                placeOrder();
            }
        });
    }

    private boolean validateForm() {
        String name = editName.getText().toString().trim();
        String phone = editPhone.getText().toString().trim();
        String address = editAddress.getText().toString().trim();
        int governoratePosition = spinnerGovernorate.getSelectedItemPosition();

        if (name.isEmpty()) {
            editName.setError("Please enter your name");
            editName.requestFocus();
            return false;
        }

        if (phone.isEmpty()) {
            editPhone.setError("Please enter your phone number");
            editPhone.requestFocus();
            return false;
        }

        if (phone.length() < 10) {
            editPhone.setError("Please enter a valid phone number");
            editPhone.requestFocus();
            return false;
        }

        if (address.isEmpty()) {
            editAddress.setError("Please enter your address");
            editAddress.requestFocus();
            return false;
        }

        if (governoratePosition == 0) {
            Toast.makeText(this, "Please select a governorate", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (CartManager.getInstance().getCartItemCount() == 0) {
            Toast.makeText(this, "Your cart is empty", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private void placeOrder() {
        String name = editName.getText().toString().trim();
        String phone = editPhone.getText().toString().trim();
        String address = editAddress.getText().toString().trim();
        String governorate = spinnerGovernorate.getSelectedItem().toString();

        NumberFormat formatter = NumberFormat.getInstance(Locale.US);
        String totalAmount = formatter.format(CartManager.getInstance().getTotal());

        // Create order summary
        StringBuilder orderSummary = new StringBuilder();
        orderSummary.append("Order Details:\n\n");
        orderSummary.append("Customer: ").append(name).append("\n");
        orderSummary.append("Phone: ").append(phone).append("\n");
        orderSummary.append("Address: ").append(address).append("\n");
        orderSummary.append("Governorate: ").append(governorate).append("\n\n");
        orderSummary.append("Total Amount: ").append(totalAmount).append(" IQD\n\n");
        orderSummary.append("Your order has been placed successfully!");

        // Show confirmation dialog
        new AlertDialog.Builder(this)
                .setTitle("Order Placed Successfully!")
                .setMessage(orderSummary.toString())
                .setPositiveButton("OK", (dialog, which) -> {
                    // Clear cart and return to main activity
                    CartManager.getInstance().clearCart();
                    finish();
                })
                .setCancelable(false)
                .show();
    }
}
