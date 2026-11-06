package parking.model;

public final class EBike extends Vehicle {
    public EBike(String regNo) { super(regNo); }
    @Override public int getHourlyRate() { return 0; }
}
