# Task 03: Jagged Parking Facility

`ParkingFacility` models each floor as a row in a jagged `String[][]`. Allocation scans floors then slots and returns the first free coordinate; release checks bounds and occupancy. Allocation takes O(S) worst-case time for S total slots; release takes O(1); storage is O(S). Compile and run with `javac ParkingFacility.java` and `java ParkingFacility`.
