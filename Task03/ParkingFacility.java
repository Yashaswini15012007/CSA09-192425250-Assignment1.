import java.util.Arrays;

public class ParkingFacility {
    private final String[][] slots;

    public ParkingFacility(int[] slotsPerFloor) {
        if (slotsPerFloor == null) {
            throw new IllegalArgumentException("slotsPerFloor cannot be null");
        }
        slots = new String[slotsPerFloor.length][];
        for (int floor = 0; floor < slotsPerFloor.length; floor++) {
            if (slotsPerFloor[floor] < 0) {
                throw new IllegalArgumentException("Slot count cannot be negative");
            }
            slots[floor] = new String[slotsPerFloor[floor]];
        }
    }

    public int[] allocate(String vehicleNo) {
        if (vehicleNo == null || vehicleNo.isBlank()) {
            throw new IllegalArgumentException("vehicleNo cannot be blank");
        }
        for (int floor = 0; floor < slots.length; floor++) {
            for (int slot = 0; slot < slots[floor].length; slot++) {
                if (slots[floor][slot] == null) {
                    slots[floor][slot] = vehicleNo;
                    return new int[] {floor, slot};
                }
            }
        }
        return null;
    }

    public boolean release(int floor, int slot) {
        if (floor < 0 || floor >= slots.length || slot < 0 || slot >= slots[floor].length
                || slots[floor][slot] == null) {
            return false;
        }
        slots[floor][slot] = null;
        return true;
    }

    public static void main(String[] args) {
        ParkingFacility facility = new ParkingFacility(new int[] {2, 1});
        System.out.println(Arrays.toString(facility.allocate("TN01AB1111")));
        System.out.println(Arrays.toString(facility.allocate("TN02CD2222")));
        System.out.println(Arrays.toString(facility.allocate("TN03EF3333")));
        System.out.println(Arrays.toString(facility.allocate("TN04GH4444")));
        System.out.println(facility.release(0, 1) + " " + facility.release(0, 1) + " " + facility.release(5, 0));
        System.out.println(Arrays.toString(facility.allocate("TN04GH4444")));
    }
}