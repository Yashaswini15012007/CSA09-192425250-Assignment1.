package parking.model;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

public final class ParkingTicket {
    private static final AtomicLong NEXT_NUMBER = new AtomicLong(1);
    private final long ticketNumber;
    private final Vehicle vehicle;
    private final int floor;
    private final int slot;
    private final LocalDateTime entryTime;

    public ParkingTicket(Vehicle vehicle, int floor, int slot) {
        this(vehicle, floor, slot, LocalDateTime.now());
    }
    public ParkingTicket(Vehicle vehicle, int floor, int slot, LocalDateTime entryTime) {
        this(NEXT_NUMBER.getAndIncrement(), vehicle, floor, slot, entryTime);
    }
    private ParkingTicket(long ticketNumber, Vehicle vehicle, int floor, int slot, LocalDateTime entryTime) {
        if (vehicle == null || floor < 0 || slot < 0 || entryTime == null)
            throw new IllegalArgumentException("Invalid ticket data");
        this.ticketNumber = ticketNumber;
        this.vehicle = vehicle;
        this.floor = floor;
        this.slot = slot;
        this.entryTime = entryTime;
    }
    public ParkingTicket(ParkingTicket original) {
        this(NEXT_NUMBER.getAndIncrement(), Objects.requireNonNull(original).vehicle,
                original.floor, original.slot, original.entryTime);
    }
    public long getTicketNumber() { return ticketNumber; }
    public Vehicle getVehicle() { return vehicle; }
    public int getFloor() { return floor; }
    public int getSlot() { return slot; }
    public LocalDateTime getEntryTime() { return entryTime; }
    @Override public boolean equals(Object other) {
        return other instanceof ParkingTicket ticket && ticketNumber == ticket.ticketNumber;
    }
    @Override public int hashCode() { return Long.hashCode(ticketNumber); }
    @Override public String toString() {
        return "Ticket#" + ticketNumber + " " + vehicle + " at " + floor + ":" + slot + " entered " + entryTime;
    }
}
