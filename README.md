# 📱 Registrasi App

<div align="center">

![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![Kotlin](https://img.shields.io/badge/Kotlin-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white)
![Material Design](https://img.shields.io/badge/Material%20Design-757575?style=for-the-badge&logo=material-design&logoColor=white)
![Min SDK](https://img.shields.io/badge/Min%20SDK-24-blue?style=for-the-badge)
![Target SDK](https://img.shields.io/badge/Target%20SDK-34-green?style=for-the-badge)

**Aplikasi Android form registrasi lengkap — Tugas Week 5 Pemrograman Mobile**

</div>

---

## 📋 Deskripsi

Registrasi App adalah aplikasi Android yang dibangun menggunakan **Kotlin** dan **Material Design Components**. Aplikasi ini merupakan implementasi form registrasi pengguna yang menggabungkan berbagai komponen UI interaktif yang dipelajari pada mata kuliah Pemrograman Mobile.

---

## ✅ Fitur yang Diimplementasikan

| # | Fitur | Keterangan |
|---|-------|------------|
| 01 | **Complete Form** | Form registrasi dengan `TextInputLayout`: Nama, Email, Password, Confirm Password |
| 02 | **Advanced Validation** | Validasi real-time saat mengetik + validasi penuh saat submit |
| 03 | **Selection Controls** | `RadioGroup` jenis kelamin + 5 `CheckBox` hobi (minimal 3 wajib dipilih) |
| 04 | **Spinner & Dialog** | `Spinner` 10 kota Indonesia + `AlertDialog` konfirmasi data sebelum submit |
| 05 | **Gesture Interaction** | `Long Press` pada tombol Submit dan Reset |
| 06 | **GitHub Repository** | Project di-upload ke GitHub dengan README lengkap |

---

---

## 🏗️ Struktur Project

```
RegistrasiApp/
├── app/
│   ├── src/
│   │   └── main/
│   │       ├── AndroidManifest.xml
│   │       ├── java/com/example/registrasiapp/
│   │       │   └── MainActivity.kt
│   │       └── res/
│   │           ├── drawable/
│   │           │   └── spinner_background.xml
│   │           ├── layout/
│   │           │   └── activity_main.xml
│   │           ├── values/
│   │           │   ├── colors.xml
│   │           │   ├── strings.xml
│   │           │   └── themes.xml
│   │           └── xml/
│   │               ├── backup_rules.xml
│   │               └── data_extraction_rules.xml
│   └── build.gradle
├── build.gradle
├── settings.gradle
└── README.md
```

---

## 🛠️ Tech Stack

| Komponen | Detail |
|----------|--------|
| Bahasa | Kotlin |
| IDE | Android Studio |
| Min SDK | API 24 (Android 7.0 Nougat) |
| Target SDK | API 34 (Android 14) |
| UI Library | Material Design Components 1.11.0 |
| AndroidX | Core KTX 1.12.0, AppCompat 1.6.1 |

---

## 📖 Penjelasan Fitur

### 1. Complete Form
Form registrasi menggunakan `TextInputLayout` dengan style `OutlinedBox` dari Material Design. Terdapat 4 field input:
- **Nama Lengkap** — input teks biasa
- **Alamat Email** — input dengan `inputType="textEmailAddress"`
- **Password** — input dengan toggle visibility
- **Konfirmasi Password** — input dengan toggle visibility

### 2. Advanced Validation
Validasi diimplementasikan dalam dua lapisan:
- **Real-time** — menggunakan `TextWatcher`, error langsung muncul saat pengguna mengetik
- **On Submit** — fungsi `validateAllFields()` memvalidasi semua field saat tombol ditekan

Aturan validasi:
- Semua field tidak boleh kosong
- Email harus sesuai format menggunakan `Patterns.EMAIL_ADDRESS`
- Password minimal 8 karakter
- Konfirmasi password harus cocok dengan password

### 3. Selection Controls
- **RadioGroup** — memilih jenis kelamin (Laki-laki / Perempuan), hanya 1 yang bisa dipilih
- **CheckBox** — 5 pilihan hobi, wajib memilih minimal 3
- Error ditampilkan secara dinamis jika belum memenuhi syarat

### 4. Spinner & Dialog
- **Spinner** — berisi daftar 10 kota Indonesia menggunakan `ArrayAdapter`
- **AlertDialog** — muncul setelah semua validasi lolos, menampilkan ringkasan data sebelum dikonfirmasi

### 5. Gesture Interaction — Long Press
```kotlin
// Long Press pada tombol Submit
btnSubmit.setOnLongClickListener {
    // Tampilkan dialog panduan pengisian form
    true // return true = event sudah dikonsumsi
}

// Long Press pada tombol Reset
btnReset.setOnLongClickListener {
    // Tampilkan dialog konfirmasi sebelum reset
    true
}
```

## Link Video Screen Record Demo Aplikasi

https://youtube.com/shorts/QstqcGgIEn8?feature=share

---

## 🚀 Cara Menjalankan Project

### Prasyarat
- Android Studio **Hedgehog** atau versi lebih baru
- JDK 17
- Android SDK API 24+

### Langkah-langkah

1. **Clone repository ini**
   ```bash
   git clone https://github.com/username/RegistrasiApp.git
   ```

2. **Buka di Android Studio**
   ```
   File → Open → pilih folder RegistrasiApp
   ```

3. **Sync Gradle**
   ```
   Klik "Sync Now" pada banner yang muncul, atau
   File → Sync Project with Gradle Files
   ```

4. **Jalankan aplikasi**
   ```
   Tekan tombol ▶ Run, pilih emulator atau device fisik (min API 24)
   ```

---

## 📦 Dependencies

```gradle
dependencies {
    implementation 'androidx.core:core-ktx:1.12.0'
    implementation 'androidx.appcompat:appcompat:1.6.1'
    implementation 'com.google.android.material:material:1.11.0'
    implementation 'androidx.constraintlayout:constraintlayout:2.1.4'
}
```

---

## 👨‍💻 Informasi Tugas

| | |
|---|---|
| **Mata Kuliah** | Pemrograman Mobile |
| **Tugas** | Week 5 |
| **Semester** | Genap 2025/2026 |
| **Nama** | Rayhan Dwi Saputra |
| **NIM** | 24552011135 |

---

<div align="center">
  <sub>Dibuat dengan ❤️ menggunakan Kotlin & Material Design</sub>
</div>
