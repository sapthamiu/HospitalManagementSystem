package src.common;
public class Address {
    private int house_no;
    private String city;
    private int postal_code;

    public Address(int house_no, String city, int postal_code) {
        this.house_no = house_no;
        this.city = city;
        this.postal_code = postal_code;
    }

    public int getHouseNo() {
        return house_no;
    }

    public String getCity() {
        return city;
    }

    public int getPostalCode() {
        return postal_code;
    }
    public String toString()
    {
        return house_no + ", " + city + ", " + postal_code;
    }
}