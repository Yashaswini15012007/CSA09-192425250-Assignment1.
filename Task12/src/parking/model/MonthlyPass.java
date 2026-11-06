package parking.model;

import java.time.LocalDate;
import java.util.Set;

public final class MonthlyPass implements Pass {
    private final String ownerRegNo;
    private final LocalDate validFrom;
    private final LocalDate validUntil;
    private final Set<String> eligibleTypes;

    public MonthlyPass(String ownerRegNo, LocalDate validFrom, LocalDate validUntil, Set<String> eligibleTypes) {
        if (ownerRegNo == null || ownerRegNo.isBlank() || validFrom == null || validUntil == null
                || validUntil.isBefore(validFrom) || eligibleTypes == null) throw new IllegalArgumentException("Invalid pass");
        this.ownerRegNo = ownerRegNo;
        this.validFrom = validFrom;
        this.validUntil = validUntil;
        this.eligibleTypes = Set.copyOf(eligibleTypes);
    }
    @Override public boolean isValidFor(ParkingTicket ticket) {
        LocalDate entry = ticket.getEntryTime().toLocalDate();
        return ownerRegNo.equals(ticket.getVehicle().getRegNo()) && eligibleTypes.contains(ticket.getVehicle().getType())
                && !entry.isBefore(validFrom) && !entry.isAfter(validUntil);
    }
}
