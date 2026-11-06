package parking.service;

import java.time.LocalTime;
import parking.model.Vehicle;

public final class EBikePricingRule implements PricingRule {
    private final int flatFee;
    public EBikePricingRule(int flatFee) {
        if (flatFee < 0) throw new IllegalArgumentException("Flat fee cannot be negative");
        this.flatFee = flatFee;
    }
    @Override public int calculate(Vehicle vehicle, int minutes, LocalTime entryTime, int dailyMaximum) {
        return minutes == 0 ? 0 : flatFee;
    }
}
