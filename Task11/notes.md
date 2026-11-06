# Task 11: Complete Parking System

The package tree separates `parking.model`, `parking.service`, and `parking.app`. Tickets use an atomic static counter, constructor chaining, and a copy constructor that issues a fresh ID. `ParkingService` tracks active vehicles by registration in a `HashMap`; monthly passes are checked against owner, eligible vehicle type, and ticket entry date. Standard facility layouts are 1x20, 3x50, and 5x100. Fee calculation uses integer ceiling hours, the vehicle's hourly rate, a per-day maximum, and EV kWh charges. `TestRunner` reports 17 passing checks, including a hand-checked five-car total of Rs 380.

Allocation is O(S) worst case over S slots; release is O(1). Active-ticket lookup/insert/remove is expected O(1), with O(V) map storage for V active vehicles. Test run: `java -cp out parking.app.TestRunner`. Interactive menu: `java -cp out parking.app.ParkingApp`. Compile commands are in the root README.
