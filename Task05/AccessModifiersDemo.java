class AccessVehicle {
    public String category = "campus vehicle";
    protected int hourlyRate = 30;
    String parkingZone = "North";
    private String regNo = "TN05XY5555";

    public String getRegNo() {
        return regNo;
    }
}

class AccessCar extends AccessVehicle {
    int getRate() {
        return hourlyRate;
    }
}

public class AccessModifiersDemo {
    public static void main(String[] args) {
        AccessCar car = new AccessCar();
        System.out.println("Public: " + car.category);
        System.out.println("Protected via subclass: " + car.getRate());
        System.out.println("Package-private: " + car.parkingZone);
        System.out.println("Private via getter: " + car.getRegNo());
    }
}