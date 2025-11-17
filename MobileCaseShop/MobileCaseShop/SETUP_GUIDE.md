# Mobile Case Shop - Setup and Build Guide

This guide will walk you through setting up and building the Mobile Case Shop Android application.

## Prerequisites

Before you begin, ensure you have the following installed on your development machine:

### Required Software

**Android Studio** (Recommended: Latest stable version)
- Download from: https://developer.android.com/studio
- Version: Arctic Fox (2020.3.1) or later recommended
- The IDE includes Android SDK, emulator, and build tools

**Java Development Kit (JDK)**
- JDK 8 or higher is required
- JDK 11 is recommended for optimal compatibility
- Android Studio typically includes a bundled JDK

**Android SDK**
- Minimum SDK: API 24 (Android 7.0)
- Target SDK: API 34 (Android 14)
- These will be automatically downloaded when you open the project in Android Studio

## Project Setup

### Step 1: Extract the Project

Extract the `MobileCaseShop.zip` file to your desired location on your computer. You should see a folder structure like this:

```
MobileCaseShop/
├── app/
├── build.gradle
├── settings.gradle
├── gradle.properties
└── README.md
```

### Step 2: Open in Android Studio

1. Launch Android Studio
2. Select **"Open an Existing Project"** from the welcome screen
3. Navigate to the extracted `MobileCaseShop` folder
4. Click **"OK"** to open the project

### Step 3: Gradle Sync

When the project opens, Android Studio will automatically start syncing Gradle files. This process:
- Downloads all required dependencies
- Configures the build system
- Prepares the project for compilation

If Gradle sync doesn't start automatically:
1. Click **"File"** → **"Sync Project with Gradle Files"**
2. Wait for the sync to complete (this may take several minutes on first run)

### Step 4: Install Missing SDK Components

If Android Studio prompts you to install missing SDK components:
1. Click **"Install missing platform(s) and sync project"**
2. Accept the license agreements
3. Wait for the installation to complete

## Running the App

### Option 1: Run on Physical Device

**Enable Developer Options on Your Android Phone:**
1. Go to **Settings** → **About Phone**
2. Tap **"Build Number"** seven times to enable Developer Options
3. Go back to **Settings** → **Developer Options**
4. Enable **"USB Debugging"**

**Connect and Run:**
1. Connect your Android device to your computer via USB
2. If prompted on your phone, allow USB debugging
3. In Android Studio, select your device from the device dropdown menu
4. Click the **"Run"** button (green play icon) or press `Shift + F10`
5. The app will be installed and launched on your device

### Option 2: Run on Android Emulator

**Create an Emulator:**
1. In Android Studio, click **"Tools"** → **"Device Manager"**
2. Click **"Create Device"**
3. Select a device definition (e.g., Pixel 5)
4. Click **"Next"**
5. Select a system image (API 24 or higher, recommended: API 34)
6. Download the system image if needed
7. Click **"Next"** and then **"Finish"**

**Run on Emulator:**
1. Select the created emulator from the device dropdown
2. Click the **"Run"** button or press `Shift + F10`
3. Wait for the emulator to boot (first boot may take several minutes)
4. The app will be automatically installed and launched

## Building APK

### Debug APK (For Testing)

To build a debug APK that can be installed on any device:

**Using Android Studio:**
1. Click **"Build"** → **"Build Bundle(s) / APK(s)"** → **"Build APK(s)"**
2. Wait for the build to complete
3. Click **"locate"** in the notification to find the APK
4. The APK will be at: `app/build/outputs/apk/debug/app-debug.apk`

**Using Command Line:**
```bash
./gradlew assembleDebug
```

### Release APK (For Distribution)

To build a release APK for distribution:

**Using Command Line:**
```bash
./gradlew assembleRelease
```

The unsigned release APK will be at: `app/build/outputs/apk/release/app-release-unsigned.apk`

**Note:** For production distribution, you should sign the APK with a keystore. This requires additional configuration not covered in this basic guide.

## Project Configuration Files

### Key Configuration Files

**build.gradle (app level)**
- Contains app-specific build configuration
- Dependencies are listed here
- Modify `minSdk`, `targetSdk`, or `versionCode` as needed

**build.gradle (project level)**
- Contains project-wide build configuration
- Gradle plugin version is specified here

**AndroidManifest.xml**
- Declares app components (activities, services, etc.)
- Specifies app permissions
- Defines the launcher activity

**gradle.properties**
- Project-wide Gradle settings
- JVM memory allocation
- AndroidX configuration

## Troubleshooting

### Gradle Sync Failed

**Problem:** Gradle sync fails with dependency errors

**Solution:**
1. Check your internet connection
2. Click **"File"** → **"Invalidate Caches / Restart"**
3. Try syncing again
4. If still failing, update Gradle plugin version in root `build.gradle`

### SDK Not Found

**Problem:** Android SDK path not configured

**Solution:**
1. Click **"File"** → **"Project Structure"** → **"SDK Location"**
2. Set the Android SDK location (usually auto-detected)
3. Click **"Apply"** and **"OK"**

### Build Failed - Missing Dependencies

**Problem:** Build fails due to missing dependencies

**Solution:**
1. Ensure you have a stable internet connection
2. Click **"File"** → **"Sync Project with Gradle Files"**
3. Check the `build.gradle` file for correct dependency versions
4. Try cleaning the project: **"Build"** → **"Clean Project"**

### Emulator Won't Start

**Problem:** Android emulator fails to start

**Solution:**
1. Ensure hardware acceleration (Intel HAXM or AMD Hypervisor) is enabled
2. Allocate more RAM to the emulator in AVD Manager
3. Try creating a new emulator with a different API level
4. Restart Android Studio

### App Crashes on Launch

**Problem:** App crashes immediately after launching

**Solution:**
1. Check the **Logcat** window in Android Studio for error messages
2. Ensure your device/emulator meets the minimum SDK requirement (API 24)
3. Try cleaning and rebuilding: **"Build"** → **"Clean Project"** → **"Rebuild Project"**

## Testing the App

### Features to Test

**Home Page:**
1. Select a brand from the dropdown (Apple, Samsung, or Xiaomi)
2. Select a model from the second dropdown
3. Scroll through "Top Sold Cases" section
4. Scroll through "Latest Products" section
5. Click "Add to Cart" on any product
6. Verify the cart count updates on the "View Cart" button

**Checkout Page:**
1. Click "View Cart" button
2. Verify all added items are displayed
3. Check that subtotal, delivery fee (4,000 IQD), and total are calculated correctly
4. Fill in customer information:
   - Full Name
   - Phone Number
   - Address
   - Select a governorate from the dropdown
5. Click "Place Order"
6. Verify the order confirmation dialog appears
7. Click "OK" and verify you're returned to the home page
8. Verify the cart is now empty

## Customization

### Adding More Products

Edit the file: `app/src/main/java/com/example/mobilecaseshop/models/DataProvider.java`

Add new products to the `getSampleProducts()` method:

```java
products.add(new Product(
    "13",                           // Unique ID
    "Your Product Name",            // Product name
    "Product description",          // Description
    18000,                          // Price in IQD
    "image_name",                   // Image reference
    true,                           // Is top sold?
    false                           // Is latest?
));
```

### Adding More Brands/Models

Edit the file: `app/src/main/java/com/example/mobilecaseshop/activities/MainActivity.java`

In the `initializeData()` method, add new brands:

```java
List<PhoneModel> newBrandModels = new ArrayList<>();
newBrandModels.add(new PhoneModel("Model 1"));
newBrandModels.add(new PhoneModel("Model 2"));
brands.add(new Brand("New Brand", newBrandModels));
```

### Changing the Delivery Fee

Edit the file: `app/src/main/java/com/example/mobilecaseshop/models/CartManager.java`

Modify the `getTotal()` method to change the delivery fee from 4000 to your desired amount.

### Adding Product Images

Currently, the app uses placeholder images. To add real images:

1. Place your images in `app/src/main/res/drawable/`
2. In `ProductAdapter.java` and `CartAdapter.java`, replace:
   ```java
   imgProduct.setImageResource(android.R.drawable.ic_menu_gallery);
   ```
   with:
   ```java
   imgProduct.setImageResource(R.drawable.your_image_name);
   ```

## Next Steps

After successfully building and testing the app, you can:

1. **Add Backend Integration**: Connect to a REST API for real product data
2. **Implement Payment Gateway**: Add payment processing functionality
3. **Add User Authentication**: Implement login/registration system
4. **Enhance UI**: Add animations, better styling, and custom themes
5. **Add More Features**: Search, filters, favorites, order history, etc.
6. **Prepare for Production**: Sign the APK, optimize performance, add analytics

## Support

For issues or questions about the project:
- Review the main README.md file
- Check the code comments in Java files
- Consult Android Developer documentation: https://developer.android.com

## Version Information

- **App Version**: 1.0
- **Minimum Android Version**: 7.0 (API 24)
- **Target Android Version**: 14 (API 34)
- **Build Tools**: Gradle 8.2.0
- **Language**: Java 8

---

**Happy Coding!** 🚀
