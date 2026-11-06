public class RepresentationBenchmark {
    private static final int SIZE = 100_000;
    private static final int ROUNDS = 20;

    private static final class VehicleObject {
        final int rate;
        VehicleObject(int rate) { this.rate = rate; }
    }

    public static void main(String[] args) {
        int[] rates = new int[SIZE];
        VehicleObject[] vehicles = new VehicleObject[SIZE];
        for (int i = 0; i < SIZE; i++) {
            rates[i] = i % 31;
            vehicles[i] = new VehicleObject(i % 31);
        }

        long arraySum = 0;
        long start = System.nanoTime();
        for (int round = 0; round < ROUNDS; round++) {
            for (int rate : rates) arraySum += rate;
        }
        long arrayNanos = System.nanoTime() - start;

        long objectSum = 0;
        start = System.nanoTime();
        for (int round = 0; round < ROUNDS; round++) {
            for (VehicleObject vehicle : vehicles) objectSum += vehicle.rate;
        }
        long objectNanos = System.nanoTime() - start;

        System.out.println("Records: " + SIZE + ", rounds: " + ROUNDS);
        System.out.println("Parallel arrays checksum=" + arraySum + ", ns=" + arrayNanos);
        System.out.println("Objects checksum=" + objectSum + ", ns=" + objectNanos);
    }
}