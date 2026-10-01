# TreeGrow - نسخه Production Ready

## 📱 نسخه فعلی: 1.0.0 (Production)

### ✨ ویژگی‌های اصلی:

✅ **Firebase Authentication**
- ورود با ایمیل و رمز عبور
- ثبت‌نام کاربر جدید
- Session management

✅ **صفحات اصلی**
- Home - صفحه اصلی با آمار کاربر
- Map - نقشه تعاملی درخت‌ها
- Leaderboard - رتبه‌بندی کاربران
- Challenges - چالش‌های اجتماعی
- Achievements - نشان‌ها و دستاوردها
- Profile - پروفایل کاربر

✅ **معماری حرفه‌ای**
- MVVM + Clean Architecture
- Hilt Dependency Injection
- Flow-based state management
- Repository pattern
- Use Cases for business logic

✅ **Database & Cloud**
- Room Database (Local)
- Firestore (Cloud)
- Cloud Storage (Images)
- Cloud Messaging (Push Notifications)

✅ **Networking**
- Retrofit + OkHttp
- Error handling
- Request/Response logging
- Type-safe API calls

✅ **UI/UX**
- Material 3 Design
- Compose Navigation
- RTL Support (Farsi)
- Dark/Light Theme
- Responsive layouts

---

## 🔧 نصب و اجرا:

### پیش‌نیازها:
```bash
- Android Studio Flamingo+
- JDK 11+
- Kotlin 1.9+
- Gradle 8.0+
```

### مراحل نصب:

1. **Clone Repository**
```bash
git clone https://github.com/naomi197/treegrow-android.git
cd treegrow-android
```

2. **Firebase Setup**
```bash
# دانلود google-services.json از Firebase Console
# قرار دادن در app/google-services.json
```

3. **Build Project**
```bash
./gradlew build
./gradlew installDebug
```

4. **اجرا بر روی Device**
```bash
./gradlew installDebug
```

---

## 📂 ساختار پروژه:

```
app/src/main/kotlin/com/treegrow/app/
├── presentation/
│   ├── screens/
│   │   ├── auth/
│   │   │   ├── LoginScreen.kt
│   │   │   └── SignupScreen.kt
│   │   └── HomeScreenWithViewModel.kt
│   ├── viewmodel/
│   │   ├── AuthViewModel.kt
│   │   ├── HomeViewModel.kt
│   │   └── AchievementsViewModel.kt
│   └── navigation/
│       └── AppNavGraph.kt
├── domain/
│   ├── models/
│   │   ├── Tree.kt
│   │   ├── User.kt
│   │   ├── Achievement.kt
│   │   ├── Challenge.kt
│   │   └── NGO.kt
│   └── usecase/
│       ├── PlantTreeUseCase.kt
│       ├── GetUserStatsUseCase.kt
│       └── GetAchievementsUseCase.kt
├── data/
│   ├── local/
│   │   ├── dao/
│   │   │   ├── UserDao.kt
│   │   │   ├── TreeDao.kt
│   │   │   └── AchievementDao.kt
│   │   └── database/
│   │       ├── TreeGrowDatabase.kt
│   │       └── Converters.kt
│   ├── remote/
│   │   └── api/
│   │       └── TreeGrowApiService.kt
│   ├── repository/
│   │   ├── UserRepository.kt
│   │   ├── TreeRepository.kt
│   │   └── AchievementRepository.kt
├── di/
│   ├── DatabaseModule.kt
│   ├── FirebaseModule.kt
│   └── NetworkModule.kt
├── services/
│   └── FirebaseMessagingService.kt
├── utils/
│   ├── Constants.kt
│   └── DateTimeUtils.kt
├── MainActivity.kt
└── TreeGrowApp.kt
```

---

## 🔐 Firebase Setup:

برای راهنمایی کامل Firebase، فایل `FIREBASE_SETUP.md` را ببینید.

مراحل کوتاه:
1. Firebase Console میں پروژه ایجاد کریں
2. Android app ریجسٹر کریں
3. `google-services.json` ڈاؤن لوڈ کریں
4. Authentication، Firestore، Storage فعال کریں
5. Security Rules تنظیم کریں

---

## 🚀 Production Release:

### تیاری Release:
```bash
# Version update
# app/build.gradle.kts میں version code/name تبدیل کریں

# Build release APK
./gradlew assembleRelease

# Build AAB for Play Store
./gradlew bundleRelease
```

### Google Play Submission:
1. Google Play Console میں app بنائیں
2. Release notes تیار کریں
3. Screenshots اپ لوڈ کریں
4. Privacy Policy شامل کریں
5. AAB فائل اپ لوڈ کریں
6. Internal/Beta testing میں publish کریں
7. Production میں release کریں

---

## 📊 Analytics:

- Firebase Analytics
- Crash Reporting
- Performance Monitoring
- User Engagement Tracking

---

## 🤝 مشارکت:

اگر آپ مشارکت کرنا چاہتے ہیں:

1. Fork the repository
2. Feature branch بنائیں
3. Changes commit کریں
4. Push to branch
5. Pull Request بھیجیں

---

## 📄 License:

MIT License - تفصیلات کے لیے LICENSE فائل دیکھیں

---

## 📧 تماس:

- GitHub: [@naomi197](https://github.com/naomi197)
- Email: alirezafazeli@live.com

---

**خوش آمدید به TreeGrow! بیائیے دنیا کو سبز بنائیں! 🌍💚**
