package src;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import src.alldepartments.*;
import src.common.*;
import src.common.exceptions.*;

public class Main {
    private static Hospital hospital;
    private static Scanner sc = new Scanner(System.in);
    static Cardiology d1 = new Cardiology();
    static Dermatology d2 = new Dermatology();
    static Gynaecology d3 = new Gynaecology();
    static Oncology d4 = new Oncology();
    static Neurology d5 = new Neurology();
    static Orthopaedics d6 = new Orthopaedics();
    static Pediatrics d7 = new Pediatrics();
    static Emergency d8 = new Emergency();

    public static void main(String[] args) {
        hospital = new Hospital(100, 50, 20); 
        int choice;
        if(Patient_consultation.arr1 == null){
            Patient_consultation.arr1 = new LL<Patient_registration>();
        }
        if (Patient_consultation.arr == null) {
            Patient_consultation.arr = new LL<Doctor>();
        }

        Doctor doc1 = new Doctor("Dr. Smith", "Cardiology", "01");
        Doctor doc2 = new Doctor("Dr. Johnson", "Orthopaedics", "02");
        Doctor doc3 = new Doctor("Dr. Brown", "Pediatrics", "03");
        Doctor doc4 = new Doctor("Dr. Taylor", "Neurology", "04");
        Doctor doc5 = new Doctor("Dr. Anderson", "Dermatology", "05");
        Doctor doc6 = new Doctor("Dr. Martin", "Oncology", "06");
        Doctor doc7 = new Doctor("Dr. White", "Gynaecology", "07");
        Doctor doc8 = new Doctor("Dr. Garcia", "Cardiology", "08");
        Doctor doc9 = new Doctor("Dr. Martinez", "Cardiology", "09");
        Doctor doc10 = new Doctor("Dr. Lee", "Neurology", "10");
        Doctor doc11 = new Doctor("Dr. Clark", "Orthopaedics", "11");
        Doctor doc12 = new Doctor("Dr. Lewis", "Pediatrics", "12");
        Doctor doc13 = new Doctor("Dr. Walker", "Dermatology", "13");
        Doctor doc14 = new Doctor("Dr. Hall", "Gynaecology", "14");
        Doctor doc15 = new Doctor("Dr. Allen", "Oncology", "15");

        d1.InsertDoctor(doc1);
        d6.InsertDoctor(doc2);
        d7.InsertDoctor(doc3);
        d5.InsertDoctor(doc4);
        d2.InsertDoctor(doc5);
        d4.InsertDoctor(doc6);
        d3.InsertDoctor(doc7);
        d1.InsertDoctor(doc8);
        d1.InsertDoctor(doc9);
        d5.InsertDoctor(doc10);
        d6.InsertDoctor(doc11);
        d7.InsertDoctor(doc12);
        d2.InsertDoctor(doc13);
        d3.InsertDoctor(doc14);
        d4.InsertDoctor(doc15);

        do{
            System.out.println("\nWelcome to the Hospital Management System");
            System.out.println("1. Doctor Functionality");
            System.out.println("2. Patient Functionality");
            System.out.println("3. Display Hospital Resources");
            System.out.println("4. Exit");
            System.out.print("Select an option: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    doctorMenu();
                    break;
                case 2:
                    patientMenu();
                    break;
                case 3:
                    resourcesMenu();
                    break;
                case 4:
                    System.out.println("Thank you. Exiting.");
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        } while (choice != 4);
    }

    private static void resourcesMenu()
    {
        int choice;
        do{
            System.out.println("\nResources menu");
            System.out.println("1. Display details of doctors");
            System.out.println("2. Display other resources");
            System.out.println("3. Back to main menu");
            System.out.println("Select an option: ");
            choice = sc.nextInt();
            sc.nextLine();
           switch(choice)
            {
                case 1:
                    deptsMenu();
                    break;
                case 2:
                    hospital.displayResources();
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }while(choice != 3);
    }

    private static void deptsMenu()
    {
        int choice;
        do{
            System.out.println("\nDepartments menu");
            System.out.println("1. Cardiology");
            System.out.println("2. Dermatology");
            System.out.println("3. Gynaecology");
            System.out.println("4. Oncology");
            System.out.println("5. Neurology");
            System.out.println("6. Orthopaedics");
            System.out.println("7. Pediatrics");
            System.out.println("8. Emergency");
            System.out.println("9. Back to main menu");
            System.out.println("Select an option: ");
            choice = sc.nextInt();
            sc.nextLine();
           switch(choice)
            {
                case 1:
                    System.out.println("Number of doctors: " + d1.getDoctorCount());
                    System.out.println("Doctors are: ");
                    d1.DisplayDoctors();
                    break;
                case 2:
                    System.out.println("Number of doctors: " + d2.getDoctorCount());
                    System.out.println("Doctors are: ");
                    d2.DisplayDoctors();
                    break;
                case 3:
                    System.out.println("Number of doctors: " + d3.getDoctorCount());
                    System.out.println("Doctors are: ");
                    d3.DisplayDoctors();
                    break;
                case 4:
                    System.out.println("Number of doctors: " + d4.getDoctorCount());
                    System.out.println("Doctors are: ");
                    d4.DisplayDoctors();
                    break;
                case 5:
                    System.out.println("Number of doctors: " + d5.getDoctorCount());
                    System.out.println("Doctors are: ");
                    d5.DisplayDoctors();
                    break;
                case 6:
                    System.out.println("Number of doctors: " + d6.getDoctorCount());
                    System.out.println("Doctors are: ");
                    d6.DisplayDoctors();
                    break;
                case 7:
                    System.out.println("Number of doctors: " + d7.getDoctorCount());
                    System.out.println("Doctors are: ");
                    d7.DisplayDoctors();
                    break;
                case 8:
                    System.out.println("Number of doctors: " + d8.getDoctorCount());
                    System.out.println("Doctors are: ");
                    d8.DisplayDoctors();
                    break;
                case 9:
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }while(choice != 9);
    }
    private static void doctorMenu() {
        if(Patient_consultation.arr == null)
            Patient_consultation.arr = new LL<>();
        if(Patient_consultation.arr.l == null){
            System.out.println("No doctors are available to set unavailability.");
            return;
        }
        int choice;
        do{
            System.out.println("\nDoctor Menu");
            System.out.println("1. Set Unavailability");
            System.out.println("2. Mark Patient as Critical");
            System.out.println("3. Discharge Patient");
            System.out.println("4. Back to Main Menu");
            System.out.print("Select an option: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    // Set doctor as unavailable
                    System.out.println("Enter doctor's ID to mark as unavailable: ");
                    String doctorId = sc.nextLine();
                    System.out.println("Enter the date (YYYY-MM-DD): ");
                    String dateS = sc.nextLine();
                    try{
                        LocalDate date = LocalDate.parse(dateS);
                        LL<Doctor> temp = Patient_consultation.arr;
                        boolean found = false;
                        Node<Doctor> node = temp.l;
                        while(node != null){
                            Doctor doctor = node.data;
                            if(doctor.getID().equalsIgnoreCase(doctorId) && doctor.isAvailable(date)){
                                doctor.setUnavailable(date);
                                found = true;
                                break;
                            }
                            node = node.next;
                        }
                        if(!found)
                            System.out.println("Doctor not found or is already unavailable.");
                    }catch(DateTimeParseException e){
                        System.out.println("Invalid date format: " + e.getMessage());
                    }
                    break;
                case 2:
                    // Mark patient as critical
                    System.out.println("Enter patient registration number to mark as critical: ");
                    int regNo = sc.nextInt();
                    sc.nextLine();
                    
                    Node<Patient_registration> currentNode = Patient_consultation.arr1.l;
                    boolean foundP = false;
                    // //test
                    // System.out.println("Searching for patient with Reg No: " + regNo);
                    while(currentNode != null){
                    //     System.out.println("Checking patient with Reg No: " + currentNode.data.getRegistrationNo());
                        Patient_registration patientR = currentNode.data;
                        if(patientR.getRegistrationNo() == regNo){
                            foundP = true;
                           // System.out.println("Found patient with Reg No: " + regNo);
                            Patient_consultation consultation = patientR.getConsultation();
                            if (consultation == null) {
                                System.out.println("Consultation is null for patient " + patientR.getRegistrationNo());
                            }

                            if(consultation != null){
                                consultation.setCritical(true);
                                Bed assignedBed = hospital.occupyBed();
                                if(assignedBed != null){
                                    consultation.setbed(assignedBed);
                                    System.out.println("Patient " + patientR.getName() + " has been marked as critical and assigned to bed "+ assignedBed.getBedno());
                                    double heartRate = 85;
                                    double spO2 = 92;
                                    double bp = 130;
                                    consultation.recordVitalSigns(heartRate, spO2, bp);
                                }
                                else System.out.println("No available beds to assign.");
                            }
                            else System.out.println("No consultation found for this patient.");
                            break;
                        }
                        currentNode = currentNode.next;
                    }
                    if(!foundP)
                        System.out.println("Patient with Registration number " + regNo + " not found.");
                    break;
                case 3:
                    // Free a bed
                    System.out.println("Enter patient's Reg. No. to discharge:");
                    regNo = sc.nextInt();
                    sc.nextLine();
                    currentNode = Patient_consultation.arr1.l;
                    boolean foundPtofree = false;
                    while(currentNode != null){
                        Patient_registration patientR = currentNode.data;
                        if(patientR.getRegistrationNo() == regNo){
                            foundPtofree = true;
                            Patient_consultation consultation = patientR.getConsultation();
                            if(consultation != null){
                                if(consultation.getCritical()){
                                    System.out.println("Patient is marked as critical. Are you sure you want to discharge? (yes/no)");
                                    String confirmation = sc.nextLine();
                                    if(!confirmation.equalsIgnoreCase("yes")){
                                        System.out.println("Discharge canceled.");
                                        break;
                                    }
                                    consultation.setCritical(false);
                                }
                                Bed bedtofree = consultation.getBed();
                                if(bedtofree != null){
                                    hospital.freeBed(bedtofree);
                                    System.out.println("Patient " + patientR.getName() + " has been discharged successfully.");
                                    //consultation.setbed(null);
                                }else System.out.println("No bed is assigned to patient " + patientR.getName());
                            }
                            break;
                        }
                        currentNode = currentNode.next;
                    }
                    if(!foundPtofree)
                        System.out.println("Patient not found.");
                    break;
                case 4:
                    // Back to main menu
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        } while (choice != 4);
    }

    private static void patientMenu() {
        int choice;
        
        System.out.println("\nPatient Menu");
        System.out.println("1. Register Patient");
        System.out.println("2. Book Appointment");
        System.out.println("3. Display Patient Details");
        System.out.println("4. Back to Main Menu");
        do {
            System.out.print("Select an option: ");
            choice = sc.nextInt();
            sc.nextLine(); 
            switch (choice) {
                case 1:
                    // Register patient
                    System.out.println("Enter patient details: ");
                    System.out.print("Enter name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter age: ");
                    int age = sc.nextInt();
                    sc.nextLine(); 
                    System.out.println("Enter date of birth(YYYY-MM-DD): ");
                    String dobS = sc.nextLine();
                    String[] dobParts = dobS.split("-");
                    int day = Integer.parseInt(dobParts[2]);
                    int month = Integer.parseInt(dobParts[1]);
                    int year = Integer.parseInt(dobParts[0]);
                    DOB dob = new DOB(day, month, year);

                    System.out.print("Enter address details: ");
                    System.out.print("House number: ");
                    int houseNo = sc.nextInt();
                    sc.nextLine();
                    System.out.print("City: ");
                    String city = sc.nextLine();
                    System.out.print("Postal Code: ");
                    int postalcode = sc.nextInt();
                    sc.nextLine();
                    Address address = new Address(houseNo, city, postalcode);

                    System.out.print("Enter contact number: ");
                    long contactNumber = sc.nextLong();
                    sc.nextLine();

                    System.out.println("Enter blood group: ");
                    String bg = sc.nextLine();

                    System.out.println("Choose gender: ");
                    String g = sc.nextLine();

                    System.out.println("Do you have insurance?(yes/no): ");
                    boolean insure = sc.nextLine().equalsIgnoreCase("yes");

                    Patient_registration patient = new Patient_registration(name, age, dob, address, contactNumber, bg, g, insure);
                    //Patient_consultation.arr1.insert(patient);
                    System.out.println("Patient " + patient.getName() + " registered with reg no " + patient.getRegistrationNo());

                    // //test
                    // System.out.println("Current registered patients:");
                    // Node<Patient_registration> tempNode = Patient_consultation.arr1.l;
                    // while (tempNode != null) {
                    //     System.out.println("Patient Reg No: " + tempNode.data.getRegistrationNo());
                    //     tempNode = tempNode.next;
                    // }

                    break;
                case 2:
                    // Book an appointment
                    System.out.println("Enter patient's registration number: ");
                    int regNo = sc.nextInt();
                    sc.nextLine();

                    boolean foundP = false;
                    Node<Patient_registration> currentNode = Patient_consultation.arr1.l;
                    while (currentNode != null){
                        Patient_registration patientR = currentNode.data;
                        
                        if (patientR.getRegistrationNo() == regNo) {
                            foundP = true;
                            System.out.println("Enter department: ");
                            String department = sc.nextLine();

                            System.out.print("Enter appointment date (YYYY-MM-DD): ");
                            String dateS = sc.nextLine();
                            LocalDate appDate = null;
                            try{
                                appDate = LocalDate.parse(dateS);
                            }catch(DateTimeParseException e){
                                System.out.println("Invalid date format. Please enter the date in YYYY-MM-DD format.");
                                return;
                            }
                            
                            try{
                                Patient_consultation consultation = new Patient_consultation(patientR.getName(), department, appDate.getDayOfMonth(), appDate.getMonthValue(), appDate.getYear());
                                patientR.setConsultation(consultation);
                                consultation.calculateAmt();
                                System.out.println("Appointment booked successfully for " + patientR.getName() + " in " + department + " on " + appDate.getDayOfMonth() + "/" +  appDate.getMonthValue() + "/" + appDate.getYear());
                                try{
                                    consultation.assign_Doc(appDate, department);
                                    consultation.displayAppointment();
                                }catch(DoctorUnavailableException e){
                                    System.out.println("No Doctor available in the " + department + " department on the selected date.");
                                    return;
                                }
                            } catch(NotRegisteredException e){
                                System.out.println("Error: " + e.getMessage());
                            }
                            
                            break;
                        }
                        currentNode = currentNode.next;
                    }
                    if (!foundP) {
                        System.out.println("Patient with registration number " + regNo + " not found.");
                    }
                    break;
                case 3:
                    // Display patient details
                    System.out.println("Enter registration number to display details: ");
                    int regNum = sc.nextInt();
                    sc.nextLine();
                    boolean foundPD = false;
                    Node<Patient_registration> currentD = Patient_consultation.arr1.l;
                    while(currentD != null){
                        Patient_registration patientD = currentD.data;
                        if(patientD.getRegistrationNo() == regNum){
                            foundPD = true;

                            System.out.println("\n--- Patient Details ---");
                            System.out.println("Name: " + patientD.getName());
                            System.out.println("Age: " + patientD.getAge());
                            System.out.println("DOB: " + patientD.getdateOfBirth());
                            System.out.println("Address: " + patientD.getAddress());
                            System.out.println("Contact Number: " + patientD.getContact());
                            System.out.println("Blood Group: " + patientD.getBloodGroup());
                            System.out.println("Gender: " + patientD.getGender());
                            System.out.println("Insurance Status: " + (patientD.getInsurance() ? "Insured" : "Not Insured"));
                            
                            Patient_consultation consultation = patientD.getConsultation();
                            if(consultation != null)
                                consultation.displayAppointment();
                            else System.out.println("No consultation scheduled for this patient: ");
                            break;
                        }
                        currentD = currentD.next;
                    }
                    if(!foundPD)
                    System.out.println("Patient with Registration number " + regNum + " not found.");
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        } while (choice != 4);
    }
}
