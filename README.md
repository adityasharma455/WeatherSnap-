# WeatherSnap

WeatherSnap is a small Android application built as an internship assignment to demonstrate practical Android development skills using modern tools and clean architecture principles.

The app allows a user to:
- search live weather for a city using Open-Meteo
- view city suggestions with autocomplete
- create a weather report
- capture a photo using a custom CameraX screen
- compress the captured image
- add notes
- save the report locally in Room Database
- view all saved reports later

---

# Features

## 1. City Autocomplete

The app supports live city autocomplete using the Open-Meteo geocoding API.

After typing more than 2 letters, the app fetches matching city suggestions and allows the user to select the correct city before loading weather data.

---

## 2. Weather Search

After selecting a city, the app fetches live weather information from Open-Meteo.

Each weather result includes:

• city name  
• temperature  
• condition  
• humidity  
• wind speed  
• pressure  

The screen also supports:

• loading state  
• success state  
• empty state  
• error state  

---

## 3. Create Report

After weather is loaded, the user can create a report using the selected weather snapshot.

The Create Report screen includes:

• selected weather details  
• image preview area  
• Capture Photo button  
• notes input  
• Save button  

---

## 4. Custom Camera Screen

The app uses a custom CameraX screen instead of the device camera app intent.

The custom camera screen includes:

• live camera preview  
• Capture action  
• Close action  
• local file capture  
• image return to the report screen  

The captured image is compressed before being saved/displayed in the final report.

The screen also shows:

• original image size  
• compressed image size  

---

## 5. Saved Reports

All saved reports are stored locally in Room Database.

The Saved Reports screen displays:

• captured image  
• weather details saved at report creation time  
• notes  
• original image size  
• compressed image size  
• saved timestamp  

The screen also shows an empty state when no reports exist.

---

# Screenshots

| Weather_Screen | Create_Report |
|------------|--------|
| ![Weather_Screen](screenshots/Weather_Screen.jpeg) | ![Create_Report](screenshots/Create_Report.jpeg) |

| Custom_Camera | Saved_Reports |
|--------|--------|
| ![Custom_Camera](screenshots/Custom_Camera.jpeg) | ![Saved_Reports](screenshots/Saved_Reports.jpeg) |

## Demo Video
https://your-demo-video-link-here

---

# Tech Stack

• Kotlin  
• Jetpack Compose  
• Material 3  
• MVVM Architecture  
• ViewModel  
• StateFlow  
• Coroutines  
• Hilt  
• Navigation Compose  
• Retrofit  
• Gson Converter  
• OkHttp Logging Interceptor  
• Room Database  
• CameraX  
• Coil  

---

## Project Architecture

The application follows MVVM with a clean separation of responsibilities.

### Architecture Layers

presentation/  
UI layer built using Jetpack Compose and ViewModels.  
ViewModels manage UI state and interact with domain use cases.

domain/  
Contains business logic including:
- UseCases
- Domain models
- Repository interfaces

data/  
Implements repository interfaces and handles data sources:
- Open-Meteo API services
- Room Database
- Repository implementations
- Mappers
- Hilt modules

---

### MVVM Flow

The UI follows the MVVM pattern:

UI (Compose Screens)  
↓  
ViewModel  
↓  
UseCase  
↓  
Repository  
↓  
Data Source (Open-Meteo API / Room Database)

ViewModels expose StateFlows that are collected by Compose UI.

This ensures reactive UI updates and clean state management.

---

# API Used

This project uses Open-Meteo:

• Geocoding API for city suggestions  
• Forecast API for live weather details  

No API key is required.

---

# Setup Instructions

## 1. Clone the Repository

    git clone <your-repository-link>

## 2. Open the Project

    Open the project in Android Studio.

## 3. Sync Gradle

    Let Gradle sync all dependencies.

## 4. Run the App

    Run the app on an emulator or physical Android device.

## 5. Permissions

    Grant camera permission when prompted to use the custom camera screen.

---

## Notes

The app does not use a splash screen, onboarding, login screen, or settings page.

The app does not use the device camera intent.

Reports are stored locally in Room Database and are not saved only in memory.

The project focuses only on the required assignment flow.

---

👨‍💻 Author

Aditya Sharma
🎓 3rd Year Computer Science Student
📱 Android Developer | Kotlin | Jetpack Compose 

🔗 GitHub: https://github.com/adityasharma455
