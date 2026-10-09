# TreeGrow

[![Kotlin](https://img.shields.io/badge/Kotlin-1.9-blue)](https://kotlinlang.org/)
[![Android](https://img.shields.io/badge/Android-API%2024%2B-green)](https://www.android.com/)
[![License](https://img.shields.io/badge/License-MIT-yellow)](LICENSE)

Android application for virtual tree planting and personal environmental-impact tracking.

Users record planted trees, review achievements, and keep a local profile. Room stores trees, users, and achievements on the device. Firebase, network, and authentication modules are in the project. Sign-in, a live map, and NGO planting are still on the roadmap.

Developer: Alireza Sani · [alirezafazeli@live.com](mailto:alirezafazeli@live.com)

## Features

- Virtual tree planting
- Local Room database for trees, users, and achievements
- Jetpack Compose screens for home, sign-in, and sign-up
- Domain models for trees, users, achievements, challenges, and NGOs
- Hilt modules for the database, Firebase, and the network client

## Technology

- Kotlin
- Jetpack Compose and Material 3
- Room
- Hilt
- Retrofit and OkHttp

## Setup

Android Studio is required.

```bash
git clone https://github.com/naomi197/treegrow-android.git
cd treegrow-android
./gradlew assembleDebug
```

`app/google-services.json` is a local placeholder so the project compiles without a Firebase account. Replace it with the file from a Firebase project before using email sign-in. Until then, choose **Continue on this device**. Tree records are stored in Room on the phone, and the add button writes a local tree.

## Project structure

```text
app/src/main/kotlin/com/treegrow/app/
├── data/local/          # Room database and DAOs
├── data/remote/         # API service
├── data/repository/
├── domain/models/
├── domain/usecase/
├── presentation/        # Compose screens and view models
└── di/
```

## Roadmap

- Firebase Authentication
- Remote database sync
- Map of planted trees
- Push notifications
- Offline sync beyond the local database
- NGO planting partners

## Related work

- [ClimaScope](https://github.com/naomi197/climascope) — live climate observatory for Android and the browser
- [Climate Assistant](https://github.com/naomi197/weather-climate-assistant) — Android weather and air-quality client
- [CleanFlow Ghana](https://github.com/naomi197/cleanflow-ghana) — water-pollution reporting and priority ranking

## License

MIT License. See [LICENSE](LICENSE).
