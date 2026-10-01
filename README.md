# 🌳 TreeGrow - Virtual Tree Planting App

[![Kotlin](https://img.shields.io/badge/Kotlin-1.9-blue)](https://kotlinlang.org/)
[![Android](https://img.shields.io/badge/Android-API%2024%2B-green)](https://www.android.com/)
[![License](https://img.shields.io/badge/License-MIT-yellow)](LICENSE)

## 📱 نمای کلی (Overview)

**TreeGrow** یک اپلیکیشن اندرویدی است که کاربران را تشویق می‌کند درخت‌های مجازی بکارند و با سازمان‌های محیط زیستی همکاری کنند تا درخت‌های واقعی کاشته شوند.

### ✨ ویژگی‌های اصلی:

- 🌱 **کاشت درخت مجازی** - برای هر فعالیت صحیح محیطی
- 🏆 **نشان‌ها و دستاوردها** - انگیزه‌دهی مستمر کاربران
- 📊 **نقشه تعاملی** - دیدن درخت‌های کاشته شده در جهان
- 🔗 **ادغام سازمان‌ها** - پیوند با NGO‌های محیط زیستی
- 👥 **رقابت اجتماعی** - Leaderboard و چالش‌های دسته‌جمعی
- 📈 **آمار و تحلیل** - ردگیری تاثیر محیطی شخصی
- 🌍 **سازگاری جهانی** - پشتیبانی چندزبانه

---

## 🛠️ تکنولوژی استفاده شده:

- **Language**: Kotlin
- **UI Framework**: Jetpack Compose + Material 3
- **Architecture**: MVVM + Clean Architecture
- **Database**: Room + Firebase Firestore
- **Authentication**: Firebase Auth
- **Maps**: Google Maps API
- **Cloud**: Firebase (Storage, Analytics, Push Notifications)
- **DI**: Hilt
- **Networking**: Retrofit + OkHttp
- **Testing**: JUnit, Mockito, Espresso

---

## 📁 ساختار پروژه:

```
treegrow-android/
├── app/src/main/kotlin/com/treegrow/app/
│   ├── ui/
│   │   ├── screens/
│   │   │   ├── HomeScreen.kt
│   │   │   ├── MapScreen.kt
│   │   │   ├── LeaderboardScreen.kt
│   │   │   ├── AchievementsScreen.kt
│   │   │   ├── ChallengesScreen.kt
│   │   │   └── ProfileScreen.kt
│   │   ├── components/
│   │   │   └── BottomNavBar.kt
│   │   ├── navigation/
│   │   │   └── NavGraph.kt
│   │   └── theme/
│   │       ├── Theme.kt
│   │       ├── Color.kt
│   │       └── Type.kt
│   ├── domain/
│   │   └── models/
│   │       ├── Tree.kt
│   │       ├── User.kt
│   │       ├── Achievement.kt
│   │       ├── Challenge.kt
│   │       └── NGO.kt
│   ├── data/
│   │   └── local/
│   │       └── database/
│   │           └── TreeGrowDatabase.kt
│   └── MainActivity.kt
├── build.gradle.kts
└── README.md
```

---

## 🚀 شروع کار:

### پیش‌نیازها:
- Android Studio Flamingo یا بالاتر
- JDK 11+
- Kotlin 1.9+
- Gradle 8.0+

### نصب و اجرا:

```bash
# Clone the repository
git clone https://github.com/naomi197/treegrow-android.git
cd treegrow-android

# Build the project
./gradlew build

# Run on emulator or device
./gradlew installDebug
```

---

## 📋 نقشه راه (Roadmap):

- [x] Setup project structure
- [x] Create main UI screens
- [ ] Firebase Authentication
- [ ] Real-time Database Integration
- [ ] Google Maps Integration
- [ ] Push Notifications
- [ ] Offline Mode
- [ ] Multi-language Support
- [ ] Analytics Dashboard
- [ ] NGO Integration

---

## 👥 مشارکت:

ما از مشارکت‌های شما استقبال می‌کنیم! لطفاً این مراحل را دنبال کنید:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit changes (`git commit -m 'Add AmazingFeature'`)
4. Push to branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

---

## 📞 تماس و پشتیبانی:

- 📱 GitHub: [@naomi197](https://github.com/naomi197)
- 💬 Issues: [Report a bug](https://github.com/naomi197/treegrow-android/issues)
- 📧 Email: alirezafazeli@live.com

---

## 📄 License:

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

**خوش آمدید به TreeGrow! بیایید دنیا را سبز‌تر کنیم! 🌍💚**
