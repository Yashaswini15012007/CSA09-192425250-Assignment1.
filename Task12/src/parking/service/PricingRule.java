package parking.service;

import java.time.LocalTime;
import parking.model.Vehicle;

public interface PricingRule {
    int calculate(Vehicle vehicle, int minutes, LocalTime entryTime, int dailyMaximum);
}
