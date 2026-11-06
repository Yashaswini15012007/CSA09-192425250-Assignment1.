package parking.model;

public interface Pass {
    boolean isValidFor(ParkingTicket ticket);
}
