# How to Build the Mobile Case Shop APK

This document provides **three different methods** to build the APK for your Mobile Case Shop Android application.

## Method 1: Using Android Studio (Recommended - Easiest)

This is the most straightforward method and works on Windows, Mac, and Linux.

### Steps:

1. **Install Android Studio** (if not already installed)
   - Download from: https://developer.android.com/studio
   - Install and complete the setup wizard

2. **Open the Project**
   - Launch Android Studio
   - Click "Open an Existing Project"
   - Navigate to the `MobileCaseShop` folder and select it
   - Click OK

3. **Wait for Gradle Sync**
   - Android Studio will automatically download dependencies
   - Wait for "Gradle build finished" message (may take 5-10 minutes on first run)
   - If prompted to install SDK components, click "Install" and accept licenses

4. **Build the APK**
   - Click **Build** → **Build Bundle(s) / APK(s)** → **Build APK(s)**
   - Wait for the build to complete
   - Click "locate" in the notification to find your APK

5. **Find Your APK**
   - Location: `MobileCaseShop/app/build/outputs/apk/debug/app-debug.apk`
   - This APK can be installed on any Android device

---

## Method 2: Using Command Line with Gradle (For Developers)

If you prefer command-line tools or don't want to install Android Studio.

### Prerequisites:

- **Java JDK 11 or higher** installed
  - Check: `java -version`
  - Download from: https://adoptium.net/ if needed

- **Android SDK** installed
  - Download Command Line Tools: https://developer.android.com/studio#command-tools
  - Or install via Android Studio

### Steps:

1. **Set Environment Variables**

   On **Linux/Mac**:
   ```bash
   export ANDROID_HOME=$HOME/Android/Sdk
   export PATH=$PATH:$ANDROID_HOME/cmdline-tools/latest/bin
   export PATH=$PATH:$ANDROID_HOME/platform-tools
   ```

   On **Windows** (Command Prompt):
   ```cmd
   set ANDROID_HOME=%LOCALAPPDATA%\Android\Sdk
   set PATH=%PATH%;%ANDROID_HOME%\cmdline-tools\latest\bin
   set PATH=%PATH%;%ANDROID_HOME%\platform-tools
   ```

2. **Navigate to Project Directory**
   ```bash
   cd MobileCaseShop
   ```

3. **Make Gradlew Executable** (Linux/Mac only)
   ```bash
   chmod +x gradlew
   ```

4. **Build the APK**

   On **Linux/Mac**:
   ```bash
   ./gradlew assembleDebug
   ```

   On **Windows**:
   ```cmd
   gradlew.bat assembleDebug
   ```

5. **Find Your APK**
   - Location: `app/build/outputs/apk/debug/app-debug.apk`

### Build Release APK (Unsigned):
```bash
./gradlew assembleRelease
```
- Location: `app/build/outputs/apk/release/app-release-unsigned.apk`

---

## Method 3: Using Online Build Services

If you don't want to install anything locally, you can use online Android build services.

### Option A: GitHub Actions (Free)

1. Create a GitHub repository
2. Upload your `MobileCaseShop` project
3. Create `.github/workflows/android.yml`:

```yaml
name: Android CI

on:
  push:
    branches: [ main ]
  pull_request:
    branches: [ main ]

jobs:
  build:
    runs-on: ubuntu-latest
    steps:
    - uses: actions/checkout@v3
    - name: Set up JDK 11
      uses: actions/setup-java@v3
      with:
        java-version: '11'
        distribution: 'temurin'
    - name: Grant execute permission for gradlew
      run: chmod +x gradlew
    - name: Build with Gradle
      run: ./gradlew assembleDebug
    - name: Upload APK
      uses: actions/upload-artifact@v3
      with:
        name: app-debug
        path: app/build/outputs/apk/debug/app-debug.apk
```

4. Push to GitHub and download the APK from the Actions tab

### Option B: Appetize.io or Similar Services

Some online services allow you to build and test Android apps in the cloud, though most require payment for full features.

---

## Installing the APK on Your Device

### On Android Device:

1. **Transfer the APK** to your device (via USB, email, cloud storage, etc.)

2. **Enable Unknown Sources**
   - Go to **Settings** → **Security** → Enable **"Install unknown apps"** for your file manager

3. **Install**
   - Open the APK file using a file manager
   - Tap **Install**
   - Tap **Open** to launch the app

### Using ADB (Android Debug Bridge):

If you have ADB installed:

```bash
adb install app/build/outputs/apk/debug/app-debug.apk
```

---

## Troubleshooting

### "Gradle sync failed"
**Solution**: 
- Ensure you have a stable internet connection
- In Android Studio: **File** → **Invalidate Caches / Restart**
- Delete `.gradle` folder in project and retry

### "SDK location not found"
**Solution**:
- Create `local.properties` file in project root:
  ```
  sdk.dir=/path/to/your/Android/Sdk
  ```
- On Windows: `sdk.dir=C\:\\Users\\YourName\\AppData\\Local\\Android\\Sdk`
- On Mac: `sdk.dir=/Users/YourName/Library/Android/sdk`
- On Linux: `sdk.dir=/home/YourName/Android/Sdk`

### "Minimum SDK version error"
**Solution**:
- Ensure your device runs Android 7.0 (API 24) or higher
- Or lower `minSdk` in `app/build.gradle` (not recommended)

### "Build failed with error code 1"
**Solution**:
- Check the error message in the build output
- Usually a dependency issue - try **Build** → **Clean Project** then **Rebuild Project**

### "Out of memory error"
**Solution**:
- Edit `gradle.properties` and add:
  ```
  org.gradle.jvmargs=-Xmx2048m -XX:MaxPermSize=512m
  ```

---

## Build Variants

### Debug APK
- Quick to build
- Includes debugging information
- Larger file size
- For testing only

### Release APK
- Optimized and minified
- Smaller file size
- Requires signing for distribution
- For production use

---

## Signing the APK for Release

To publish on Google Play Store, you need a signed APK:

1. **Generate a Keystore** (one-time setup):
   ```bash
   keytool -genkey -v -keystore my-release-key.jks -keyalg RSA -keysize 2048 -validity 10000 -alias my-key-alias
   ```

2. **Create `keystore.properties`** in project root:
   ```
   storePassword=yourStorePassword
   keyPassword=yourKeyPassword
   keyAlias=my-key-alias
   storeFile=my-release-key.jks
   ```

3. **Update `app/build.gradle`**:
   ```gradle
   android {
       signingConfigs {
           release {
               storeFile file(keystoreProperties['storeFile'])
               storePassword keystoreProperties['storePassword']
               keyAlias keystoreProperties['keyAlias']
               keyPassword keystoreProperties['keyPassword']
           }
       }
       buildTypes {
           release {
               signingConfig signingConfigs.release
               minifyEnabled true
               proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
           }
       }
   }
   ```

4. **Build Signed APK**:
   ```bash
   ./gradlew assembleRelease
   ```

---

## Summary

- **Easiest**: Use Android Studio (Method 1)
- **For Developers**: Use command line with Gradle (Method 2)
- **No Installation**: Use online services (Method 3)

**Recommended for beginners**: Method 1 (Android Studio)

The APK file can be installed on any Android 7.0+ device for testing and use.

---

**Need Help?** Check the error messages carefully - they usually indicate what's wrong and how to fix it!
