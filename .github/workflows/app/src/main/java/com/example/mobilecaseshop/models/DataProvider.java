package com.example.mobilecaseshop.models;

import java.util.ArrayList;
import java.util.List;

public class DataProvider {

    public static List<Product> getSampleProducts() {
        List<Product> products = new ArrayList<>();

        // Top Sold Products
        products.add(new Product(
                "1",
                "Silicone Case - Black",
                "Premium silicone case with soft touch finish",
                15000,
                "case_black_silicone",
                true,  // isTopSold
                false  // isLatest
        ));

        products.add(new Product(
                "2",
                "Clear Transparent Case",
                "Crystal clear case showing your phone's original design",
                12000,
                "case_clear",
                true,
                false
        ));

        products.add(new Product(
                "3",
                "Leather Wallet Case",
                "Premium leather case with card slots",
                25000,
                "case_leather_wallet",
                true,
                false
        ));

        products.add(new Product(
                "4",
                "Armor Shockproof Case",
                "Military grade protection with reinforced corners",
                20000,
                "case_armor",
                true,
                false
        ));

        products.add(new Product(
                "5",
                "Glitter Sparkle Case",
                "Stylish glitter case with bling design",
                18000,
                "case_glitter",
                true,
                false
        ));

        // Latest Products
        products.add(new Product(
                "6",
                "Magnetic Ring Case",
                "Built-in magnetic ring for car mount and stand",
                22000,
                "case_magnetic_ring",
                false,
                true  // isLatest
        ));

        products.add(new Product(
                "7",
                "Carbon Fiber Case",
                "Lightweight carbon fiber texture design",
                19000,
                "case_carbon_fiber",
                false,
                true
        ));

        products.add(new Product(
                "8",
                "Marble Pattern Case",
                "Elegant marble design with glossy finish",
                16000,
                "case_marble",
                false,
                true
        ));

        products.add(new Product(
                "9",
                "Gradient Color Case",
                "Beautiful gradient color transition design",
                17000,
                "case_gradient",
                false,
                true
        ));

        products.add(new Product(
                "10",
                "Liquid Silicone Case - Navy",
                "Premium liquid silicone with microfiber lining",
                21000,
                "case_liquid_navy",
                false,
                true
        ));

        // Products that are both top sold and latest
        products.add(new Product(
                "11",
                "Tempered Glass Case",
                "Hybrid case with tempered glass back",
                23000,
                "case_glass",
                true,
                true
        ));

        products.add(new Product(
                "12",
                "Slim Fit Case - Rose Gold",
                "Ultra-thin hard case with metallic finish",
                14000,
                "case_slim_rosegold",
                true,
                true
        ));

        return products;
    }
}
