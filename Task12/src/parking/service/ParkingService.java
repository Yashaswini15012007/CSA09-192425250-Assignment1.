package parking.service;

import java.util.HashMap;
import java.util.Map;
import parking.model.ParkingFacility;
import parking.model.Pass;
import parking.model.ParkingTicket;
import parking.model.Vehicle;

public final class ParkingService {
    private final ParkingFacility facility;
    private final FeeCalculator feeCalculator;
    private final Map<String, ParkingTicket> activeTickets = new HashMap<>();
    private final Map<String, Pass> passes = new HashMap<>();

    public ParkingService(ParkingFacility facility, FeeCalculator feeCalculator) {
        if (facility == null || feeCalculator == null) throw new IllegalArgumentException("Facility and calculator are required");
        this.facility = facility;
        this.feeCalculator = feeCalculator;
    }
    public synchronized void registerPass(String regNo, Pass pass) {
        if (regNo == null || regNo.isBlank() || pass == null) throw new IllegalArgumentException("Valid registration and pass are required");
        passes.put(regNo, pass);
    }
    public synchronized ParkingTicket park(Vehicle vehicle) {
        if (vehicle == null) throw new IllegalArgumentException("Vehicle is required");
        String regNo = vehicle.getRegNo();
        if (activeTickets.containsKey(regNo)) throw new IllegalArgumentException("Duplicate active registration: " + regNo);
        int[] location = facility.allocate(regNo);
        if (location == null) return null;
        ParkingTicket ticket = new ParkingTicket(vehicle, location[0], location[1]);
        activeTickets.put(regNo, ticket);
        return ticket;
    }
    public synchronized int exit(String regNo, int minutes) {
        if (minutes < 0) throw new IllegalArgumentException("Duration cannot be negative");
        ParkingTicket ticket = activeTickets.get(regNo);
        if (ticket == null) throw new IllegalArgumentException("No active ticket for " + regNo);
        int fee = passes.containsKey(regNo) && passes.get(regNo).isValidFor(ticket)
            ? 0 : feeCalculator.calculateFee(ticket.getVehicle(), minutes, ticket.getEntryTime().toLocalTime());
        if (!facility.release(ticket.getFloor(), ticket.getSlot())) throw new IllegalStateException("Ticket slot is not occupied");
        activeTickets.remove(regNo);
        return fee;
    }
    public synchronized Map<String, ParkingTicket> getActiveTickets() { return Map.copyOf(activeTickets); }
    public ParkingFacility getFacility() { return facility; }
}
