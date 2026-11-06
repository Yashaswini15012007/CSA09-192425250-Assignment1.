package parking.model;

import java.util.Objects;

public abstract class Vehicle {
    private final String regNo;

    protected Vehicle(String regNo) {
        if (regNo == null || regNo.isBlank()) throw new IllegalArgumentException("Registration is required");
        this.regNo = regNo;
    }

    public final String getRegNo() { return regNo; }
    public abstract int getHourlyRate();
    public String getType() { return getClass().getSimpleName(); }

    @Override public final boolean equals(Object other) {
        return other instanceof Vehicle vehicle && regNo.equals(vehicle.regNo);
    }
    @Override public final int hashCode() { return Objects.hash(regNo); }
    @Override public String toString() { return getType() + "[" + regNo + "]"; }
}
