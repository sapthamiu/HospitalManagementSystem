package common;
import common.Doctor;
import common.exceptions.*;
import common.DOB;
import common.Address;
import common.Hospital;

/*public class Bed{
    private int bedno;
    //private String dept;
    private double heartRate;
    private double spO2;
    private double bp;
    private boolean help; //toggle to true if urgent help needed
    private boolean isOccupied; //true when bed is occupied
    private Doctor assignedDoc;
    public Bed(int bedno) {
        this.bedno = bedno;
        //this.dept = dept;
        this.heartRate = 0;
        this.spO2 = 0;
        this.bp = 0;
        this.help = false;
        this.isOccupied = false; //initial condition
    }
    public int getBedno() { return bedno; }
    //public String getDept() { return dept; }
    public boolean isOccupied() { return isOccupied; }

    public void setVitals(double heartRate, double spO2, double bp) {
        this.heartRate = heartRate;
        this.spO2 = spO2;
        this.bp = bp;
        checkVitals();
    }

    public void setHelp(boolean help){
        this.help = help;
        if(help) notifyDoctor();
    }

    public void assignDoc(Doctor doc){
        this.assignedDoc = doc;
        System.out.println("Doctor " + doc.getDname() + " assigned to bed " + bedno);
    }
    public void checkVitals() {
        if (heartRate < 60 || heartRate > 100 || spO2 < 90 || bp > 140) {
            help = true;
            notifyDoctor();
        } else {
            help = false;
        }
    }

    public void notifyDoctor() {
        if(assignedDoc != null){
            System.out.println("Alerting Doctor " + assignedDoc.getDname() + ": Patient in bed " + bedno + " has critical vitals.");
            assignedDoc.respondToHelp(this);
        }
        else{
            System.out.println("No doctor assigned to this bed. ");
        }
    }

    public boolean freeBed(Bed bed) {
        if (occupiedBeds > 0) {
            occupiedBeds--;
            System.out.println("Bed " + bed.getBedno() + " has been freed. Total occupied beds: " + occupiedBeds);
            return true;
        } else{
            System.out.println("No bed is currently occupied.");
            return false;
        }
    }

    public Bed occupyBed() {
        if (occupiedBeds < totalBeds) {
            occupiedBeds++;
            System.out.println("A bed has been occupied. Total occupied beds: " + occupiedBeds);
            return new Bed(occupiedBeds, this);
        } else {
            System.out.println("No beds available.");
            return null;
        }
    }

    public void displayStatus() {
        // System.out.println("Bed No: " + bedno + "\nDepartment: " + dept);
        System.out.println("Heart Rate: " + heartRate + "\nSpO2: " + spO2 + "\nBlood Pressure: " + bp);
        System.out.println("Help Needed: " + (help ? "Yes" : "No"));
        System.out.println("Occupied: " + (isOccupied ? "Yes" : "No"));
        System.out.println("Assigned Doctor: " + assignedDoc.getDname());
    }
}*/

public class Bed{
    private int bedno; 
    private double heartRate; 
    private double spO2; 
    private double bp; 
    private boolean help; //toggle to true if urgent help needed
    private boolean isOccupied; //true when bed is occupied
    private Doctor assignedDoc;
    private Hospital hospital;
    public Bed(int bedno, Hospital hospital) {
        this.bedno = bedno;
        this.hospital = hospital;
        this.heartRate = 0;
        this.spO2 = 0;
        this.bp = 0;
        this.help = false;
        this.isOccupied = false; //initial condition

    }

    public int getBedno() { return bedno; }
    public boolean isOccupied() { return isOccupied; }

    public void setVitals(double heartRate, double spO2, double bp) {
        if(isOccupied){
            this.heartRate = heartRate;
            this.spO2 = spO2;
            this.bp = bp;
            checkVitals();
        }else  System.out.println("Bed " + bedno + " is unoccupied. Please occupy before setting vitals.");
    }

    public void setHelp(boolean help){
        this.help = help;
        if(help){
            if(assignedDoc != null)
            notifyDoctor();
        }else System.out.println("Help required, but no doctor is currently assigned to bed " + bedno);
    }

   public void assignDoc(Doctor doc){
        this.assignedDoc = doc;
        System.out.println("Doctor " + doc.getDname() + " assigned to bed " + bedno);
    }
    private void checkVitals() {
        if (heartRate < 60 || heartRate > 100 || spO2 < 90 || bp > 140) {
            help = true;
            notifyDoctor();
        } else
            help = false;
    }

    public void notifyDoctor() {
        if(assignedDoc != null){
            System.out.println("Alerting Doctor " + assignedDoc.getDname() + ": Patient in bed " + bedno + " has critical vitals.");
            assignedDoc.respondToHelp(this);
        }
        else
            System.out.println("No doctor assigned to this bed. ");
    }

    public void freeBed() {
        if (isOccupied && hospital.freeBed(this)) {
            isOccupied = false;
            heartRate = 0;
            spO2 = 0;
            bp = 0;
            help = false;
           // occupiedBeds--;
            assignedDoc = null;
            System.out.println("Bed " + bedno + " has been freed and is now available.");
        } else
            System.out.println("Bed " + bedno + " is already unoccupied.");
    }

    public void occupyBed() {
        if (!isOccupied /*&& hospital.occupyBed() != null*/) {
            isOccupied = true;
            //occupiedBeds++;
            System.out.println("Bed " + bedno + " is now occupied.");
        } else {
            System.out.println("Bed " + bedno + " is already occupied.");
        }
    }

    public void displayStatus() {
        System.out.println("Bed No: " + bedno);
        System.out.println("Heart Rate: " + heartRate + "\nSpO2: " + spO2 + "\nBlood Pressure: " + bp);
        System.out.println("Help Needed: " + (help ? "Yes" : "No"));
        System.out.println("Occupied: " + (isOccupied ? "Yes" : "No"));
        System.out.println("Assigned Doctor: " + (assignedDoc != null ? assignedDoc.getDname() : "None"));
    }
}