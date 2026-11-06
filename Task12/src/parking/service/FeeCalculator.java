package parking.service;

import java.time.Duration;
import java.time.LocalTime;
import parking.model.DeliveryVan;
import parking.model.EBike;
import parking.model.Vehicle;

public final class FeeCalculator {
    private final int dailyMaximum;
    private final PricingRule standardRule = new StandardPricingRule();
    private final PricingRule deliveryVanRule = new DeliveryVanPricingRule();
    private final PricingRule ebikeRule = new EBikePricingRule(25);

    public FeeCalculator() { this(100); }
    public FeeCalculator(int dailyMaximum) {
        if (dailyMaximum < 0) throw new IllegalArgumentException("Daily maximum cannot be negative");
        this.dailyMaximum = dailyMaximum;
    }
    public int calculateFee(Vehicle vehicle, int minutes) {
        return calculateFee(vehicle, minutes, LocalTime.MIDNIGHT);
    }
    public int calculateFee(Vehicle vehicle, int minutes, LocalTime entryTime) {
        if (vehicle == null || minutes < 0) throw new IllegalArgumentException("Vehicle and nonnegative duration are required");
        if (entryTime == null) throw new IllegalArgumentException("Entry time is required");
        PricingRule rule = vehicle instanceof EBike ? ebikeRule
                : vehicle instanceof DeliveryVan ? deliveryVanRule : standardRule;
        return rule.calculate(vehicle, minutes, entryTime, dailyMaximum);
    }
    public int calculateFee(Vehicle vehicle, LocalTime in, LocalTime out) {
        if (in == null || out == null) throw new IllegalArgumentException("Times are required");
        long minutes = Duration.between(in, out).toMinutes();
        if (minutes < 0) minutes += 24L * 60L;
        return calculateFee(vehicle, Math.toIntExact(minutes), in);
    }
}
