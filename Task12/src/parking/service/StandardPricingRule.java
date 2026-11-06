package parking.service;

import java.time.LocalTime;
import parking.model.Vehicle;

public final class StandardPricingRule implements PricingRule {
    @Override public int calculate(Vehicle vehicle, int minutes, LocalTime entryTime, int dailyMaximum) {
        if (minutes == 0) return 0;
        long billedHours = (minutes + 59L) / 60L;
        long days = billedHours / 24L;
        long remainderHours = billedHours % 24L;
        long total = days * dailyMaximum + Math.min(remainderHours * vehicle.getHourlyRate(), dailyMaximum);
        if (vehicle instanceof parking.model.ElectricCar electricCar) total += electricCar.getChargingFee();
        return Math.toIntExact(total);
    }
}
