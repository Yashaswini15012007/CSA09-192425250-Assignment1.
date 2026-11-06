final class TicketCounter {
    private static int nextNumber = 1;
    private final int ticketNumber;

    TicketCounter() {
        ticketNumber = nextNumber++;
    }

    int getTicketNumber() {
        return ticketNumber;
    }

    static int getNextNumber() {
        return nextNumber;
    }
}

public class StaticCounterDemo {
    public static void main(String[] args) {
        TicketCounter first = new TicketCounter();
        TicketCounter second = new TicketCounter();
        System.out.println("Tickets: " + first.getTicketNumber() + ", " + second.getTicketNumber());
        System.out.println("Next number: " + TicketCounter.getNextNumber());
    }
}