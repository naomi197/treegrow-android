# 📋 Firebase Setup Guide

## 1. Create Firebase Project

1. Go to [Firebase Console](https://console.firebase.google.com)
2. Click "Add Project"
3. Enter project name: `TreeGrow`
4. Enable Google Analytics (optional)
5. Create project

## 2. Register Android App

1. Click "Add App" → Android
2. Package name: `com.treegrow.app`
3. Debug SHA-1:
   ```bash
   ./gradlew signingReport
   ```
4. Download `google-services.json`
5. Place in `app/google-services.json`

## 3. Enable Authentication

1. Firebase Console → Authentication
2. Sign-in method:
   - Enable Email/Password
   - Enable Google Sign-In (optional)
3. Set up user verification email

## 4. Enable Firestore Database

1. Firebase Console → Firestore Database
2. Create database in production mode
3. Set security rules:

```javascript
rules_version = '2';
service cloud.firestore {
  match /databases/{database}/documents {
    // Users collection
    match /users/{userId} {
      allow read, write: if request.auth.uid == userId;
    }
    
    // Trees collection
    match /trees/{treeId} {
      allow read: if true;
      allow write: if request.auth != null;
    }
    
    // Achievements collection
    match /achievements/{achievementId} {
      allow read: if true;
    }
  }
}
```

## 5. Enable Cloud Storage

1. Firebase Console → Storage
2. Create storage bucket
3. Set security rules:

```javascript
rules_version = '2';
service firebase.storage {
  match /b/{bucket}/o {
    match /users/{userId}/profile_pic {
      allow read, write: if request.auth.uid == userId;
    }
    match /trees/{treeId}/images {
      allow read: if true;
      allow write: if request.auth != null;
    }
  }
}
```

## 6. Enable Cloud Messaging

1. Firebase Console → Cloud Messaging
2. Generate server key
3. Store for push notifications

## 7. Environment Configuration

### Local Configuration (local.properties)
```properties
android.useAndroidX=true
android.enableJetifier=true
kotlin.code.style=official
```

### Production Build
```bash
# Set environment variables
export KEYSTORE_PASSWORD="your_password"
export KEY_ALIAS="treegrow"
export KEY_PASSWORD="your_key_password"

# Build release APK
./gradlew assembleRelease

# Build release AAB (for Play Store)
./gradlew bundleRelease
```

## 8. Google Maps Setup

1. Google Cloud Console → APIs
2. Enable Maps SDK for Android
3. Create API key
4. Add to `strings.xml`:
   ```xml
   <string name="google_maps_api_key">YOUR_KEY</string>
   ```

---

## Deployment Checklist

- [ ] Firebase project created
- [ ] Android app registered
- [ ] google-services.json added
- [ ] Authentication enabled
- [ ] Firestore database created
- [ ] Cloud Storage configured
- [ ] Cloud Messaging enabled
- [ ] Google Maps API key added
- [ ] Release keystore created
- [ ] Proguard rules configured
- [ ] App signing configured
- [ ] Privacy policy created
- [ ] Terms of service created

---

## Production Release Steps

1. Update version number in `build.gradle`
2. Test on multiple devices
3. Build release APK/AAB
4. Sign with release keystore
5. Test release build
6. Upload to Google Play Console
7. Monitor crash reports
8. Monitor analytics

