package parking.model;

public class Car extends Vehicle {
    public Car(String regNo) { super(regNo); }
    @Override public int getHourlyRate() { return 30; }
}
