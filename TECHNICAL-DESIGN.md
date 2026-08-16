# Hospital Management System Technical Design

## 1. Project Overview
The Hospital Management System is a Java-based console application that simulates core hospital operations using Object-Oriented Programming (OOP) principles. It bridges the gap between patient needs (registration, appointment booking, medical status) and hospital resources (doctors, departments, beds, and medical supplies).

The system serves as a comprehensive simulation demonstrating state management, custom data structure implementation, and real-time interaction between various hospital entities.

## 2. Problem Statement
Traditional hospital environments face challenges in coordinating resources efficiently:
- Tracking patient registration and mapping them to available doctors in specific departments.
- Managing doctor availability to prevent booking conflicts.
- Monitoring critical patients and dynamically assigning hospital beds based on real-time availability.
- Managing hospital resources (masks, oxygen cylinders) effectively.

This system centralizes these operations into an interactive terminal interface, ensuring that patient appointments are valid, critical care is expedited, and hospital bed availability is strictly tracked.

## 3. Users and Core Workflows

The application supports three primary interaction models:

### 3.1 Patient Functionality
- **Registration:** Register with personal demographics, contact info, and insurance status.
- **Booking:** Book appointments with a specific department on a specific date.
- **Viewing Details:** View patient profile and consultation details.

### 3.2 Doctor Functionality
- **Availability Management:** Set specific dates as unavailable to block appointments.
- **Critical Care:** Evaluate patient vitals and mark patients as critical.
- **Bed Management:** Automatically trigger bed assignment for critical patients and manually discharge them to free up beds.

### 3.3 Resource Management (Admin View)
- View hospital-wide resources (total beds, occupied beds, masks, oxygen cylinders).
- View total doctor counts and specific doctor details across all specialized departments (Cardiology, Neurology, etc.).

## 4. System Architecture

### 4.1 High-Level Architecture
The system follows a monolithic, menu-driven architecture running in a standard Java Virtual Machine (JVM). 

```text
Main.java (Controller & UI Router)
 ├── Patient Workflows
 ├── Doctor Workflows
 └── Resource Dashboards
      │
      ├── (Models & Business Logic)
      ├── Hospital, Bed
      ├── Patient_registration, Patient_consultation
      ├── Doctor
      │
      └── (Storage Layer)
          Custom Linked List (LL<T>)
```

### 4.2 Package Structure
- `src.common`: Contains core entities (`Hospital`, `Bed`, `Doctor`, `Patient_registration`, `Patient_consultation`, `LL`, `Node`).
  - Also contains `DOB` and `Address` (composition entities used by `Patient_registration`).
  - Also includes `Client` and `Server` classes, which are implemented for a planned (but currently unused) socket communication feature.
- `src.alldepartments`: Contains the `BaseDepartment` abstract class and its specific concrete implementations (e.g., `Cardiology`, `Oncology`).
- `src.common.exceptions`: Custom exception classes (`NotRegisteredException`, `DoctorUnavailableException`, `InvalidContactNumberException`).
- `src.Main`: The entry point containing the `public static void main` method and terminal UI loops.

## 5. Domain Model and Data Structures

### 5.1 Design Philosophy
The application strictly adheres to OOP principles (Encapsulation, Inheritance, Abstraction, and Polymorphism). Data is not stored in standard Java Collections (like `ArrayList`); instead, a custom Generic Linked List (`LL<T>`) is implemented from scratch to act as the in-memory database.

### 5.2 Core Entities

| Entity | Purpose |
|--------|---------|
| **Hospital** | Singleton-like manager for global resources (Beds, Masks, Cylinders). Tracks `occupiedBeds`. |
| **Bed** | Represents a physical bed. Holds patient vitals (HR, SpO2, BP) and tracks occupancy status and assigned doctor. |
| **Doctor** | Represents a medical professional. Tracks department, qualifications, and an array of `unavailableDates`. |
| **Patient_registration** | Stores demographic data (Name, Age, Contact, Insurance). Uses composition for `DOB` and `Address`. |
| **DOB & Address** | Composition classes representing Date of Birth and physical location details, respectively. |
| **Patient_consultation** | The critical link between Patient and Doctor. Stores appointment date, fee, assigned bed (if critical), and insurance-applied discounts. |
| **BaseDepartment** | Abstract base class that holds an `LL<Doctor>`. Extended by specific departments. |

### 5.3 Custom Data Structure (Generic Linked List)
To store global state, the system utilizes a custom `LL<T>` class with `Node<T>`.
- `Patient_consultation.arr1`: An `LL<Patient_registration>` storing all registered patients globally.
- `Patient_consultation.arr`: An `LL<Doctor>` storing all doctors globally.
- Each `BaseDepartment` instance also maintains its own `LL<Doctor>` to allow O(1) departmental filtering.

## 6. Request Lifecycle (Workflows)

### 6.1 Patient Registration and Booking
1. **Registration:** User inputs demographics. A `Patient_registration` object is created and appended to `Patient_consultation.arr1`. A unique Registration Number is generated based on the current date.
2. **Booking:**
   - The user inputs their Registration Number. The system performs a linear search `O(n)` through `arr1` to find the patient.
   - User inputs Department and Date.
   - A `Patient_consultation` object is instantiated.
   - `assign_Doc(LocalDate, String)` searches the global doctor list (`arr`) for a doctor matching the department who is *also* available on that date.
   - If no doctor is found, a `DoctorUnavailableException` is thrown.
   - If found, the appointment is booked and fees are calculated based on age-bracket insurance rules.

### 6.2 Critical Patient & Bed Assignment Flow
1. **Trigger:** A doctor selects "Mark Patient as Critical" and provides a Registration Number.
2. **Evaluation:** The system fetches the patient's `Patient_consultation` record.
3. **Bed Assignment:**
   - `Hospital.occupyBed()` is called. If `occupiedBeds < totalBeds`, it increments the counter and returns a new `Bed` object.
   - The `Bed` is assigned to the consultation record.
4. **Vitals Simulation:** The system simulates critical vitals (e.g., HR=85, SpO2=92, BP=130) and binds them to the bed.

### 6.3 Doctor Unavailability
Doctors manage a standard primitive array `LocalDate[] unavailableDates`.
When a doctor marks a date as unavailable, the system iterates through this array. If there is space (limit 100), the date is appended. Future `assign_Doc` queries iterate through this array to ensure the doctor isn't booked on their day off.

### 6.4 Discharge Flow
1. A doctor inputs a Registration Number to discharge.
2. The system checks if the patient is currently marked as critical and has a bed.
3. If confirmed, `Hospital.freeBed()` decrements `occupiedBeds`.
4. The patient's critical flag is toggled to false, freeing the physical resource for the next patient.

## 7. Exception Handling & Business Rules

The application enforces business rules using a mix of conditional logic and custom Exception classes:

- **NotRegisteredException:** Thrown if an operation is attempted on a patient name that cannot be found in the global patient list.
- **DoctorUnavailableException:** Thrown during booking if all doctors in the requested department have marked the requested `LocalDate` as unavailable.
- **InvalidContactNumberException:** A custom exception defined in the package, ready for future robust contact validation (currently unused in main workflow).
- **DateTimeParseException:** Handled gracefully using standard Java `try-catch` blocks to prevent the console application from crashing if a user types a date in the wrong format (not `YYYY-MM-DD`).
- **Bed Capacity Limits:** Hardcoded logic in `Hospital.java` prevents `occupyBed()` from assigning a bed if `occupiedBeds >= totalBeds`.

## 8. Security and Validation

As a local console application, traditional web security (SQLi, XSS) does not apply. However, input validation is enforced:
- Scanner input mismatches (e.g., typing letters instead of integers) will cause `InputMismatchException`, though some areas lack robust `try-catch` wrappers for this, assuming clean user input.
- Data privacy is encapsulated; one patient cannot easily access another's data without knowing their specific Registration Number.

## 9. Known Limitations and Proposed Improvements

### 9.1 Data Persistence
- **Limitation:** The system operates entirely in memory (RAM). When the Java process terminates, all registered patients, booked appointments, and doctor unavailabilities are lost.
- **Improvement:** Implement serialization or integrate a lightweight database (like SQLite or PostgreSQL via JDBC) to persist data across sessions.

### 9.2 Data Structures & Performance
- **Limitation:** Searching for patients and doctors uses a custom Linked List (`LL<T>`), which forces an `O(n)` linear search time complexity for every lookup.
- **Improvement:** Replace or supplement the Linked List with Hash Maps (`HashMap<Integer, Patient_registration>`) where the Key is the Registration Number, allowing `O(1)` lookups.

### 9.3 Hardcoded State
- **Limitation:** The `unavailableDates` inside the `Doctor` class is a fixed primitive array of size 100. If a doctor exceeds this, the application cannot record more dates.
- **Improvement:** Refactor to use dynamic collections like `ArrayList` or `HashSet<LocalDate>` for O(1) date lookups and infinite bounds.

### 9.4 Architectural Scalability
- **Limitation:** The `Main.java` file handles UI rendering (System.out.print), input gathering, and business logic routing. This violates the Single Responsibility Principle.
- **Improvement:** Implement an MVC (Model-View-Controller) pattern. Separate the terminal rendering logic into `View` classes, and the routing logic into dedicated `Controller` classes.

### 9.5 Authentication
- **Limitation:** Any user who runs the application has access to both Patient and Doctor menus.
- **Improvement:** Introduce a login system defining roles (Admin, Doctor, Patient) to restrict access to specific menus.
