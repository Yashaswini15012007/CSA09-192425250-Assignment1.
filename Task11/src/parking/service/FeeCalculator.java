package parking.service;

import java.time.Duration;
import java.time.LocalTime;
import parking.model.ElectricCar;
import parking.model.Vehicle;

public final class FeeCalculator {
    private final int dailyMaximum;

    public FeeCalculator() { this(100); }
    public FeeCalculator(int dailyMaximum) {
        if (dailyMaximum < 0) throw new IllegalArgumentException("Daily maximum cannot be negative");
        this.dailyMaximum = dailyMaximum;
    }
    public int calculateFee(Vehicle vehicle, int minutes) {
        if (vehicle == null || minutes < 0) throw new IllegalArgumentException("Vehicle and nonnegative duration are required");
        if (minutes == 0) return 0;
        long hours = (minutes + 59L) / 60L;
        long days = hours / 24L;
        long remainingHours = hours % 24L;
        long rate = vehicle.getHourlyRate();
        long total = days * dailyMaximum + Math.min(remainingHours * rate, dailyMaximum);
        if (vehicle instanceof ElectricCar electricCar) total += electricCar.getChargingFee();
        return Math.toIntExact(total);
    }
    public int calculateFee(Vehicle vehicle, LocalTime in, LocalTime out) {
        if (in == null || out == null) throw new IllegalArgumentException("Times are required");
        long minutes = Duration.between(in, out).toMinutes();
        if (minutes < 0) minutes += 24L * 60L;
        return calculateFee(vehicle, Math.toIntExact(minutes));
    }
}
