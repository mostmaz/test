# Mobile Case Shop - Android E-Commerce App

A fully functional Android e-commerce application for selling mobile phone cases with a complete shopping cart and checkout system.

## Features

### Home Page
- **Brand & Model Selection**: Dropdown menus to filter cases by phone brand and model
  - Pre-configured brands: Apple, Samsung, Xiaomi
  - Multiple models for each brand
- **Top Sold Cases**: Horizontal scrolling section displaying best-selling products
- **Latest Products**: Horizontal scrolling section showing newly added items
- **Shopping Cart**: View cart button with item count indicator

### Product Display
- Product cards with:
  - Product image placeholder
  - Product name and description
  - Price in Iraqi Dinar (IQD)
  - "Add to Cart" button
- Horizontal scrolling RecyclerView for smooth browsing

### Checkout Page
- **Order Summary**:
  - List of all cart items with quantities
  - Subtotal calculation
  - Fixed delivery fee: **4,000 IQD**
  - Total amount (subtotal + delivery fee)

- **Customer Information Form**:
  - Full Name (text input)
  - Phone Number (numeric input with validation)
  - Detailed Address (multi-line text input)
  - Governorate Selection (dropdown with all 19 Iraqi governorates)

- **Order Placement**:
  - Form validation for all required fields
  - Order confirmation dialog
  - Cart clearing after successful order

### Iraqi Governorates Supported
The app includes all 19 governorates of Iraq:
- Al-Anbar (الأنبار)
- Babil (بابل)
- Baghdad (بغداد)
- Basra (البصرة)
- Dhi Qar (ذي قار)
- Diyala (ديالى)
- Duhok (دهوك)
- Erbil (أربيل)
- Halabja (حلبجة)
- Karbala (كربلاء)
- Kirkuk (كركوك)
- Maysan (ميسان)
- Muthanna (المثنى)
- Najaf (النجف)
- Nineveh (نينوى)
- Al-Qādisiyyah (القادسية)
- Saladin (صلاح الدين)
- Sulaymaniyah (السليمانية)
- Wasit (واسط)

## Project Structure

```
MobileCaseShop/
├── app/
│   ├── src/
│   │   └── main/
│   │       ├── java/com/example/mobilecaseshop/
│   │       │   ├── activities/
│   │       │   │   ├── MainActivity.java          # Home page activity
│   │       │   │   └── CheckoutActivity.java      # Checkout page activity
│   │       │   ├── adapters/
│   │       │   │   ├── ProductAdapter.java        # RecyclerView adapter for products
│   │       │   │   └── CartAdapter.java           # RecyclerView adapter for cart items
│   │       │   └── models/
│   │       │       ├── Brand.java                 # Brand data model
│   │       │       ├── PhoneModel.java            # Phone model data model
│   │       │       ├── Product.java               # Product data model
│   │       │       ├── CartItem.java              # Cart item data model
│   │       │       ├── CartManager.java           # Singleton cart management
│   │       │       └── DataProvider.java          # Sample data provider
│   │       ├── res/
│   │       │   ├── layout/
│   │       │   │   ├── activity_main.xml          # Home page layout
│   │       │   │   ├── activity_checkout.xml      # Checkout page layout
│   │       │   │   ├── item_product.xml           # Product card layout
│   │       │   │   └── item_cart.xml              # Cart item layout
│   │       │   └── values/
│   │       │       └── strings.xml                # String resources
│   │       └── AndroidManifest.xml
│   └── build.gradle
├── build.gradle
├── settings.gradle
└── gradle.properties
```

## Technical Details

### Architecture
- **Pattern**: MVC (Model-View-Controller)
- **Language**: Java
- **Minimum SDK**: 24 (Android 7.0)
- **Target SDK**: 34 (Android 14)

### Key Components

#### Models
- `Brand`: Represents phone brands with associated models
- `PhoneModel`: Represents specific phone models
- `Product`: Represents mobile case products with pricing and categorization
- `CartItem`: Represents items in the shopping cart with quantity
- `CartManager`: Singleton pattern for global cart state management

#### Activities
- `MainActivity`: Main entry point, displays products and brand filters
- `CheckoutActivity`: Handles order placement and customer information

#### Adapters
- `ProductAdapter`: Manages product display in RecyclerView with "Add to Cart" functionality
- `CartAdapter`: Displays cart items in checkout page

### Dependencies
```gradle
- androidx.appcompat:appcompat:1.6.1
- com.google.android.material:material:1.11.0
- androidx.constraintlayout:constraintlayout:2.1.4
- androidx.recyclerview:recyclerview:1.3.2
- androidx.cardview:cardview:1.0.0
```

## Sample Data

The app comes pre-loaded with 12 sample products including:
- Silicone cases
- Clear transparent cases
- Leather wallet cases
- Armor shockproof cases
- Glitter cases
- Magnetic ring cases
- Carbon fiber cases
- Marble pattern cases
- And more...

Products are categorized as:
- **Top Sold**: Popular items displayed in the first section
- **Latest**: Newly added items displayed in the second section

## Building the App

### Prerequisites
- Android Studio Arctic Fox or later
- JDK 8 or higher
- Android SDK 24 or higher

### Steps to Build
1. Open the project in Android Studio
2. Sync Gradle files
3. Connect an Android device or start an emulator
4. Click "Run" or use `Shift + F10`

### Building APK
To build a release APK:
```bash
./gradlew assembleRelease
```
The APK will be generated in `app/build/outputs/apk/release/`

## Future Enhancements

Potential features to add:
- Product image loading from URLs or local resources
- Product detail page with more information
- Cart item quantity adjustment and removal
- Product search functionality
- Filter by price range
- User authentication and order history
- Payment gateway integration
- Backend API integration for real-time data
- Push notifications for order updates
- Product reviews and ratings

## License

This project is created for educational and commercial purposes.

## Contact

For questions or support, please contact the development team.
