import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LookupBenchmark {
    private static final int SIZE = 50_000;
    private static final int ROUNDS = 2_000;

    public static void main(String[] args) {
        List<String> registrations = new ArrayList<>(SIZE);
        Map<String, Integer> tickets = new HashMap<>(SIZE * 2);
        for (int i = 0; i < SIZE; i++) {
            String registration = "CAMPUS" + i;
            registrations.add(registration);
            tickets.put(registration, i);
        }
        String target = "CAMPUS" + (SIZE - 1);

        long listChecksum = 0;
        long start = System.nanoTime();
        for (int round = 0; round < ROUNDS; round++) {
            listChecksum += registrations.indexOf(target);
        }
        long listNanos = System.nanoTime() - start;

        long mapChecksum = 0;
        start = System.nanoTime();
        for (int round = 0; round < ROUNDS; round++) {
            mapChecksum += tickets.get(target);
        }
        long mapNanos = System.nanoTime() - start;

        System.out.println("Entries: " + SIZE + ", lookups: " + ROUNDS);
        System.out.println("ArrayList checksum=" + listChecksum + ", ns=" + listNanos);
        System.out.println("HashMap checksum=" + mapChecksum + ", ns=" + mapNanos);
    }
}