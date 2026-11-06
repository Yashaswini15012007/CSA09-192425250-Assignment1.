package parking.app;

import java.util.Scanner;
import parking.model.Car;
import parking.model.DeliveryVan;
import parking.model.EBike;
import parking.model.ElectricCar;
import parking.model.ParkingFacility;
import parking.model.ParkingTicket;
import parking.model.TwoWheeler;
import parking.model.Vehicle;
import parking.service.FeeCalculator;
import parking.service.ParkingService;

public final class ParkingApp {
    private ParkingApp() {}
    public static void main(String[] args) {
        ParkingService service = new ParkingService(new ParkingFacility(new int[] {30, 40, 50, 20, 60}), new FeeCalculator());
        try (Scanner scanner = new Scanner(System.in)) {
            boolean running = true;
            while (running) {
                System.out.println("1 Park  2 Exit  3 Status  0 Quit");
                String choice = scanner.nextLine().trim();
                try {
                    switch (choice) {
                        case "1" -> {
                            System.out.print("Registration and type (two/car/ev/ebike/van): ");
                            String[] input = scanner.nextLine().trim().split("\\s+");
                            if (input.length != 2) throw new IllegalArgumentException("Enter registration and type");
                            Vehicle vehicle = switch (input[1].toLowerCase()) {
                                case "two" -> new TwoWheeler(input[0]);
                                case "car" -> new Car(input[0]);
                                case "ev" -> new ElectricCar(input[0]);
                                case "ebike" -> new EBike(input[0]);
                                case "van" -> new DeliveryVan(input[0]);
                                default -> throw new IllegalArgumentException("Unknown type: " + input[1]);
                            };
                            ParkingTicket ticket = service.park(vehicle);
                            System.out.println(ticket == null ? "Facility full" : "Parked: " + ticket);
                        }
                        case "2" -> {
                            System.out.print("Registration and duration in minutes: ");
                            String[] input = scanner.nextLine().trim().split("\\s+");
                            if (input.length != 2) throw new IllegalArgumentException("Enter registration and minutes");
                            System.out.println("Fee: Rs " + service.exit(input[0], Integer.parseInt(input[1])));
                        }
                        case "3" -> System.out.println("Occupied " + service.getFacility().getOccupiedCount()
                                + "/" + service.getFacility().getSlotCount() + "; active=" + service.getActiveTickets().keySet());
                        case "0" -> running = false;
                        default -> System.out.println("Unknown menu option");
                    }
                } catch (IllegalArgumentException ex) {
                    System.out.println("Error: " + ex.getMessage());
                }
            }
        }
    }
}
