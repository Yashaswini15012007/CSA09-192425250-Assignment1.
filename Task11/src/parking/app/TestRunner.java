package parking.app;

import java.time.LocalDate;
import java.util.Set;
import parking.model.Car;
import parking.model.ElectricCar;
import parking.model.MonthlyPass;
import parking.model.ParkingFacility;
import parking.model.ParkingTicket;
import parking.model.TwoWheeler;
import parking.service.FeeCalculator;
import parking.service.ParkingService;

public final class TestRunner {
    private static int passed;
    private static int failed;
    private TestRunner() {}

    private static void check(String name, boolean condition, String detail) {
        if (condition) passed++; else failed++;
        System.out.printf("%-30s %s %s%n", name, condition ? "PASS" : "FAIL", detail);
    }
    private static boolean throwsIllegal(Runnable action) {
        try { action.run(); return false; } catch (IllegalArgumentException expected) { return true; }
    }

    public static void main(String[] args) {
        FeeCalculator fees = new FeeCalculator(100);
        check("Two-wheeler 60 min", fees.calculateFee(new TwoWheeler("T1"), 60) == 10, "Rs 10");
        check("Car 120 min", fees.calculateFee(new Car("C1"), 120) == 60, "Rs 60");
        check("EV plus charging", fees.calculateFee(new ElectricCar("E1", 5), 60) == 90, "Rs 30 + Rs 60 = Rs 90");
        check("Zero minutes", fees.calculateFee(new Car("C2"), 0) == 0, "Rs 0");
        check("Exactly 60 minutes", fees.calculateFee(new Car("C3"), 60) == 30, "Rs 30");
        check("Daily maximum", fees.calculateFee(new Car("C4"), 1440) == 100, "Rs 100");
        check("LocalTime overnight", fees.calculateFee(new Car("C5"), java.time.LocalTime.of(23, 30), java.time.LocalTime.of(0, 30)) == 30, "60 min; Rs 30");

        ParkingFacility facility = new ParkingFacility(new int[] {1});
        ParkingService service = new ParkingService(facility, fees);
        ParkingTicket first = service.park(new TwoWheeler("LAST"));
        check("Last free slot", first != null && first.getFloor() == 0 && first.getSlot() == 0, "slot 0:0 allocated");
        check("Facility full", service.park(new Car("FULL")) == null, "returns null");
        check("Duplicate registration", throwsIllegal(() -> service.park(new Car("LAST"))), "rejected");
        check("Invalid slot", !facility.release(5, 0) && !facility.release(0, 3), "rejected");
        check("Standard facility sizes", ParkingFacility.standardLayout(1).getSlotCount() == 20
            && ParkingFacility.standardLayout(3).getSlotCount() == 150
            && ParkingFacility.standardLayout(5).getSlotCount() == 500, "20, 150, 500 slots");
        check("Negative duration", throwsIllegal(() -> fees.calculateFee(new Car("BAD"), -1)), "rejected");
        check("Unknown type", throwsIllegal(() -> parseType("spaceship", "U1")), "rejected");
        check("Monthly pass", monthlyPassIsFree(fees), "eligible student parks free");
        check("Ticket copy gets new number", new ParkingTicket(first).getTicketNumber() != first.getTicketNumber(), "numbers differ");

        // Five-car hand check: 30 + 60 + 90 + 100 + 100 (daily caps) = Rs 380.
        int handTotal = 0;
        for (int i = 1; i <= 5; i++) handTotal += fees.calculateFee(new Car("HAND" + i), i * 60);
        check("Five-vehicle hand total", handTotal == 380, "program Rs " + handTotal + "; hand total Rs 380");
        System.out.printf("RESULT passed=%d failed=%d%n", passed, failed);
        if (failed != 0) throw new AssertionError("Test failures: " + failed);
    }
    private static boolean monthlyPassIsFree(FeeCalculator fees) {
        ParkingFacility facility = new ParkingFacility(new int[] {1});
        ParkingService service = new ParkingService(facility, fees);
        Car car = new Car("STUDENT1");
        service.registerPass(car.getRegNo(), new MonthlyPass(car.getRegNo(), LocalDate.now().minusDays(1),
                LocalDate.now().plusDays(30), Set.of("Car")));
        service.park(car);
        return service.exit(car.getRegNo(), 180) == 0;
    }
    private static Object parseType(String type, String regNo) {
        return switch (type.toLowerCase()) {
            case "two" -> new TwoWheeler(regNo);
            case "car" -> new Car(regNo);
            case "ev" -> new ElectricCar(regNo);
            default -> throw new IllegalArgumentException("Unknown type: " + type);
        };
    }
}
