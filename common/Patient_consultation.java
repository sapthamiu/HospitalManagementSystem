package common;
import common.Patient_registration;
import java.time.*;
import common.Bed;
import common.Hospital;
import common.Doctor;
import common.exceptions.*;
import java.util.Scanner;
public class Patient_consultation
{
    Scanner sc = new Scanner(System.in);
    private Hospital hospital;
    private Patient_registration patient;
    private String dept; //Name of department
    private double amt; //Appointment fee, not to be confused with Doctor's consultation fee
    private double insvalue; //Insurance percentage
    private boolean isCritical; //Will be marked True by the Doctor if necessary
    private Bed b; //Will be assigned if isCritical = true
    private int bedNo;
    private LocalDate doc; //Date of appointment
    private LocalTime t; //Appointment time
    private Doctor d; //Doctor assigned
    // arr is the Linked List(LL) of all doctors in the hospital. Whenever a Doctor object is created, it should be added to this Linked List
    public static LL<Doctor> arr = new LL<Doctor>();
    public static LL<Patient_registration> arr1 = new LL<Patient_registration>();
    public Patient_consultation(String n, String x, int d, int m, int y, int hr, int min) throws NotRegisteredException
    {
        int check = checkRegistration(n);
        if(check == 0)
            throw new NotRegisteredException(n);
        amt = 500; //default appointment fee
        isCritical = false; //default value
        dept = x;
        assignDiscount();
        doc = LocalDate.of(y, m, d);
        t = LocalTime.of(hr, min);
    }
    public Patient_consultation()
    {
        amt = 500;
    }
    public Patient_consultation(String n, String x, int d, int m, int y) throws NotRegisteredException
    {
        int check = checkRegistration(n);
        if(check == 0)
            throw new NotRegisteredException(n);
        amt = 500; //default appointment fee
        isCritical = false; //default value
        dept = x;
        assignDiscount();
        doc = LocalDate.of(y, m, d);
    }
    public Doctor getDoctor()
    {
        return d;
    }
    public Patient_registration getPatient()
    {
        return patient;
    }
    public String getDept()
    {
        return dept;
    }
    public double getAmt()
    {
        return amt;
    }
    public double getInsurancePercentage()
    {
        return insvalue;
    }
    public LocalDate getDOC()
    {
        return doc;
    }
    public LocalTime getTime()
    {
        return t;
    }
    public boolean getCritical()
    {
        return isCritical;
    }
    public Bed getBed(){return b;}
    public int getBedno()
    {
        return b.getBedno();
    }
    public void setDoctor(Doctor m)
    {
        d = m;
    }
    public void setPatient(Patient_registration p)
    {
        patient = p;
    }
    public void setDept(String d)
    {
        dept = d;
    }
    public void setAmt(double x)
    {
        amt = x;
    }
    public void setInsurancePercentage(double b)
    {
        insvalue = b;
    }
    public void setDOC(LocalDate d)
    {
        doc = d;
    }
    public void setTime(LocalTime x)
    {
        t = x;
    }
    public void setCritical(boolean t)
    {
        isCritical = t;
    }
    public void setBedNo()
    {
        int bedno = Hospital.occupiedBeds++; //bedcount will be a static variable holding the number of occupied beds
        b = new Bed(bedno, hospital);
    }
    public void setbed(Bed bed){
        b = bed;
        this.bedNo = bed.getBedno();
        b.occupyBed();
    }
    public void recordVitalSigns(double heartRate, double spO2, double bp){
        if(b != null)
            b.setVitals(heartRate, spO2, bp);
        else System.out.println("No bed assigned to the patient.");
    }
    public void notifyDocifCritical(){
        if(b != null && b.isOccupied())
            b.notifyDoctor();

    }
    public int checkRegistration(String n) throws NotRegisteredException
    {
        Node<Patient_registration> temp = arr1.l;
        int flag = 0;
        while(temp != null){
            if(temp.data.getName().equals(n)){
                flag = 1;
                patient = temp.data;
                break;
            }
            temp = temp.next;
        }
        return flag;
		/*if(flag == 0)
			throw new NotRegisteredException(n);*/
    }
    public int assign_Doc(LocalDate d, String x) throws DoctorUnavailableException
    {
        int flag = 0;
        Node<Doctor> temp = arr.l;
        while(temp != null){
            if(temp.data.getDept().equals(x)){
                if(temp.data.isAvailable(d)){
                    this.d = temp.data;
                    flag = 1;
                    break;
                }
            }
            temp = temp.next;
        }
        if(flag == 0)
            throw new DoctorUnavailableException();
        return flag;
    }
    public void assignDiscount()
    {
        if(patient.getInsurance()){
            if(patient.getAge() >= 0 && patient.getAge() <= 12)
                insvalue = 0.15;
            else if(patient.getAge() >= 13 && patient.getAge() <= 19)
                insvalue = 0.12;
            else if(patient.getAge() >= 20 && patient.getAge() <= 35)
                insvalue = 0.1;
            else
                insvalue = 0.2;
        }
        else
            insvalue = 0;
    }
    public void calculateAmt()
    {
        amt -= amt * insvalue;
    }
    public void getDetails()
	{
		String name;
		int day, month, year/*, hr, min*/;
		amt = 500; //Default appointment fee
		isCritical = false; //default value
		System.out.println("Hello, enter your name");
		name = sc.nextLine();
        try{
		checkRegistration(name);
        }catch(NotRegisteredException e){
            System.out.println(e.getMessage());
            return;
        }
		System.out.println("Which department do you want to visit?");
		dept = sc.nextLine();
		System.out.println("Enter the date of appointment: In number format without any special characters");
		day = sc.nextInt();
		month = sc.nextInt();
		year = sc.nextInt();
		doc = LocalDate.of(year, month, day);
		// System.out.println("Enter the time of consultation: In 24 hr number format without special characters");
		// hr = sc.nextInt();
		// min = sc.nextInt();
		// t = LocalTime.of(hr, min);
		assignDiscount();
	}
	public void displayAppointment()
	{
		System.out.println("Success! Appointment booked");
		System.out.println("Doctor");
		System.out.println(d);
        System.out.println("Insurance: " + insvalue);
		System.out.println("Appointment date:" + doc);
	//	System.out.println("Appointment time:" + t);
		System.out.println("Appointment fee:" + amt);
	}
}
