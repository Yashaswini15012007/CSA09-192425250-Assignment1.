import java.util.ArrayDeque;
import java.util.Deque;

public class FreeSlotStackDemo {
    public static void main(String[] args) {
        Deque<Integer> freeSlots = new ArrayDeque<>();
        for (int slot = 4; slot >= 0; slot--) freeSlots.push(slot);

        System.out.println("Allocated slots: " + freeSlots.pop() + ", " + freeSlots.pop());
        freeSlots.push(1);
        System.out.println("Released slot 1; next allocation: " + freeSlots.pop());
        System.out.println("Remaining free slots: " + freeSlots.size());
    }
}