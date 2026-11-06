import java.util.Objects;

final class VehicleRecord {
    private final String regNo;
    private final String type;

    VehicleRecord(String regNo, String type) {
        this.regNo = Objects.requireNonNull(regNo);
        this.type = Objects.requireNonNull(type);
    }

    @Override
    public String toString() {
        return "VehicleRecord{regNo='" + regNo + "', type='" + type + "'}";
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof VehicleRecord record
                && regNo.equals(record.regNo) && type.equals(record.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(regNo, type);
    }
}

public class ObjectMethodsDemo {
    public static void main(String[] args) {
        VehicleRecord first = new VehicleRecord("TN01AB1111", "Car");
        VehicleRecord second = new VehicleRecord("TN01AB1111", "Car");
        System.out.println(first);
        System.out.println("Equal records: " + first.equals(second));
        System.out.println("Equal hash codes: " + (first.hashCode() == second.hashCode()));
    }
}