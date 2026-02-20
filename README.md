# Habit Check

A simple Android habit tracker app using Kotlin + Jetpack Compose.

## Features
- Add habit with custom target frequency
- Mark today\'s completion
- Delete habits
- Daily streak and 7-day completion rate
- Lightweight, clean screen composition

## Project structure
- `app/src/main/java/com/cys123431/habitcheck/` Main application + activity
- `app/src/main/java/com/cys123431/habitcheck/data/` Room entities, DAO, DB, repository, stats util
- `app/src/main/java/com/cys123431/habitcheck/ui/` Compose screens and app-level composables
- `app/src/main/java/com/cys123431/habitcheck/ui/theme/` App theme
- `app/src/main/res/` Resources

## Run locally
1. Open project in Android Studio
2. Install Android SDK + emulator or USB device (Android 8.0+, API 26+)
3. Run with `app` module

## Build APK
- Debug: `./gradlew assembleDebug`
- Release: `./gradlew assembleRelease`

Generated APK path:
- `app/build/outputs/apk/debug/app-debug.apk`
- `app/build/outputs/apk/release/app-release-unsigned.apk`

## Versioning and release
- Tag releases with semantic version e.g. `v0.1.0`
- Upload `app-release.apk` in GitHub Release assets after signing as needed
