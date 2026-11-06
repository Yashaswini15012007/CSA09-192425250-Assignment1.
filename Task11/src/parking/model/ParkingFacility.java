package parking.model;

import java.util.Arrays;

public final class ParkingFacility {
    private final String[][] slots;

    public static ParkingFacility standardLayout(int floorCount) {
        return switch (floorCount) {
            case 1 -> new ParkingFacility(new int[] {20});
            case 3 -> new ParkingFacility(new int[] {50, 50, 50});
            case 5 -> new ParkingFacility(new int[] {100, 100, 100, 100, 100});
            default -> throw new IllegalArgumentException("Standard layouts support 1, 3, or 5 floors");
        };
    }

    public ParkingFacility(int[] slotsPerFloor) {
        if (slotsPerFloor == null || slotsPerFloor.length == 0) throw new IllegalArgumentException("At least one floor is required");
        slots = new String[slotsPerFloor.length][];
        for (int floor = 0; floor < slotsPerFloor.length; floor++) {
            if (slotsPerFloor[floor] < 0) throw new IllegalArgumentException("Slot counts cannot be negative");
            slots[floor] = new String[slotsPerFloor[floor]];
        }
    }
    public synchronized int[] allocate(String regNo) {
        if (regNo == null || regNo.isBlank()) throw new IllegalArgumentException("Registration is required");
        for (int floor = 0; floor < slots.length; floor++) {
            for (int slot = 0; slot < slots[floor].length; slot++) {
                if (slots[floor][slot] == null) {
                    slots[floor][slot] = regNo;
                    return new int[] {floor, slot};
                }
            }
        }
        return null;
    }
    public synchronized boolean release(int floor, int slot) {
        if (!isValidSlot(floor, slot) || slots[floor][slot] == null) return false;
        slots[floor][slot] = null;
        return true;
    }
    public boolean isValidSlot(int floor, int slot) {
        return floor >= 0 && floor < slots.length && slot >= 0 && slot < slots[floor].length;
    }
    public int getFloorCount() { return slots.length; }
    public int getSlotCount() { return Arrays.stream(slots).mapToInt(row -> row.length).sum(); }
    public synchronized int getOccupiedCount() {
        int count = 0;
        for (String[] floor : slots) for (String regNo : floor) if (regNo != null) count++;
        return count;
    }
    public synchronized String[][] snapshot() {
        String[][] copy = new String[slots.length][];
        for (int floor = 0; floor < slots.length; floor++) copy[floor] = slots[floor].clone();
        return copy;
    }
    @Override public String toString() { return "ParkingFacility[floors=" + getFloorCount() + ", slots=" + getSlotCount() + "]"; }
}
