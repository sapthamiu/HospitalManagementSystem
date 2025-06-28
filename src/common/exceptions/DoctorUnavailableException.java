package src.common.exceptions;
public class DoctorUnavailableException extends Exception
{
    public DoctorUnavailableException(){
        System.out.println("Sorry! No doctor is free in the entered slot");
    }
}