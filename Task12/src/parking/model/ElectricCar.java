package parking.model;

public final class ElectricCar extends Car {
    private final int chargingKWh;

    public ElectricCar(String regNo) { this(regNo, 0); }
    public ElectricCar(String regNo, int chargingKWh) {
        super(regNo);
        if (chargingKWh < 0) throw new IllegalArgumentException("Charging energy cannot be negative");
        this.chargingKWh = chargingKWh;
    }
    public int getChargingKWh() { return chargingKWh; }
    public int getChargingFee() { return chargingKWh * 12; }
    @Override public String toString() { return super.toString() + ", charging=" + chargingKWh + "kWh"; }
}
