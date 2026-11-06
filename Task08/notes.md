# Task 08: Polymorphic Fee Calculation

`Vehicle` declares the hourly-rate operation, subclasses override it, and `instanceof Chargeable chargeable` adds the EV energy charge. Fees round any partial hour upward. Equality is based on registration, so a `Car` matches an `ElectricCar` with the same registration. Compile and run with `javac FeeDemo.java` and `java FeeDemo`.
