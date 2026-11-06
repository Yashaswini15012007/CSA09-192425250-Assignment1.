package parking.model;

public interface ParkingPass {
    boolean isValidFor(ParkingTicket ticket);
}
