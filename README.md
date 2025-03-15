# 🏥 Hospital Management System  

## 📌 Project Overview  
The **Hospital Management System** is a Java-based application that helps streamline hospital operations. This system allows hospital administrators to manage **doctors, patients, appointments, medical tests, and billing** efficiently.  

It is a **console-based** system that stores data persistently in text files, making it simple yet effective for small-scale hospital management.

---

## 🚀 Features  

### 👨‍⚕️ Doctor Management  
✔ Add, update, delete, and view doctor details.  
✔ Doctors have a **name, specialization, and experience** field.  

### 🏥 Patient Management  
✔ Register new patients with **name, age, and disease** details.  
✔ Update or delete existing patient records.  

### 📅 Appointment System  
✔ Schedule patient appointments with available doctors.  
✔ View, update, or cancel existing appointments.  

### 🧪 Test Management  
✔ Assign medical tests to patients.  
✔ Add new test types and view available tests.  

### 🧾 Billing System  
✔ Calculate total bill based on:  
   - Consultation fees  
   - Medical test charges  
   - Hospital stay duration  
✔ Display itemized bill breakdown in tabular format.  

### 💾 Persistent Data Storage  
✔ All records are stored in text files (`doctors.txt`, `patients.txt`, etc.), ensuring data is saved even after program termination.  

---

## 🛠️ Installation & Setup  

### Prerequisites  
- **Java Development Kit (JDK 8 or later)**  
- **Git** (for cloning the repository, optional)  

### 📥 Clone the Repository  
```sh
git clone https://github.com/YOUR_GITHUB_USERNAME/HospitalManagementSystem.git
cd HospitalManagementSystem
```

### ▶️ Run the Project  
```sh
javac HospitalManagementSystem.java
java HospitalManagementSystem
```

---

## 📂 Project Structure  
```
HospitalManagementSystem/
│── src/
│   ├── HospitalManagementSystem.java  # Main Java Program
│── data/
│   ├── doctors.txt                    # Stored Doctor Data
│   ├── patients.txt                    # Stored Patient Data
│   ├── appointments.txt                # Stored Appointments
│   ├── tests.txt                        # Stored Test Data
│── README.md

```

---

## 📜 How the System Works  

### 🔹 Doctor Management  
1. **Add a Doctor**: Enter name, specialization, and experience.  
2. **View Doctors**: Displays a list of available doctors.  
3. **Update Doctor Details**: Modify specialization and experience.  
4. **Delete a Doctor**: Remove a doctor from the system.  

### 🔹 Patient Management  
1. **Register a Patient**: Enter name, age, and disease.  
2. **View Patients**: List all registered patients.  
3. **Update Patient Details**: Modify age and disease.  
4. **Delete a Patient**: Remove patient records.  

### 🔹 Appointment Management  
1. **Schedule an Appointment**: Assign a doctor to a patient on a specific date.  
2. **View Appointments**: List all scheduled appointments.  
3. **Update Appointment Date**: Modify appointment details.  
4. **Cancel an Appointment**: Remove a scheduled appointment.  

### 🔹 Test Management  
1. **Assign Tests to a Patient**: Select tests from the available list.  
2. **Add New Tests**: Create new medical tests with costs.  
3. **View Available Tests**: Display all registered tests.  

### 🔹 Billing System  
1. **Generate Bill for a Patient**  
   - Consultation Fee: ₹1000 per visit  
   - Test Charges: Variable based on assigned tests  
   - Hospital Stay Charges: ₹400 per day  

---

## 🏗️ Future Enhancements  
🔹 **Graphical User Interface (GUI)** using JavaFX/Swing.  
🔹 **Database Integration** with MySQL or PostgreSQL.  
🔹 **User Authentication** for secure access.  
🔹 **Doctor Availability System** to avoid overlapping appointments.  

---

## 🏆 Why Use This Project?  
✅ **Simple and Effective**: Console-based system for quick access.  
✅ **Easy to Modify**: Readable Java code structure.  
✅ **Persistent Data Storage**: Keeps data across sessions.  

---

## 🤝 Contributing  
We welcome contributions! To contribute:  
1. Fork the repository  
2. Create a new branch (`feature-newFunctionality`)  
3. Commit changes and push  
4. Open a pull request  

---

## 📞 Contact  
For any issues or suggestions, feel free to reach out:  
📧 Email: **swarup8125@gmail.com**  
🔗 GitHub: [Chennuru Swarup](https://github.com/swarup79997/)  

---
