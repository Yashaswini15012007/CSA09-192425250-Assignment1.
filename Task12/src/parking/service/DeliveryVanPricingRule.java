package parking.service;

import java.time.LocalTime;
import parking.model.Vehicle;

public final class DeliveryVanPricingRule implements PricingRule {
    private static final LocalTime START = LocalTime.of(9, 0);
    private static final LocalTime END = LocalTime.of(17, 0);

    @Override public int calculate(Vehicle vehicle, int minutes, LocalTime entryTime, int dailyMaximum) {
        if (minutes == 0) return 0;
        long billedHours = (minutes + 59L) / 60L;
        long total = 0;
        long dayHours = 0;
        LocalTime blockStart = entryTime;
        for (long hour = 0; hour < billedHours; hour++) {
            boolean daytime = !blockStart.isBefore(START) && blockStart.isBefore(END);
            dayHours += daytime ? vehicle.getHourlyRate() * 2L : vehicle.getHourlyRate();
            blockStart = blockStart.plusHours(1);
            if ((hour + 1) % 24 == 0) {
                total += Math.min(dayHours, dailyMaximum);
                dayHours = 0;
            }
        }
        total += Math.min(dayHours, dailyMaximum);
        return Math.toIntExact(total);
    }
}
