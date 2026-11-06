import java.util.Objects;

interface Chargeable {
    double chargingFee(int kWh);
}

abstract class Vehicle {
    private final String regNo;

    protected Vehicle(String regNo) {
        if (regNo == null || regNo.isBlank()) {
            throw new IllegalArgumentException("Registration number cannot be blank");
        }
        this.regNo = regNo;
    }

    public final String getRegNo() {
        return regNo;
    }

    protected abstract double ratePerHour();

    public final double calculateFee(int minutes) {
        if (minutes < 0) {
            throw new IllegalArgumentException("Duration cannot be negative");
        }
        int hours = (minutes + 59) / 60;
        return hours * ratePerHour();
    }

    @Override
    public final boolean equals(Object other) {
        return other instanceof Vehicle vehicle && regNo.equals(vehicle.regNo);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(regNo);
    }
}

final class TwoWheeler extends Vehicle {
    TwoWheeler(String regNo) {
        super(regNo);
    }

    @Override
    protected double ratePerHour() {
        return 10;
    }
}

class Car extends Vehicle {
    Car(String regNo) {
        super(regNo);
    }

    @Override
    protected double ratePerHour() {
        return 30;
    }
}

final class ElectricCar extends Car implements Chargeable {
    ElectricCar(String regNo) {
        super(regNo);
    }

    @Override
    public double chargingFee(int kWh) {
        if (kWh < 0) {
            throw new IllegalArgumentException("Energy cannot be negative");
        }
        return kWh * 12.0;
    }
}

public class FeeDemo {
    public static void main(String[] args) {
        Vehicle[] vehicles = {
            new TwoWheeler("TN01A1"),
            new Car("TN02B2"),
            new ElectricCar("TN03C3")
        };

        for (Vehicle vehicle : vehicles) {
            double fee = vehicle.calculateFee(130);
            if (vehicle instanceof Chargeable chargeable) {
                fee += chargeable.chargingFee(10);
            }
            System.out.println(vehicle.getClass().getSimpleName() + " " + vehicle.getRegNo()
                    + " -> Rs " + fee);
        }
        System.out.println(new Car("TN02B2").equals(new ElectricCar("TN02B2")));
    }
}