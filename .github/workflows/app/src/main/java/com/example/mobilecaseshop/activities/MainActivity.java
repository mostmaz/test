package com.example.mobilecaseshop.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobilecaseshop.R;
import com.example.mobilecaseshop.adapters.ProductAdapter;
import com.example.mobilecaseshop.models.Brand;
import com.example.mobilecaseshop.models.CartManager;
import com.example.mobilecaseshop.models.PhoneModel;
import com.example.mobilecaseshop.models.Product;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ProductAdapter.OnCartUpdateListener {

    private Spinner spinnerBrand;
    private Spinner spinnerModel;
    private RecyclerView recyclerTopSold;
    private RecyclerView recyclerLatest;
    private Button btnViewCart;

    private List<Brand> brands;
    private List<Product> allProducts;
    private List<Product> topSoldProducts;
    private List<Product> latestProducts;

    private ProductAdapter topSoldAdapter;
    private ProductAdapter latestAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();
        initializeData();
        setupBrandSpinner();
        setupRecyclerViews();
        loadSampleProducts();
        updateCartButton();
    }

    private void initializeViews() {
        spinnerBrand = findViewById(R.id.spinnerBrand);
        spinnerModel = findViewById(R.id.spinnerModel);
        recyclerTopSold = findViewById(R.id.recyclerTopSold);
        recyclerLatest = findViewById(R.id.recyclerLatest);
        btnViewCart = findViewById(R.id.btnViewCart);

        btnViewCart.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CheckoutActivity.class);
            startActivity(intent);
        });
    }

    private void initializeData() {
        // Initialize brands and models
        brands = new ArrayList<>();
        
        // Apple models
        List<PhoneModel> appleModels = new ArrayList<>();
        appleModels.add(new PhoneModel("iPhone 15 Pro Max"));
        appleModels.add(new PhoneModel("iPhone 15 Pro"));
        appleModels.add(new PhoneModel("iPhone 15"));
        appleModels.add(new PhoneModel("iPhone 14 Pro Max"));
        appleModels.add(new PhoneModel("iPhone 14"));
        appleModels.add(new PhoneModel("iPhone 13"));
        brands.add(new Brand("Apple", appleModels));

        // Samsung models
        List<PhoneModel> samsungModels = new ArrayList<>();
        samsungModels.add(new PhoneModel("Galaxy S24 Ultra"));
        samsungModels.add(new PhoneModel("Galaxy S24"));
        samsungModels.add(new PhoneModel("Galaxy S23 Ultra"));
        samsungModels.add(new PhoneModel("Galaxy A54"));
        samsungModels.add(new PhoneModel("Galaxy A34"));
        brands.add(new Brand("Samsung", samsungModels));

        // Xiaomi models
        List<PhoneModel> xiaomiModels = new ArrayList<>();
        xiaomiModels.add(new PhoneModel("Xiaomi 14 Pro"));
        xiaomiModels.add(new PhoneModel("Xiaomi 13"));
        xiaomiModels.add(new PhoneModel("Redmi Note 13 Pro"));
        xiaomiModels.add(new PhoneModel("Redmi Note 12"));
        brands.add(new Brand("Xiaomi", xiaomiModels));

        // Initialize products (this will be populated with sample data)
        allProducts = new ArrayList<>();
        topSoldProducts = new ArrayList<>();
        latestProducts = new ArrayList<>();
    }

    private void setupBrandSpinner() {
        // Add a default "Select Brand" option
        List<String> brandNames = new ArrayList<>();
        brandNames.add("Select Brand");
        for (Brand brand : brands) {
            brandNames.add(brand.getName());
        }

        ArrayAdapter<String> brandAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                brandNames
        );
        brandAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBrand.setAdapter(brandAdapter);

        spinnerBrand.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    // User selected a brand (not the default option)
                    Brand selectedBrand = brands.get(position - 1);
                    setupModelSpinner(selectedBrand);
                } else {
                    // Reset model spinner
                    setupModelSpinner(null);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                setupModelSpinner(null);
            }
        });
    }

    private void setupModelSpinner(Brand brand) {
        List<String> modelNames = new ArrayList<>();
        modelNames.add("Select Model");

        if (brand != null) {
            for (PhoneModel model : brand.getModels()) {
                modelNames.add(model.getModelName());
            }
        }

        ArrayAdapter<String> modelAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                modelNames
        );
        modelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerModel.setAdapter(modelAdapter);
    }

    private void setupRecyclerViews() {
        // Top Sold Products
        LinearLayoutManager topSoldLayoutManager = new LinearLayoutManager(
                this,
                LinearLayoutManager.HORIZONTAL,
                false
        );
        recyclerTopSold.setLayoutManager(topSoldLayoutManager);
        topSoldAdapter = new ProductAdapter(topSoldProducts, this);
        recyclerTopSold.setAdapter(topSoldAdapter);

        // Latest Products
        LinearLayoutManager latestLayoutManager = new LinearLayoutManager(
                this,
                LinearLayoutManager.HORIZONTAL,
                false
        );
        recyclerLatest.setLayoutManager(latestLayoutManager);
        latestAdapter = new ProductAdapter(latestProducts, this);
        recyclerLatest.setAdapter(latestAdapter);
    }

    public void setProducts(List<Product> products) {
        this.allProducts = products;
        
        // Filter top sold products
        topSoldProducts.clear();
        for (Product product : products) {
            if (product.isTopSold()) {
                topSoldProducts.add(product);
            }
        }

        // Filter latest products
        latestProducts.clear();
        for (Product product : products) {
            if (product.isLatest()) {
                latestProducts.add(product);
            }
        }

        // Notify adapters
        topSoldAdapter.notifyDataSetChanged();
        latestAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCartUpdated() {
        updateCartButton();
    }

    private void updateCartButton() {
        int itemCount = CartManager.getInstance().getCartItemCount();
        btnViewCart.setText("View Cart (" + itemCount + ")");
    }

    private void loadSampleProducts() {
        List<Product> products = DataProvider.getSampleProducts();
        setProducts(products);
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateCartButton();
    }
}
