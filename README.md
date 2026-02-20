# Habit Check

A simple Android habit tracker app built with Kotlin + Jetpack Compose.

## What is included
- Habit add/delete
- Daily completion toggle
- Streak and 7-day completion rate stats
- Home / Stats screen structure
- Room persistence for habit history
- README + Gradle project scaffold

## Repository
- Main branch: `main`
- Current tag: `v0.2.1`

## Run in Android Studio
1. Open this folder in Android Studio.
2. Set minimum SDK to Android 8.0+ (API 26+ is already set).
3. Run `Debug` on a real device to confirm on-device behavior.

## Local build
```
# from project root
gradlew.bat assembleDebug
# or on Linux/macOS
./gradlew assembleDebug
```

APK output:
- `app/build/outputs/apk/debug/app-debug.apk`

## GitHub publish (if repo is already created)
```
git push -u origin main
git push origin v0.1.0
```

If you have the `gh` CLI, release:
```
gh release create v0.2.1 app/build/outputs/apk/release/app-release-signed.apk --title "Habit Check v0.2.1" --notes-file RELEASE_NOTES.md
```

If you do not have `gh`, use GitHub web and upload the APK from the release screen.
