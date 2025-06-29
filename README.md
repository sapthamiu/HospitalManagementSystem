# Hospital Management System (Java, OOP)
[Watch Demo](HospitalManagementSystemDemo.mp4)

A console-based hospital simulation tool built with Java and Object-Oriented Programming. Supports both doctor and patient functionalities—such as managing appointments, admissions, and bed allocations—with a modular, department-based structure.


## Project Structure

```bash
HospitalManagementSystem/
├── src/
│   ├── alldepartments/         # Contains department-wise classes (Cardiology, Neurology, etc.)
│   ├── common/                 # Core hospital classes (Doctor, Patient, Bed, Hospital, etc.)
│   │   └── exceptions/         # Custom exception handling (e.g., DoctorUnavailableException)
│   └── Main.java               # Entry point and menu-driven terminal interface
```

## Features

### Doctor Functionality

* Mark unavailable dates
* Mark patients as critical → auto-bed assignment
* Discharge patients → free beds

### Patient Functionality

* Register with personal and medical info
* Book appointments with department choice
* Auto-assign available doctor
* View appointment and health details

### Resource Functionality

* View doctors by department
* View hospital bed and resource availability


## Tech Stack

* Language: Java
* Paradigm: Object-Oriented Programming
* UI: Console (menu-driven); GUI with JavaFX was explored separately


## Team & My Role

* **Team Project** (3 members)

  * I developed most of the doctor-side functionality, including:
    * Marking unavailability
    * Critical patient flow and bed management
    * Discharge system
  * Compiled and structured the `Main.java` logic
  * Shared class definitions (common module) were equally distributed
  * GUI (JavaFX) was developed by the team but removed from this version

## How To Run (Locally)

```shell
# Clone the repository
git clone https://github.com/sapthamiu/HospitalManagementSystem.git
cd HospitalManagementSystem

# Compile the program
javac -Xlint src/common/*.java src/common/exceptions/*.java src/alldepartments/*.java src/Main.java

# Run the program
java src/Main
```

## Future Improvements

* Add login/authentication system for roles: Doctor, Patient, Admin
* Reintegrate or redesign JavaFX GUI
* Introduce persistent storage (file or database)
* Add admin panel for full resource control
* Provide analytics and reports (e.g., patient flow, bed usage)
