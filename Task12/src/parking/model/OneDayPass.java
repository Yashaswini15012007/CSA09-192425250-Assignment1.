package parking.model;

import java.time.LocalDate;

public final class OneDayPass implements Pass {
    private final String ownerRegNo;
    private final LocalDate validDate;

    public OneDayPass(String ownerRegNo, LocalDate validDate) {
        if (ownerRegNo == null || ownerRegNo.isBlank() || validDate == null) throw new IllegalArgumentException("Invalid day pass");
        this.ownerRegNo = ownerRegNo;
        this.validDate = validDate;
    }
    @Override public boolean isValidFor(ParkingTicket ticket) {
        return ownerRegNo.equals(ticket.getVehicle().getRegNo()) && validDate.equals(ticket.getEntryTime().toLocalDate());
    }
}
