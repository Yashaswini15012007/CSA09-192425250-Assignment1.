package parking.model;

public final class TwoWheeler extends Vehicle {
    public TwoWheeler(String regNo) { super(regNo); }
    @Override public int getHourlyRate() { return 10; }
}
