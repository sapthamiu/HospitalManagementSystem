package src.common;
import java.time.*;
import java.util.Scanner;

import src.common.Address;
import src.common.Bed;
import src.common.DOB;
import src.common.Doctor;
import src.common.Patient_consultation;
import src.common.exceptions.*;
public class Patient_registration {
    private String name;
    private int age;
    private DOB dateOfBirth;
    private Address address;
    private long contact_no;
    private static int patient_count = 0;
    private String bloodGroup;
    private String gender;
    private boolean insurance;
    private LocalDate firstDayAtHospital;
    private int reg_no;
    private Patient_consultation consultation;

    public Patient_registration(String n, int a, DOB dob, Address add, long contact, String bg, String g, boolean insure) {
        name = n;
        age = a;
        dateOfBirth = dob;
        address = add;
        contact_no = contact;
        patient_count++;
        firstDayAtHospital = LocalDate.now();
        reg_no = generateRegistrationNo();
        bloodGroup = bg;
        gender = g;
        insurance = insure;
        Patient_consultation.arr1.insert(this);
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public DOB getdateOfBirth() {
        return dateOfBirth;
    }

    public Address getAddress() {
        return address;
    }

    public long getContact() {
        return contact_no;
    }

    public int getRegistrationNo() {
        return reg_no;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }
    public LocalDate getFirstDayAtHospital(){
        return firstDayAtHospital;
    }

    public String getGender() {
        return gender;
    }

    public boolean getInsurance() {
        return insurance;
    }

    public void setInsurance(boolean i) {
        insurance = i;
    }

    public void setAge(int a) {
        age = a;
    }

    public void setAddress(Address add) {
        address = add;
    }

    public void setContact(long contact) {
        contact_no = contact; // Add missing semicolon
    }

    public Patient_consultation getConsultation(){return consultation;}
    
    public void setConsultation(Patient_consultation consultation){
        this.consultation = consultation;
    }
    public int generateRegistrationNo() {
        // Format: MMYYXX where XX is patient count
        String firstMonth = Integer.toString(getFirstDayAtHospital().getMonthValue());
        String firstYear = Integer.toString(getFirstDayAtHospital().getYear());
        String lastTwoOfYear = firstYear.substring(2);

        if (firstMonth.length() == 1) {
            firstMonth = "0" + firstMonth;
        }

        String count = Integer.toString(patient_count);
        if (count.length() == 1) {
            count = "0" + count;
        }

        String reg = firstMonth + lastTwoOfYear + count;
        return Integer.parseInt(reg);
    }

    public void SchemeAllotment() {
        if (age >= 0 && age <= 12) {
            System.out.println("Kid's care Scheme available with a 15% discount.\n");
        } else if (age >= 13 && age <= 19) {
            System.out.println("Teen Health Plan is available with a 12% discount.\n");
        } else if (age >= 20 && age <= 35) {
            System.out.println("Young Adult Care is available with a 10% discount.\n");
        } else if (age >= 36 && age <= 59) {
            System.out.println("Middle-Age Wellness Plan is available with a 20% discount.\n");
        } else {
            System.out.println("Senior Citizen Health Plan is available with a 20% discount.\n");
        }
    }

    public void displayPatientDetails(){
        System.out.println("Name: " + name);
        System.out.println("Registration No.: " + reg_no);
        if(consultation != null){
            System.out.println("Consultation Details: ");
            consultation.displayAppointment();
        }
        else System.out.println("No consultation scheduled.");
    }
    
    public void InsuranceCheck(Scanner sc) {
        System.out.print("Do you have an insurance? (Enter true or false): ");
        insurance = sc.nextBoolean();
    }

    public void UpdateAge() {
        LocalDate currentDate = LocalDate.now();
        int birthDay = dateOfBirth.getDay();
        int birthMonth = dateOfBirth.getMonth();
        if (currentDate.getMonthValue() == birthMonth && currentDate.getDayOfMonth() == birthDay) {
            age += 1;
        }
    }

    public void SchemeUpdate() {
        UpdateAge();
        if (age == 13 || age == 20 || age == 36 || age == 60)
            SchemeAllotment();
    }
    public String toString() {
        return "Name: " + name + "\n" + "Age: " + age + "\n" + "Date of Birth: " + dateOfBirth + "\n" + "Address: " + address + "\n" + "Contact no: " + contact_no + "\n" + "Blood Group: " + bloodGroup + "\n" + "Gender: " + gender + "\n" + "Insurance: " + insurance + "\n" + "Registration no: " + reg_no;
    }
    public boolean equals(Object s)
    {
        Patient_registration x = (Patient_registration)(s);
        if((x.reg_no == reg_no))
            return true;
        return false;
    }
}