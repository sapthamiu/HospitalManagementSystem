package src.common;
/*public class Hospital {
    private int totalBeds;
    public static int occupiedBeds;
    private int masksAvailable;
    private int cylindersAvailable;

    public Hospital(int totalBeds, int masksAvailable, int cylindersAvailable) {
        this.totalBeds = totalBeds;
        this.occupiedBeds = 0;
        this.masksAvailable = masksAvailable;
        this.cylindersAvailable = cylindersAvailable;
    }


    public void updateMasks(int masksUsed) {
        if (masksAvailable >= masksUsed) {
            masksAvailable -= masksUsed;
            System.out.println("Updated masks available: " + masksAvailable);
        } else {
            System.out.println("Not enough masks available.");
        }
    }

    public void updateCylinders(int cylindersUsed) {
        if (cylindersAvailable >= cylindersUsed) {
            cylindersAvailable -= cylindersUsed;
            System.out.println("Updated oxygen cylinders available: " + cylindersAvailable);
        } else {
            System.out.println("Not enough oxygen cylinders available.");
        }
    }

    public void displayResources() {
        System.out.println("Total Beds: " + totalBeds);
        System.out.println("Occupied Beds: " + occupiedBeds);
        System.out.println("Masks Available: " + masksAvailable);
        System.out.println("Oxygen Cylinders Available: " + cylindersAvailable);
    }
}*/
public class Hospital {
    private int totalBeds;
    public static int occupiedBeds; 
    public int masksAvailable;
    public int cylindersAvailable;

    public Hospital(int totalBeds, int masksAvailable, int cylindersAvailable) {
        this.totalBeds = totalBeds;
        this.occupiedBeds = 0;
        this.masksAvailable = masksAvailable;
        this.cylindersAvailable = cylindersAvailable;
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
    
    public void updateMasks(int masksUsed) {
        if (masksAvailable >= masksUsed) {
            masksAvailable -= masksUsed;
            System.out.println("Updated masks available: " + masksAvailable);
        } else {
            System.out.println("Not enough masks available.");
        }
    }

    public void updateCylinders(int cylindersUsed) {
        if (cylindersAvailable >= cylindersUsed) {
            cylindersAvailable -= cylindersUsed;
            System.out.println("Updated oxygen cylinders available: " + cylindersAvailable);
        } else {
            System.out.println("Not enough oxygen cylinders available.");
        }
    }

    public void displayResources() {
        System.out.println("Total Beds: " + totalBeds);
        System.out.println("Occupied Beds: " + occupiedBeds);
        System.out.println("Masks Available: " + masksAvailable);
        System.out.println("Oxygen Cylinders Available: " + cylindersAvailable);
    }
}