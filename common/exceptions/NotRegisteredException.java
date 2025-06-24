package common.exceptions;
public class NotRegisteredException extends Exception
{
    public NotRegisteredException(String n){
        System.out.println("Sorry, " + n + " is not registered");
    }
}
