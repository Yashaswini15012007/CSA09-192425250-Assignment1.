# Task 12: Extended Parking System

Task12 copies the Task11 package baseline, then adds `EBike`, `DeliveryVan`, `PricingRule` implementations, the `Pass` abstraction, and `OneDayPass`. EBikes use a flat Rs 25 fee; delivery vans bill at twice the car rate for billed hours beginning from 09:00 through 16:59. The console facility uses five floors with 30, 40, 50, 20, and 60 slots (200 total), while the standard 1/3/5-floor layouts remain available.

Seven existing classes were modified for the extension: `ParkingFacility`, `MonthlyPass`, `ParkingTicket`, `FeeCalculator`, `ParkingService`, `ParkingApp`, and `TestRunner`. Eight types were added: `EBike`, `DeliveryVan`, `Pass`, `OneDayPass`, `PricingRule`, `StandardPricingRule`, `DeliveryVanPricingRule`, and `EBikePricingRule`. Existing vehicle types remain unchanged. The test runner reports 22 passing checks.

Pricing is O(1) for standard vehicles and EBikes. Delivery-van time-window pricing checks each billed hour, O(H) for H billed hours. Facility allocation is O(S) worst case; active-ticket lookup is expected O(1). Run `java -cp out parking.app.TestRunner` or launch `java -cp out parking.app.ParkingApp`.
