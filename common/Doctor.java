package common;
import java.time.*;
import java.util.Scanner;
import common.Patient_registration;
import common.LL;
import common.exceptions.*;
import alldepartments.*;

public class Doctor{
    private String dname;
    private String dept;
    private String id;
    private String phone;
    private String qualification;
    private boolean available;
    private boolean retired;
    private LocalDate[] unavailableDates;
    private int unavLength;
    public String getDname(){return dname;}
    public String getDept(){return dept;}
    public String getID(){return id;}
    public String getPhone(){return phone;}
    public String getQualification(){return qualification;}
    public void setDname(String dname){this.dname = dname;}
    public void setDept(String dept){this.dept = dept;}
    public void setID(String id){this.id = id;}
    public void setPhone(String phone){this.phone = phone;}
    public void setQualification(String qualification){this.qualification = qualification;}

    public Doctor(String dname, String dept, String id){
        this.dname = dname;
        this.dept = dept;
        this.id = id;
        this.retired=false;
        this.available = true;
        this.unavailableDates = new LocalDate[100];
        this.unavLength = 0;
        Patient_consultation.arr.insert(this);
    }
    public boolean isAvailable(LocalDate date){
        for(LocalDate unavDate:unavailableDates)
            if(unavDate!= null && unavDate.equals(date)){
                return false;
            }
        return true;
    }
    public boolean isRetired(){
        return retired;
    }
    //doctor takes his leaves with a max leave limit
    public void setUnavailable(LocalDate date){
        if(unavLength < unavailableDates.length){
            unavailableDates[unavLength] = date;
            unavLength++;
            System.out.println("Doctor " + dname + " is now unavailable on " + date);
        }
        else System.out.println("Cannot add more unavailable dates");
    }
    //will check if patient is critical based on signs from bed class
    public void signCritical(Patient_consultation patient, double heartRate, double spO2, double bp){
        if(heartRate < 60 || heartRate > 100 || spO2 < 90 || bp > 140 )
            patient.setCritical(true);
        System.out.println("patient has been marked as critical based on vitals by Doctor " + dname);
    }
    //doctor can manually set patient as critical
    public void markCritical(Patient_consultation patient){
        patient.setCritical(true);
        System.out.println("Doctor " + dname + " has marked the patient (insert name) as critical.");
    }
    //assign bed if patient is critical
    public void assignBed(Patient_consultation patient, Bed bed){
        if(patient.getCritical()){
            bed.assignDoc(this);
            patient.setBedNo();
            System.out.println("Bed no.: " + patient.getBedno() + " has been assigned to the critical patient");
        }
        else System.out.println("Patient is not critical. No bed required");
    }

    //respond to help
    public void respondToHelp(Bed bed){
        System.out.println("Doctor " + dname + " notified: Patient in bed " + bed.getBedno() + "needs attention. ");
    }
    //discharging
    public void dischargePatient(Patient_consultation patient){
        if(patient.getCritical()){
            patient.setCritical(false);
            Hospital.occupiedBeds--;
            System.out.println("Doctor " + dname + " has discharged the patient from bed " + patient.getBedno());
        }
        else System.out.println("Patient is not critical, no bed assigned to discharge.");
    }
    //display
    public String toString(){
        return "\nDoctor: " + dname + "\nDept: " + dept + "\n";
    }
    public boolean equals(Object s)
    {
        Doctor x = (Doctor)(s);
        if((x.id == id))
            return true;
        return false;
    }
}